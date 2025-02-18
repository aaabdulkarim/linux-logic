<template>
  <div class="LevelOben grid">
    <h1 style="color: white;">Chapters</h1>
  </div>
  <div class="all grid">
    <div class="container">
      <div class="beschreibung">
        <h3>Kapitel I: Mittelalter</h3>
        <p> 
          Der König plant eine große Eröffnung für das neue Schloss und hat dich mit
          einfachen, aber wichtigen Aufgaben beauftragt, um sicherzustellen, dass alles
          perfekt vorbereitet ist. Die Diener eilen geschäftig durch die großen Hallen, um die letzten Vorbereitungen zu treffen, während der königliche Gärtner die prachtvollen Blumenbeete inspiziert. Die Köche in der Schlossküche bereiten ein Festmahl vor, das die Gäste in Staunen versetzen soll. Doch plötzlich erreicht eine eilende Botschaft den König – ein unerwarteter Gast von weit her kündigt sein Kommen an.

        </p>
      </div>
      <div class="card grid">
        <div class="col-12 cards-container">
          <Card 
            style="width: 20rem; overflow: hidden;" 
            v-for="level in levels" 
            :key="level.id"
          >
            <template #header>
              
              <h2 style="color: white;">{{ level.name }}</h2>
              <small>Schwierigkeit: {{ level.difficulty }}</small>
            </template>
            <template #content>
              <p class="m-0">
                {{ level.description }}
              </p>
            </template>
            <template #footer>
              <div class="flex gap-4 mt-1">
                <Button label="Start" @click="selectLevel(level.id)" severity="success" class="w-full" />
              </div>
            </template>
          </Card>
        </div>
      </div>
      <div class="beschreibung grid">
        <h3>Kapitel II: </h3>
        <p>Gesperrt</p>
      </div>
    </div>
  </div> 
</template>

<script>
import Card from 'primevue/card';
import Button from 'primevue/button';
import router from '@/router';

export default {
  components: {
    Card,
    Button
  },
  data() {
    return {
      levels: [
        {
          id: 1,
          name: "Level I",
          difficulty: "Einfach",
          description: "Grundlagen des Navigierens im Linux-Terminal."
        },
        {
          id: 2,
          name: "Level II",
          difficulty: "Mittel",
          description: "Dateien und Verzeichnisse erstellen und verwalten."
        },
        {
          id: 3,
          name: "Level III",
          difficulty: "Schwer",
          description: "Systemprozesse überwachen und konfigurieren."
        },
        {
          id: 4,
          name: "Level IV",
          difficulty: "Sehr schwer",
          description: "Benutzer und Gruppen verwalten."
        },
        {
          id: 5,
          name: "Level V",
          difficulty: "Sehr schwer",
          description: "Netzwerkverbindungen konfigurieren."
        }
      ]
    };
  },
  methods: {
    selectLevel(levelId) {
      // alert(`Level ${levelId} ausgewählt!`);
      router.push("/terminal")
    }
  }
};
</script>

<style scoped>
.all {
  background-color: white;
  padding-bottom: 18rem;
  justify-content: center;
}
.container {
  background-color: white;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: 1600px;
  width: 100%;
}
.LevelOben {
  justify-content: center;
  text-align: center;
  background-color: #569191;
  padding: 2rem 0;
}
.beschreibung {
  padding-top: 2rem;
  padding-left: 38px;
  background-color: white;
  justify-content: left;
  text-align: left;
}

.card {
  background-color: white;
  padding: 2rem 0;
  padding-bottom: 7rem;
  max-width: 1600px;
  width: 100%;
}
.cards-container {
  display: flex;
  gap: 2rem;
  justify-content: center;
  flex-wrap: nowrap;
  padding: 2rem;
  background-color: white;
}
.cards-container >>> .p-card-header {
  background-color: #569191;
  color: white;
}
.cards-container >>> .p-card-body {
  background-color: white;
  color: #3D525C;
  border: 1px solid #569191;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
}
.cards-container >>> .p-button {
  border: 1px solid #569191;
  color: #569191;
  background-color: transparent;
}
.cards-container >>> .p-button:hover {
  border: 1px solid white !important;
  color: white !important;
  background-color: #569191 !important;
}
.cards-container .m-0 {
  font-size: 1rem;
}

.m-0 {
  margin: 0;
}
</style>
