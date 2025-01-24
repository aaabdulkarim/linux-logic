<template>
  <Header />
  
  <Kopfzeile />

  <section id="infos">
    <Infos />
  </section>

  <section id="meilenstein">
    <Meilenstein />
  </section>

  <section id="umfrage">
    <Umfrage />
  </section>

  <section id="fortschritt">
    <Fortschritt />
  </section>

  <section id="team">
    <Team />
  </section>

  <section id="impressum">
    <Impressum />
  </section>
  
  <section id="footer">
    <Footer />
  </section>

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
import Header from '@/components/Header.vue';
import Impressum from '@/components/HomePage/Impressum.vue';

export default {
  name: 'AboutView',
  components: {
    Kopfzeile,
    Infos,
    Meilenstein,
    Umfrage,
    Fortschritt,
    Team,
    Footer,
    Impressum,
    Header
  }
}
</script>
