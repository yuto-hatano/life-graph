<template>
  <div id="signupSection">
    <div class="plate">
      <div class="script">
        <p class="signup_tittle">
          Life Graph
        </p>
      </div>
    </div>
    <!-- <form id="login"> -->
    <div id="signup">
      <h1>SIGNUP</h1>
      <div id="input">
        <font-awesome-icon id="icon_email" icon="envelope" />
        <input id="email" v-model="email" type="email" placeholder="Mail" autofocus required>
        <font-awesome-icon id="icon_user" icon="user" placeholder="Name" required />
        <input id="username" v-model="username" type="username" placeholder="username" required>
        <font-awesome-icon id="icon_lock" icon="lock" />
        <input id="password" v-model="password" type="password" placeholder="Password" required>
      </div>
      <div id="action">
        <button id="submit" value="Enter" @click="SignUp()">
          SIGN UP
        </button>
        <!-- </form> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      email: '',
      username: '',
      password: ''
    }
  },
  computed: {
    signUpSuccess () {
      return this.$store.state.auth.message
    }
  },
  watch: {
    signUpSuccess (newSignUpSuccess) {
      this.$router.push('/login')
    }
  },
  methods: {
    SignUp () {
      this.$store.dispatch(
        'auth/signup',
        {
          email: this.email,
          username: this.username,
          password: this.password
        }
      )
    }
  }
}
</script>

<style scoped>
.plate {
  font-size: 150px;
}

.login_tittle {
  color: #fff;
  font-family: 'Arvo', serif;
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
  letter-spacing: 0.01em;
  transform: scaleY(0.7);
  -webkit-transform: scaleY(0.7);
  -moz-transform: scaleY(0.7);
  margin:0;
  padding-bottom: 100px;
  text-align: center;
}

#SignupSection {
  background-color:#e5f3f3;
  width: 100%;
  height: auto;
  padding: 100px 0;
}

#Signup {
  width: 40%;
  border-top: 4px solid #434a52;
  border-bottom: 4px solid #434a52;
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
  font: normal 43px/1 Helvetica, Arial, sans-serif;
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
  cursor : pointer;
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
  left:10px;/*アイコン左右の位置調整*/
  top:132px;
  background:none;
  color:#666;
  border:none;
  font-size:26px;/*アイコンサイズ*/
  padding: 6px 9px;
  background-color: #dddddd;
}

#password {
  cursor : pointer;
  padding: 9px 10px ;
  display: block;
  width: 100%;
  font-size: 1em;
  font-weight: 400;
  border: solid 1px #666;
}

#icon_user {
position:absolute;
left:8px;/*アイコン左右の位置調整*/
top:78px;
background:none;
color:#666;
border:none;
font-size:28px;/*アイコンサイズ*/
padding: 6px 7px;
background-color: #dddddd;
}

#username{
  cursor : pointer;
  padding: 10px ;
  display: block;
  width: 100%;
  font-size: 1em;
  font-weight: 400;
  border: solid 1px #666;
  margin-bottom: 13px;
}

#action {
  width: 200px;
  margin-left: auto;
}

#submit {
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
  border-bottom: solid 2px #d27d00;
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
  font-family: 'Noto Sans JP', sans-serif;
}
</style>
