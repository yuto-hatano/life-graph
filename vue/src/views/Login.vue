<template>
  <div id="loginSection">
    <form id="login">
      <h1>LOGIN</h1>
      <div id="input">
        <input id="id" type="text" placeholder="&#xf2bd;ID" autofocus required>
        <input id="password" type="password" placeholder="&#xf084;Password" required>
      </div>
      <div id="action">
        <input id="submit" type="submit" value="Enter">
      </div>
      新規の方は<a id="signUp" href="">新規登録</a>
    </form>
  </div>
</template>

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
  font: normal 40px/1 Verdana, Helvetica;
  position: relative;
}

h1:after,
h1:before {
  background-color: #777;
  content: "";
  height: 1px;
  position: absolute;
  top: 23px;
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
  padding:25px 12px 15px 12px;
  margin: 0 auto;
}

#id,#password {
  padding: 8px 10px ;
  display: block;
  width: 100%;
  font-size: 1em;
  font-weight: 300;
}

#id {
  margin-bottom: 13px;
  font-family: FontAwesome;
}

#action {
  width: 200px;
  margin-left: auto;
  font-family: FontAwesome;
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
