import Axios from 'axios'

export default {
  namespaced: true,

  state: {
    contents: [],
    loaded: false
  },

  mutations: {
    create (state, data) {
      state.contents = data
      state.loaded = !state.loaded
    },
    clear (state) {
      state.contents = []
    }
  },
  // APIによるdataの受け渡し
  actions: {
    create ({ commit, rootState }, userId) {
      const url = '/api/life-graphs/' + userId
      Axios.get(url, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      })
        .then(res => commit('create', res.data))
        .catch(err => err)
    },
    clear ({ commit }) {
      commit('clear')
    }
  }
}
