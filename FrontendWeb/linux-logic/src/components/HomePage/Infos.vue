<template>  
    <div class="Infos grid">
      <div class="col-12 md:col-5 my-4 p-4">
        <h3>Diplomprojekt Linux Logic</h3>
        <p>
          Linux Logic ist das Diplomprojekt eines Schülerteams des TGM's bestehend aus 
          Amadeus Abdulkarim, Benjamin Eder, Batschana Gogoladze, André Karakassoglou 
          und Sergej Rychkov.
        </p>
  
        <h3>Ausgangslage</h3>
        <p>
          Die Nachfrage nach Linux-Experten wächst, insbesondere in den Bereichen 
          Sicherheit, Datenbanken und Administration. Der anspruchsvolle Einstieg und 
          fehlendes Fachwissen führen jedoch bei Anfängern häufig zu Frustration, was 
          wiederum den Mangel an IT-Fachkräften mit Linux-Kompetenz verstärkt.
        </p>
  
        <h3>Zielsetzung</h3>
        <p>
          Das Projekt Linux-Logic zielt darauf ab, einen einfachen Einstieg in Linux durch 
          spielerische Aufgaben für Auszubildende und Berufseinsteiger zu ermöglichen. 
          Eine Umfrage unter 17- bis 19-Jährigen ergab, dass viele durch Ausprobieren 
          lernen. Die App ermöglicht Lernen im echten Linux-Betriebssystem ohne eine Installation.
        </p>
      </div>
  
      <div class="col-12 md:col-7 pt-4 md:pt-8 pr-2 md:pr-5">
        <Terminal/>
      </div>
    </div> 
  </template>
  
  <script>
  import Terminal from '@/components/HomePage/BspTerminal.vue'
  
  export default {
    name: 'Infos',
    components: {
      Terminal
    }
  };
  </script>
  
  <style scoped>
  .Infos {
    background-color: white;
    color: #3D525C;
    text-align: left;
    padding: 2rem;
  }
  @media (min-width: 768px) {
    .Infos {
      padding-left: 8rem;
      padding-right: 4rem;
    }
  }
  </style>
  