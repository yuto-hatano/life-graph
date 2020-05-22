<template>
  <div class="header">
    <router-link to="/top">
      <button class="btn_top">
        TOP
      </button>
    </router-link>

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
          <button class="btn_logout">
            LOGOUT
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
      user_name: '',
      authority: ''
    }
  },
  created () {
    this.setAccount()
  },
  methods: {
    setAccount () {
      const refAccount = this.$store.state.account
      this.user_name = refAccount.account.username
      this.authority = refAccount.account.name
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
    background-color: #e5f3f3;
  }

  .header_right{
    text-align: right;
    width: 100%;
    top: 15px;
    position: absolute;
    margin-top: 0;
  }

  .header_right li{
    display:inline-block;
    margin-right: 40px;
    z-index:0;
  }

  .username{
    font-family: 'Lato', sans-serif;
    margin-bottom: 0;
  }

  .authority{
    font-family: 'Lato', sans-serif;
    margin-top: 0;
  }

  .btn_top,.btn_logout{
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

  .btn_logout{
    cursor : pointer;
    position: relative;
    top: -10px;
    right:30px;
    padding: 0.6em 1.5em 0.6em 1.5em;
  }

  .btn_top{
    cursor : pointer;
    position: absolute;
    top: 5px;
    left: 20px;
    margin-right: 20px;
    z-index:1;
    padding: 0.6em 2.5em 0.6em 2.5em;
  }

</style>
