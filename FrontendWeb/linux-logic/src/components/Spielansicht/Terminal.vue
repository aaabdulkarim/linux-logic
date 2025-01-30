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
    this.writePrompt();

    this.terminal.onData(this.handleInput);
  },
  methods: {
    writePrompt() {
      this.terminal.write("\r\nlogic@linux:~$ ");
    },
    handleInput(data) {
      const char = data.charCodeAt(0);

      if (char === 13) { // Enter key
        const input = this.terminal.buffer.active.getLine(this.terminal.buffer.active.cursorY)?.translateToString().slice(this.promptLength);
        this.respondToInput(input.trim());
        this.writePrompt();
      } else if (char === 127) { // Backspace
        if (this.terminal.buffer.active.cursorX > this.promptLength) {
          this.terminal.write('\b \b');
        }
      } else {
        this.terminal.write(data);
      }
    },
    respondToInput(input) {
      let response;
      switch (input.toLowerCase()) {
        case 'help':
          response = "Available commands: help, hello, clear";
          break;
        case 'hello':
          response = "Hello, welcome to logic Terminal!";
          break;
        case 'clear':
          this.terminal.clear();
          this.writePrompt();
          return;
        default:
          response = `Command not found: ${input}`;
      }
      this.terminal.write(`\r\n${response}`);
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
