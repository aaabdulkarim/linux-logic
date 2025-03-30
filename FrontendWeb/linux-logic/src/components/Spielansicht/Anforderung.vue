<template>
<div class="all grid " :style="backgroundStyle">
  <div class="container">
    <div class="header ">
      <h1>Level 1</h1>
    </div>
    <div class="content">
      <h2>Beschreibung</h2>
      <p>Den roten Teppich ausrollen:

        Wechsel in das Verzeichnis der Veranstaltungsvorbereitung: cd
        /home/Veranstaltung .
        Erstelle eine Datei, die den roten Teppich darstellt: touch roter_teppich.txt .
      </p>
    </div>
    <div class="terminal-container">
      <Terminal/>
    </div>
    <div class="icon-container">
      <div class="left-icons">
        <i class="pi pi-sign-out icon" title="Zurück zum enü" @click="exitToMenu"></i>
      </div>
      <div class="right-icons">
        <i class="pi pi-lightbulb icon" title="Hinweiß anzeigen" @click="showModal('hint')"></i>
        <i class="pi pi-key icon" title="Lösung anzeigen" @click="showModal('key')"></i>
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
  </div>
</div>
</template>

<script>
import Terminal from './Terminal.vue';
import  Button  from 'primevue/button';
export default {
  name: 'Anforderung',
  components: {
    Terminal,
    Button
  },
  data() {
    return {
      isModalVisible: false,
      modalContent: '',
      showRating: false,
      rating: 0,
      stars: 3
    };
  },
  methods: {
    showModal(type) {
      if (type === 'hint' && this.stars > 0) {
        this.stars--;
      } else if (type === 'key' && this.stars > 0) {
        this.stars = Math.max(0, this.stars - 2); // Minimum 0 Sterne
      }
      this.isModalVisible = true;
      this.modalContent = type === 'hint' ? 'Der Hinweiß der Aufgabe!' : 'Die Lösung der Aufgabe!';
    },
    closeModal() {
      this.isModalVisible = false;
      this.modalContent = '';
    },
    submitLevel() {
      if (this.stars > 0) {
        this.showRatingPopup();
      } else {
        alert("Du hast keine Sterne erreicht! Versuche es noch einmal.");
      }
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
    }
  },
  computed: {
    backgroundStyle() {
      return {
        height: '100vh',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
      };
    }
  },
};
</script>

<style scoped>
.all {
  min-height: fit-content;
  padding-bottom: 18rem;
  justify-content: center;
  background-color: #569191;
  background-image: url('@/assets/abstract_background_3.webp');
  background-size: cover;
  background-position: top;
  background-repeat: no-repeat;
}
.container {
  opacity: 0.9;
  background-color: transparent;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: 1200px;
  width: 100%;
  padding-left: 10px;
  padding-right: 10px;
}
.content {
  justify-content: left;
  text-align: left;
  padding-left: 10px;
  padding-right: 10px;

}

.header {
  padding-top: 8rem;

}

.terminal-container {
  width: 100%;
  max-width: 1200px;
  border-radius: 8px;
  margin-top: 20px;
}

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

.right-icons >>> .pi-angle-right {
  transition: transform 0.2s ease-in-out;
}
.right-icons >>> .pi-angle-right:hover {
  transform: translateX(5px);
}


@keyframes bounce {
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(-5px); }
}

.right-icons >>> .pi-lightbulb,
.right-icons >>> .pi-key {
  transition: transform 0.3s ease-in-out;
}

.right-icons >>> .pi-lightbulb:hover,
.right-icons >>> .pi-key:hover {
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
.pi-star-filled{
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

</style>
