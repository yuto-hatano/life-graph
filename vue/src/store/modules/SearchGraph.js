// import Vue from 'vue'
// import Vuex from 'vuex'
// import axios from 'axios'

// Vue.use(Vuex)

// const config = {
//   headers: {
//     'Access-Control-Allow-Origin': '*'
//   }
// }

// export default new Vuex.Store({
//   state: {
//     setSearchGraphs: {}
//   },
//   mutations: {
//     // TODO: delete because of test
//     setSearchGraphs (state, payload) {
//       state.search = payload.search
//     }
//   },
//   actions: {
//     // TODO: delete because of test
//     fetchLiekName ({ commit },{LikeName},) {
//       const url = '/api/auth/search'
//       // ここでAPIを動かすURLを決める
//       // このURLはAPIの住所だから、ブラウザのURLに反映されるものではない
//       return Promise.all([
//       axios.get(url, LikeName),
//       // axios.get(url, StartDate),
//       // axios.get(url, FinishDate),
//     ]).then((res) => {
//         // getリクエストでリクエストを送る
//         // res レスポンス
//         commit('setSearchGraphs', res.data)
//       })

//     }
//     fetchStartDate ({ commit },{StartDate},) {
//       const url = '/api/auth/search'
//       // ここでAPIを動かすURLを決める
//       // このURLはAPIの住所だから、ブラウザのURLに反映されるものではない
//       return Promise.all([
//       axios.get(url, StartDate),
//       // axios.get(url, StartDate),
//       // axios.get(url, FinishDate),
//     ]).then((res) => {
//         // getリクエストでリクエストを送る
//         // res レスポンス
//         commit('setSearchGraphs', res.data)
//       })
//     }
//     fetchFinishDate ({ commit },{FinishDate},) {
//       const url = '/api/auth/search'
//       // ここでAPIを動かすURLを決める
//       // このURLはAPIの住所だから、ブラウザのURLに反映されるものではない
//       return Promise.all([
//       axios.get(url, FinishDate),
//       // axios.get(url, StartDate),
//       // axios.get(url, FinishDate),
//     ]).then((res) => {
//         // getリクエストでリクエストを送る
//         // res レスポンス
//         commit('setSearchGraphs', res.data)
//       })

//     }
//   }
// })
