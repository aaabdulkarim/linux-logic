<template>
  <div class="fab-container">
    <Button class="p-button-rounded custom-button" @click="scrollToSection('download')">
      <i class="pi pi-download"></i>
    </Button>
  </div>
</template>

<script>
export default {
  methods: {
    scrollToSection(sectionId) {
      const element = document.getElementById(sectionId);
      if (element) {
        element.scrollIntoView({ behavior: "smooth" });
      }
    },
  },
};
</script>

<style scoped>
.fab-container {
  position: fixed;
  bottom: 20px;
  right: 20px;
  z-index: 1000;
}

/* Transparenter, runder Button */
.custom-button {
  width: 50px;
  height: 50px;
  background-color: rgba(61, 82, 92, 0.6); /* Leichte Transparenz */
  border: none;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2); /* Leichter Schatten für bessere Sichtbarkeit */
  transition: background 0.3s ease, transform 0.2s ease;
}


/* Hover-Effekt */
.custom-button:hover {
  background-color: rgba(61, 82, 92, 0.9); /* Weniger Transparenz beim Hover */
  transform: scale(1.1);
}

</style>
