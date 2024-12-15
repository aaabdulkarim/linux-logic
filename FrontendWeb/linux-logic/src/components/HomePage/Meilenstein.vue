<template>
  <div class="Meilenstein grid">
    <div class="col-12">
      <h1>Meilensteinplan</h1>

      <Timeline :value="events">
        <template #opposite="slotProps">
          <small class="text-surface-500 dark:text-surface-400">{{ slotProps.item.date }}</small>
        </template>
        <template #content="slotProps">
          {{ slotProps.item.event }}
          <br>
          {{ slotProps.item.details }}
        </template>
      </Timeline>

    </div>
  </div>
</template>

<script>
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
          event: "PROJEKTSTART",
          details: "Kick-off des Diplomprojekts Linux-Logic, Definition von Zielen, Teamaufstellung und DA-Antrag"
        },
        {
          date: "14.10.24",
          event: "MACHBARKEITSSTUDIE ABGESCHLOSSEN",
          details: "Fertigstellung der Machbarkeitsstudie, Evaluierung der technischen Anforderungen"
        },
        {
          date: "08.11.24",
          event: "PROTOTYP FERTIGGESTELLT",
          details: "Fertigstellung eines Prototyps für eine visuelle und funktionelle Vorstellung"
        },
        {
          date: "27.01.25",
          event: "BETA-VERSION ENTWICKELT",
          details: "Erster Release der funktionierenden Applikationen"
        },
        {
          date: "21.02.25",
          event: "TESTPHASE ABGESCHLOSSEN",
          details: "Tests, Fehlerbehebungen und Optimierungen bei Bedarf"
        },
        {
          date: "01.04.25",
          event: "DIPLOMARBEIT VOLLSTÄNDIG GESCHRIEBEN",
          details: "Die Diplomarbeit schriftlich festgehalten und bereit für Einreichung"
        },
        {
          date: "08.04.25",
          event: "PROJEKTABSCHLUSS",
          details: "Abschluss des Projekts, Einreichung der Diplomarbeit"
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
  background-color: white;
  height: 102vh;
  color: #3D525C;
}
</style>