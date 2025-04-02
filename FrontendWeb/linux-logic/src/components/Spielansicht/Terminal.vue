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
      <i class="pi pi-star" @click="rateLevel(1)" :class="{ 'pi-star-filled': rating >= 1 }"></i>
      <i class="pi pi-star" @click="rateLevel(2)" :class="{ 'pi-star-filled': rating >= 2 }"></i>
      <i class="pi pi-star" @click="rateLevel(3)" :class="{ 'pi-star-filled': rating >= 3 }"></i>
    </div>
    <Button label="Nächstes Level" @click="nextLevel" severity="success" class="w-full" />
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
      rating: 0,
      stars: 3,

      aufgabe: "",
      aufgabenId: 0,

      profileName: "",
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


  },


  methods: {
    initWebSocket() {
      this.socketClient = new WebSocket(this.socketUrl);


      this.socketClient.onopen = () => {
        console.log("WebSocket connection established.");
        this.socketClient.send(this.profileName);
        const scenarioIdFromQuery = this.$route.query.scenario_id;
        this.socketClient.send("scenario" + scenarioIdFromQuery)
        this.socketClient.send("")

      };

      this.socketClient.onmessage = (event) => {
        try {
          const message = JSON.parse(event.data);
          if (message.output) {
            this.terminal.write(`\r\n${message.output}`);
          } else if (message.hint) {
            this.terminal.write(`\r\nHint: ${message.hint}`);
          } else if (message.solution) {
            this.terminal.write(`\r\nSolution: ${message.solution}`);
          } else if (message.description) {
            this.aufgabe = message.description
          } else if (message.check) {
            this.terminal.write(`\r\nCheck Result: ${message.check}`);
          }
          this.writePrompt();
        } catch (error) {
          console.error("Error parsing JSON:", error);
          this.terminal.write(`\r\n${event.data}`); // Fallback to plain text
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

      this.terminal.onData(this.handleInput);


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
        if (this.userInput.length > 0) {
          this.userInput = this.userInput.slice(0, -1); // Letztes Zeichen aus userInput entfernen
          this.terminal.write('\b \b'); // Zeichen aus Terminal löschen
        }
      } else {
        this.userInput += data; // Zeichen zum User-Input hinzufügen
        this.terminal.write(data)
      }
    },
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
          console.log("should be send");
        }, 100); // Delay for 100ms
        console.log("should be send")

      } else {
        this.terminal.write("\r\n[Error] WebSocket not connected.");
      }
    },
    showModal(type) {
      if (type === 'hint' && this.stars > 0) {
        this.stars--;
        this.socketClient.send(">clue")
      } else if (type === 'key' && this.stars > 0) {
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

      this.showRatingPopup();
    },
    showRatingPopup() {
      this.showRating = true;
    },
    rateLevel() {
      console.log("Bewertung:", this.stars);
    },
    nextLevel() {
      this.showRating = false;
      alert("Hier geht es zum nächsten Level (noch nicht implementiert)"); // Platzhalter
      // this.$router.push('/level2'); // Beispiel mit Vue Router (entfernt)
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
