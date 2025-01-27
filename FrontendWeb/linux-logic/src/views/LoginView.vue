<template>
    <Login />
    <Footer />
</template>
    
    <script>
    import Footer from '@/components/Footer.vue';
    import Login from '@/components/LoginRegister/login.vue';
    
    export default {
        name: 'GameView',
        components: {
            Login,
            Footer
        }
    }
    </script>
    
    <style scoped>
    
    </style>