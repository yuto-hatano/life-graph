<template>
  <div id="registerSection">
    <h1>Life Graph</h1>
    <form action="">
      <fieldset id="input">
        <table id="field">
          <tr>
            <th scope="row">
              年齢
            </th>
            <td>
              <input id="age" type="text" autofocus required>
            </td>
          </tr>
          <tr>
            <th scope="row">
              スコア
            </th>
            <td>
              <input id="score" type="text" autofocus required>
            </td>
          </tr>
          <tr>
            <th scope="row">
              コメント
            </th>
            <td>
              <textarea id="comment" cols="30" rows="5" placeholder="内容を入力してください。" />
            </td>
          </tr>
        </table>
      </fieldset>
      <fieldset id="action">
        <input id="reset" type="reset" value="クリア">
        <input id="submit" type="submit" value="登録">
      </fieldset>
    </form>
  </div>
</template>

<style scoped>

h1 {
  /* font-style: Roboto Slab; */
  background-color: #e5f3f3;
  font-size: 50px;
  padding: 35px 0;
}

#input {
  border: 1px solid #434a52;
  width: 60%;
  margin: 25px auto;
  background-color: #fffcf5;
}

#field {
  width: 60%;
  margin: 45px auto;
  background-color: #fff;
}

th {
  vertical-align: middle;
  padding:10px 15px;
  border:1px solid #666;
}

td {
  padding:10px 15px;
  border:1px solid #666;
}

#age,#score,#comment {
  width: 100%;
  font-size: 1em;
}

#action {
  margin-left: 30%;
}

#reset {
  cursor: pointer;
  font-size: 1em;
  background-color: #dddddd;
  border-radius: 5px;
  box-shadow: 1px 2px #dddddd;
  margin-right: 20px;
}

#reset:active {
  box-shadow: none;
  position: relative;
  top: 2px;
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
</style>
