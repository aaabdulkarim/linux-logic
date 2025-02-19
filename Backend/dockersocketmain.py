from fastapi import FastAPI
from fastapi.websockets import WebSocket
import docker
import websockets
import asyncio


app = FastAPI()


def run_docker_commands(docker_dir_path):
    client = docker.from_env()
    try:
        client.images.build(path=docker_dir_path, tag="newone")
        
        container = client.containers.run("newone", ports={1000: 1000}, detach=True)
        return container
    except docker.errors.DockerException as e:
        print(f"Error: {e}")



@app.websocket("/ws")
async def websocket(mainsocket: WebSocket):
    await mainsocket.accept()
    client = docker.from_env()
    tagname = "testtag"

    # Erstellt ein Image aus einem Dockerfile mit einem tag(Namen)
    # client.images.build(path="./test/dockerfolder", tag=tagname)
    # container = client.containers.run(
    #     tagname,
    #     detach=True,
    #     tty=True,
    #     ports={"1000/tcp": 1000}, 
    #     name="theone"
    # )

    
    container = run_docker_commands("test/dockerfolder/.")

    # Connection mit dem docker socket mit dem Framework Socket
    container_socket_url = "ws://127.0.0.1:1000/dockersocket"
    try:
        async with websockets.connect(container_socket_url) as container_socket:
            print("connected to external")
            while True:

                # Interaktion mit Frontend Socket
                frontend_cmd = await mainsocket.receive_text()

                try:
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
        if container:
            container.stop()
            container.remove()
            print("WebSocket stopped and container removed")
    
    client.close()


@app.get("/")
async def root():
    print("Auf root gegangen")
    return {"message": "Hello World"}