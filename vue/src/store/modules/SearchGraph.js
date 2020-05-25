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
    SearchName (state, data) {
      state.name = data.name
      state.user_id = data.user_id
      state.updated_at = data.updated_at
      state.created_at = data.created_at
      debugger
    }
  },
  actions: {
    SearchName ({ commit }, data) {
      const url = 'api/auth/search'
      axios.get(url, {
        params: {
          // ここにクエリパラメータを指定する
          likeName: data.likeName,
          startDate: data.startDate,
          fromDate: data.fromDate
        }
      }
      )
        .then(res => commit('SearchName', res.data))
        .catch(err => err)
      debugger
    },
    SearchDate ({ commit }, data) {
      const url = '/api/auth/search/'
      axios.get(url, {
        params: {
          // ここにクエリパラメータを指定する
          likeName: data.likeName,
          startDate: data.startDate,
          fromDate: data.fromDate
        }
      }
      )
        .then(res => commit('SearchDate', res.data))
        .catch(err => err)
      debugger
    }
    //  const url = '/api/auth/search' +data

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
