<template>
    <div id="terminal-container" ref="terminalContainer"></div>
  </template>
  
  <script>
  import { Terminal } from "xterm";
  import "xterm/css/xterm.css";
  
  export default {
    name: 'Terminal',
    data() {
      return {
        terminal: null,
        promptLength: 13 // Length of the prompt "logic@linux:~$ "
      };
    },
    mounted() {
      this.terminal = new Terminal({
        cursorBlink: true,
        rows: 20,
        theme: {
          background: '#1e1e1e',
          foreground: '#dcdcdc',
          cursor: '#dcdcdc'
        },
        screenReaderMode: true,
        allowProposedApi: true
      });
  
      this.terminal.open(this.$refs.terminalContainer);
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
    width: 600px;
    height: 400px;
    background-color: #1e1e1e;
    padding: 10px;
    font-family: monospace;
    overflow: hidden;
  }
  .xterm {
    text-align: left !important;
    justify-content: start;
  }
  .xterm .xterm-viewport {
    text-align: left !important;
    overflow: hidden;
  }
  .xterm .xterm-screen {
    text-align: left !important;
  }
  </style>