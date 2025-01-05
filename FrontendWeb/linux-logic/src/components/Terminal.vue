
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
        'Hallo Welt!',
        'Linux-Logic: Dein Einsieg in Linux!',
        'Linux Leicht Lernen!',
        'Befehle schreiben. Probleme lösen.',
        'Learning by Doing!',
        'Dein Weg zum Linux-Profi!',
        'Willkommen im Linux-Universum!',
        'Willkommen bei Linux Logic!'        
      ],
      currentMessageIndex: 0,
      typingInterval: null, 
      currentCharIndex: 0, 
      stopTyping: false
    };
  },
  mounted() {
    // Create and configure the terminal
    this.terminal = new Terminal({
      cursorBlink: true,  // Cursor blinks to improve visibility
      rows: 30,           // Set initial number of rows
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
    simpleWrite(text){
        this.stopTyping = true;
        console.log(text);
        
        this.terminal.clear();

        // logic@linux:~& ausgeben nachdem das Terminal gecleared wird 
        // this.terminal.write("logic@linux:~$ " + text); // Add the prompt again before the text
    },

    typeTerminal() {
      const currentMessage = this.messages[this.currentMessageIndex];
      const prompt = "logic@linux:~$ "; // Terminal prompt

      if (this.currentCharIndex === 0) {
        this.terminal.write(prompt); // Write the prompt before the first message
      }

      if (this.currentCharIndex < currentMessage.length) {
        this.terminal.write(currentMessage[this.currentCharIndex]);
        this.currentCharIndex++;
      } else {
        this.currentCharIndex = 0;
        this.currentMessageIndex = (this.currentMessageIndex + 1) % this.messages.length;
        clearInterval(this.typingInterval); // Stop the current interval
        this.terminal.write('\r\n'); // Move to the next line
        setTimeout(() => {
          this.terminal.clear(); // Clear the terminal but keep the prompt

          // logic@linux:~& ausgeben nachdem das Terminal gecleared wird 
          // this.terminal.write(prompt); // Add the prompt again before starting the next message

          setTimeout(() => {
            this.startTyping(); // Start typing the next message
          }, 500); // Wait for 500ms before starting the next message
        }, 1000); // Wait for 1000ms before clearing the terminal
      }
    },

    startTyping() {
      this.typingInterval = setInterval(() => {
        if (this.stopTyping == false){
          this.typeTerminal();
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
  padding: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}
.terminal-header {
  font-family: 'Courier New', Courier, monospace;
  background-color: #111; 
  color: #fff;
  padding-left: 10px;
  padding-top: 4px;
  padding-bottom: 4px;
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

