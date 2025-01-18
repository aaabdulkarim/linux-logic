<template>
    <div class="Fortschritte grid">
        <div class="Überschrift col-12">
            <h1 style="color: white;">Fortschrittsberichte</h1>
        </div>
        <div class="card col-12">
            <Tabs value="0">
                <TabList>
                    <Tab value="0">Fortschrittsbericht I</Tab>
                    <Tab value="1">Fortschrittsbericht II</Tab>
                    <Tab value="2">Fortschrittsbericht III</Tab>
                    <Tab value="3">Fortschrittsbericht IV</Tab>
                </TabList>
                <TabPanels>
                    <TabPanel value="0">
                        <h3>Fortschrittsbericht I</h3>
                        <h5>10.11.2024</h5>
                        <p class="m-0">
                            In diesem Fortschrittsbericht werden die Entwicklungen des Diplomprojekts Linux Logic zusammengefasst, insbesondere die Fortschritte im Frontend-Design, Backend-Entwicklung und die erfolgreiche Präsentation am Tag der offenen Tür.
                        </p>
                        <Transition name="fade">
                            <div v-if="showContent['berichtI']">
                                <h4>System-Design und Architektur</h4>
                                <p class="m-0">
                                    Seit dem 29.10 wurde ein vollständiges Mockup für das App-Frontend entworfen und besprochen. Der Prototyp mit grundlegender Logik wurde begonnen und ist bis zur nächsten Woche abschließbar. Das Team hat zudem ein Plakat für die Repräsentation von Linux Logic am TGM entworfen, welches am Tag der offenen Tür präsentiert wurde. Am Event waren Batschana Gogoladze, Benjamin Eder, André Karakassoglou und Sergej Rychkov aktiv beteiligt und stellten das Projekt interessierten Besuchern vor.
                                </p>
                                <h4>Testphase und Rückmeldungen</h4>
                                <p class="m-0">
                                    Im Backend wurde der erste funktionierende Endpoint der REST-API entwickelt und befindet sich in der Testphase. Zudem wurden Tests zur Machbarkeit der Schnittstelle zwischen Frontend und Linux-Container erfolgreich abgeschlossen. Die Datenbank wurde erstellt, und eine erste Verbindung konnte hergestellt werden. Diese Fortschritte legen eine solide Grundlage für die nächste Phase der Entwicklung.
                                </p>
                            </div>
                        </Transition>
                        <Button @click="toggleContent('berichtI')" :label="showContent['berichtI'] ? 'Weniger anzeigen' : 'Weiterlesen'"></Button>
                    </TabPanel>

                    <TabPanel value="1">
                        <h3>Fortschrittsbericht II</h3>
                        <h5>24.11.2024</h5>
                        <p class="m-0">
                            Dieser Bericht bietet eine detaillierte Zusammenfassung der bisherigen Fortschritte im Diplomprojekt Linux Logic, mit besonderem Fokus auf die Literaturrecherche und die individuellen Beiträge der Teammitglieder.
                        </p>
                        <Transition name="fade">
                            <div v-if="showContent['berichtII']">
                                <h4>Literaturrecherche</h4>
                                <p class="m-0">
                                    Die intensive Literaturrecherche aller Teammitglieder bildete eine solide Grundlage für die Entwicklung der Plattform. Die Recherche umfasste Benutzerfreundlichkeit, Container-Technologien und interaktive Lernmethoden. Diese Arbeit hat die theoretischen und praktischen Anforderungen an die Plattform definiert.
                                </p>
                                <h4>Individuelle Fortschritte der Teammitglieder</h4>
                                <p class="m-0">
                                    Benjamin Eder hat das Prototyp-Frontend iterativ weiterentwickelt, Usability-Tests durchgeführt und Schwachstellen beseitigt. André Karakassoglou optimierte die Backend-Architektur und die Kommunikation zwischen Frontend und Backend. Amadeus Abdulkarim erweiterte die Terminal-Schnittstelle für realistische Linux-Übungen. Sergej Rychkov baute die Datenbankstruktur aus, und Batschana Gogoladze legte den Grundstein für die mobile App.
                                </p>
                            </div>
                        </Transition>
                        <Button @click="toggleContent('berichtII')" :label="showContent['berichtII'] ? 'Weniger anzeigen' : 'Weiterlesen'"></Button>
                    </TabPanel>

                    <TabPanel value="2">
                        <h3>Fortschrittsbericht III</h3>
                        <h5>07.12.2024</h5>
                        <p class="m-0">
                            Dieser Fortschrittsbericht behandelt die Entwicklungen der letzten Wochen, einschließlich der Fortschritte im Frontend und Backend sowie der Evaluation neuer Technologien.
                        </p>
                        <Transition name="fade">
                            <div v-if="showContent['berichtIII']">
                                <h4>Frontend</h4>
                                <p class="m-0">
                                    Die Homepage wurde UI-technisch optimiert und ist kurz vor der Fertigstellung. In der App-Entwicklung trat ein Bug in Flutter auf, der nicht gelöst werden konnte. Daher wurde Jetpack Compose als Alternative evaluiert und erste Tests durchgeführt.
                                </p>
                                <h4>Backend</h4>
                                <p class="m-0">
                                    Im Backend wurden Fortschritte bei der Datenbankstruktur erzielt, darunter die Implementierung erster User-Tabellen mit der Technologie Neon Console. Der Python-REST-API-Server liest erfolgreich Tabellen aus, und es wurden Dockerfiles erstellt, die während der Weihnachtsferien getestet und erweitert werden sollen.
                                </p>
                            </div>
                        </Transition>
                        <Button @click="toggleContent('berichtIII')" :label="showContent['berichtIII'] ? 'Weniger anzeigen' : 'Weiterlesen'"></Button>
                    </TabPanel>
                    <TabPanel value="3">
                        <h3>Fortschrittsbericht IV</h3>
                        <h5>19.12.2024 - 14.01.2025</h5>
                        <p class="m-0">
                            Über die Weihnachtsferien wurden bedeutende Fortschritte im Diplomprojekt erzielt, insbesondere in der Entwicklung der REST-API, der Datenbank sowie im Frontend und UX-Design.
                        </p>
                        <Transition name="fade">
                            <div v-if="showContent['berichtIV']">
                                <h4>Backend</h4>
                                <p class="m-0">
                                    Über die Ferien wurden Fortschritte bei der REST-API erzielt, inklusive Funktionen für Registrierung und Login. Zudem wurde die Anbindung an die Datenbank verfeinert, um Daten effizient und sicher zu verwalten.
                                </p>
                                <h4>Frontend und UX</h4>
                                <p class="m-0">
                                    Eine erweiterte Spielansicht mit Terminal-Unterstützung via xterm.js wurde entwickelt. Zusätzlich wurde am Website-Styling gearbeitet, um eine ansprechende Nutzererfahrung zu gewährleisten.
                                </p>
                                <h4>Datenbank</h4>
                                <p class="m-0">
                                    Szenarien für die Plattform wurden geschrieben, und das Schema der Datenbank überarbeitet. Die Kommunikation zwischen Sockets wurde getestet, was die Performance und Funktionalität der Plattform erheblich stärkt.
                                </p>
                            </div>
                        </Transition>
                        <Button @click="toggleContent('berichtIV')" :label="showContent['berichtIV'] ? 'Weniger anzeigen' : 'Weiterlesen'"></Button>
                    </TabPanel>
                </TabPanels>
            </Tabs>
        </div>
    </div>
