from fastapi import FastAPI, WebSocket
import asyncio

class BashRunner:
    def __init__(self):
        """
        process attribut für bestehendes Bash
        """
        self.process = None

    async def start(self):
        """
        Bash Process mit asyncio.
        """
        self.process = await asyncio.create_subprocess_exec(
            "/bin/bash",
            stdin=asyncio.subprocess.PIPE,
            stdout=asyncio.subprocess.PIPE,
            stderr=asyncio.subprocess.PIPE,
            bufsize=0
        )

    async def run_command(self, command: str) -> str:
        """
        Run a command on the bash subprocess and return its output.
        """
        if self.process and self.process.stdin and self.process.stdout:
            try:
                # Befehl wird in die "inputschleife geschickt"
                self.process.stdin.write((command + "\n").encode("utf-8"))
                await self.process.stdin.drain()

                # Line by Line gelesen, damit der Befehl vollständig ausgefürht wird
                output = []
                loop_c = 0
                while True:
                    print(str(loop_c + 1) + " noch nd gelesen")
                    try:
                        line = await asyncio.wait_for(self.process.stdout.readline(), timeout=0.5)
                        print("JO, gelesen")

                        if not line:
                            print("ich bin gebrochen")
                            break  

                        print("noch nd appended")
                        output.append(line.decode("utf-8"))  
                        # await asyncio.sleep(0.01)

                        print("Der line: ", line)
                        print("Der output: ", output)
                    
                    except asyncio.TimeoutError:
                        break

                print("es wird geschickt")
                return "".join(output)
           
           
            except Exception as e:
                print("leider nicht geschickt")
                return f"Error bei command {command}: {e}"


    async def close(self):
        """
        """
        if self.process:
            self.process.terminate()
            await self.process.wait()


app = FastAPI()


@app.websocket("/dockersocket")
async def websocket_endpoint(websocket: WebSocket):
    """
    WebSocket endpoint to execute bash commands interactively.
    """
    await websocket.accept()
    bash_runner = BashRunner()
    await bash_runner.start()  

    try:
        while True:
            data = await websocket.receive_text()

            
            output = await bash_runner.run_command(data)
            print(output)
            await websocket.send_text(output)

    except Exception as e:
        await websocket.send_text(f"Error: {str(e)}")
        print(f"WebSocket error: {e}")

    finally:
        print("Connection closed")
        await bash_runner.close()
        await websocket.close()


@app.get("/")
async def root():
    print("Accessed root")
    return {"message": "Hello World"}
