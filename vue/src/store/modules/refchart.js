import Axios from 'axios'

export default {
  namespaced: true,

  state: {
    contents: []
  },

  mutations: {
    create (state, data) {
      state.contents = data
    }
  },
  // APIによるdataの受け渡し
  actions: {
    create ({ commit, rootState }, userId) {
      const url = '/api/life_graphs/' + userId
      Axios.get(url, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      })
        .then(res => commit('create', res.data))
        .catch(err => err)
    }
  }
}
