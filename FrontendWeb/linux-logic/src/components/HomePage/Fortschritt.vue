<template>
    <div class="Fortschritte grid">
        <div class="Überschrift col-12">
            <h1 style="color: white;">Fortschrittsberichte</h1>
        </div>
        <div class="card col-12">
            <Tabs v-model="activeIndex" scrollable>
                <TabList>
                    <Tab v-for="(bericht, index) in berichte" :key="index" :value="index">
                        Fortschrittsbericht {{ index + 1 }}
                    </Tab>
                </TabList>
                <TabPanels>
                    <TabPanel v-for="(bericht, index) in berichte" :key="index" :value="index">
                        <h3>{{ bericht.berichtName }}</h3>
                        <h5>{{ bericht.datum }}</h5>
                        <p>
                            {{ bericht.text }}
                        </p>
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
                    "datum": "19.12. - 14.01.",
                    "text": `Fortschrittsbericht Linux Logic (Zeitraum: 19.12. bis 14.01.)

Im Rahmen des Diplomprojekts "Linux Logic" wurden während der Weihnachtsferien signifikante Fortschritte in den Bereichen App-Frontend, Backend, Web-Frontend, UX-Design und Datenbankmanagement erzielt. Nachfolgend eine Übersicht über den Fortschritt:

App-Frontend:
Die Entwicklung der mobilen Applikation "Linux Logic" konnte trotz anfänglicher technischer Herausforderungen erfolgreich vorangetrieben werden. Aufgrund eines schwerwiegenden Kompilierungsfehlers in Flutter wurde entschieden, die App auf Jetpack Compose umzustellen. Die Umsetzung umfasst inzwischen eine vollständig funktionale Startseite sowie Frontend-Komponenten für die Benutzerauthentifizierung (Registrierung und Anmeldung). Darüber hinaus wurde das Grundgerüst der Hauptseite erstellt, welches verschiedene Navigationsmöglichkeiten, darunter eine Top-App-Bar, Bottom-App-Bar und ein Navigation Drawer, integriert.

Backend:
Parallel zur Frontend-Entwicklung wurde das Backend als REST-API mit Datenbankanbindung weiterentwickelt. Neben allgemeinen Schnittstellen wurde auch die Funktion zur Benutzerregistrierung implementiert. Diese bildet eine essenzielle Basis für die vollständige Nutzerverwaltung und das reibungslose Zusammenspiel mit dem Frontend.
Web-Frontend:
Im Web-Frontend erfolgte die Darstellung der einzelnen Kurse (Level). Ein wesentlicher Fortschritt war die Integration eines webbasierten Terminals, welches durch die Einbindung der gut dokumentierten Drittanbieter-Library xterm.js realisiert wurde. Diese Komponente bildet (im Web) einen zentralen Bestandteil für interaktive Übungen innerhalb der Lernplattform.

UX-Design:
Im Bereich User Experience (UX) Design wurde das Styling des Web-Frontends weiter optimiert. Durch gezielte Anpassungen und Designentscheidungen konnte die Benutzerfreundlichkeit sowie das visuelle Erscheinungsbild der Plattform verbessert werden. Das Ziel besteht darin, ein ansprechendes Nutzererlebnis für die Webseite zu gewährleisten.

Datenbankmanagement:
Für die spielerischen Szenarien der Lernplattform wurde die Datenbankstruktur überarbeitet und erweitert. Dies beinhaltete sowohl die Modellierung eines neuen Datenbankschemas als auch die Implementierung von Mechanismen zur Kommunikation zwischen Sockets. Die neu entwickelten Szenarien bilden die Basis für praxisnahe und interaktive Lernaufgaben.`
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

::v-deep .card {
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

.card >>> .p-tabpanel {
    color: #3D525C;
}

/* VueJs Transition styles */
.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.5s ease;
}
.fade-enter-from,
.fade-leave-to {
    opacity: 0;
}
@media (max-width: 768px) {
    .card>>>.p-tablist-tab-list {
        margin-left: 0.5rem;
        margin-right: 0.5rem;
    }

    .card>>>.p-tabpanels {
        margin-left: 0.5rem;
        margin-right: 0.5rem;
    }

    .card>>>.p-button {
        width: 100%;
    }
}

@media (max-width: 480px) {
    .Überschrift h1 {
        font-size: 2rem; /* Noch kleinere Schriftgröße für sehr kleine Bildschirme */
    }
}
</style>
