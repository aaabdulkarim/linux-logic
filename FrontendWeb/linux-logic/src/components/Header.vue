 
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