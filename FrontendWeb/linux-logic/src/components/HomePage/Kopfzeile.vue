<template>
    <div class="all flex flex-column align-items-center justify-content-center">
      <!-- Logo -->
      <div class="mb-5">
        <img src="@/assets/LogoLinuxLogic.webp" alt="Logo" class="LogoLinuxLogic">
      </div>
  
      <!-- Button-Container Für den Anfang verstecken-->
      <!-- <div class="flex flex-column align-items-center">
        <button class="button p-3 mb-3 text-lg font-bold">Anmelden</button>
        <span class="text-sm cursor-pointer">
          <u>Registrieren</u>
        </span>
      </div> -->
    </div>
  </template>

<script>
import AnimateOnScroll from 'primevue/animateonscroll';


export default {
  name: 'Kopfzeile',
  components: {
    AnimateOnScroll
  }
}

</script>

<style scoped>
  .all {
    height: 102vh;
    color: white;
    background-color: #569191;
  }
  
  .LogoLinuxLogic {
    max-width: 300px;
    height: auto;
  }
  
  /* Button Styling */
  .button {
    color: #569191;
    background-color: white;
    border: none;
    padding: 0.75rem 2rem;
    border-radius: 8px;
    transition: background-color 0.3s ease, transform 0.2s ease;
  }
  
  .button:hover {
    background-color: #4a8080;
    color: white;
    transform: scale(1.05);
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
  