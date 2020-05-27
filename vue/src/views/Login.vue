<template>
  <div id="loginSection">
    <h1>
      <span class="char1">L</span>
      <span class="char2">i</span>
      <span class="char3">f</span>
      <span class="char4">e</span>
      <span class="char5" />
      <span class="char6">G</span>
      <span class="char7">r</span>
      <span class="char8">a</span>
      <span class="char9">p</span>
      <span class="char10">h</span>
    </h1>
    <!-- <form id="login"> -->
    <div id="login">
      <span class="loginTable">LOGIN</span>
      <div v-if="isLoginError" class="login_error">
        Login Error!!
      </div>
      <div id="input">
        <div id="searchMail">
          <ValidationProvider v-slot="{ errors }" name="メールアドレス" rules="required|email">
            <label for="email"><img id="iconMail" src="../assets/mail.png"></label>
            <!-- <font-awesome-icon id="icon_email" icon="envelope" /> -->
            <input id="email" v-model="email" type="email" placeholder="Mail" autofocus required>
            <span id="validate">{{ errors[0] }}</span>
          </ValidationProvider>
        </div>
        <div id="searchPassword">
          <ValidationProvider v-slot="{ errors }" name="パスワード" rules="required">
            <!-- <font-awesome-icon id="icon_lock" icon="lock" /> -->
            <label for="password"><img id="iconPassword" src="../assets/password.png"></label>
            <input id="password" v-model="password" type="password" placeholder="Password" required>
            <span id="validate">{{ errors[0] }}</span>
          </ValidationProvider>
        </div>
      </div>
      <div id="action">
        <button id="submit" value="Enter" @click="login()">
          SIGN IN
        </button>
      </div>
      <div class="loginRegister">
        新規の方は <a id="signUp" href="">新規登録</a>
      </div>
    <!-- </form> -->
    </div>
    <div id="parent">
      <div class="message" />
      <div class="message" />
      <div class="message" />
      <div class="message" />
      <div class="message" />
      <div class="message" />
      <div class="message" />
      <div class="message" />
      <div class="message" />
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      email: '',
      password: ''
    }
  },
  computed: {
    // isValidated () {
    //   return Object.keys(this.fields).every(k => this.fields[k].validated) &&
    //     Object.keys(this.fields).every(k => this.fields[k].valid)
    // },
    token () {
      return this.$store.state.auth.token
    },

    account () {
      return this.$store.state.account.account
    },

    isLoginError () {
      return this.$store.state.auth.isLoginError
    }
  },
  watch: {
    token (newToken) {
      const userId = this.$store.state.auth.userId
      this.$store.dispatch(
        'account/fetchAccount', userId
      )
    },

    account (newAccount) {
      this.$router.push('/top')
    }
  },
  mounted () {
    if (this.$store.state.auth.token) {
      this.$router.push('/top')
    }
  },

  created () {
    this.$store.commit('auth/resetLoginError')
  },

  methods: {
    login () {
      // ログイン画面で入力したemailとpasswordをAPIに渡す
      this.$store.dispatch(
        'auth/create',
        {
          email: this.email,
          password: this.password
        }
      )
    }
  }
}
</script>

<style scoped>
#loginSection {
  background-color: #e5f3f3;
  height: auto;
  padding: 100px 0;
  z-index: -100;
}

#parent {
  height: 30vh;
  margin: 0 auto;
  padding: 0 200px 0 200px;
  position: relative;
  width: 600px;

  /* overflow: hidden; */
}

.message {
  animation: move 5s infinite linear;
  background-color: rgba(255, 233, 35, 0.788);
  border-radius: 220px;
  bottom: -100px;
  height: 60px;
  left: 200px;
  position: fixed;
  width: 60px;
  z-index: 0;
}

.message:nth-child(2) {
  animation-delay: 2s;
  left: 900px;
}

.message:nth-child(3) {
  animation-delay: 6s;
  left: 500px;
}

.message:nth-child(4) {
  animation-delay: 5s;
  left: 400px;
}

.message:nth-child(5) {
  animation-delay: 2s;
  left: 700px;
}

.message:nth-child(6) {
  animation-delay: 10s;
  left: 800px;
}

.message:nth-child(7) {
  animation-delay: 9s;
  left: 60px;
}

.message:nth-child(8) {
  animation-delay: 1s;
  left: 1200px;
}

.message:nth-child(9) {
  animation-delay: 10.8s;
  left: 1000px;
}

@keyframes move {
  0% {
    bottom: -50px;
  }
  100% {
    bottom: 100%;
  }
}

