<template>
  <div class="ReferenceSection">
    <div>
      <Header />
    </div>
    <h1 class="title">
      Life Graph
    </h1>
    <div class="referenceName">
      <div>
        {{ reference_name }}
      </div>
    </div>
    <div id="action">
      <button id="submit" value="データを取得" @click="getData" />
    </div>
    <div id="Graph">
      <Chart />
    </div>
    <div class="back_button">
      <router-link to="/Search/" class="btn-square-pop">
        検索画面へ戻る
      </router-link>
    </div>
  </div>
</template>

<script>

import Chart from '../components/Chart.vue'
import Header from '../views/Header.vue'

export default {
  components: {
    Header,
    Chart
  },
  data () {
    return {
      reference_name: 'Kana Sekiguchi',
      // id: '',
      parentId: '3'
      // age: '',
      // score: '',
      // comment: ''
    }
  },
  methods: {
    getData () {
      const parentId = this.parentId
      this.$store.dispatch(
        'refchart/create', parentId
      )
    }
  }
}

</script>

<style scoped>
.title {
  background-color: #e5f3f3;
  font-family: 'Roboto Slab', serif;
  font-size: 60pt;
  padding: 50px;
}

.btn-square-pop {
  position: relative;
  display: inline-block;
  padding: 0.6em 0.6em;
  margin: 8px;
  text-decoration: none;
  color: #FFF;
  /* 背景色 */
  background: #fd9535;
  /*少し濃い目の色*/
  border-bottom: solid 2px #d27d00;
  /*角の丸みをもたせる*/
  border-radius: 4px;
  box-shadow: inset 0 2px 0 rgba(255,255,255,0.2), 0 2px 2px rgba(0, 0, 0, 0.19);
  font-weight: bold;
}

.btn-square-pop:active {
  border-bottom: solid 2px #fd9535;
  box-shadow: 0 0 2px rgba(0, 0, 0, 0.30);
}

.referenceName {
  font-size: 20px;
  margin-right: 380px;
  padding-top: 50px;
}

.back_button {
  padding-left: 500px;
}

#Graph {
  width: 600px;
  margin: 0 auto;
  padding-top: 10pt;
  padding-bottom: 10pt;
}

</style>
