<template>
  <router-view/>
</template>

<style>
@import url('https://fonts.googleapis.com/css2?family=Ubuntu+Mono:ital,wght@0,400;0,700;1,400;1,700&display=swap');

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

h1, h2, h3, h4, h5, h6 {
  font-family: 'Ubuntu Mono', monospace; 
  font-weight: 300; 
}

h1 {
  font-size: 3rem;
  color:  #569191;
}
h2 {
  font-size: 2.5rem;
  color: #569191;
}
h3 {
  font-size: 2rem;

  color: #569191;
}
h4 {
  font-size: 1.5rem;
  color: #3D525C;  
}
h5 {
  font-size: 1.2rem;
  color: #3D525C;
}
p {
  font-size: 1.2rem;
}


nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
