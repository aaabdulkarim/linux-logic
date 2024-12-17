// src/components/Terminal.vue
<template>
  <div id="terminal-container" ref="terminalContainer"></div>
</template>

<script>
import { Terminal } from "@xterm/xterm";
import "@xterm/xterm/css/xterm.css";
import { FitAddon } from "xterm-addon-fit";

export default {
  name: 'Terminal',
  data() {
    return {
      terminal: null,
      fitAddon: null,
      messages: [
        'Willkommen bei Linux Logic!',
        'Linux Leicht Lernen!',
        'Hallo Welt!',
        'Linux-Logic: Dein Einsieg in Linux!',
        'Befehle schreiben. Probleme lösen.',
        'Learning by Doing!',
        'Willkommen im Linux-Universum!',

      ],
      currentMessageIndex: 0,
      typingInterval: null, // Timer für Buchstabe-für-Buchstabe-Anzeige
      currentCharIndex: 0, // Index des aktuellen Buchstabens
    };
  },
  mounted() {
    // Create and configure the terminal
    this.terminal = new Terminal({
      cursorBlink: true,  // Cursor blinks to improve visibility
      rows: 25,           // Set initial number of rows
      cols: 800,           // Set initial number of columns
      theme: {
        background: '#1e1e1e', // Dark background color for better contrast
        foreground: '#dcdcdc', // Light text color
        cursor: '#dcdcdc',     // Cursor color
        selection: 'rgba(255, 255, 255, 0.3)', // Selection color
        borderColor: '#fff' // Border color
      },
    });

    // Create an instance of the FitAddon to allow the terminal to resize dynamically
    this.fitAddon = new FitAddon();
    this.terminal.loadAddon(this.fitAddon);

    // Attach the terminal to the DOM
    this.terminal.open(this.$refs.terminalContainer);
    this.fitAddon.fit(); // Adjusts terminal size to its container

    // Start typing the first message
    this.startTyping();
  },
  methods: {
    startTyping() {
      this.typingInterval = setInterval(() => {
        const currentMessage = this.messages[this.currentMessageIndex];
        if (this.currentCharIndex < currentMessage.length) {
          this.terminal.write(currentMessage[this.currentCharIndex]);
          this.currentCharIndex++;
        } else {
          this.currentCharIndex = 0;
          this.currentMessageIndex = (this.currentMessageIndex + 1) % this.messages.length;
          clearInterval(this.typingInterval); // Stop the current interval
          this.terminal.write('\r\n'); // Move to the next line
          setTimeout(() => {
            this.terminal.clear(); // Clear the terminal
            setTimeout(() => {
              this.startTyping(); // Start typing the next message
            }, 500); // Wait for 500ms before starting the next message
          }, 1000); // Wait for 1000ms before clearing the terminal
        }
      }, 100); // Adjust the typing speed by changing the interval
    }
  },
  beforeDestroy() {
    clearInterval(this.typingInterval);
  }
}
</script>

<style>
.terminal-container {
  width: 100%;
  height: 100%;
  background-color: #1e1e1e;
  border-radius: 5px;
  padding: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}
</style>
