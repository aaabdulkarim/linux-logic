from fastapi import FastAPI
from fastapi.websockets import WebSocket
from fastapi import WebSocketDisconnect
import docker
import websockets
import time

app = FastAPI()


def run_docker_commands(docker_dir_path):
    client = docker.from_env()
    try:
        client.images.build(path=docker_dir_path, tag="newone")
        
        container = client.containers.run("newone", name="theone", ports={1000: 1000}, detach=True)
        return container


    except docker.errors.DockerException as e:
        print(f"Error: {e}")


# https://stackoverflow.com/questions/60291082/wait-for-docker-container-healthcheck-to-succeed-before-detaching
def get_container_health(container):
    api_client = docker.APIClient()
    inspect_results = api_client.inspect_container(container.name)
    return inspect_results['State']['Health']['Status']


def get_scenario_data(docker_dir_path):
    # Liste von Tuples
    scenario_list = []
    md_file = docker_dir_path + "/Aufgabenstellung.md"
    print(md_file)
    with open(md_file) as file:
        lines = file.readlines()

        subtask_index = 0
        current_hint = ""
        for l in lines:

            if l[0:2] == "\_" :
                current_hint += l[1:]
 
            elif l[0:2] != "\n":
                scenario_list.append((l, current_hint))
                current_hint = ""

    return scenario_list


@app.websocket("/ws")
async def websocket(mainsocket: WebSocket):
    await mainsocket.accept()
    client = docker.from_env()
    tagname = "testtag"
    
    frontend_container_choice = await mainsocket.receive_text()
    docker_path = f"scenarios/{frontend_container_choice}"

    container = run_docker_commands(docker_path)
    scenario_data = get_scenario_data(docker_path)
    for da in scenario_data:
        print(da)

    if container:

        while get_container_health(container) != "healthy":
            print(get_container_health(container))
            time.sleep(1)
            
        await mainsocket.send_text("Container Startup successful")
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

    else:
        print("Help")

    


@app.get("/")
async def root():
    print("Auf root gegangen")
    return {"message": "Hello World"}