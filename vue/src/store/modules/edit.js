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
      const url = '/api/auth/ref-record'
      Axios.post(url, data)
        .then(res => commit('create', res.data))
        .catch(err => err)
    }
  }
}
