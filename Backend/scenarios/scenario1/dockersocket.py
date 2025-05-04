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
        if self.process and self.process.stdin and self.process.stdout and self.process.stderr:
            try:
                marker = "__CMD_DONE__"

                # Befehl wird in die "inputschleife geschickt"
                full_command = f"{command}\necho {marker}\n"
                self.process.stdin.write(full_command.encode("utf-8"))
                await self.process.stdin.drain()

                output = []
                while True:
                    line = await asyncio.wait_for(self.process.stdout.readline(), timeout=2)
                    decoded = line.decode("utf-8").strip()
                    if decoded == marker:
                        break
                    output.append(decoded)

                # Drain stderr *after* command finishes
                error_output = []
                while True:
                    try:
                        err_line = await asyncio.wait_for(self.process.stderr.readline(), timeout=0.1)
                        if not err_line:
                            break
                        error_output.append(err_line.decode("utf-8").strip())
                    except asyncio.TimeoutError:
                        break

                return "\n".join(output + error_output)

            except Exception as e:
                return f"Fehler bei Befehl '{command}'"
        
        return "Subprozess nicht korrekt initialisiert."



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

        
            current_directory = await bash_runner.run_command("pwd")
            output = await bash_runner.run_command(data)
            print(output)
            await websocket.send_json({
                "output": output,
                "cd" : current_directory
                })

    except Exception as e:
        await websocket.send_text(f"Error: {str(e)}")
        print(f"WebSocket error: {e}")

    # finally:
    #     print("Connection closed")
    #     await bash_runner.close()
    #     await websocket.close()


@app.get("/")
async def root():
    print("Accessed root")
    return {"message": "Hello World"}
