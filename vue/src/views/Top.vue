<template>
  <div class="topSection">
    <div>
      <Header />
    </div>
    <h1 class="title">
      Life Graph
    </h1>
    <h2 class="message">
      The most important thing is to enjoy your life – to be happy – it’s all that matters.
    </h2>
    <div class="button">
      <router-link to="/Register/" class="btn-square-pop">
        登録
      </router-link>
      <router-link to="/Search/" class="btn-square-pop">
        検索
      </router-link>
    </div>
    <div id="Graph">
      <Chart />
    </div>
    <div class="edit_button">
      <router-link to="/Register/" class="btn-square-pop">
        編集
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
  }
}

</script>

<style scoped>
.topSection {
  background-color: #ffffff;
  background-image: url("https://www.transparenttextures.com/patterns/ag-square.png");
}
.title {
  font-family: 'Roboto Slab', serif;
  font-size: 60pt;
  padding: 50px;
}

.message {
  font-family: 'Courgette', cursive;;
  font-style: italic;
  font-size: 15pt;
  height: 25px;
  padding-bottom: 30px;
}

.button{
  height: 60px;
}

.btn-square-pop {
  position: relative;
  display: inline-block;
  padding: 0.6em 2em 0.6em 2em;
  margin-left: 30px;
  margin-right: 30px;
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

.register_botton {
  font-size: 10pt;
  padding: 7px
}

.search_botton {
  font-size: 10pt;
  padding: 7px
}

#register {
  font-size: 20pt;
  padding: 40px
}

#search {
  font-size: 20pt;
  padding: 40px
}

#Graph {
  background-color: #FFFcf5;
  border: dashed 2px #666f7a;
  border-radius: 30px;
  width: 700px;
  margin: 0 auto;
  padding-top: 30pt;
  padding-bottom: 10pt;
}

.edit_button{
  padding-top: 20px;
  padding-left: 500px;
  padding-bottom: 20px;
}
</style>
