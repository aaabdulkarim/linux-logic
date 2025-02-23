<template>
    <Header />
    <Benutzerseite />
    <Footer />
</template>

<script>
import Header from '../components/Header.vue';
import Footer from '../components/Footer.vue';
import Benutzerseite from '@/components/Benutzer/Benutzerseite.vue';
export default {
    components: {
        Header,
        Footer,
        Benutzerseite
    }
}
</script>

<style scoped></style>