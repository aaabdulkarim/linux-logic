import asyncio
import websockets

async def check_websocket():
    url = "ws://0.0.0.0:1000/dockersocket"
    try:
        async with websockets.connect(url) as ws:
            print("alive")
            exit(0)

    except Exception:
        print("WebSocket is down")
        exit(1)

asyncio.run(check_websocket())
