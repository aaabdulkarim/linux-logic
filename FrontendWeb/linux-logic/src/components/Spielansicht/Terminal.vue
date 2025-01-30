<template>
  <div id="terminal-container" ref="terminalContainer">
    <div class="terminal-header">
      <span>logic Terminal</span>
    </div>
    <div class="terminal-output">
      <!-- Terminal output is rendered here -->
    </div>
  </div>
</template>

<script>
import { Terminal } from "xterm";
import "xterm/css/xterm.css";
import { FitAddon } from "xterm-addon-fit";

export default {
  name: 'Terminal',
  data() {
    return {
      terminal: null,
      fitAddon: null,
      socketClient: null,
      promptLength: 13 // Länge des Prompts "logic@linux:~$ "
    };
  },
  mounted() {
    this.terminal = new Terminal({
      cursorBlink: true,
      rows: 30,
      theme: {
        background: '#1e1e1e',
        foreground: '#dcdcdc',
        cursor: '#dcdcdc'
      },
      screenReaderMode: true,
      allowProposedApi: true
    });

    this.fitAddon = new FitAddon();
    this.terminal.loadAddon(this.fitAddon);
    this.terminal.open(this.$refs.terminalContainer);
    this.fitAddon.fit();

    // WebSocket Initialization (Move this above onmessage assignment)
    let url = "ws://192.168.0.76:8000/ws";
    this.socketClient = new WebSocket(url);

    // WebSocket event handlers must be assigned AFTER initialization
    this.socketClient.onopen = () => {
      console.log("WebSocket connection established.");


    };

    this.socketClient.onmessage = (event) => {
      this.terminal.write(`\r\n${event.data}`);
      this.writePrompt();
    };

    this.socketClient.onerror = (error) => {
      console.error("WebSocket Error:", error);
    };

    this.socketClient.onclose = () => {
      console.warn("WebSocket connection closed.");

      setTimeout(() => this.initWebSocket(), 2000); // Reconnect after 2 seconds

    };

    this.terminal.onData(this.handleInput);
  },
  methods: {
    initWebSocket() {
    let url = "ws://192.168.0.76:8000/ws";
    this.socketClient = new WebSocket(url);

    this.socketClient.onopen = () => {
      console.log("WebSocket connection established.");
      
    };

    this.socketClient.onmessage = (event) => {
      this.terminal.write(`\r\n${event.data}`);
      this.writePrompt();
    };

    this.socketClient.onerror = (error) => {
      console.error("WebSocket Error:", error);
    };

    this.socketClient.onclose = () => {
      console.warn("WebSocket connection closed. Reconnecting...");
    };       
  },


    writePrompt() {
      this.terminal.write("\r\nlogic@linux:~$ ");
    },
    handleInput(data) {
      const char = data.charCodeAt(0);

      if (char === 13) { // Enter key
        const line = this.terminal.buffer.active.getLine(this.terminal.buffer.active.cursorY);
        console.log("Line Object:", line);

        if (line) {
            const lineText = line.translateToString();
            console.log("Full Line Text:", lineText);
            console.log("Prompt Length:", this.promptLength);
            console.log("Extracted Input:", lineText.slice(this.promptLength));
            this.respondToInput(lineText.slice(14));

        }
        
      } else if (char === 127) { // Backspace
        if (this.terminal.buffer.active.cursorX > this.promptLength) {
          this.terminal.write('\b \b');
        }
      } else {
        this.terminal.write(data);
      }
    },
    respondToInput(input) {
      
      if (input.toLowerCase() === "clear") {
        this.terminal.clear();
        this.writePrompt();
        return;
      }

      if (this.socketClient.readyState === WebSocket.OPEN) {
        console.log("SENDING")
        setTimeout(() => {
          this.socketClient.send(input);
          console.log("should be send");
        }, 100); // Delay for 100ms
        console.log("should be send")

      } else {
        this.terminal.write("\r\n[Error] WebSocket not connected.");
      }



      
    }
  }
};
</script>

<style scoped>
#terminal-container {
  width: 100%;
  height: 100%;
  background-color: #1e1e1e;
  padding: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}
.terminal-header {
  font-family: 'Courier New', Courier, monospace;
  background-color: #111;
  color: #fff;
  padding: 4px 10px;
  text-align: left;
  font-size: 18px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}
.terminal-output {
  text-align: center;
  color: #dcdcdc;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
}
</style>
