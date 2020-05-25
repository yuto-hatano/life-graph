import Axios from 'axios'

export default {
  namespaced: true,

  state: {
    contents: []
  },

  mutations: {
    create (state, data) {
      console.log(data)
      state.contents = data
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
