<template>
  <div class="content">

    <h2>Level {{ aufgabenId }}</h2>
    <p>{{ aufgabe }}
    </p>

  </div>

  <div id="terminal-container" ref="terminalContainer">
    <div class="terminal-header">
      <span>logic Terminal</span>
    </div>
    <div class="terminal-output">
      <!-- Terminal output is rendered here -->
    </div>
  </div>
  <div class="terminal-bottom">
  </div>
  <div class="icon-container">
    <div class="left-icons">
      <i class="pi pi-sign-out icon" title="Zurück zum enü" @click="exitToMenu"></i>
    </div>
    <div class="right-icons">
      <i class="pi pi-lightbulb icon" title="Hinweiß anzeigen" @click="showModal('hint')"></i>
      <i class="pi pi-key icon" title="Lösung anzeigen" @click="showModal('key')"></i>
      <!-- TODO: -->
      <i class="pi pi-angle-right icon" title="Aufgabe abgeben" @click="submitLevel"></i>
    </div>
  </div>
  <div v-if="showRating" class="rating-popup">
    <h2>Level abgeschlossen!</h2>
    <h5>Deine Bewertung</h5>
    <div class="stars">
      <i class="pi" :class="rating >= 1 ? 'pi-star-fill' : 'pi-star'"></i>
      <i class="pi" :class="rating >= 2 ? 'pi-star-fill' : 'pi-star'"></i>
      <i class="pi" :class="rating >= 3 ? 'pi-star-fill' : 'pi-star'"></i>
    </div>
    <button @click="nextLevel" severity="success" class="w-full">
        Nächstes Level
    </button>
  </div>
  <div v-if="isModalVisible" class="modal">
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <p>{{ modalContent }}</p>
    </div>

  </div>
</template>

<script>
import { Terminal } from "xterm";
import "xterm/css/xterm.css";
import { FitAddon } from "xterm-addon-fit";
import api from "@/api";
import Button from "primevue/button";

export default {
  name: 'Terminal',
  data() {
    return {
      terminal: null,
      fitAddon: null,
      socketClient: null,
      promptLength: 14,
      socketUrl: "http://localhost:8000/ws",

      isModalVisible: false,
      modalContent: '',
      showRating: false,
      rating: 1,  
      
      current_directory: "/",

      aufgabe: "",
      aufgabenId: 0,

      profileName: "",
      scenarioId: 0,
    };
  },
  mounted() {
    api.get('/me')
      .then(response => {
        const data = response.data;
        console.log(data);

        this.profileName = data.username;

        this.initWebSocket()
      })
      .catch(error => {
        console.error('Fehler beim Abrufen der Benutzerdaten:', error);
      });

    this.terminal = new Terminal({
      cursorBlink: true,
      rows: 26,
      cols: 120,
      theme: {
        background: '#1e1e1e',
        foreground: '#dcdcdc',
        cursor: '#dcdcdc'
      },
      screenReaderMode: true,
      allowProposedApi: true,
    });

    this.fitAddon = new FitAddon();
    this.terminal.loadAddon(this.fitAddon);
    this.terminal.open(this.$refs.terminalContainer);
    this.fitAddon.fit();

    this.terminal.onKey((event) => {
      const { key, domEvent } = event;
      if (domEvent.key === "Enter") {
        this.terminal.write("\r\n");
        this.respondToInput(this.userInput);
        this.userInput = ""; // Eingabe zurücksetzen
      } else if (domEvent.key === "Backspace") {
        if (this.userInput.length > 0) {
          this.userInput = this.userInput.slice(0, -1);
          this.terminal.write("\b \b"); // Löscht das letzte Zeichen
        }
      } else {
        this.userInput += key;
        this.terminal.write(key);
      }
    });



  },


  methods: {
    initWebSocket() {
      this.socketClient = new WebSocket(this.socketUrl);


      this.socketClient.onopen = () => {
        console.log("WebSocket connection established.");
        this.socketClient.send(this.profileName);
        const scenarioIdFromQuery = this.$route.query.scenario_id;
        this.socketClient.send(scenarioIdFromQuery)
        this.scenarioId = parseInt(scenarioIdFromQuery)
        this.socketClient.send("")
      };

      this.socketClient.onmessage = (event) => {
        try {
          const message = JSON.parse(event.data);
          console.log(message)

          if (message.output) {
            const lines = message.output.trim().split("\n"); // Trim entfernt leere Zeilen
            lines.forEach((line) => {
              this.terminal.write(`\r\n${line}`);
            });
          }

          if (message.check) {
            this.terminal.write(`\r\nCheck Result: ${message.check}`);
          }
          if (message.hint) {
            this.terminal.write(`\r\nHint: ${message.hint}`);
          }
          if (message.solution) {
            this.terminal.write(`\r\nSolution: ${message.solution}`);
          }

          if (message.current_directory) {
            this.current_directory = message.current_directory
          }

          if (message.description) {
            this.aufgabe = message.description;
          }

          if (message.levelNr) {
            this.aufgabenId = message.levelNr;
          }

          // Falls das letzte Level erreicht wurde, Bewertung anzeigen
          if (message.last_level) {
            this.showRatingPopup();
          }

          // Immer nur einen Prompt setzen, wenn nicht schon einer existiert
          // setTimeout(() => this.writePrompt(), 50);
          this.writePrompt()

        } catch (error) {
          console.error("Error parsing JSON:", error);
          this.terminal.write(`\r\n${event.data}`); // Fallback zu einfachem Text
          this.writePrompt();
        }
      };


      this.socketClient.onerror = (error) => {
        console.error("WebSocket Error:", error);
      };

      this.socketClient.onclose = () => {
        console.warn("WebSocket connection closed.");

        // setTimeout(() => this.initWebSocket(), 2000); // Reconnect after 2 seconds

      };

      // this.terminal.onData(this.handleInput);


    },
    
    hexToRgb(hex) {
      const bigint = parseInt(hex.replace("#", ""), 16);
      return [(bigint >> 16) & 255, (bigint >> 8) & 255, bigint & 255];

    },

    writePrompt() {
      const lastLine = this.terminal.buffer.active.getLine(this.terminal.buffer.active.cursorY);
      if (!lastLine || !lastLine.translateToString().includes("logic@linux:")) {
        const [r, g, b] = this.hexToRgb("#569191");
        const coloredPath = `\x1b[1m\x1b[38;2;${r};${g};${b}m${this.current_directory}\x1b[0m`;
        this.terminal.write(`\r\nlogic@linux:${coloredPath}$ `);
      }



    },
    handleInput(data) {
      const char = data.charCodeAt(0);

      if (char === 13) { // Enter key
        const line = this.terminal.buffer.active.getLine(this.terminal.buffer.active.cursorY);
        if (line) {
          const lineText = line.translateToString().trim();
          const userCommand = lineText.slice(this.promptLength).trim();
          this.respondToInput(userCommand);
        }
      } else if (char === 127) { // Backspace
        if (this.userInput.length > 0) {
          this.userInput = this.userInput.slice(0, -1);
          this.terminal.write('\b \b');
        }
      } else {
        this.userInput += data;
        this.terminal.write(data);
      }
    }
    ,
    respondToInput(input) {

      if (input.toLowerCase() == "clear") {
        this.terminal.clear();
        this.writePrompt();
        return;
      }

      if (this.socketClient.readyState === WebSocket.OPEN) {
        console.log("SENDING")
        setTimeout(() => {
          this.socketClient.send(input);
        }, 100); // Delay for 100ms

      } else {
        this.terminal.write("\r\n[Error] WebSocket not connected.");
      }
    },
    showModal(type) {
      if (type === 'hint') {
        this.socketClient.send(">clue")
      } else if (type === 'key') {
        this.socketClient.send(">solution")
      }
      this.isModalVisible = true;
      this.modalContent = type === 'hint' ? 'Der Hinweiß der Aufgabe!' : 'Die Lösung der Aufgabe!';
    },
    closeModal() {
      this.isModalVisible = false;
      this.modalContent = '';
    },
    submitLevel() {
      this.socketClient.send(">check")
    },
    showRatingPopup() {
      this.showRating = true;
    },
    nextLevel() {
      this.showRating = false;
      this.$router.push('/auswahl'); // Beispiel mit Vue Router (entfernt)
    },
    exitToMenu() {
      this.$router.push('/auswahl');




    },

  }
};
</script>

