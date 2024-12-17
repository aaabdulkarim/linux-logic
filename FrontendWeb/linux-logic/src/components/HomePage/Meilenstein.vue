<template>
  <div class="Meilenstein grid">
    <div class="col-12">
      <h1 style="padding-top: 10rem; padding-left: 10rem; padding-bottom: 4rem;">Meilensteinplan</h1>

      <Timeline :value="events" class="customized-timeline">
        <!-- Linkes Datum -->
        <template #opposite="slotProps">
          <small class="event-date">{{ slotProps.item.date }}</small>
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
          icon: "pi pi-check"
        },
        {
          date: "14.10.24",
          status: "MACHBARKEITSSTUDIE ABGESCHLOSSEN",
          event: "Machbarkeitsstudie abgeschlossen",
          details: "Fertigstellung der Machbarkeitsstudie, Evaluierung der technischen Anforderungen",
          color: "#569191",
          icon: "pi pi-check"
        },
        {
          date: "08.11.24",
          status: "PROTOTYP FERTIGGESTELLT",
          event: "Prototyp fertiggestellt",
          details: "Fertigstellung eines Prototyps für eine visuelle und funktionelle Vorstellung",
          color: "#569191",
          icon: "pi pi-check"
        },
        {
          date: "27.01.25",
          status: "BETA-VERSION ENTWICKELT",
          event: "Beta-Version entwickelt",
          details: "Erster Release der funktionierenden Applikationen",
          color: "#808080",
          icon: "pi pi-check"
        },
        {
          date: "21.02.25",
          status: "TESTPHASE ABGESCHLOSSEN",
          event: "Testphase abgeschlossen",
          details: "Tests, Fehlerbehebungen und Optimierungen bei Bedarf",
          color: "#808080",
          icon: "pi pi-check"
          
        },
        {
          date: "01.04.25",
          status: "DIPLOMARBEIT VOLLSTÄNDIG",
          event: "Diplomarbeit vollständig",
          details: "Die Diplomarbeit schriftlich festgehalten und bereit für Einreichung",
          color: "#808080",
          icon: "pi pi-check"
        },
        {
          date: "08.04.25",
          status: "PROJEKTABSCHLUSS",
          event: "Projektabschluss",
          details: "Abschluss des Projekts, Einreichung der Diplomarbeit",
          color: "#808080",
          icon: "pi pi-check"
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
.Meilenstein {
  text-align: left; 
  background-color: white;
  color: #3D525C;
}

.event-title {
  font-size: 1.8rem;
  margin-bottom: 0rem;
  margin-top: 0;
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
  margin-top: 0;
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

</style>