import Vue from 'vue'
import Vuex from 'vuex'
// import axios from 'axios'

Vue.use(Vuex)

export default {
  state: {
    contents: [
      {
        years: 0,
        score: 20,
        comment: 'コメント1'
      },
      {
        years: 1,
        score: 30,
        comment: 'コメント2'
      },
      {
        years: 2,
        score: 10,
        comment: 'コメント3'
      },
      {
        years: 3,
        score: 40,
        comment: 'コメント4'
      },
      {
        years: 4,
        score: 20,
        comment: 'コメント5'
      },
      {
        years: 5,
        score: 40,
        comment: 'コメント6'
      },
      {
        years: 6,
        score: 10,
        comment: 'コメント7'
      },
      {
        years: 7,
        score: 60,
        comment: 'コメント8'
      },
      {
        years: 8,
        score: 20,
        comment: 'コメント9'
      },
      {
        years: 9,
        score: 30,
        comment: 'コメント10'
      },
      {
        years: 10,
        score: 40,
        comment: 'コメント11'
      },
      {
        years: 11,
        score: 50,
        comment: 'コメント12'
      },
      {
        years: 12,
        score: 20,
        comment: 'コメント13'
      },
      {
        years: 13,
        score: 30,
        comment: 'コメント14'
      },
      {
        years: 14,
        score: 10,
        comment: 'コメント15'
      },
      {
        years: 15,
        score: 70,
        comment: 'コメント16'
      },
      {
        years: 16,
        score: 20,
        comment: 'コメント17'
      },
      {
        years: 17,
        score: 30,
        comment: 'コメント18'
      },
      {
        years: 18,
        score: 10,
        comment: 'コメント19'
      },
      {
        years: 19,
        score: 50,
        comment: 'コメント20'
      },
      {
        years: 20,
        score: 10,
        comment: 'コメント21'
      },
      {
        years: 21,
        score: 80,
        comment: 'コメント22'
      },
      {
        years: 22,
        score: 90,
        comment: 'コメント23'
      }
    ],
    // chart: [],
    load: false,
    loaded: false
  },
  mutations: {
    loadDone (state, payload) {
      state.loaded = payload.loading
      state.loaded = true
    }
  },
  actions: {
    load ({ commit }) {
      const loading = ['DONE']
      commit('loadDone', { loading })
    }
  }
}
