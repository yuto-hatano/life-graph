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
    create ({ commit }, parentId) {
      const url = '/api/auth/ref/' + parentId
      Axios.get(url)
        .then(res => commit('create', res.data))
        .catch(err => err)
    }
  }
}
