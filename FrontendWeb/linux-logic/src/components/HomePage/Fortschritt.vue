<template>
    <div class="Fortschritte grid">
        <div class="Überschrift col-12">
            <h1 style="color: white;">Fortschrittsberichte</h1>
        </div>
        <div class="card col-12 ">
            <Tabs v-model="activeIndex">
        <div class="card col-12">
            <Tabs value="0">
                <TabList>
                    <Tab v-for="(bericht, index) in berichte" :key="index" :value="index">
                        <a :href="bericht.link" target="_blank">
                            Fortschrittsbericht {{ index + 1 }}
                        </a>

                    </Tab>
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
            },
            activeIndex: 0,

            berichte: [
                {
                    "berichtName": "Bericht 1",
                    "datum": "09.09 - 13.09",
                    "text": `Im Zeitraum vom 9. bis zum 13. September konnten wir wesentliche Fortschritte in der Planung und Organisation unserer Diplomarbeit erzielen. In dieser ersten Arbeitswoche haben wir den Diplomarbeitsantrag nahezu vollständig ausgearbeitet und die Aufgabenverteilung innerhalb des Teams klar definiert. Die Arbeit wurde in drei Hauptkategorien unterteilt: App-Entwicklung (Frontend), Web-Entwicklung (Frontend) und Backend-Entwicklung. Durch diese klare Strukturierung konnten wir sicherstellen, dass alle relevanten Teilbereiche abgedeckt sind.
Jedes Projektmitglied hat sich anschließend intensiv mit seinem jeweiligen Aufgabenbereich auseinandergesetzt, wobei verschiedene Methoden der Wissensaneignung zum Einsatz kamen, beispielsweise Recherchen, Lehrvideos und technische (online) Dokumentationen. Darüber hinaus haben wir eine Domäne für diese offizielle Website erworben, über die wir den aktuellen Projektstatus und weitere Entwicklungen transparent kommunizieren können.`,
                    "link": "https://linux-logic.com/f/fortschrittsbericht-0909-1309"

                },
                {
                    "berichtName": "Bericht 2",
                    "datum": "08.10. - 22.10.",
                    "text": `Im genannten Zeitraum hat das Linux-Logic Team unter anderem an der Erstellung der Machbarkeitsstudie gearbeitet, die eine zentrale Rolle dabei spielt, potenzielle Risiken frühzeitig zu identifizieren. In unserem Fall zeigt sich die technische Machbarkeit des Projekts als unproblematisch, da unser Vorhaben auf realistischen Annahmen basiert und weder als Proof-of-Concept noch als experimentelles Projekt angesehen wird. Zudem verfügen wir über fundierte Kenntnisse in den relevanten Technologien, was das Risiko technischer Problematik weiter minimiert.
Derzeit laufen Tests an den verschiedenen Terminals, wobei in der App-Entwicklung das xterm.dart und in der Web-Entwicklung das PrimeVue-Terminal evaluiert werden. Für diese Woche sind außerdem die Erstellung eines Plakats sowie ein Zwischenstand der offiziellen Website vorgesehen, um den Fortschritt des Projekts übersichtlich darzustellen und weitere Entwicklungen mitzuteilen.`,
                    "link" : "https://linux-logic.com/f/fortschrittsbericht-0810-2210"
                },
                {
                    "berichtName": "Bericht 3",
                    "datum": "29.10. - 12.11.",
                    "text": `Seit dem 29.10 wurde ein vollständiges Mockup für das App Frontend designed und besprochen. Der Prototyp mit der Logik wurde ebenso angefangen, sodass er bis zur nächsten Woche fertiggestellt werden kann. Außerdem hat das Team geschafft, ein Plakat zu designen welches Linux Logic im TGM Repräsentiert und es für die Besucher der Tag der offenen Tür aufgehängt. Am Tag der offenen Tür sorgten Batschana Gogoladze, Eder Benjamin, André Karakassoglou und Sergej Rychkov für eine Vorstellung unseres Projekts im Sinne der Besucher des Tgms und präsentierten Vorort unseren aktuellen Stand.
Im Backend tat sich etwas in der praktischen Machbarkeit, da schon einmal ein funktionierender Endpoint der REST API entwickelt wurde welcher sich schon in der Testphase befindet. Neben der REST API wurde auch an der Schnittstelle zwischen dem Frontend und dem Linux Container gearbeitet. Versuche erbrachten positive Testergebnisse auf Machbarkeit der Schnittstelle welche beweisen, dass eine Frontend Verbindung zum Backend für die Verarbeitung bis zum Docker Linux Container möglich ist. Die Datenbank wurde auch erstellt und eine Verbindung zu dieser aufgebaut.`,
                    "link" : "https://linux-logic.com/f/fortschrittsbericht-1011"
                },
                {
                    "berichtName": "Bericht 4",
                    "datum": "12.11 - 19.11",
                    "text": `Im Rahmen des Diplomprojekts Linux Logic hat das Team bedeutende Fortschritte in verschiedenen Teilbereichen erzielt. Die bisherige Arbeit war geprägt von intensiver Zusammenarbeit und individuellen Spezialisierungen, um die angestrebte webbasierte Lernplattform für Linux erfolgreich zu realisieren. Die folgenden Abschnitte geben einen detaillierten Überblick über die Ergebnisse der einzelnen Teammitglieder sowie die gemeinschaftlich erarbeiteten Beiträge.
Literaturrecherche 
Alle Mitglieder des Projektteams waren intensiv an der Durchführung der Literatursichtung beteiligt. Dieser zentrale Arbeitsschritt diente der Erarbeitung einer fundierten Grundlage für das Konzept der Plattform. Die Recherche umfasste Themen wie Benutzerfreundlichkeit, Container-Technologien, interaktive Lernmethoden sowie bestehende Lösungen zur Schulung von Linux. Das umfangreiche Material wurde systematisch analysiert, um die theoretischen und praktischen Anforderungen an die Plattform zu definieren. Dieser gemeinsame Aufwand hat die Basis für die nachfolgenden Entwicklungsphasen gestärkt.
Individuelle Fortschritte der Teammitglieder 
Benjamin Eder – Weiterentwicklung der Prototypen
Benjamin Eder konzentrierte sich auf die iterative Weiterentwicklung des Prototyps der Benutzeroberfläche. Ziel war es, ein funktionales und zugleich ansprechendes Frontend zu schaffen, das eine intuitive Navigation gewährleistet. Neben der grafischen Gestaltung wurden auch Usability-Tests durchgeführt, um Schwachstellen zu identifizieren und Verbesserungen zu implementieren.
Andre Karakassoglou – Arbeiten an der Website
Andre Karakassoglou hat die Struktur und Funktionalität der Website weiterentwickelt. Dabei standen die Implementierung einer stabilen Backend-Architektur sowie die Optimierung der Kommunikationsschnittstellen zwischen Frontend und Backend im Fokus. Durch diese Arbeiten konnte die technische Grundlage der Plattform erheblich gefestigt werden.
Amadeus Abdulkarim – Erweiterung der Terminal-Schnittstelle
Amadeus Abdulkarim hat die Terminal-Schnittstelle erweitert und für die Interaktion funktionsfähig gemacht. Diese Komponente ist essenziell für die Simulation realistischer Linux-Umgebungen, in denen Nutzer praktische Übungen absolvieren können. Die Schnittstelle wurde so konzipiert, dass sie eine reibungslose Integration in die Plattform ermöglicht.
Sergej Rychkov – Aufbau und Erweiterung der Datenbanken
Sergej Rychkov war für die Einrichtung und Weiterentwicklung der Datenbankinfrastruktur verantwortlich. Der Fokus lag auf der Erstellung eines skalierbaren und sicheren Systems zur Speicherung von Nutzer- und Lernfortschrittsdaten. Dies gewährleistet eine zuverlässige Datengrundlage für die Analyse und Personalisierung der Lerninhalte.
Batschana Gogolazde – Entwicklung der mobilen App
Batschana Gogolazde hat das Grundgerüst der mobilen Anwendung erstellt, mit besonderem Schwerpunkt auf der Anmelde- und Registrierungsseite. Die App wurde so entworfen, dass sie sich nahtlos in das Gesamtkonzept der Plattform einfügt und den Nutzern eine flexible Lernumgebung bietet.`,
                    "link": "https://linux-logic.com/f/fortschrittsbericht-17112024"

                },
                {
                    "berichtName": "Bericht 5",
                    "datum": "10.12 – 19.12",
                    "text": `Seit dem 10.12. bis zum 19.12. gab es weitere Entwicklungen an der Backend- und Frontend-Komponente.
Frontend-Entwicklung 
Die Homepage, die als Einführung in unsere Webseite dient, wurde anwenderfreundlich und UI-technisch ansprechend gestaltet. Sie befindet sich kurz vor der Fertigstellung.
In der App-Entwicklung trat ein Bug in Flutter auf, der bislang nicht behoben werden konnte. Trotz intensiver Recherche auf GitHub und anderen Plattformen konnte keine konkrete Lösung gefunden werden. Daher wurde als Alternative Jetpack Compose in Betracht gezogen und erste Experimente durchgeführt.
Backend-Entwicklung 
Im Backend gab es Fortschritte bei der Erstellung der Datenbank-Tabellen und Entities. Die Datenbanktechnologie Neon Console wurde ausgewählt, und die ersten User-Tabellen sind bereits definiert.
Der Python-REST-API-Server kann erfolgreich eine Verbindung zur Datenbank herstellen und Tabellen auslesen. Zusätzlich wurden Dockerfiles erstellt, die in den Weihnachtsferien für die Implementierung und das Testen weiterer Funktionen genutzt werden.`,
                    "link" : "https://linux-logic.com/f/fortschrittsbericht-1012-%E2%80%93-1912"
                },
                {
                    "berichtName": "Bericht 6",
                    "datum": "19.12. bis zum 14.01.",
                    "text": `Über die Weihnachtsferien war einiges geplant für die Realisierung des Diplomprojekts Linux Logic. Folgenden Fortschritt haben wir im Zeitraum vom 19.12. bis zum 14.01. erreicht:
Backend: REST API mit Datenbankanbindung, Register Funktion, login
Web Frontend: Spielansicht mit erweitertem terminal xtermjs
UX: Website styling
Datenbank: Szenarien geschrieben, Schema überarbeitet`,
                    "link": "https://linux-logic.com/f/fortschrittsbericht-1912---1401"
                }
            ]

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

.card>>>.p-tablist-tab-list {
    background-color: white;
    border-bottom: none;
    margin-left: 10rem;
    margin-right: 10rem;
}

.card>>>.p-tab {
    border-bottom: none !important;
}

.card>>>.p-tab:hover {
    color: #569191 !important;
}

.card>>>.p-tab-active {
    color: #569191 !important;
}

.card>>>.p-tablist-active-bar {
    background-color: #569191 !important;
    border: 1.5px solid #569191 !important;
}

.card>>>.p-button {
    background-color: white;
    color: #569191;
    border: 1px solid #569191;
    margin-top: 1rem;
}

.card>>>.p-button:hover {
    background-color: #569191;
    color: white;
    border: 1px solid #569191;
}

.card>>>.p-tabpanels {
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
