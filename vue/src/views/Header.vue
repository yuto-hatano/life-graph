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
        <button class="btn_logout" @click="logout">
          LOGOUT
        </button>
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
      const Account = this.$store.state.account
      this.user_name = Account.account.username
      const authority = Account.account.name
      if (authority === 'ROLE_ADMIN') {
        this.authority = 'Manager'
      } else if (authority === 'ROLE_MODERATOR') {
        this.authority = 'Moderator'
      } else {
        this.authority = 'General user'
      }
    },

    logout () {
      this.$store.dispatch(
        'auth/logout'
      )
      this.$store.dispatch(
        'account/deleteAccount'
      )
      this.$store.dispatch(
        'refchart/clear'
      )
      this.$router.push('/login')
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
    height: 70px;
    background-color: #e5f3f3;
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.16), 0 1px 4px rgba(0, 0, 0, 0.23);
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
    font-family: 'M PLUS Rounded 1c', sans-serif;
    margin-bottom: 0;
  }

  .authority{
    font-family: 'M PLUS Rounded 1c', sans-serif;
    margin-top: 0;
  }

  .btn_top,.btn_logout{
  font-family: 'M PLUS Rounded 1c', sans-serif;
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
  box-shadow: inset 0 2px 0 rgba(255,255,255,0.2), 0 2px 2px rgba(0, 0, 0, 0.021);
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
    margin-left: 40px;
    cursor : pointer;
    position: absolute;
    top: 5px;
    left: 20px;
    margin-right: 20px;
    z-index:1;
    padding: 0.6em 2.5em 0.6em 2.5em;
  }

</style>
