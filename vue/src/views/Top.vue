<template>
  <div class="topSection">
    <h1 class="title">
      Life Graph
    </h1>
    <div class="botton">
      <a href="./register" class="btn-square-pop">登録</a>
      <a href="./search" class="btn-square-pop">検索</a>
      <!-- <button class="register_botton" onclick="location.href='./register'">登録</button>
      <button class="search_botton" onclick="location.href='./search'">検索</button> -->
    </div>
    <div>
      <button class="register_botton" onclick="location.href='./register'">
        登録
      </button>
      <button class="search_botton" onclick="location.href='./search'">
        検索
      </button>
    </div>
    <div class="nav">
      <router-link id="register" to="/register">
        登録
      </router-link>
      <router-link id="search" to="/search">
        検索
      </router-link>
    </div>
  </div>
</template>

<style scoped>
.title {
  font-size: 60pt;
}

.botton{
  background-color: #e5f3f3;
  height: 60px;
}

.btn-square-pop {
  position: relative;
  display: inline-block;
  padding: 0.6em 0.6em;
  margin: 8px;
  text-decoration: none;
  color: #FFF;
  background: #fd9535;/*背景色*/
  border-bottom: solid 2px #d27d00;/*少し濃い目の色に*/
  border-radius: 4px;/*角の丸み*/
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
  font-size:20pt;
  padding: 40px
}
</style>
