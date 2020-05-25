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
    }
  },
  // APIによるdataの受け渡し
  actions: {
    create ({ commit }, userId) {
      const url = '/api/auth/ref/' + userId
      Axios.get(url)
        .then(res => commit('create', res.data))
        .catch(err => err)
    }
  }
}
