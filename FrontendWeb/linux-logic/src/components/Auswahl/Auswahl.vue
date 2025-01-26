<template>
  <div class="LevelOben grid">
    <h1 style="color: white;">Levelauswahl</h1>
  </div>

  <div class="LevelUnten grid">
    <div class="col-12 cards-container">
      <Card 
        style="width: 20rem; overflow: hidden;" 
        v-for="level in levels" 
        :key="level.id"
      >
        <template #header>
          <h2>{{ level.name }}</h2>
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
</template>

<script>
import Card from 'primevue/card';
import Button from 'primevue/button';

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
          name: "Level 1",
          difficulty: "Einfach",
          description: "Grundlagen des Navigierens im Linux-Terminal."
        },
        {
          id: 2,
          name: "Level 2",
          difficulty: "Mittel",
          description: "Dateien und Verzeichnisse erstellen und verwalten."
        },
        {
          id: 3,
          name: "Level 3",
          difficulty: "Schwer",
          description: "Systemprozesse überwachen und konfigurieren."
        }
      ]
    };
  },
  methods: {
    selectLevel(levelId) {
      alert(`Level ${levelId} ausgewählt!`);
    }
  }
};
</script>

<style scoped>
.LevelOben {
  justify-content: center;
  text-align: center;
  background-color: #569191;
  padding: 2rem 0;
}
.LevelUnten {
  background-color: white;
  padding: 2rem 0;
  position: static;
}

.cards-container {
  display: flex;
  gap: 2rem;
  justify-content: center;
  flex-wrap: nowrap;
  padding: 2rem;
  background-color: white;
}

.cards-container >>> .p-card-body {
  background-color: white;
  color: #3D525C;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
}
.cards-container >>> .p-button {
  border: 1px solid #3D525C;
  color: #3D525C;
  background-color: transparent;
}
.cards-container >>> .p-button:hover {
  border: 1px solid white !important;
  color: white !important;
  background-color: #3D525C !important;
}
.cards-container .m-0 {
  font-size: 1rem;
}

.m-0 {
  margin: 0;
}

@media (max-width: 1200px) {
  .cards-container {
    flex-wrap: wrap;
  }
}
</style>