</template>

<script>
import Tabs from 'primevue/tabs';
import TabList from 'primevue/tablist';
import Tab from 'primevue/tab';
import TabPanels from 'primevue/tabpanels';
import TabPanel from 'primevue/tabpanel';
import Button from 'primevue/button';

export default {
    name: 'Fortschritt',
    data() {
        return {
            showContent: {
                'berichtI': false,
                'berichtII': false,
                'berichtIII': false,
                'berichtIV': false,
            },
        };
    },
    methods: {
        toggleContent(report) {
            this.showContent[report] = !this.showContent[report];
        },
    },
    components: {
        Tabs,
        TabList,
        Tab,
        TabPanels,
        TabPanel,
        Button,
    },
};
</script>

<style scoped>
.Fortschritte {
    background-color: white;
}
.card {
    background-color: white;
    color: #3D525C;
    text-align: left;
    margin-top: 6rem;
    margin-bottom: 8rem;
}
.card >>> .p-tablist-tab-list {
    background-color: white;
    border-bottom: none;
    margin-left: 10rem;
    margin-right: 10rem;
}
.card >>> .p-tab {
    border-bottom: none !important;
}
.card >>> .p-tab:hover {
    color: #569191 !important;
}
.card >>> .p-tab-active {
    color: #569191 !important;  
}
.card >>> .p-tablist-active-bar {
    background-color: #569191 !important;
    border: 1.5px solid #569191 !important;
}
.card >>> .p-button {
    background-color: white;
    color: #569191;
    border: 1px solid #569191;
    margin-top: 1rem;
}
.card >>> .p-button:hover {
    background-color: #569191;
    color: white;
    border: 1px solid #569191;
}
.card >>> .p-tabpanels {
    background-color: white;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    margin-left: 10rem;
    margin-right: 10rem;
}

.Überschrift {
    background-color: #569191;
    color: white;
}
.m-0 {
    color: #3D525C;
}

/* VueJs Transition styles */
.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.3s ease;
}
.fade-enter-from,
.fade-leave-to {
    opacity: 0;
}
</style>
