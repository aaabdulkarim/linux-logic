<template>
  <div class="header grid">
    <a href="/#/about" class="logo-link">
      <img src="@/assets/LogoLinuxLogic.webp" alt="Logo" class="logo-sticky" />
    </a>
    <div class="icon-container">
      <i class="pi pi-star icon"></i>
      <i class="pi pi-user icon" @click="navigate"></i>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
methods: {
  navigate() {
    this.$router.push("/profil");
  },
},
};

</script>

<style scoped>
.header {
  width: 100%;
  height: 4.80rem;
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
  height: 3rem;
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
