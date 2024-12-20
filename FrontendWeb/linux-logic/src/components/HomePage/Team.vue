<template>

    <div class="TeamOben grid" >
        <h1 style="color: white;">Team</h1>
    </div>

    <div class="animated-text">
        Test
    </div>
    <div class="TeamUnten grid">
        <div class="col-12 cards-container">
            <Card 
                style="width: 20rem; overflow: hidden;" 
                v-for="member in teamMembers" 
                :key="member.id"
            >
                <template #header>
                    <img :alt="'Bild von ' + member.name" :src="member.image" />
                </template>
                <template #title><h5>{{ member.name }}</h5></template>
                <template #subtitle>{{ member.role }}</template>
                <template #content>
                    <p class="m-0">
                        {{ member.description }}
                    </p>
                </template>
                <template #footer>
                    <div class="flex gap-4 mt-1">
                        <Button label="Kontakt" severity="secondary" outlined class="w-full" />
                    </div>
                </template>
            </Card>
        </div>
    </div>
</template>

<script>
import Card from 'primevue/card';
import Button from 'primevue/button';


export default {
    components: {
        Card,
        Button
    },
    data() {
        return {
            teamMembers: [
                {
                    id: 1,
                    name: "Amadeus Abdulkarim",
                    role: "Projektleiter",
                    image: require("../../assets/AA.webp"),
                    description: "Organisation und Koordination des gesamten Projekts.",
                },
                {
                    id: 2,
                    name: "Benjamin Eder",
                    role: "Frontend-Design",
                    image: require("../../assets/BE.webp"),
                    description: "Gestaltung der Benutzeroberfläche und Optimierung der Nutzererfahrung.",
                },
                {
                    id: 3,
                    name: "Batschana Gogoladze",
                    role: "Mobile-App Entwicklung",
                    image: require("../../assets/BG.webp"),
                    description: "Entwicklung und Implementierung der Mobile-App.",
                },
                {
                    id: 4,
                    name: "André Karakassoglou",
                    role: "Web-App-Entwicklung",
                    image: require("../../assets/AK.webp"),
                    description: "Entwicklung und Implementierung der Web-App.",
                },
                {
                    id: 5,
                    name: "Sergey Rychkov",
                    role: "Backend-Entwicklung",
                    image: require("../../assets/SR.webp"),
                    description: "Entwicklung der Backend-Infrastruktur und der Datenbankanbindungen.",
                },
            ],
        };
    },
};
</script>

<style scoped>

.TeamOben {
    justify-content: center;
    text-align: center;
    background-color: #569191;
    padding: 2rem 0;
}
.TeamUnten {
    background-color: white;
    padding: 2rem 0;
    position:static;
}

.cards-container {
    display: flex;
    gap: 2rem;
    justify-content: center;
    flex-wrap: nowrap;
    padding: 2rem;
    background-color: white;
}

.cards-container img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.cards-container >>> .p-card-body {
    background-color: white; 
    color: #3D525C; 
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100%;
}
.cards-container >>> .p-button {
    border: 1px solid #3D525C; 
    color: #3D525C; 
    background-color: transparent; 
}
.cards-container >>> .p-button:hover {
    border: 1px solid white !important;
    color: white !important; 
    background-color: #3D525C !important; 
    
}
.cards-container .m-0{
    font-size: 1rem;
}

.m-0 {
    margin: 0;
}

@media (max-width: 1200px) {
    .cards-container {
        flex-wrap: wrap;
    }
}
</style>
