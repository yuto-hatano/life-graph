// import Vue from 'vue'
// import Vuex from 'vuex'
import axios from 'axios'
// import qs from 'qs'
// Vue.use(Vuex)

// const config = {
//   headers: {
//     'Access-Control-Allow-Origin': '*'
//   }
// }

export default {
  state: {
    // list:this.users.slice()[
    name: '',
    user_id: '',
    updated_id: '',
    created_id: ''
    // // ]
  },
  mutations: {
    // TODO: delete because of test
    SearchGraphs (state, data) {
      state.name = data.name
      state.user_id = data.user_id
      state.updated_at = data.updated_at
      state.created_at = data.created_at
    }
  },
  actions: {
    SearchGraphs ({ commit }, data) {
      const url = '/api/auth/search' + data
      // const data = {
      //   likeName = this.like_name,
      //   startDate = this.start_date,
      //   finishDate = this.finish_date
      // }
      debugger
      axios.get(url, data)
        .then(res => commit('SearchGraphs', res.data))
        .catch(err => err)
    }
    //   const url = '/api/auth/search' +data

    //   const data = {
    //     word: 'userName',
    //     category: {
    //       $in: ['startDate', 'finishDate']
    //     }
    //   }

    //   const dataSerializer = (data) => qs.stringify(data)

    //   axios.get(url, { params, dataSerializer })
    // }

  }
}
