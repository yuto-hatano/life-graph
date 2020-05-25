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
    // tokenの状態を監視して、tokenが更新されたらtop画面に遷移する
    // token (newToken) {
    //   this.$router.push('/top')
    // }
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
  z-index: -100;
  background-color:#e5f3f3;
  height: auto;
  padding: 100px 0;
}
#parent {
  padding: 0 200px 0 200px;
  margin: 0 auto;
  position: relative;
  width: 600px;
  height: 30vh;
  /* overflow: hidden; */
}
.message {
  z-index: 0;
  position: fixed;
  left: 200px;
  bottom: -100px;
  height: 60px;
  width: 60px;
  background-color: rgba(255, 233, 35, 0.788);
  border-radius: 220px;
  animation: move 5s infinite linear;
}
.message:nth-child(2) {
  left: 900px;
  animation-delay: 2s;
}
.message:nth-child(3) {
  left: 500px;
  animation-delay: 6s;
}
.message:nth-child(4) {
  left: 400px;
  animation-delay: 5s;
}
.message:nth-child(5) {
  left: 700px;
  animation-delay: 2s;
}
.message:nth-child(6) {
  left: 800px;
  animation-delay: 10s;
}
.message:nth-child(7) {
  left: 60px;
  animation-delay: 9s;
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
        opacity:0;
    }
    100% {
        opacity:1;
    }
}

.message:hover {
  background: #e5f3f3;
}

h1 span {
  z-index: 30;
  font: 120px 'Arvo', serif;
  color: #fff;
  height: 600px;
  position: absolute;
  font-weight: bold;
  text-shadow:
    -3px -3px 0  rgb(59, 59, 59),
    3px -3px 0  rgb(59, 59, 59),
    -3px 3px 0  rgb(59, 59, 59),
    3px 3px 0  rgb(59, 59, 59),
    4px 4px 0 #fff,
    5px 5px 0 #fff,
    6px 6px 0 #fff,
    7px 7px 0 #fff;
  line-height: 0.9em;
  letter-spacing: 0.01em;  width: 20px;
  transform: scaleY(0.7);
  -webkit-transform: scaleY(0.7);
  -moz-transform: scaleY(0.7);
  transform-origin: bottom center;
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
  z-index: 90;
  position: relative;
  width: 40%;
  border-top: 4px solid #434a52;
  border-bottom: 4px solid #434a52;
  padding: 40px 0;
  margin: 250px auto 0 auto;
  background-color: #fff;
}

.loginTable {
  z-index: 50;
  text-shadow: 0 1px 0 rgba(255, 255, 255, .7), 0px 2px 0 rgba(0, 0, 0, .5);
  text-transform: uppercase;
  text-align: center;
  color: rgb(82, 82, 82);
  margin: 20px 0;
  letter-spacing: 4px;
  font: normal 43px/1 Helvetica, Arial, sans-serif;
  position: relative;
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
  z-index: 80;
  width: 70%;
  padding:25px 12px 15px 12px;
  margin: 0 auto;
  position: relative;
}

#validate {
  font-size: 13pt;
  display: inline;
  color: rgb(233, 0, 0);
}

#icon_email {
  position:absolute;
  left:8px;
  top:25px;
  background:none;
  color:#666;
  border:none;
  font-size:28px;
  padding: 6px 7px;
  background-color: #dddddd;
}

#iconMail {
  display: inline-block;
  width: 8%;
  float: left;
}

#email {
  cursor : pointer;
  padding: 10px ;
  display: inline-block;
  width: 100%;
  font-size: 1em;
  font-weight: 400;
  border: solid 1px #666;
  margin-bottom: 5px;
}

#icon_lock {
  position:absolute;
  left:8px;/*アイコン左右の位置調整*/
  top:96px;
  background:none;
  color:#666;
  border:none;
  font-size:29px;/*アイコンサイズ*/
  padding: 4.6px 8px;
  background-color: #dddddd;
}

#iconPassword {
  display: inline-block;
  width: 8%;
  float: left;
  margin-bottom: 5px;
}

#password {
  cursor : pointer;
  padding: 9px 10px ;
  display: block;
  width: 100%;
  font-size: 1em;
  font-weight: 400;
  border: solid 1px #666;
  margin-top: 40px;
  margin-bottom: 5px;
}

#action {
  width: 200px;
  margin-left: auto;
}

#submit {
  z-index: 80;
  cursor : pointer;
  font-size: 9pt;
  position: relative;
  display: inline-block;
  padding: 0.6em 1.5em 0.6em 1.5em;
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

#submit:active {
  border-bottom: solid 2px #fd9535;
  box-shadow: 0 0 2px rgba(0, 0, 0, 0.30)
}

#signUp {
  color: #4699ca;
  cursor: pointer;
}

#signUp:hover {
  color: #ff7043;
}
.loginRegister {
  z-index: 100;
  position: relative;
  font-family: 'M PLUS Rounded 1c', sans-serif;
}
</style>
