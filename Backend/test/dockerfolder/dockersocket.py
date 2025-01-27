from fastapi import FastAPI
from fastapi.websockets import WebSocket
import subprocess



class BashRunner:

    def __init__(self, whole_string : str) -> None:
        self.command = "command"
        self.arguments = "arguments"
        self.values = "values"
        self.whole_string = whole_string

    def run_command(self):
        if self.check():
            try:
                p = subprocess.run(self.whole_string, capture_output=True, shell=True, text=True)
                if p.returncode == 0:
                    return p.stdout  
                else:
                    return p.stderr  
            
            except FileNotFoundError as e:
                return f"Error: {str(e)}"

            except Exception as e:
                return f"Unexpected Error: {str(e)}"

    def check(self):
        return True


    # Utility funktionen eventuell



app = FastAPI()


@app.websocket("/dockersocket")
async def websocket(websocket: WebSocket):
    """
    Diese WebSocket sollte man nur in einer Linux Umgebung, welche in einem Docker Container ist ausführen können.
    """
    await websocket.accept()
    try:
        while True:
            data = await websocket.receive_text()
            bashCommand = BashRunner(data)
            output = bashCommand.run_command()

            await websocket.send_text(output)
    
    except Exception as e:
        print(f"WebSocket error: {e}")

    finally:
        print("Connection closed")
        await websocket.close()

@app.get("/")
async def root():
    print("Auf root gegangen")
    return {"message": "Hello World"}