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
    register ({ commit, rootState }, data) {
      const url = '/api/life-graphs'
      Axios.post(url, data, {
        headers: {
          Authorization: `Bearer ${rootState.auth.token}`
        }
      })
    }
  }
}
