import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import PrimeVue from 'primevue/config';
import Aura from '@primevue/themes/aura';

import '../node_modules/primeflex/primeflex.css';
import 'primeicons/primeicons.css';

const app = createApp(App);

app.use(PrimeVue, {
    theme: {
        preset: Aura
    }, 
    unstyled: false
});

app.use(router);

app.mount('#app');
