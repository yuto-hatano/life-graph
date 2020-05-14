import Vue from 'vue'
import Vuex from 'vuex'
// import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    chart: [],
    loaded: false
  },

  mutations: {
    lifeGraph: function(state,chart){
      state,chart=Chart
      state.loaded=true
    }
  },

  actions: {
    getChart: function({commit}){
      commit('chartData')
    }
  },

  getters: {
    graphYears: (state) => (index) => {
      const graphYearsArray = []
      if(state.chart[index]){
        state.chart[index].chart.forEach((Years) => {
          graphYearsArray.push(Years.years)
        })
      }
      return graphYearsArray
    },
    graphScore: (state) => (index) => {
      const graphScoreArray = []
      if(state.chart[index]){
        state.chart[index].chart.forEach((Score) => {
          graphYearsArray.push(Score.score)
        })
      }
      return graphScoreArray
    }
  }
})