@keyframes fadeIn {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

.message:hover {
  background: #e5f3f3;
}

h1 span {
  color: #fff;
  font: 120px 'Arvo', serif;
  font-weight: bold;
  height: 600px;
  letter-spacing: 0.01em;
  line-height: 0.9em;
  position: absolute;
  text-shadow:
    -3px -3px 0  rgb(59, 59, 59),
    3px -3px 0  rgb(59, 59, 59),
    -3px 3px 0  rgb(59, 59, 59),
    3px 3px 0  rgb(59, 59, 59),
    4px 4px 0 #fff,
    5px 5px 0 #fff,
    6px 6px 0 #fff,
    7px 7px 0 #fff;
  -webkit-transform: scaleY(0.7);
  -moz-transform: scaleY(0.7);
  transform: scaleY(0.7);
  transform-origin: bottom center;
  width: 20px;
  z-index: 30;
}

.char1 {
  transform: rotate(-40deg);
}

.char2 {
  transform: rotate(-30deg);
}

.char3 {
  transform: rotate(-24deg);
}

.char4 {
  transform: rotate(-18deg);
}

.char5 {
  transform: rotate(-4deg);
}

.char6 {
  transform: rotate(-5deg);
}

.char7 {
  transform: rotate(7deg);
}

.char8 {
  transform: rotate(14.3deg);
}

.char9 {
  transform: rotate(22.5deg);
}

.char10 {
  transform: rotate(32deg);
}

/* #loginSection {
  background-color:#e5f3f3;
  width: 100%;
  height: auto;
  padding: 100px 0;
} */

#login {
  background-color: #fff;
  border-bottom: 4px solid #434a52;
  border-top: 4px solid #434a52;
  margin: 250px auto 0 auto;
  padding: 40px 0;
  position: relative;
  width: 40%;
  z-index: 90;
}

.loginTable {
  color: rgb(82, 82, 82);
  font: normal 43px/1 Helvetica, Arial, sans-serif;
  letter-spacing: 4px;
  margin: 20px 0;
  position: relative;
  text-align: center;
  text-shadow: 0 1px 0 rgba(255, 255, 255, 0.7), 0 2px 0 rgba(0, 0, 0, 0.5);
  text-transform: uppercase;
  z-index: 50;
}

/* h1:after,
h1:before {
  background-color: #777;
  content: "";
  height: 1px;
  position: absolute;
  top: 21px;
  width: 200px;
} */

/* h1:after {
  right: 0;
}

h1:before {
  left: 0;
} */

#input {
  margin: 0 auto;
  padding: 25px 12px 15px 12px;
  position: relative;
  width: 70%;
  z-index: 80;
}

#validate {
  color: rgb(233, 0, 0);
  display: inline;
  font-size: 13pt;
}

#icon_email {
  background: none;
  background-color: #ddd;
  border: none;
  color: #666;
  font-size: 28px;
  left: 8px;
  padding: 6px 7px;
  position: absolute;
  top: 25px;
}

#iconMail {
  display: inline-block;
  float: left;
  width: 8%;
}

#email {
  border: solid 1px #666;
  cursor: pointer;
  display: inline-block;
  font-size: 1em;
  font-weight: 400;
  margin-bottom: 5px;
  padding: 10px;
  width: 100%;
}

#icon_lock {
  background: none;
  background-color: #ddd;
  border: none;
  color: #666;
  font-size: 29px;
  left: 8px;
  padding: 4.6px 8px;
  position: absolute;
  top: 96px;

  /* アイコン左右の位置調整 */

  /* アイコンサイズ */
}

#iconPassword {
  display: inline-block;
  float: left;
  margin-bottom: 5px;
  width: 8%;
}

#password {
  border: solid 1px #666;
  cursor: pointer;
  display: block;
  font-size: 1em;
  font-weight: 400;
  margin-bottom: 5px;
  margin-top: 40px;
  padding: 9px 10px;
  width: 100%;
}

#action {
  margin-left: auto;
  width: 200px;
}

#submit {
  /* 背景色 */
  background: #fd9535;

  /* 少し濃い目の色 */
  border: solid 2px #f59402;

  /* 角の丸みをもたせる */
  border-radius: 4px;
  box-shadow: inset 0 2px 0 rgba(255, 255, 255, 0.2), 0 2px 2px rgba(0, 0, 0, 0.19);
  color: #fff;
  cursor: pointer;
  display: inline-block;
  font-size: 9pt;
  font-weight: bold;
  margin: 8px;
  padding: 0.6em 1.5em 0.6em 1.5em;
  position: relative;
  text-decoration: none;
  z-index: 80;
}

#submit:active {
  border-bottom: solid 2px #fd9535;
  box-shadow: 0 0 2px rgba(0, 0, 0, 0.3);
}

#signUp {
  color: #4699ca;
  cursor: pointer;
}

#signUp:hover {
  color: #ff7043;
}

.loginRegister {
  font-family: 'M PLUS Rounded 1c', sans-serif;
  position: relative;
  z-index: 100;
}

.loginAlert{
  background-color: #FADBDA;
  border: none;
  color: #666;
  font-family: 'Open Sans', sans-serif;
  font-size: 10px;
  height: 16px;
  transition: border-color 0.3s;
  width: 280px;
  display: inline-block;
  text-align: center;
  padding: 4px 0px 0px 0px;
  border-radius:0px;
}
.loginNoAlert{
  display: block;
  text-align: center;
  margin: 0px 0px 0px 0px;
  height: 16px;
}

</style>
