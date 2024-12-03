<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <Terminal/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import Terminal from '@/components/Terminal.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld,
    Terminal
  }
}
</script>
