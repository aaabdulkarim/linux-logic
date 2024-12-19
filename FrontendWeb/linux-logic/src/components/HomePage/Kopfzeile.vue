<template>  
    <div class="all grid">
        <div class="col-12">
        <img src="..\..\assets\LogoLinuxLogic.webp" alt="Logo" class="LogoLinuxLogic pl-5">
        </div> 
        <!--
        <div class="button-container">
            <button class="col-2 m-2 mt-3 text-lg">Anmelden</button>
        </div>
        <span class="m-2 mt-3 button-container text-sm"><u>Registrieren</u></span>
        -->
    </div>
</template>

<style scoped>
    .all {
        height: 102vh;
        color: white;
        background-color: #569191;
        padding-top: 10rem;
        padding-bottom: 16rem;
    }
    .LogoLinuxLogic {
        max-width: 500%;
        height: auto;
    }
    .button-container {
        display: flex;
        justify-content: center;
        width: 100%;
    }
    button {
        color: #569191;
        background-color: white;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        cursor: pointer;
    }
</style>