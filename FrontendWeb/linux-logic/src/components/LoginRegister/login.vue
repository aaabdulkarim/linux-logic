<template>
  <div class="login-page grid" :style="backgroundStyle">
    <div class="login-container">
      <h2>Anmelden</h2>

      <form @submit.prevent="onSubmit">
        <div class="p-field">
          <label for="email"><h5>Benutzername</h5></label>
          <InputText id="email" v-model="email"/>
          <label for="password"><h5>Passwort</h5></label>
          <Password id="password" v-model="password" :feedback="false" toggleMask/>
          <label for="code"><h5>Access Code (nicht notwendig)</h5></label>
          <Password id="code" v-model="code" :feedback="false" toggleMask/>
        </div>

        <div class="login-actions">
          <div class="stay-logged-in">
            <Checkbox v-model="stayLoggedIn" id="stayLoggedIn" />
            <label for="stayLoggedIn">Angemeldet bleiben</label>
          </div>
          <div class="forgot-password">
            <router-link to="/forgot-password" class="forgot-password">Passwort Vergessen</router-link>
          </div>
        </div>
        <Button @click="check" label="Anmelden" />
      </form>

      <div class="register-link">
        <span>Haben Sie keinen Account? </span>
        <router-link to="/register">Registrieren</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import InputText from 'primevue/inputtext';
import Password from 'primevue/password';
import Checkbox from 'primevue/checkbox';
import Button from 'primevue/button';
import axios from 'axios';
import api from "@/api";

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
      base_url: "http://localhost:8000",
      response: false,
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
      this.check();
    },
    check() {
      api.post('/login', {
        
          username: this.email,
          password: this.password,
        
      })
      .then((response) => {
        console.log(response.data);
        if (response.status === 200) {
          this.$router.push('/auswahl');
        } else {
          alert("Benutzername oder Passwort ist falsch!");
          this.email = '';
          this.password = '';
        }
      })
      .catch((error) => {
        console.error('Fehler bei der Anmeldung:', error);
      });
    }
  },
  mounted() {
    const user = JSON.parse(localStorage.getItem('user') || sessionStorage.getItem('user'));
    if (user) {
      this.email = user.email;
      this.stayLoggedIn = !!localStorage.getItem('user');
    }
  }
};
</script>

<style scoped>
.login-page {
  min-height: 100vh;
  padding-bottom: 69rem;
  background-color: #569191;
  background-image: url('@/assets/abstract_background_3.webp');
  background-size: cover;
  background-position: top;
  background-repeat: no-repeat;
}

.login-container {
  margin-top: 2rem;
  background: rgba(255, 255, 255, 0.5);
  padding: 1rem;
  border-radius: 1rem;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
  width: 40%;
  min-width: 420px;
}

.p-field {
  display: flex;
  flex-direction: column; 
  align-items: flex-start; 
  margin-top: 1rem;
  margin-bottom: 1rem;
}
.p-field label {
    margin-bottom: -1rem;
}

.p-field input {
  padding: 0.8rem;
  background: rgba(255, 255, 255, 0.69);
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
  background: rgba(255, 255, 255, 0.5);
  color: #3D525C;
  border: none;
  width: 100%;
}
::v-deep .p-password input:focus {
  outline: none;
  border: none;
  box-shadow: 0 0 0 1px #569191; /* Fokusrahmen */
}

.login-actions {
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
.register-link {
  font-family: 'Ubuntu', monospace; 
  text-align: center;
  margin-top: 1rem;
}
.register-link a {
  color: #569191;
  text-decoration: none;
}
.register-link a:hover {
  text-decoration: underline;
}
</style>
