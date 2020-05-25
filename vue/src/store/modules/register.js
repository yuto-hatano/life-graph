import Axios from 'axios'

export default {
  state: {
    contents: []
  },
  mutations: {
    // registerSuccess (state) {
    //   state.update = !state.update
    // }
  },
  // APIによるdataの受け渡し
  actions: {
    register ({ commit }, data) {
      const url = '/api/auth/life_graphs'
      Axios.post(url, data)
    }
  }
}
