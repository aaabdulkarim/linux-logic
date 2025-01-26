<template>
    <div class="login h-screen flex flex-col items-center justify-center bg-cover bg-center" 
         :style="{ backgroundImage: `url(@/assets/abstract_art_aqua_final.png)` }">
      <div class="login-container bg-white/70 rounded-lg shadow-lg p-8 w-full max-w-md">
        <h1 class="text-xl font-bold mb-6 text-center text-gray-800">Login</h1>
        <form>
          <!-- Email -->
          <div class="mb-4">
            <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
            <InputText id="email" v-model="email" class="w-full mt-1 p-inputtext-sm" placeholder="Email" />
          </div>
          <!-- Passwort -->
          <div class="mb-4">
            <label for="password" class="block text-sm font-medium text-gray-700">Passwort</label>
            <Password id="password" v-model="password" class="w-full mt-1 p-password-sm" placeholder="Passwort" />
          </div>
          <!-- Checkbox und Link -->
          <div class="flex items-center justify-between mb-6">
            <div class="flex items-center">
              <Checkbox v-model="stayLoggedIn" id="stayLoggedIn" class="mr-2" />
              <label for="stayLoggedIn" class="text-sm text-gray-700">Angemeldet bleiben</label>
            </div>
            <a href="#" class="text-sm text-blue-600 hover:underline">Passwort Vergessen</a>
          </div>
          <!-- Button -->
          <Button label="Anmelden" class="w-full bg-teal-500 hover:bg-teal-600 text-white font-medium py-2 rounded-md" />
        </form>
        <!-- Registrierung -->
        <div class="text-center mt-4 text-sm">
          Haben Sie keinen Account? 
          <a href="#" class="text-blue-600 hover:underline">Registrieren</a>
        </div>
      </div>
      <!-- Footer -->
      <Footer />
    </div>
  </template>
  
  <script>
  import Footer from "@/components/Footer.vue";
  import { ref } from "vue";
  import { InputText } from "primevue/inputtext";
  import { Password } from "primevue/password";
  import { Button } from "primevue/button";
  import { Checkbox } from "primevue/checkbox";
  
  export default {
    name: "Login",
    components: {
      Footer,
      InputText,
      Password,
      Button,
      Checkbox,
    },
    setup() {
      const email = ref("");
      const password = ref("");
      const stayLoggedIn = ref(false);
  
      return { email, password, stayLoggedIn };
    },
  };
  </script>
  
  <style scoped>
  .login {
    min-height: 100vh;
  }
  </style>
  