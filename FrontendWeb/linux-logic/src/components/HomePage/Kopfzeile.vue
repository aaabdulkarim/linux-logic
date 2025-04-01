<template>
    <div class="all flex flex-column align-items-center justify-content-center grid">
      <!-- Logo -->
      <div class="mb-5 ">
        <img src="@/assets/LogoLinuxLogic.webp" alt="Logo" class="LogoLinuxLogic">
      </div>
  
      
      <div class="flex flex-column align-items-center">
        <button @click="navigate"class="button p-3 mb-3 text-lg font-bold">Spielen</button>
        <!-- Button-Container Für den Anfang verstecken-->
        <!-- <span class="text-sm cursor-pointer">
          <u>Registrieren</u>
        </span>-->
      </div> 
    </div>
  </template>

<script>
import AnimateOnScroll from 'primevue/animateonscroll';


export default {
  name: 'Kopfzeile',
  components: {
    AnimateOnScroll
  },
  methods: {
    navigate() {
      this.$router.push("/auswahl");
    },
  },
};

</script>

<style scoped>
  .all {
    height: 102vh;
    color: white;
    background-color: #569191;
  }
  
  .LogoLinuxLogic {
    max-width: 80%;
    height: auto;
  }
  
  /* Button Styling */
  .button {
    color: #569191;
    background-color: white;
    border: 2px solid white;
    border-radius: 10px;
    transition: background-color 0.3s ease;
    width: 300px;
  }
  
  .button:hover {
    background-color: #569191;
    border: 2px solid #d0f0f0;
    color: white;
  }
  
  /* Link Styling */
  .text-sm {
    color: white;
    transition: color 0.3s ease;
  }
  
  .text-sm:hover {
    color: #d0f0f0;
  }
  </style>
  