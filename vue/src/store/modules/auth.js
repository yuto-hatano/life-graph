import Axios from 'axios'

export default {
  namespaced: true,
  state: {
    userId: '',
    token: '',
<<<<<<< Updated upstream
    isLoginError: false
=======
    message: true
>>>>>>> Stashed changes
  },
  mutations: {
    create (state, data) {
      state.userId = data.id
      state.token = data.accessToken
      state.isLoginError = false
    },
    // エラーメッセージ表示
    errorMessage (state) {
      state.message = !state.message
    },
    // destroy (state) {
    //   state.userId = ''
    //   state.token = ''
    // },
    logout (state) {
      state.userId = ''
      state.token = ''
      state.isLoginError = false
    },
    loginError (state) {
      state.isLoginError = true
    },
    resetLoginError (state) {
      state.isLoginError = false
    }
  },
  // APIによるdataの受け渡し
  actions: {
    async create ({ commit }, data) {
      const url = '/api/auth/login'
      await Axios.post(url, data)
        .then(res => commit('create', res.data))
<<<<<<< Updated upstream
        .catch(() => commit('loginError'))
=======
        .catch(err => commit('errorMessage', err))
>>>>>>> Stashed changes
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
