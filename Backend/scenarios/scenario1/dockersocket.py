from fastapi import FastAPI, WebSocket
import asyncio

class BashRunner:
    def __init__(self):
        self.process = None

    async def start(self):
        self.process = await asyncio.create_subprocess_exec(
            "/bin/bash",
            stdin=asyncio.subprocess.PIPE,
            stdout=asyncio.subprocess.PIPE,
            stderr=asyncio.subprocess.PIPE,
            bufsize=0
        )

    async def run_command(self, command: str) -> str:
        if self.process and self.process.stdin and self.process.stdout:
            try:
                self.process.stdin.write((command + "\n").encode("utf-8"))
                await self.process.stdin.drain()
                
                output = []
                while True:
                    try:
                        line = await asyncio.wait_for(self.process.stdout.readline(), timeout=0.5)
                        if not line:
                            break  
                        output.append(line.decode("utf-8"))  
                    except asyncio.TimeoutError:
                        break
                
                return "".join(output)
            except Exception as e:
                return f"Error bei command {command}: {e}"

    async def close(self):
        if self.process:
            self.process.terminate()
            await self.process.wait()

app = FastAPI()

@app.websocket("/dockersocket")
async def websocket_endpoint(websocket: WebSocket):
    await websocket.accept()
    bash_runner = BashRunner()
    await bash_runner.start()
    
    try:
        while True:
            data = await websocket.receive_text()
            
            commands = {
                "roter_teppich": "mkdir -p /home/Veranstaltung && touch /home/Veranstaltung/roter_teppich.txt",
                "einladungen": "mkdir -p /home/Einladungen && ls /home/Einladungen",
                "banner": "mkdir -p /home/Dekoration && cp /home/Dekoration/banner.txt /home/Veranstaltung/",
                "menue": "mkdir -p /home/Kueche && touch /home/Kueche/menue.txt",
                "musik": "mkdir -p /home/Musik && touch /home/Musik/musikliste.txt",
                "beleuchtung": "mkdir -p /home/Beleuchtung && touch /home/Beleuchtung/licht1.txt",
                "reinigung": "mkdir -p /home/Reinigung && touch /home/Reinigung/boden_sauber.txt",
                "uhr": "mkdir -p /home/Uhr && echo \"12:00\" > /home/Uhr/zeit.txt"
            }
            
            command = commands.get(data, "echo 'Unbekannter Befehl'")
            output = await bash_runner.run_command(command)
            await websocket.send_text(output)
    
    except Exception as e:
        await websocket.send_text(f"Error: {str(e)}")
    finally:
        await bash_runner.close()
        await websocket.close()

@app.get("/")
async def root():
    return {"message": "WebSocket für Docker-Interaktion läuft"}