<template>
<<<<<<< HEAD
    <div class="header grid">
    <img src="@/assets/LogoLinuxLogic.webp" alt="Logo" v-show="showLogo"  class="logo-sticky" />
      <div class="icon-container">
        <i class="pi pi-sun icon"></i>
        <i class="pi pi-cog icon"></i>
        <i class="pi pi-user icon" @click="navigateToProfile"></i>

        <i class="pi pi-bars icon"></i>
      </div>

    </div>
  </template>
  <script>
  export default {
  methods: {
    navigateToProfile() {
      this.$router.push({ name: 'profil' });
    }
  }
};
  showLogo = false;
  </script>
  <style scoped>
  .header {
    position: sticky;
    top: 0;
    background-color: #569191;
    padding: 1rem 1rem;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    transition: background-color 0.3s ease;
    z-index: 10;
  }
  .icon-container {
    display: flex;
    gap: 1.7rem;
  }
  .icon {
    font-size: 1.6rem;
    color: white;
    transition: transform 0.2s ease;
  }
  .icon:hover {
    transform: scale(1.05);
  }
  </style>
=======
  <div class="header grid">
    <a href="/#/about" class="logo-link">
      <img src="@/assets/LogoLinuxLogic.webp" alt="Logo" class="logo-sticky" />
    </a>
    <div class="icon-container grid">
      <i class="pi pi-cog icon"></i>
      <i class="pi pi-user icon"></i>
    </div>
  </div>
</template>

<style scoped>
.header {
  position: sticky;
  width: 100%;
  top: 0;
  background-color: #569191;
  padding: 0.5rem 1.5rem;
  display: flex;
  align-items: center;
  justify-content: space-between; 
  transition: background-color 0.3s ease;
  z-index: 1000;
}

.logo-link {
  display: flex;
  align-items: center;
}

.logo-sticky {
  height: 40px;
}

.icon-container {
  display: flex;
  gap: 1.7rem;
}

.icon {
  font-size: 1.6rem;
  color: white;
  transition: transform 0.2s ease;
}

.icon:hover {
  transform: scale(1.05);
}
</style>
>>>>>>> 9d75b2622f3f07a119c4f1dba34e6c82bb48c571
