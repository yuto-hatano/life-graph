// // import Vue from 'vue'
// // import Vuex from 'vuex'
// import axios from 'axios'
// // import qs from 'qs';
// // Vue.use(Vuex)

// // const config = {
// //   headers: {
// //     'Access-Control-Allow-Origin': '*'
// //   }
// // }

// export default {
//   state: {
//     // list:this.users.slice()[
//     name: '',
//     user_id: '',
//     updated_id: '',
//     created_id: ''
//     // ]
//   },
//   mutations: {
//     // TODO: delete because of test
//     setSearchGraphs (state, data) {
//       state.name = data.name
//       state.user_id = data.user_id
//       state.updated_at = data.updated_at
//       state.created_at = data.created_at
//     }
//   },
//   actions: {
//     // searchName (commit, data) {
//     //   const url = '/api/auth/search' + data
//     //   axios.get(url)
//     //     .then(res => commit('setSearchName', res.data))
//     //     .catch(err => err)
//     // },
//     // searchDate (commit, data) {
//     //   const url = '/api/auth/search' + data
//     //   axios.get(url)
//     //     .then(res => commit('setSearchDate', res.data))
//     //     .catch(err => err)
//     // }
//     setSearchGraphs (commit, data) {
//       // const params = {
//       //   likeName: 'searchUser',
//       //   startDate: 'updateFrom',
//       //   finishDate: 'updatedFrom'
//       // }
//       // const paramsSerializer = (params) => qs.stringify(params)
//       axios.get('/api/auth/search' + data, {
//         data: {
//           likeName: 'searchUser',
//           startDate: 'updateFrom',
//           finishDate: 'updatedFrom'
//         }
//       })
//         .then(res => commit('setSearchGraphs', res.data))
//         .catch(err => err)
//     }
//   }
// }

// // actions: {s
// //   // TODO: delete because of test
// //   fetchLikeName ({ commit }) {
// //     const url = '/api/auth/search'
// //     // ここでAPIを動かすURLを決める
// //     // このURLはAPIの住所だから、ブラウザのURLに反映されるものではない
// //     return Promise.all([
// //       axios.get('/LikeName'),
// //       axios.get('/StareDate'),
// //       axios.get('/FinishDate')
// //     ]).then((res) => {
// //       commit('setSearchGraphs', res.data)
// //     })
// //   }
