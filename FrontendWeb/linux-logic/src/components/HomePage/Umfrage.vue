<template>
    <div class="Umfrage grid">
        <div class="oben col-12">
            <h1 style="color: white;">Umfrage Linux-Kenntnisse</h1>
            <p >In einer groß angelegten Studie am TGM wurden 20 Schülerinnen und Schüler nach ihren Linux-Kenntnissen und wie sie diese erlangt haben, befragt. Sie sollten auf einer Skala von 1 bis 10 ihre Linux-Kenntnisse bewerten und angeben, wie sie diese Kenntnisse erworben haben.</p>
        </div>
        <div class="chart-container col-12">
            <canvas id="linuxChart"></canvas>
        </div>
        <div class="unten col-12 md-8 grid">
        <p-splitter class="splitter" >
            <!-- Linke Seite: Wissensübersicht und Verteilung -->
            <p-splitter-panel class="splitter-left"  size="75">
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
            <p-splitter-panel class="splitter-right" size="25">
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

        const gradient = ctx.createLinearGradient(0, 0, 0, 400);
        gradient.addColorStop(0, 'rgba(86, 145, 145, 1)');
        gradient.addColorStop(1, 'rgba(86, 145, 145, 0.3)');

        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'],
                datasets: [{
                    label: 'Anzahl der Teilnehmer',
                    data: [0, 3, 2, 3, 2, 5, 2, 1, 2, 0],
                    backgroundColor: 'rgba(86, 145, 145, 1)',
                    borderColor: 'rgba(86, 145, 145, 1)',
                    borderWidth: 2,
                    borderRadius: 8, 
                    hoverBackgroundColor: 'rgba(86, 145, 145, 0.8)'
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                // aspectRatio: 1.5, 
                plugins: {
                    legend: {
                        display: false  
                    },
                    tooltip: {
                        backgroundColor: '#569191',
                        titleColor: '#fff',
                        bodyColor: '#fff',
                        borderWidth: 1,
                        borderColor: '#fff'
                    }
                },
                scales: {
                    x: {
                        title: {
                            display: true,
                            text: 'Linux-Kenntnisse',
                            color: '#3D525C',
                            font: { weight: 'bold' }
                        },
                        grid: {
                            display: false 
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: 'Anzahl der Teilnehmer',
                            color: '#3D525C',
                            font: { weight: 'bold' }
                        },
                        beginAtZero: true,
                        ticks: {
                            stepSize: 1,
                            color: '#3D525C'
                        },
                        grid: {
                            color: 'rgba(86, 145, 145, 0.2)'
                        }
                    }
                },
                animation: {
                    duration: 1000,
                    easing: 'easeInOutQuart'
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

.chart-container {
    color: #569191;
    background-color: white;
    padding: 2rem; 
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    max-width: 900px;
    margin: auto; 
}

canvas {  
    width: 100% !important; 
    height: fit-content !important; 
    max-height: 400px; 
}


.splitter-left {
    padding-right: 2rem;
}
.p-splitter >>> .p-splitter-gutter {
    display: none;
}
@media (max-width: 768px) {
    .splitter {
        display: flex;
        flex-direction: column;
        gap: 2rem; 
    }

    .splitter-left,
    .splitter-right {
        width: 100%;
        padding-top: 0;
    }

    .splitter-left {
        padding-bottom: 6rem;
    }

    /* Verschiebe den rechten Bereich (Fazit) unter den linken Bereich */
    .splitter-right {
        order: 2;  /* Verschiebt den rechten Bereich nach unten */
    }

    /* Optional: Reduziere die Innenabstände für mobile Geräte */
    .oben, .unten {
        padding-left: 5rem;
        padding-right: 5rem;
        padding-top: 1rem;
        padding-bottom: 2rem;
    }

    .chart-container {
        padding: 1rem;
        max-width: 100%;
    }

    canvas {
        max-height: 300px; /* Größer auf Mobilgeräten */
    }
}
</style>