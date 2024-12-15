<template>
  <div class="home">
    
    <a href="/#/about">To About</a>

    <Terminal/>
  </div>
</template>

<script>
// @ is an alias to /src
import Terminal from '@/components/Terminal.vue'

export default {
  name: 'HomeView',
  components: {
    Terminal
  }
}
</script>
