<template>  
    <div class="links grid">

    </div>
    <div class="copyright grid">
        <p>© Copyright 2024 </p>
        <p><router-link to="/about"> Linux Logic</router-link></p>
    </div>
</template>

<style>
    .copyright {
        padding-top: 60px;
        padding-bottom: 20px;
        padding-right: 30px;
        gap: 6px;
        background-color: #569191;
        color: white;
        justify-content: right;
    } 
    .copyright a {
        color: white;
        text-decoration: none;
    }
    .copyright a:hover {
        text-decoration: underline;
    }

</style>
