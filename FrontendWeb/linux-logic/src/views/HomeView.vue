<template>
  <div class="home">
    
    <a href="/#/about">To About</a>
    <br>
    <a href="/#/terminal">To Game</a>
    <br>
    <a href="/#/login">To Login</a>
    <br>
    <a href="/#/register">To Register</a>
    <br>
    <a href="/#/auswahl">To Auswahl</a>
    <br>
    <a href="/#/profil">To Benutzer</a>

    <Terminal/>
  </div>
</template>

<script>
// @ is an alias to /src
import Terminal from '@/components/HomePage/BspTerminal.vue'

export default {
  name: 'HomeView',
  components: {
    Terminal
  }
}
</script>