<style scoped>
.icon-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  max-width: 1200px;
  margin-top: 20px;
  padding: 0 0;
  transition: transform 0.2s ease;
}

.left-icons {
  transform: rotate(180deg);
  transition: transform 0.2s ease;
}

.left-icons:hover {
  transform: translateX(-5px) rotate(180deg);
}

.right-icons {
  display: flex;
  gap: 15px;
  transition: transform 0.2s ease;
}

.right-icons {
  display: flex;
  gap: 15px;
}

.right-icons>>>.pi-angle-right {
  transition: transform 0.2s ease-in-out;
}

.right-icons>>>.pi-angle-right:hover {
  transform: translateX(5px);
}


@keyframes bounce {

  0%,
  100% {
    transform: translateY(0);
  }

  50% {
    transform: translateY(-5px);
  }
}

.right-icons>>>.pi-lightbulb,
.right-icons>>>.pi-key {
  transition: transform 0.3s ease-in-out;
}

.right-icons>>>.pi-lightbulb:hover,
.right-icons>>>.pi-key:hover {
  animation: bounce 0.4s ease-in-out;
}


.icon {
  font-size: 24px;
  cursor: pointer;
}

.rating-popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  z-index: 1000;
}

.stars i {
  padding: 10px;
  font-size: 24px;
  cursor: pointer;
  color: gold;
}

.pi-star-filled {
  color: gold;
}


button {
  margin-top: 10px;
  padding: 0.6rem;
  border: 1px solid #569191;
  color: #569191;
  background-color: transparent;
}

button:hover {
  border: 1px solid white !important;
  color: white !important;
  background-color: #569191 !important;
}
 
.content {
  justify-content: left;
  text-align: left;
  padding-left: 10px;
  padding-right: 10px;

}

.terminal-container {
  opacity: 0.8;
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
  color: #dcdcdc;
  padding-top: 0px;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
}

.terminal-bottom {
  background-color: #1e1e1e;
  padding-top: 12px;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
}


::v-deep(.xterm-screen) {
  text-align: left !important;
  padding-left: 10px;
}
</style>
