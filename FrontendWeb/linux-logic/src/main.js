import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import PrimeVue from 'primevue/config';
import Dialog from 'primevue/dialog';
import Checkbox from 'primevue/checkbox';
import InputText from 'primevue/inputtext';
import Password from 'primevue/password';
import Button from 'primevue/button';
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
app.component('Dialog', Dialog);
app.component('Checkbox', Checkbox);
app.component('InputText', InputText);
app.component('Password', Password);
app.component('Button', Button);


app.mount('#app');
