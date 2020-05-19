import Axios from 'axios'

export default {
  namespaced: true,
  state: {
    tenant: '',
    userId: '',
    token: ''
  },
  mutations: {
    create (state, data) {
      state.tenant = ''
      state.token = data.token
      state.userId = data.user_id
    },
    destroy (state) {
      state.tenant = ''
      state.userId = ''
      state.token = ''
    }
  },
  // APIによるdataの受け渡し
  actions: {
    create ({ commit }, data) {
      const url = '/api/auth/login'
      Axios.post(url, data).then(res => commit('create', res.data))
        .then(res => commit('create', res.data))
        .catch(err => err)
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
}
