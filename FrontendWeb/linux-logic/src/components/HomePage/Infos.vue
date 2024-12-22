<template>
    <div class="align-items-center">
      <h1 class="text-center">Diplomprojekt Linux Logic</h1>

      <!-- Button Badges -->
      <div class="">
        <SelectButton @click="writeInTerminal" v-model="selected" :options="createTitlelist()" />
      </div>
  
      <!-- Terminal Section -->
      <div class="flex justify-content-center">
        <div class="w-6 align-items-center justify-content-center border-round" style="height: 100%;">
          <Terminal ref="terminalInfo"/>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import Accordion from "primevue/accordion";
  import AccordionPanel from "primevue/accordionpanel";
  import AccordionHeader from "primevue/accordionheader";
  import AccordionContent from "primevue/accordioncontent";
  import SelectButton from 'primevue/selectbutton';
  import Terminal from "@/components/Terminal.vue";
  
  export default {
    name: "Infos",
    components: {
      Terminal,
      Accordion,
      AccordionPanel,
      AccordionHeader,
      AccordionContent,
      SelectButton
    },
    data(){
      return {
        info: [
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
        ],
        selected: "Über das Projekt",

      }
    },
    methods: {
      createTitlelist(){
        const titlelist = this.info.map((infoObj) => infoObj.title);
        return titlelist
      },
      writeInTerminal(){
        const selectedItem = this.info.find((infoObj) => infoObj.title === this.selected);

        if (selectedItem) {
          // Führt Methode in einem Terminal aus
          this.$refs.terminalInfo.simpleWrite(selectedItem.content);
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .terminal{
    width: 50%;
    align-self: center;
  }
  .grid {
    height: 100%;
  }
  .text-center {
    text-align: center;
  }
  </style>