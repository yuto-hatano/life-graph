<template>
  <div id="searchSection">
    <h1>Life Graph</h1>
    <div id="users">
      <div class="container">
        <div id="field">
          <p id="serch_tittle">
            グラフを検索
          </p>
          <input id="search" type="text" @click="search">
          <input id="submit" type="submit" value="検索">
        </div>
        <div id="output">
          <p id="serch_tittle">
            検索結果
          </p>
          <table id="table">
            <tr>
              <th>ユーザー名</th>
              <th>登録日時</th>
              <th>更新日時</th>
            </tr>
            <tr>
              <td>
                {{ name }}
              </td>
              <td>
                {{ created_at }}
              </td>
              <td>
                {{ updated_at }}
              </td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      name: 'ishida',
      created_at: 'aaa',
      updated_at: 'aaa'
    }
  }
}
</script>

<style scoped>

h1 {
  /* font-style: Roboto Slab; */
  background-color: #e5f3f3;
  font-size: 40px;
  padding: 35px 0;
}

#field {
  border: 1px solid #434a52;
  border-radius: 50px;
  margin: 20px auto;
  width: 60%;
  padding: 30px 0;
  background-color: #fffcf5;
}

#serch_tittle {
  font-size: 20px;
  margin-right: 70%;
}

#search {
  width: 60%;
  margin-top: 10px ;
  font-size: 1em;
  padding: 5px 0;
}

#submit {
  font-size: 1em;
}

#output {
  border: 1px solid #434a52;
  border-radius: 50px;
  margin: 60px auto;
  width: 60%;
  padding: 20px 0;
  background-color: #fffcf5;
}

#table {
  margin: 30px auto;
  border-collapse:separate;
  border-spacing: 0;
  border: 1px solid #434a52;
  border-radius: 5px;
}

th {
  padding: 10px 30px;
  border: 1px solid #434a52;
}

td {
  padding: 5px 30px;
  border: 1px solid #434a52;
}
</style>
