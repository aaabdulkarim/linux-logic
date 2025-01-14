<template>
    <div class="Fortschritte grid">
        <div class="Überschrift col-12">
            <h1 style="color: white;">Fortschrittsberichte</h1>
        </div>
        <div class="card col-12 ">
            <Tabs value="0">
                <TabList>
                    <Tab value="0">Fortschrittsbericht I</Tab>
                    <Tab value="1">Fortschrittsbericht II</Tab>
                </TabList>
                <TabPanels>

                    <TabPanel value="0">
                        <h3>Fortschrittsbericht I</h3>
                        <h5>Datum Von Bis</h5>
                        <p class="m-0">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. 
                        </p>
                        <div v-if="showContent['berichtI']">
                            <h4>Literaturrecherche</h4>
                            <p class="m-0">
                                Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. Nullam varius, turpis et commodo pharetra, est eros bibendum elit, nec luctus magna felis sollicitudin mauris.Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. 
                            </p>
                            <h4>Allgemeine Fortschritte</h4>
                            <p class="m-0">
                                Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. 
                            </p>
                        </div>
                        <Button @click="toggleContent('berichtI')" :label="showContent['berichtI'] ? 'Weniger anzeigen' : 'Weiterlesen'"></Button>
                    </TabPanel>

                    <TabPanel value="1">
                        <h3>Fortschrittsbericht II</h3>
                        <h5>Datum Von Bis</h5>
                        <p class="m-0">
                            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
                            Excepteur sint occaecat cupidatat non proident. Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. 
                            Sed libero elit, faucibus eu eleifend eget, vulputate et magna. Curabitur pretium tincidunt lacus. 
                        </p>
                        
                        
                        <div v-if="showContent['berichtII']">
                            <h4>System-Design und Architektur</h4>
                            <p class="m-0">
                                Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. Sed libero elit, faucibus eu eleifend eget, vulputate et magna. Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. Sed libero elit, faucibus eu eleifend eget, vulputate et magna. Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. Sed libero elit, faucibus eu eleifend eget, vulputate et magna.
                            </p>
                            <h4>Testphase und Rückmeldungen</h4>
                            <p class="m-0">
                                Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. Sed libero elit, faucibus eu eleifend eget, vulputate et magna. Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. Sed libero elit, faucibus eu eleifend eget, vulputate et magna. Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. Sed libero elit, faucibus eu eleifend eget, vulputate et magna.
                            </p>
                        </div>
                        <Button @click="toggleContent('berichtII')" :label="showContent['berichtII'] ? 'Weniger anzeigen' : 'Weiterlesen'"></Button>
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
        Button
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
    color: #3D525C
}

</style>