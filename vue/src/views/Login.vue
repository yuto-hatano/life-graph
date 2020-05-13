<template>
  <div id="loginSection">
    <form id="login" action="http://localhost:8081/Top">
      <h1>LOGIN</h1>
      <div id="input">
        <font-awesome-icon id="icon_id" icon="user" />
        <input id="id" type="text" placeholder="ID" autofocus required>
        <font-awesome-icon id="icon_lock" icon="lock" />
        <input id="password" type="password" placeholder="Password" required>
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

#icon_id {
position:absolute;
left:9px;/*アイコン左右の位置調整*/
top:25px;
background:none;
color:#666;
border:none;
font-size:28px;/*アイコンサイズ*/
padding: 6px 8px;
background-color: #dddddd;
}

#id {
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
