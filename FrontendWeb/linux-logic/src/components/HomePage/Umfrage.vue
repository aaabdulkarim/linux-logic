<template>
    <div class="Umfrage grid">
        <div class="oben col-12">
            <h1 style="color: white;">Umfrage Linux-Kenntnisse</h1>
            <p style="max-width: 40rem;">In einer groß angelegten Studie am TGM wurden 20 Schülerinnen und Schüler nach ihren Linux-Kenntnissen und wie sie diese erlangt haben, befragt. Sie sollten auf einer Skala von 1 bis 10 ihre Linux-Kenntnisse bewerten und angeben, wie sie diese Kenntnisse erworben haben.</p>
        </div>
        <div class="chart-container col-12">
            <canvas id="linuxChart"></canvas>
        </div>
        <div class="unten col-12 grid">
        <p-splitter class="splitter" >
            <!-- Linke Seite: Wissensübersicht und Verteilung -->
            <p-splitter-panel class="splitter-left" minSize="50" size="75">
                <div>
                <h2>Durchschnittliche Linux-Kenntnisse</h2>
                <p>Durchschnittlicher Wert: Der durchschnittliche Kenntnisstand in Linux liegt bei etwa 5,1, was auf ein mittleres Niveau hinweist. Dies zeigt, dass die Gruppe insgesamt eine moderate Vertrautheit mit Linux hat, mit einigen Ausreißern nach oben und unten.</p>

                <h2>Verteilung der Kenntnisse</h2>
                <p>
                    Hohe Kenntnisse (8-10): Vier Personen haben einen hohen Kenntnisstand (8 oder höher), was darauf hindeutet, dass sie wahrscheinlich sehr erfahren im Umgang mit Linux sind. Dies könnte auf intensives "Learning by doing", privates Interesse oder den häufigen Einsatz von Linux hinweisen.
                    Mittlere Kenntnisse (4-7): Die meisten Personen befinden sich im mittleren Bereich (4-7). Sie haben grundlegende bis solide Kenntnisse, meist erworben durch Schule, Learning by doing und Online-Ressourcen wie Videos und Foren.
                    Niedrige Kenntnisse (1-3): Vier Personen haben geringe Kenntnisse (2 oder 3), was darauf hinweist, dass sie wahrscheinlich nur begrenzten Kontakt mit Linux hatten, meist in einem schulischen Umfeld.
                </p>
                
                <h2>Lernmethoden</h2>
                <p>
                    Learning by Doing: Dies ist die am häufigsten genannte Methode, was darauf hinweist, dass praktisches Arbeiten und eigenständiges Erforschen eine wichtige Rolle beim Erlernen von Linux spielen. Es wird sowohl von Personen mit hohen als auch mittleren Kenntnissen oft erwähnt.
                    Schule: Die Schule wird häufig als Lernquelle genannt, vor allem bei Personen mit mittleren bis niedrigen Kenntnissen. Dies deutet darauf hin, dass der schulische Unterricht eine grundlegende Einführung in Linux bietet, aber möglicherweise nicht tief genug geht, um hohe Kenntnisse zu vermitteln.
                    Videos/Youtube: Viele Teilnehmer nutzen Videos, oft wird Youtube angegeben, als Lernressource, was die Popularität visueller und praktischer Lernmethoden betont. Dies wird oft mit anderen Methoden kombiniert, um das Verständnis zu vertiefen.
                    Privates Interesse: Einige der fortgeschritteneren Benutzer (8-9) haben ihre Kenntnisse durch privates Interesse und den Einsatz von Linux in ihrer Freizeit erworben, was auf eine höhere Motivation und Eigeninitiative hinweist.
                    Stack Overflow: Eine Person erwähnte die Nutzung von Stack Overflow, was auf die Bedeutung von Community-gestützten Plattformen für das Lösen spezifischer Probleme hinweist.
                </p>
                
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
                <p>Die Umfrage zeigt, dass praktische Erfahrung ("Learning by doing") in Kombination mit einem hohen Maß an Eigeninitiative die effektivsten Methoden zur Entwicklung von Linux-Kenntnissen sind. Der schulische Unterricht bietet eine grundlegende Einführung, reicht jedoch oft nicht aus, um fortgeschrittene Kenntnisse zu erreichen. Auch Videos und Online-Ressourcen sind wertvolle Hilfsmittel für das autodidaktische Lernen.</p>
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
                    }
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
    text-align: left; 
    padding-left: 20rem;
    padding-right: 20rem;
    padding-left: 10rem;
    padding-right: 10rem;
    padding-top: 3rem;
    padding-bottom: 3rem;
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
canvas {
    max-width: 100%; 
    height: auto;    
}
.Umfrage{
    color: #569191;
    background-color:white ;;
}
.splitter {
    color: #3D525C;
    background-color: white;
    border: none;

}
.splitter-left {
    padding-right: 2rem;
}
.p-splitter >>> .p-splitter-gutter {
    background: transparent; 
    border: none;
}
</style>