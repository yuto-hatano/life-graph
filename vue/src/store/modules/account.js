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
    async fetchAccount ({ commit }, userId) {
      // const authId = rootState.auth.userId
      // 他のmoduleからstateを取得したいがうまく行かない、、
      const url = '/api/auth/accounts/' + userId
      await axios.get(url).then(res => commit('setAccount', res.data))
        .catch(err => err)
    }
  }
}
