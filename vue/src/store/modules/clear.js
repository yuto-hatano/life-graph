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
    create ({ commit, rootState }, data) {
      const url = '/api/life_graphs'
      Axios.delete(url, {
        data,
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      })
        .then(res => commit('create', res.data))
        .catch(err => err)
    }
  }
}
