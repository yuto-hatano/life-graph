import Axios from 'axios'

export default {
  namespaced: true,
  state: {
    id: '',
    parent_id: '',
    age: '',
    score: '',
    comment: ''
  },
  mutations: {
    create (state, data) {
      state.age = data.age
      state.score = data.score
      state.comment = data.comment
    }
  },
  // APIによるdataの受け渡し
  actions: {
    create ({ commit }, data) {
      const url = '/auth/ref/' + data
      Axios.get(url)
        .then(res => commit('create', res.data))
        .catch(err => err)
    }

  }
}
