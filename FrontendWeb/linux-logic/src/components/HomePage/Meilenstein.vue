<template>
  <div class="Meilenstein grid">
    <div class="col-12  text-center py-6">
      <h1>Meilensteinplan</h1>
      
      <Timeline :value="events" class="customized-timeline">
        <!-- Linkes Datum -->
        <template #opposite="slotProps">
          <h5 class="event-date">{{ slotProps.item.date }}</h5>
        </template>
        
        <!-- Marker -->
        <template #marker="slotProps">
          <span
            class="p-timeline-event-marker"
            :style="{ backgroundColor: slotProps.item.color }">
            <i :class="slotProps.item.icon" class="timeline-icon"></i>
          </span>
        </template>

        <!-- Event-Inhalt -->
        <template #content="slotProps">
          <div class="event-content">
            <h4 class="event-title">{{ slotProps.item.event }}</h4>
            <p class="event-details">{{ slotProps.item.details }}</p>
          </div>
        </template>
      </Timeline>
    </div>
  </div>
</template>

<script>
import 'primeicons/primeicons.css';
import Badge from 'primevue/badge';
import Stepper from 'primevue/stepper';
import StepList from 'primevue/steplist';
import StepPanels from 'primevue/steppanels';
import StepItem from 'primevue/stepitem';
import Step from 'primevue/step';
import StepPanel from 'primevue/steppanel';
import Timeline from 'primevue/timeline';



export default {
  name: 'Meilenstein',
  data() {
    return {
      events: [
            {
          date: "09.09.24",
          status: "PROJEKTSTART",
          event: "Projektstart",
          details: "Kick-off des Diplomprojekts Linux-Logic, Definition von Zielen, Teamaufstellung und DA-Antrag",
          color: "#569191",
          icon: "pi pi-check",
          direction: "opposite", // This will display on the opposite side
        },
        {
          date: "14.10.24",
          status: "MACHBARKEITSSTUDIE ABGESCHLOSSEN",
          event: "Machbarkeitsstudie",
          details: "Fertigstellung der Machbarkeitsstudie, Evaluierung der technischen Anforderungen",
          color: "#569191",
          icon: "pi pi-check",
          direction: "normal", // Default side
        },
        {
          date: "08.11.24",
          status: "PROTOTYP FERTIGGESTELLT",
          event: "Prototyp",
          details: "Fertigstellung eines Prototyps für eine visuelle und funktionelle Vorstellung",
          color: "#569191",
          icon: "pi pi-check",
          direction: "opposite", 
        },
        {
          date: "27.01.25",
          status: "BETA-VERSION ENTWICKELT",
          event: "Beta-Version",
          details: "Beta-Version der funktionierenden Applikationen entwickelt",
          color: "#569191",
          icon: "pi pi-check",
          direction: "normal", 
        },
        {
          date: "21.02.25",
          status: "TESTPHASE ABGESCHLOSSEN",
          event: "Testphase ",
          details: "Tests, Fehlerbehebungen und Optimierungen abgeschlossen",
          color: "#569191",
          icon: "pi pi-check",
          direction: "opposite"

        },
        {
          date: "01.04.25",
          status: "DIPLOMARBEIT VOLLSTÄNDIG",
          event: "Diplomarbeit",
          details: "Die Diplomarbeit vollständig schriftlich festgehalten und bereit für Einreichung",
          color: "#808080",
          icon: "pi pi-check",
          direction: "normal", 

        },
        {
          date: "08.04.25",
          status: "PROJEKTABSCHLUSS",
          event: "Projektabschluss",
          details: "Abschluss des Projekts, Einreichung der Diplomarbeit",
          color: "#808080",
          icon: "pi pi-check",
          direction: "opposite", 
        },
      ]
    }
  },
  methods: {
    extractDate(item) {
      // Ersten Teil (Datum) extrahieren
      const match = item.match(/^\S+/);
      return match ? match[0] : '';
    },
    extractEvent(item) {
      // Zweiten Teil (Event) extrahieren
      const parts = item.split(' ');
      return parts[1] || '';
    },
    extractDetails(item) {
      // Restlichen Text (Details) extrahieren
      const parts = item.split(' ');
      return parts.slice(2).join(' ');
    },
  },
  components: {
    Badge,
    Stepper,
    StepList,
    Step,
    Timeline,
  }
}
</script>

<style scoped>
.Meilenstein h1 {
  text-align: center;
  padding: 10rem 0 4rem 0; /* Zentriert den Titel und hält Abstand */
}
.Meilenstein {
  text-align: left; 
  background-color: white;
  color: #3D525C;
}

.event-title {
  font-size: 1.8rem;
  margin-bottom: 0rem;
  margin-top: 0;
  word-wrap: break-word; 
  overflow-wrap: break-word;
}
.event-details {
  font-size: 1rem;
  color: #5a5a5a;
  margin-bottom: 3rem;
  padding-right: 20rem;
}
.event-date {
  font-size: 1rem;
  color: #808080;
  margin-bottom: 0rem;
  margin-top: 10px;
}
.customized-timeline .p-timeline-event-marker {
  width: 2.2rem;
  height: 2.2rem;
  color: white;
  border: none; 
}
.customized-timeline .p-timeline-event-marker .timeline-icon {
  font-size: 1.2rem;
  margin-left: 1rem;
  margin-right: 1rem;
}
.customized-timeline >>> .p-timeline-event-connector {
  background-color: #808080;
}
@media (max-width: 768px) {
  .event-title { font-size: 1.4rem; }
  .event-details { font-size: 0.9rem; }
}
</style>