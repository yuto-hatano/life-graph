import Axios from 'axios'

export default {
  namespaced: true,
  state: {
    userId: '',
    token: ''
  },
  mutations: {
    create (state, data) {
      state.userId = data.id
      state.token = data.accessToken
    },
    // destroy (state) {
    //   state.userId = ''
    //   state.token = ''
    // },
    logout (state) {
      state.userId = ''
      state.token = ''
    }
  },
  // APIによるdataの受け渡し
  actions: {
    async create ({ commit }, data) {
      const url = '/api/auth/login'
      await Axios.post(url, data)
        .then(res => commit('create', res.data))
        .catch(err => err)
    },
    logout ({ commit }) {
      commit('logout')
    }
  }
  //   destroy ({ commit, dispatch }, data) {
  //     dispatch(
  //       'http/delete',
  //       { url: '/auth', data },
  //       { root: true }
  //     ).then(res => commit('create', res.data))
  //       .catch(err => err)
  //       // logout anyway ...
  //       .finally(res => commit('destroy'))
  //   }
}
