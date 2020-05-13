<template>
  <div class="header">
    <button class="btn_top" onclick="location.href='./home'">
      TOP
    </button>
    <ul class="header_right">
      <li>
        <h5 class="username">
          {{ user_name }}
        </h5>
        <h5 class="authority">
          {{ authority }}
        </h5>
      </li>
      <li>
        <router-link to="/login">
          <button class="btn_logout" onclick="location.href='./login'">
            ログアウト
          </button>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data () {
    return {
      user_name: 'Kana Sekiguchi',
      authority: 'Owner'
    }
  }
}
</script>

<style scoped>
  .header{
    position: relative;
    margin-top: 0;
    display:block;
    width:100%;
    height: 60px;
    background-color: #e6e6e6;
  }

  .header_right{
    text-align: right;
    width: 100%;
    top:0;
    position: absolute;
    margin-top: 0;
  }

  .header_right li{
    display:inline-block;
    margin-right: 40px;
  }

  .username{
    margin-bottom: 0;
  }

  .authority{
    margin-top: 0;
  }

  .btn_logout{
    display: inline-block;
    position: relative;
    top:-10px;
    right:30px;
  }

  .btn_top{
    position: absolute;
    display: block;
    /* margin :auto,auto,0,0; */
    top: 13px;
    left: 20px;
    margin-right: 20px;
  }

  .btn_top,.btn_logout{
    width: 100px;
    height: 50px;
  }
</style>
