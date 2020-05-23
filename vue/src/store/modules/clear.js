import Axios from 'axios'

export default {
  namespaced: true,
  state: {
    record: []
  },
  mutations: {
    create (state, data) {
      state.record = data
    }
  },
  // APIによるdataの受け渡し
  actions: {
    create ({ commit }, data) {
      const url = '/api/auth/life_graphs'
      Axios.delete(url, { data })
        .then(res => commit('create', res.data))
        .catch(err => err)
    }
  }
}
