<template>
    <div class="grid align-items-center">
      <!-- Accordion Section -->
      <div class="col-6 p-3">
        <div class="surface-100 border-round">
          <h1 class="text-center py-6">Diplomprojekt Linux Logic</h1>
          <Accordion :value="['0']" multiple>
            <AccordionPanel value="0">
              <AccordionHeader class="event-title">Über das Projekt</AccordionHeader>
              <AccordionContent>
                <p class="event-date">
                  Linux Logic ist das Diplomprojekt eines Schülerteams des TGM's
                  bestehend aus Amadeus Abdulkarim, Benjamin Eder, Batschana Gogoladze,
                  André Karakassoglou und Sergej Rychkov.
                </p>
              </AccordionContent>
            </AccordionPanel>
            <AccordionPanel value="1">
              <AccordionHeader class="event-title">Ausgangslage</AccordionHeader>
              <AccordionContent >
                <p>
                  In IT-Kreisen steigt die Nachfrage nach Linux-Experten und deren
                  Fachwissen, vor allem wegen des Potenzials in den Bereichen
                  Sicherheit, Datenbanken und Administration. Viele scheitern jedoch am
                  schwierigen Einstieg. Fehlendes Know-how führt zu Frustration, da
                  eine passende Einführung fehlt. Dieser Mangel sorgt für ein Defizit
                  an IT-Fachkräften mit Linux-Spezialisierung.
                </p>
              </AccordionContent>
            </AccordionPanel>
            <AccordionPanel value="2">
              <AccordionHeader class="event-title">Zielsetzung</AccordionHeader>
              <AccordionContent>
                <p>
                  Das Projekt Linux-Logic zielt darauf ab, einen einfachen Einstieg in
                  Linux durch spielerische Aufgaben für Auszubildende und
                  Berufseinsteiger zu ermöglichen. Eine Umfrage unter 17- bis
                  19-Jährigen ergab, dass viele durch Ausprobieren lernen. Die App
                  unterstützt das Lernen im echten Linux-Betriebssystem. Dabei kann
                  Linux geübt werden, ohne dass eine vollständige Installation
                  notwendig ist.
                </p>
              </AccordionContent>
            </AccordionPanel>
          </Accordion>
        </div>
      </div>
  
      <!-- Terminal Section -->
      <div class="col-6 p-3">
        <div class=" border-round shadow-2" style="height: 100%;">
          <Terminal />
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import Accordion from "primevue/accordion";
  import AccordionPanel from "primevue/accordionpanel";
  import AccordionHeader from "primevue/accordionheader";
  import AccordionContent from "primevue/accordioncontent";
  import Terminal from "@/components/Terminal.vue";
  
  export default {
    name: "Infos",
    components: {
      Terminal,
      Accordion,
      AccordionPanel,
      AccordionHeader,
      AccordionContent,
    },
    data(){
      return {
        infos: [
          {
            title: "Über das Projekt",
            content: "Linux Logic ist das Diplomprojekt eines Schülerteams des TGM's bestehend aus Amadeus Abdulkarim, Benjamin Eder, Batschana Gogoladze, André Karakassoglou und Sergej Rychkov."
          },

          {
            title: "Ausgangslage",
            content: "In IT-Kreisen steigt die Nachfrage nach Linux-Experten und deren Fachwissen, vor allem wegen des Potenzials in den Bereichen Sicherheit, Datenbanken und Administration. Viele scheitern jedoch am schwierigen Einstieg. Fehlendes Know-how führt zu Frustration, da eine passende Einführung fehlt. Dieser Mangel sorgt für ein Defizit an IT-Fachkräften mit Linux-Spezialisierung."
          },

          {
            title: "Zielsetzung",
            content: "Das Projekt Linux-Logic zielt darauf ab, einen einfachen Einstieg in Linux durch spielerische Aufgaben für Auszubildende und Berufseinsteiger zu ermöglichen. Eine Umfrage unter 17- bis 19-Jährigen ergab, dass viele durch Ausprobieren lernen. Die App unterstützt das Lernen im echten Linux-Betriebssystem. Dabei kann Linux geübt werden, ohne dass eine vollständige Installation notwendig ist."
          },

        ]
      }
    }
  };
  </script>
  
  <style scoped>
  
  .grid {
    height: 100%;
  }
  .text-center {
    text-align: center;
  }
  .shadow-2 {
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  }
  </style>