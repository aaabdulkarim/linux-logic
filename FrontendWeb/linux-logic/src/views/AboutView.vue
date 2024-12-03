<template>
    <Kopfzeile/>
    <Infos/>
    <Meilenstein/>
    <Umfrage/>
    <Fortschritt/>
    <Team/>
    <Footer/>
</template>

<script>
// @ is an alias to /src
import Kopfzeile from '@/components/HomePage/Kopfzeile.vue'
import Fortschritt from '@/components/HomePage/Fortschritt.vue';
import Infos from '@/components/HomePage/Infos.vue';
import Meilenstein from '@/components/HomePage/Meilenstein.vue';
import Umfrage from '@/components/HomePage/Umfrage.vue';
import Team from '@/components/HomePage/Team.vue';
import Footer from '@/components/Footer.vue';

export default {
  name: 'AboutView',
  components: {
    Kopfzeile,
    Infos,
    Meilenstein,
    Umfrage,
    Fortschritt,
    Team,
    Footer
  }
}
</script>
