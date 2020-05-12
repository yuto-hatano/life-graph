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

import Login from './views/Login.vue'
import Header from './views/Header.vue'
import Top from './views/Top.vue'
import Register from './views/Register.vue'
import Search from './views/Search.vue'
import Reference from './views/Reference.vue'

export default {
  name: 'App',
  components: {
    Login,
    Header,
    Top,
    Register,
    Search,
    Reference
  }
}

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
