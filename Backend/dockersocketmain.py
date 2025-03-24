from fastapi import FastAPI
from fastapi.websockets import WebSocket
from fastapi import WebSocketDisconnect

import websockets
import time
import uuid

from ScenarioTrack import ScenarioTrack
from DockerManager import DockerManager 

app = FastAPI()

scm = ScenarioTrack()
dm = DockerManager()





@app.websocket("/ws")
async def websocket(mainsocket: WebSocket):
    await mainsocket.accept()
    

    session_id = str(uuid.uuid4())
    frontend_user_name = await mainsocket.receive_text()    
    
    print(frontend_user_name)
    
    frontend_container_choice = await mainsocket.receive_text()

    print(frontend_container_choice)
    scm = ScenarioTrack()


    container_name = dm.addConnection(
        userSessionId=session_id,
        userName=frontend_user_name,
        frontendChoice=frontend_container_choice
    )



    # TODO: Graceful Closure

    if container_name:

        # TODO: Nach 1h Inaktivität automatisch schließen 
        while dm.get_container_health(container_name) != "healthy":
            print(dm.get_container_health(container_name))
            time.sleep(2)
            
        await mainsocket.send_text("Container Startup successful")
        container_socket_port = dm.get_dynamic_port(container_name)
        print("Das isses: ", container_socket_port)


        # Connection mit dem docker socket mit dem modul websockets
        container_socket_url = f"ws://127.0.0.1:{container_socket_port}/dockersocket"
        try:
            async with websockets.connect(container_socket_url) as container_socket:
                print("connected to external")
                while True:

                    # Interaktion mit Frontend Socket
                    frontend_cmd = await mainsocket.receive_text()

                    try:
                        if ">clue" == frontend_cmd:
                            # TODO: SCM Korrekt mit User Connection identifizieren 
                            # TODO: Update Progress wird nur bei einem Check ausgeführt
                            scm.update_progress()
                            clues = "".join(scm.get_clue())
                            await mainsocket.send_text(clues)

                        if ">check" == frontend_cmd:
                            await container_socket.send("bash /app/checks_fun.sh")
                            data = await container_socket.recv()
                            await mainsocket.send_text(data)
                            print(data)

                        else:
                            await container_socket.send(frontend_cmd)
                            data = await container_socket.recv()
                            await mainsocket.send_text(data)
                            print(data)

                
                    except WebSocketDisconnect:
                        print("WebSocket client disconnected")
                        break

        except Exception as e:
            print(f"Error with external WebSocket: {e}")

        finally:
            await mainsocket.close()
            # if container:
            #     container.stop()
            #     container.remove()
            #     print("WebSocket stopped and container removed")
        
            dm.close(container_name)
            print("WebSocket stopped and container removed")

    else:
        print("Help")

    


@app.get("/")
async def root():
    print("Auf root gegangen")
    return {"message": "Hello World"}