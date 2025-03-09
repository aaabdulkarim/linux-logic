<template>
  <div 
    id="terminal-container" 
    ref="terminalContainer"
    role="log"
    aria-live="polite"
    aria-labelledby="terminal-label"
  >
    <div class="terminal-header">
      <span>Logic Terminal</span>
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
  name: 'BspTerminal',
  data() {
    return {
      terminal: null,
      fitAddon: null,
      messages: [
        'Hallo Welt!',
        'Linux-Logic: Dein Einstieg in Linux!',
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
    this.terminal = new Terminal({
      cursorBlink: true,
      rows: 30,
      theme: {
        background: '#1e1e1e',
        foreground: '#dcdcdc', 
        cursor: '#dcdcdc',
        selection: 'rgba(255, 255, 255, 0.3)',
        borderColor: '#fff'
      },
    });

    this.fitAddon = new FitAddon();
    this.terminal.loadAddon(this.fitAddon);

    this.terminal.open(this.$refs.terminalContainer);
    this.fitAddon.fit(); 

    this.startTyping();  
  },
  methods: {
    simpleWrite(text){
      this.stopTyping = true;
      this.terminal.clear();
    },

    typeTerminal() {
      const currentMessage = this.messages[this.currentMessageIndex];
      const prompt = " logic@linux:~$ ";

      if (this.currentCharIndex === 0) {
        this.terminal.write(prompt);
      }

      if (this.currentCharIndex < currentMessage.length) {
        this.terminal.write(currentMessage[this.currentCharIndex]);
        this.currentCharIndex++;
      } else {
        this.currentCharIndex = 0;
        this.currentMessageIndex = (this.currentMessageIndex + 1) % this.messages.length;
        clearInterval(this.typingInterval);
        this.terminal.write('\r\n');
        setTimeout(() => {
          this.terminal.clear();
          setTimeout(() => {
            this.startTyping();
          }, 500);
        }, 1000);
      }
    },

    startTyping() {
      this.typingInterval = setInterval(() => {
        if (!this.stopTyping) {
          this.typeTerminal();
        }
      }, 100);
    }
  },
  beforeDestroy() {
    clearInterval(this.typingInterval);
  }
}
</script>



<style scoped>
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

