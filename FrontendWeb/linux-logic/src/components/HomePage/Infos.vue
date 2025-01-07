<template>  
    <div class="Infos grid">
        <div class="InfoText"></div>
        <div class="col-7 my-4 p-4">
            <h1>Diplomprojekt Linux Logic</h1>
            <p>Linux Logic ist das Diplomprojekt eines Schülerteams des TGM's bestehend aus Amadeus Abdulkarim, Benjamin Eder, Batschana Gogoladze, André Karakassoglou und Sergej Rychkov</p>
            <h1>Ausgangslage</h1>
            <p>In IT-Kreisen steigt die Nachfrage nach Linux-Experten und deren Fachwissen, vor allem wegen des Potenzials in den Bereichen Sicherheit, Datenbanken und Administration. Viele scheitern jedoch am schwierigen Einstieg. Fehlendes Know-how führt zu Frustration, da eine passende Einführung fehlt. Dieser Mangel sorgt für ein Defizit an IT-Fachkräften mit Linux-Spezialisierung.</p>
            <h1>Zielsetzung</h1>
            <p>Das Projekt Linux-Logic zielt darauf ab, einen einfachen Einstieg in Linux durch spielerische Aufgaben für Auszubildende und Berufseinsteiger zu ermöglichen. Eine Umfrage unter 17- bis 19-Jährigen ergab, dass viele durch Ausprobieren lernen. Die App unterstützt das Lernen im echten Linux-Betriebssystem. Dabei kann Linux geübt werden, ohne dass eine vollständige Installation notwendig ist. </p>
        </div>
        <div class="col-5 pt-8 pr-5">
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
        padding-left: 20rem;
        text-align: left;  
        padding-left: 10rem;
        padding-right: 4rem;
    }
</style>