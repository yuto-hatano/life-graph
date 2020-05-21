// import Vue from 'vue'
// import Vuex from 'vuex'
// import axios from 'axios'

// Vue.use(Vuex)

// const config = {
//   headers: {
//     'Access-Control-Allow-Origin': '*'
//   }
// }

// export default {
//   state: {
//     name: '',
//     user_id: '',
//     updated_id: '',
//     created_id: ''
//   },
//   mutations: {
//     // TODO: delete because of test
//     setSearchGraphs (state, data) {
//       state.name = data.name
//       state.user_id=data.user_id
//       state.updated_at=data.updated_at
//       state.created_at=data.created_at
//     }
//   },
//   actions: {
//     searchGraphs ({ commit },data){
//       // const authId = rootState.auth.userId
//       // 他のmoduleからstateを取得したいがうまく行かない、、
//       return Promise.all([
//               axios.get('/LikeName'),
//               axios.get('/StareDate'),
//               axios.get('/FinishDate')
//             ])
//         .then(res => commit('searchGraphs', res.data))
//         .catch(err => err)
//     }
//   }
// }

// // actions: {
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
