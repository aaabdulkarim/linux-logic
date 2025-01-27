<template>
    <Register />
    <Footer />
</template>
    
    <script>
    import Footer from '@/components/Footer.vue';
    import Register from '@/components/LoginRegister/register.vue';

    
    export default {
        name: 'GameView',
        components: {
            Register,
            Footer
        }
    }
    </script>
    
    <style scoped>
    
    </style>