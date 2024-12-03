<template>  
    <div class="Meilenstein grid">
      <div class="col-12">
        <h1>Meilensteinplan</h1>
        <Badge value="1" size="xlarge" severity="success"></Badge>
        <Badge value="2" size="xlarge" severity="success"></Badge>
        <Badge value="3" size="xlarge" severity="success"></Badge>
        <Badge value="4" size="xlarge" severity="danger"></Badge>
        <Badge value="5" size="xlarge" severity="danger"></Badge>
        <Badge value="6" size="xlarge" severity="danger"></Badge>
        <Badge value="7" size="xlarge" severity="danger"></Badge>
        
        <Timeline :value="events" layout="horizontal" align="alternate" class="pt-8">
            <!-- Optional: Opposite Slot -->
            <template #opposite> &nbsp; </template>
      
            <!-- Content Slot -->
            <template #content="slotProps">
              <div>
                <div class="date">
                 <u>{{ slotProps.item.date }}</u>
                </div>
                <div class="event">
                  <b>{{ slotProps.item.event }}</b>
                </div>
                <div class="details">
                  {{ slotProps.item.details }}
                </div>
              </div>
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
    .Meilenstein{
        background-color: white;
        height: 102vh;
        color: #3D525C;
    }

    
</style>