<template>
  <div id="header">
    <div id="header-left">
      <button class="btn_top"
              href="top"
      >
        TOP
      </button>
    </div>
    <div id="header-right">
      <div id="username">
        username
      </div>
      <button class="btn_logout"
              href="login"
      >
        LOGOUT
      </button>
    </div>
  </div>
</template>

<style scoped>
  #header{
    height: 50px;
    background-color: gray;
  }

  #header-left{
    text-align: left;
    padding-top: 10px;
    padding-left: 10px
  }

  #header-right{
    text-align: right;
  }

  #user-name{
    padding-right: 100px
  }
  .btn_top,.btn_logout{
    width: 100px;
    height: 30px;
    font-size: 20px;
  }
</style>
