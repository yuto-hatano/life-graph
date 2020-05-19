<template>
  <div id="loginSection">
    <form  id="login" name="login" action="auth/Top" @submit.prevent="handleLogin">
      <h1>LOGIN</h1>
      <div id="input">
        <font-awesome-icon id="icon_email" icon="envelope" />
        <label for="email" />
        <input id="email" v-model="user.email"
               v-validate="'required'" type="email" placeholder="メールアドレス"
               autofocus required name="email"
        >
        <font-awesome-icon id="icon_lock" icon="lock" />
        <labal for="password" />
        <input id="password" v-model="user.password" v-validate="'required'" type="password"
               placeholder="Password" required name="password"
        >
      </div>
      <div id="action">
        <input id="submit" type="submit" value="Enter">
        <span v-show="loading" class="spinner-border spinner-border-sm" />
      </div>
      新規の方は<a id="signUp" href="">新規登録</a>
    </form>
  </div>
</template>

<script>
import User from '../models/user'

export default {
  name: 'Login',
  data () {
    return {
      user: new User('', ''),
      loading: false,
      message: ''
    }
  },
  computed: {
    loggedIn () {
      return this.$store.state.auth.status.loggedIn
    }
  },
  created () {
    if (this.loggedIn) {
      this.$router.push('auth/Top')
    }
  },
  methods: {
    handleLogin () {
      this.loading = true
      this.$validator.validateAll().then(isValid => {
        if (!isValid) {
          this.loading = false
          return
        }

        if (this.user.email && this.user.password) {
          this.$store.dispatch('auth/login', this.user).then(
            () => {
              this.$router.push('/Top')
            },
            error => {
              this.loading = false
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString()
            }
          )
        }
      })
    }
  }
}
// export default {
//   data() {
//     return {

//     }
//   }
// }
</script>

<style scoped>
#loginSection {
  background-color:#e5f3f3;
  width: 100%;
  height: auto;
  padding: 100px 0;
}

#login {
  width: 40%;
  border-top: 3px solid #434a52;
  border-bottom: 3px solid #434a52;
  padding: 40px 0;
  margin: 0 auto;
  background-color: #fff;
}

h1 {
  text-shadow: 0 1px 0 rgba(255, 255, 255, .7), 0px 2px 0 rgba(0, 0, 0, .5);
  text-transform: uppercase;
  text-align: center;
  color: #666;
  margin: 20px 0 ;
  letter-spacing: 4px;
  font: normal 40px/1 Helvetica, Arial, sans-serif;
  position: relative;
}

h1:after,
h1:before {
  background-color: #777;
  content: "";
  height: 1px;
  position: absolute;
  top: 21px;
  width: 200px;
}

h1:after {
  right: 0;
}

h1:before {
  left: 0;
}

#input {
  width: 70%;
  padding:25px 12px 15px 50px;
  margin: 0 auto;
  position: relative;
}

#icon_email {
position:absolute;
left:8px;/*アイコン左右の位置調整*/
top:25px;
background:none;
color:#666;
border:none;
font-size:28px;/*アイコンサイズ*/
padding: 6px 7px;
background-color: #dddddd;
}

#email {
  padding: 10px ;
  display: block;
  width: 100%;
  font-size: 1em;
  font-weight: 400;
  border: solid 1px #666;
  margin-bottom: 13px;
}

#icon_lock {
  position:absolute;
  left:9px;/*アイコン左右の位置調整*/
  top:78px;
  background:none;
  color:#666;
  border:none;
  font-size:26px;/*アイコンサイズ*/
  padding: 6px 9px;
  background-color: #dddddd;
}

#password {
  padding: 9px 10px ;
  display: block;
  width: 100%;
  font-size: 1em;
  font-weight: 400;
  border: solid 1px #666;
}

#action {
  width: 200px;
  margin-left: auto;
}

#submit {
  cursor: pointer;
  font-size: 1em;
  background-color: #dddddd;
  border-radius: 5px;
  box-shadow: 1px 2px #dddddd;
}

#submit:active {
  box-shadow: none;
  position: relative;
  top: 2px;
}

#signUp {
  color: #4699ca;
  cursor: pointer;
}

#signUp:hover {
  color: #ff7043;
}
</style>
