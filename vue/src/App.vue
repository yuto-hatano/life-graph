<template>
  <div id="app">
    <Login />
    <Header />
    <Top />
    <Register />
    <Search />
    <Reference />
    <Footer />
  </div>
</template>

<script>
import Header from './components/Header.vue'
import Login from './components/Login.vue'
import  Top from './components/Top.vue'
import Register from './components/Register.vue'
import Reference from './components/Reference.vue'
import Search from './components/Search.vue'
import Footer from './components/Footer.vue'

</script>

<style>
#app {
  color: #2c3e50;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

#nav {
  padding: 30px;
}

#nav a {
  color: #2c3e50;
  font-weight: bold;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
