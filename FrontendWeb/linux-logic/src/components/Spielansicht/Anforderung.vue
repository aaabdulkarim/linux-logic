<template>
  <div class="all grid " :style="backgroundStyle">
    <div class="container">
      <div class="header ">
        <h1>Level {{ scenario_id }}</h1>
      </div>
      <div class="content">
      </div>
      <div class="terminal-container">
        <Terminal />
      </div>
    </div>
  </div>
</template>

<script>
import Terminal from './Terminal.vue';
import Button from 'primevue/button';
import api from '@/api';

export default {
  name: 'Anforderung',
  components: {
    Terminal,
    Button
  },
  data() {
    return {
      scenario_id: null,
    };
  },
  computed: {
    backgroundStyle() {
      return {
        minHeight: '100vh',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
      };
    }
  },
  mounted() {
    // Abrufen der scenario_id aus den Query-Parametern
    const scenarioIdFromQuery = this.$route.query.scenario_id;
    
    // TODO: Check if user is Really authorized for this level 
    api.get('/progress')
      .then(response => {
        const data = response.data;
        if (scenarioIdFromQuery > data.nextCourse){
          alert("Scenario noch nicht verfügbar, Stelle erst alle notwendigen Aufgaben fertig");
          this.$router.push("/auswahl")

        }
      })
      .catch(error => {
        console.error('Fehler beim Abrufen der Benutzerdaten:', error);
      });

    if (scenarioIdFromQuery) {
      this.scenario_id = parseInt(scenarioIdFromQuery); // Konvertieren in eine Zahl
      if(scenarioIdFromQuery > 5){
        alert("Scenario ID nicht gefunden");
        this.$router.push("/auswahl")

      }
    } else {
      
      alert("Keine Scenario ID im URL gefunden");
      this.$router.push("/auswahl")
    }
  }

};
</script>

<style scoped>
.all {
  min-height: fit-content;
  padding-bottom: 18rem;
  justify-content: space-between;
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


@keyframes bounce {

  0%,
  100% {
    transform: translateY(0);
  }

  50% {
    transform: translateY(-5px);
  }
}
</style>
