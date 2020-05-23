<template>
  <div id="app">
    <div id="nav">
      <router-link to="/Signup">
        SignUp
      </router-link>|
      <router-link to="/Login">
        Login
      </router-link>|
      <router-link to="/Header">
        Header
      </router-link>|
      <router-link to="/Top">
        Top
      </router-link>|
      <router-link to="/Register">
        Register
      </router-link>|
      <router-link to="/Search">
        Search
      </router-link>|
      <router-link to="/Reference">
        Reference
      </router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
import Signup from './views/Signup.vue'
import Login from './views/Login.vue'
import Header from './views/Header.vue'
import Top from './views/Top.vue'
import Register from './views/Register.vue'
import Search from './views/Search.vue'
import Reference from './views/Reference.vue'

export default {
  name: 'App',
  views: {
    Signup,
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
@import url('https://fonts.googleapis.com/css2?family=Lato:wght@300&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+JP&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@200;300&display=swap');
@import url('https://fonts.googleapis.com/css?family=Arvo:700');
@import url('https://fonts.googleapis.com/css?family=Seaweed+Script');
@import url('https://fonts.googleapis.com/css2?family=Courgette&display=swap');
</style>
