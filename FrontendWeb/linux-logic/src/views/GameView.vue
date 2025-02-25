<template>
<Header />
<Anforderung/>
<Footer />
</template>

<script>
import Anforderung from '@/components/Spielansicht/Anforderung.vue';
import Header from '@/components/Header.vue';
import Footer from '@/components/Footer.vue';

export default {
    name: 'GameView',
    components: {
        Anforderung,
        Header,
        Footer
    }
}
</script>

<style scoped>

</style>