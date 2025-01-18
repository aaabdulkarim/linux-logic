<template>
    <div class="Umfrage grid">
        <div class="oben col-12">
            <h1 style="color: white;">Umfrage Linux-Kenntnisse</h1>
            <p style="max-width: 60%;">In einer groß angelegten Studie am TGM wurden 20 Schülerinnen und Schüler nach ihren Linux-Kenntnissen und wie sie diese erlangt haben, befragt. Sie sollten auf einer Skala von 1 bis 10 ihre Linux-Kenntnisse bewerten und angeben, wie sie diese Kenntnisse erworben haben.</p>
        </div>
        <div class="chart-container col-12">
            <canvas id="linuxChart"></canvas>
        </div>
        <div class="unten col-12 md-8grid">
        <p-splitter class="splitter" >
            <!-- Linke Seite: Wissensübersicht und Verteilung -->
            <p-splitter-panel class="splitter-left" minSize="50" size="75">
                <div>
                <h2>Durchschnittliche Linux-Kenntnisse</h2>
                <p>Durchschnittlicher Wert: Der durchschnittliche Kenntnisstand in Linux liegt bei etwa 5,1, was auf ein mittleres Niveau hinweist. Dies zeigt, dass die Gruppe insgesamt eine moderate Vertrautheit mit Linux hat, mit einigen Ausreißern nach oben und unten.</p>
                <h2>Verteilung der Kenntnisse</h2>
                <p>
                Hohe Kenntnisse (8-10): Vier Personen sind sehr erfahren, was auf intensives "Learning by Doing", privates Interesse oder häufigen Einsatz von Linux hinweist.
                Mittlere Kenntnisse (4-7): Die Mehrheit hat grundlegende bis solide Kenntnisse, meist durch Schule, "Learning by Doing" und Online-Ressourcen erworben. 
                Niedrige Kenntnisse (1-3): Vier Personen haben nur geringe Kenntnisse, vermutlich durch begrenzten Kontakt im schulischen Umfeld.                </p>
                <h2>Lernmethoden</h2>
                <p>
                Learning by Doing: Diese Methode spielt eine zentrale Rolle beim Erlernen von Linux und wird von Personen mit hohen und mittleren Kenntnissen häufig genutzt.
                Schule: Der schulische Unterricht bietet eine grundlegende Einführung, reicht jedoch oft nicht für fortgeschrittene Kenntnisse aus.
                Videos/Youtube: Viele nutzen Videos, insbesondere YouTube, als Lernressource und kombinieren sie mit anderen Methoden zur Vertiefung.
                Privates Interesse: Fortgeschrittene Nutzer (8-9) haben ihre Kenntnisse oft durch privates Interesse und den Einsatz in der Freizeit erworben.
                Stack Overflow: Eine Person hob die Bedeutung von Community-Plattformen wie Stack Overflow zur Lösung spezifischer Probleme hervor.                </p>
                <h2>Besondere Beobachtungen</h2>
                <p>
                Person A (9): Dieser Teilnehmer erwarb seine hohen Kenntnisse durch die Verwendung von Linux auf einem alten Laptop, was zeigt, dass das regelmäßige Arbeiten mit älterer Hardware und alternativen Betriebssystemen zur Vertiefung des Wissens beitragen kann.
                Person B (9) Person C (8): Beide haben durch "Learning by doing" sehr hohe Kenntnisse erworben, was darauf hinweist, dass praktische Erfahrung eine der effektivsten Methoden zum Erlernen von Linux ist.
                </p>
                </div>
            </p-splitter-panel>
            <!-- Rechte Seite: Fazit -->
            <p-splitter-panel class="splitter-right" minSize="50" size="25">
                <div>
                <h2>Fazit</h2>
                <p>Die Umfrage zeigt, dass praktische Erfahrung ("Learning by doing") in Kombination mit hoher Eigeninitiative die effektivsten Methoden zur Entwicklung von Linux-Kenntnissen sind. Das Diplomprojekt "Linux Logic" greift dieses Konzept auf und ergänzt es durch spielerische Aufgaben, die eine praxisnahe und interaktive Erlernung von Linux ermöglichen. Der schulische Unterricht an einer IT-HTL bietet eine grundlegende Einführung, reicht jedoch oft nicht aus, um fortgeschrittene Kenntnisse zu erreichen. Auch Videos und Online-Ressourcen sind wertvolle Hilfsmittel für das autodidaktische Lernen.</p>
                </div>
            </p-splitter-panel>
        </p-splitter>
        </div>
    </div>
</template>

<script>
import Splitter from 'primevue/splitter';
import SplitterPanel from 'primevue/splitterpanel';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

export default {
    components: {
        'p-splitter': Splitter,
        'p-splitter-panel': SplitterPanel
    },
    mounted() {
        const canvas = document.getElementById('linuxChart');
        if (canvas) {
            const ctx = canvas.getContext('2d');
            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'],
                    datasets: [
                        {
                            label: 'Teilnehmer',
                            data: [0, 3, 2, 3, 2, 5, 2, 1, 2, 0],
                            backgroundColor: 'rgba(86, 145, 145, 0.6)',
                            borderColor: 'rgba(86, 145, 145, 1)',
                            borderWidth: 1
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: true,
                    scales: {
                        x: {
                            title: {
                                display: true,
                                text: 'Linux-Kenntnisse'
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                text: 'Teilnehmer'
                            },
                            beginAtZero: true,
                            ticks: {
                                stepSize: 1
                            },
                            suggestedMax: 7
                        }
                    },
                }
            });
        }
    }
};
</script>

<style scoped>
.oben {
    color: white;
    background-color: #569191;
    text-align: left; 
    padding-left: 20rem;
    padding-left: 10rem;
    padding-right: 20rem;
    padding-top: 8rem;
    padding-bottom: 3rem;
}
.chart-container {
    color: #569191;
    background-color:white ;
    padding-left: 8rem;
    padding-right: 8rem;
    justify-content: center;
    width: 100%;

}
.unten {
    color: #3D525C;
    background-color:white ;
    text-align: left; 
    padding-left: 20rem;
    padding-right: 20rem;
    padding-left: 10rem;
    padding-right: 10rem;
    padding-top: 3rem;
    padding-bottom: 3rem;
}
.Umfrage{
    color: #569191;
    background-color:white;
}
.splitter {
    color: #3D525C;
    background-color: white;
    border: none;

}
canvas {  
    width: 100% !important; /* Erzwingt die Anpassung an die Containerbreite */
    height: fit-content !important; /* Erzwingt die Anpassung an die Containerhöhe */
}
.splitter-left {
    padding-right: 2rem;
}
.p-splitter >>> .p-splitter-gutter {
    background: transparent; 
    border: none;
}
</style>