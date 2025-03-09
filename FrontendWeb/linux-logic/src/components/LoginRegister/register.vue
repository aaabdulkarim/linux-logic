    <template>
        <div class="register-page grid" :style="backgroundStyle">
        <div class="register-container">
            <h2>Registrieren</h2>
    
            <form @submit.prevent="onSubmit">
            <div class="name">
                <label for="name"><h5>Vorname</h5></label>
                <label for="name"><h5>Nachname</h5></label>
            </div>
            <div class="p-field-name">
                <InputText id="name" v-model="name"/>
                <InputText id="name" v-model="name"/>
            </div>
            <div class="p-field">
                <label for="email"><h5>Email</h5></label>
                <InputText id="email" v-model="email"/>
                <label for="username"><h5>Benutzername</h5></label>
                <InputText id="username" v-model="username"/>
                <label for="password"><h5>Passwort</h5></label>
                <Password id="password" v-model="password" :feedback="false" toggleMask/>
                <label for="password_2"><h5>Passwort bestätigen</h5></label>
                <Password id="password_2" v-model="password_2" :feedback="false" toggleMask/>
            </div>
            <div class="register-actions">
                <div class="stay-logged-in">
                <Checkbox v-model="stayLoggedIn" id="stayLoggedIn" />
                <label for="stayLoggedIn">Angemeldet bleiben</label>
                
                </div>
            </div>
                <Button @click="create" label="Registrieren" />
            </form>
    
            <div class="login-link">
            <span>Haben Sie bereits einen Account? </span>
            <router-link to="/login">Anmelden</router-link>
            </div>
        </div>
        </div>
    </template>
    
    <script>
    import  InputText  from 'primevue/inputtext';
    import  Password  from 'primevue/password';
    import  Checkbox  from 'primevue/checkbox';
    import  Button  from 'primevue/button';
    import axios from 'axios';
    
    export default {
        components: { 
        InputText, 
        Password, 
        Checkbox, 
        Button 
        },
        data() {
        return {
            email: '',
            password: '',
            stayLoggedIn: false,
            base_url : "http://10.0.107.220:8001"
        };
        },
        computed: {
        backgroundStyle() {
            return {
            height: '100vh',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            };
        }
        },
        methods: {
        onSubmit() {
            console.log(this.email);
        },
        create() {
            axios.post('http://');

        },
    },
    };
    </script>
    
    <style scoped>
    .register-page {
        min-height: 100vh;
        padding-bottom: 69rem;
        background-color: #569191;
        background-image: url('@/assets/abstract_background_3.webp');
        background-size: cover;
        background-position: top;
        background-repeat: no-repeat;
    }
    
    .register-container {
        background: rgba(255, 255, 255, 0.5);
        margin-top: 2rem;
        padding: 1rem;
        border-radius: 1rem;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        width: 40%;
        min-width: 420px;
    }
    .name {
        margin-bottom: -1rem;
        display: flex;
        flex-direction: row;
        gap: 1.6rem;
    }
    .name label {
        width: 50%;
        display: flex;
        align-items: left;
    }
    .p-field-name {
        display: flex;
        flex-direction: row; 
        align-items: flex-start; 
        margin-bottom: 0rem;
        gap: 1.6rem;
    }
    .p-field-name label {
        margin-bottom: -2rem;
    }
    .p-field-name input {
        padding: 0.8rem;
        background: rgba(255, 255, 255, 0.9);
        color: #3D525C;
        border: none;
        width: 50%;
    }
    .p-field-name input:focus {
        outline: none;
        border: none;
        box-shadow: 0 0 0 1px #569191;
    }
    .p-field {
        display: flex;
        flex-direction: column; 
        align-items: flex-start; 
        margin-top: 0.3rem;
        margin-bottom: 0.3rem;
    }
    .p-field label {
        margin-bottom: -1rem;
    }
    .p-field input {
        padding: 0.8rem;
        background: rgba(255, 255, 255, 0.9);
        color: #3D525C;
        border: none;
        width: 100%;
    }
    .p-field input:focus {
        outline: none;
        border: none;
        box-shadow: 0 0 0 1px #569191;
    }
    ::v-deep .p-password {
        width: 100%;
    }
    ::v-deep .p-password .p-inputtext {
        padding: 0.8rem;
        background: rgba(255, 255, 255, 0.9);
        color: #3D525C;
        border: none;
        width: 100%;
    }
    ::v-deep .p-password input:focus {
        outline: none;
        border: none;
        box-shadow: 0 0 0 1px #569191; /* Fokusrahmen */
    }
    
    .register-actions {
        font-family: 'Ubuntu', monospace; 
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 1rem;
        margin-top: 3rem;
    }
    .p-checkbox {
        margin-right: 0.5rem;
    }
    ::v-deep .p-checkbox .p-checkbox-box {
        width: 20px;
        height: 20px;
        border-radius: 6px;
        border: 1px solid #569191;
        background-color: #fff;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    
    button {
        margin-right: 1rem;
        background-color: #569191;
        color: white;
        border: none;
        height: 46px;
        width: 100%;
    }
    button:hover {
        border: none !important;
        background-color: #7eb9b9 !important;
        color: white !important;
    }
    
    .forgot-password {
        font-family: 'Ubuntu', monospace; 
        color: #569191;
        text-decoration: none;
    }
    .forgot-password:hover {
        text-decoration: underline;
    }
    .login-link {
        font-family: 'Ubuntu', monospace; 
        text-align: center;
        margin-top: 1rem;
    }
    .login-link a {
        color: #569191;
        text-decoration: none;
    }
    .login-link a:hover {
        text-decoration: underline;
    }
    </style>
    