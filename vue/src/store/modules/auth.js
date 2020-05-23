import Axios from 'axios'

export default {
  namespaced: true,
  state: {
    userId: '',
    token: '',
    message: ''
  },
  mutations: {
    create (state, data) {
      state.userId = data.id
      state.token = data.accessToken
    },
    destroy (state) {
      state.userId = ''
      state.token = ''
    },
    signup (state, data) {
      debugger
      state.message = data.message
    }
  },
  // APIによるdataの受け渡し
  actions: {
    create ({ commit }, data) {
      const url = '/api/auth/login'
      Axios.post(url, data)
        .then(res => commit('create', res.data))
        .catch(err => err)
    },
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
    signup ({ commit }, data) {
      const url = '/api/auth/signup'
      Axios.post(url, data)
        .then(res => commit('signup', res.data))
        .catch(err => err)
    }
  }
}
