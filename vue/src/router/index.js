import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Header from '../views/Header.vue'
import Top from '../views/Top.vue'
import Register from '../views/Register.vue'
import Search from '../views/Search.vue'
import Reference from '../views/Reference.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/header',
    name: 'Header',
    component: Header
  },
  {
    path: 'auth/top',
    name: 'Top',
    component: Top
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: function () {
    //   return import(/* webpackChunkName: "about" */ '../views/About.vue')
    // }
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },

  {
    path: '/reference',
    name: 'Reference',
    component: Reference
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
