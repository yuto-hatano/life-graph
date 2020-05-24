// import Vue from 'vue'
// import Vuex from 'vuex'
// // import axios from 'axios'

// Vue.use(Vuex)

export default {
  state: {
    contents: [
      // ０歳いるかなあ
      {
        age: '',
        score: '',
        comment: ''
      }
    ],
    // chart: [],
    load: false,
    loaded: true
  },
  mutations: {
    addContentMutation (state, content) {
      // console.log('mutation')
      state.contents.push(content)
    }
  },
  actions: {
    addContent ({ commit }, content) {
      // console.log('action')
      commit('addContentMutation', content)
    }
  }
}
