import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    account: {}

  },
  mutations: {
    setAccount (state, payload) {
      state.account = payload
    },
    deleteAccount (state) {
      state.account = ''
    }
  },
  actions: {
    fetchAccount ({ commit }, userId) {
      console.log(userId)
      const url = '/api/accounts/' + userId
      axios.get(url).then(res => commit('setAccount', res.data))
        .catch(err => err)
    }
  }
}
