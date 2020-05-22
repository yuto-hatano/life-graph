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
    debugger
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
    background-color: #e6e6e6;
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
    margin-bottom: 0;
  }

  .authority{
    margin-top: 0;
  }

  .btn_top,.btn_logout{
      width: 100px;
      height: 50px;
    }

  .btn_logout{
    position: relative;
    top: -10px;
    right:30px;
  }

  .btn_top{
    position: absolute;
    top: 5px;
    left: 20px;
    margin-right: 20px;
    z-index:1;
  }

</style>
