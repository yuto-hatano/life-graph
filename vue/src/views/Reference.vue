<template>
  <div class="referenceSection">
    <div>
      <Header />
    </div>
    <h1 class="title">
      Life Graph
    </h1>
    <div class="message_3">
      The referenced "LifeGraph".
    </div>
    <div class="referenceName">
      <div>
        {{ reference_name() }}
      </div>
    </div>
    <div id="action">
      <button id="submit" value="データを取得" @click="getData" />
    </div>
    <div id="Graph_3">
      <Chart />
    </div>
    <div class="back_button">
      <router-link to="/Search/" class="btn-square-pop">
        検索画面へ戻る
      </router-link>
    </div>
    <Footer />
  </div>
</template>

<script>

import Chart from '../components/Chart.vue'
import Header from '../views/Header.vue'
import Footer from '../components/Footer.vue'

export default {
  components: {
    Header,
    Chart,
    Footer
  },
  data () {
    return {
      // 以下２行 コード検証用
      userId: 2,
      name: 'taito'
      // id: '',
      // parentId: 2
      // age: '',
      // score: '',
      // comment: ''
    }
  },
  computed: {
    referenceName () {
      // 実装用
      // return this.$store.state.SearchGraph.list.name
      // コード検証用
      return this.name
    },
    referenceUserId () {
      // 実装用
      // return this.$store.state.SearchGraph.list.user_id
      // コード検証用
      return this.user_id
    }
  },
  // 実装用
  created: function () {
    this.getData()
    this.reference_name()
  },
  methods: {
    getData () {
      // 実装用
      // const userId = this.$store.state.SearchGraph.list.user_id
      // コード検証用
      const userId = this.userId
      this.$store.dispatch(
        'refchart/create', userId
      )
    },
    reference_name () {
      // 実装用
      // var refName =this.$store.state.SearchGraph.name
      // コード検証用
      var refName = this.name
      return refName
    }

  }
}

</script>

<style scoped>
.referenceSection {
  background-color: #ffffff;
  background-image: url("https://www.transparenttextures.com/patterns/ag-square.png");
}
.title {
  font-family: 'Roboto Slab', serif;
  font-size: 60pt;
  padding: 50px;
}

.message_3 {
  font-family: 'Courgette', cursive;
  font-style: italic;
  font-size: 15pt;
  height: 25px;
  font-weight: bold;
  padding-bottom: 5px;
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
  border: solid 2px #f59402;
  /*角の丸みをもたせる*/
  border-radius: 4px;
  box-shadow: inset 0 2px 0 rgba(255,255,255,0.2), 0 2px 2px rgba(0, 0, 0, 0.19);
  font-weight: bold;
}

.btn-square-pop:active {
  border: solid 2px #f59402;
  box-shadow: 0 0 2px rgba(0, 0, 0, 0.30);
}

.referenceName {
  font-size: 20px;
  font-family: 'M PLUS Rounded 1c', sans-serif;
  margin-right: 500px;
  padding-top: 50px;
}

.back_button {
  font-family: 'M PLUS Rounded 1c', sans-serif;
  padding-top: 20px;
  padding-left: 500px;
  padding-bottom: 20px;
}

#Graph_3 {
  background-color: #FFFcf5;
  border: dashed 2px #666f7a;
  border-radius: 30px;
  width: 700px;
  margin: 0 auto;
  padding-top: 30pt;
  padding-bottom: 10pt;
}

</style>
