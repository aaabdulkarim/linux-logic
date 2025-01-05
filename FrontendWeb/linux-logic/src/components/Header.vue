<template>
    <div class="all grid">
        <i class="pi pi-bars bars"></i>
        <div class="icon-container">
            <i class="pi pi-sun icon"></i>
            <i class="pi pi-cog icon"></i>
            <i class="pi pi-user icon"></i>
        </div>
    </div>
</template>

<style>
    .all {
        color: white;
        background-color: #569191;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding-top: 1rem;
        padding-left: 1rem;
        padding-right: 1rem;

    }

    .bars {
        font-size: 2rem;
        color: white;
    }

    .icon-container {
        display: flex;
        gap: 1.7rem;
    }

    .icon {
        font-size: 2rem;
        color: white;
    }
</style>

<!-- Logo einblendung nach scrollen geht noch nicht-->
<template>
    <div class="header grid">
    <img src="@/assets/LogoLinuxLogic.webp" alt="Logo" v-show="showLogo"  class="logo-sticky" />
      <div class="icon-container">
        <i class="pi pi-sun icon"></i>
        <i class="pi pi-cog icon"></i>
        <i class="pi pi-user icon"></i>
        <i class="pi pi-bars icon"></i>
      </div>

    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        showLogo: false, // Zustand, ob das Logo angezeigt wird
      };
    },
    mounted() {
      window.addEventListener("scroll", this.handleScroll);
    },
    beforeDestroy() {
      window.removeEventListener("scroll", this.handleScroll);
    },
    methods: {
      handleScroll() {
        const scrollPosition = window.scrollY;
        if (scrollPosition > 100) {
          this.showLogo = true;
        } else {
          this.showLogo = false;
        }
      },
    },
  };
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
    font-size: 2rem;
    color: white;
    transition: transform 0.2s ease;
  }
  .icon:hover {
    transform: scale(1.05);
}
  
  .logo-sticky {
    width: 40px;
    height: auto;
    transition: transform 0.3s ease, opacity 0.3s ease;
    transform: scale(0.5);
    opacity: 0;
  }
  </style>