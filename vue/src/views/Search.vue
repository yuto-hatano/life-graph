<template>
  <div id="searchSection">
    <div>
      <Header />
    </div>
    <h1>Life Graph</h1>
    <div id="users">
      <div id="field">
        <p id="serch_tittle">
          グラフを検索
        </p>
        <ul>
          <ol>
            <input id="search_name" v-model="user" type="text" placeholder="ユーザー名">
          </ol>
          <ol>
            <input id="search_day" type="date" >
          </ol>
          <ol>
            <button id="submit" @click="active">
              検索
            </button>
          </ol>
        </ul>
      </div>
      <div id="output">
        <p id="serch_tittle">
          検索結果
        </p>
        <div v-if="isActive">
          <table id="table">
            <tr>
              <th>ユーザー名</th>
              <th>登録日時</th>
              <th>更新日時</th>
              <th>参照</th>
            </tr>
            <tr v-for="user in users" :key="user.id">
              <td>
                {{ user.name }}
              </td>
              <td>
                {{ user.created_at }}
              </td>
              <td>
                {{ user.updated_at }}
              </td>
              <td>
                <router-link to="'/Reference/' + user.id" tag="button" class="button">
                参照
              </router-link>
              </td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from '../views/Header.vue'

export default {
  name: 'Search',
  components: {
    Header
  },
  data () {
    return {
      isActive: false,
      users: [
        {
          id: 1,
          name: 'ishida',
          created_at: '2020/05/01',
          updated_at: '2020/05/13'
        },
        {
          id: 2,
          name: 'sekiguti',
          created_at: '2020/05/03',
          updated_at: '2020/05/10'
        },
        {
          id: 3,
          name: 'sato',
          created_at: '2020/05/02',
          updated_at: '2020/05/11'
        }
      ]
    }
  },
  methods: {
    active () {
      this.isActive = true
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
  margin: 40px auto;
  width: 50%;
  padding: 30px 0;
  background-color: #fffcf5;
}

#serch_tittle {
  font-size: 20px;
}

#search_name {
  width: 50%;
  margin-top: 20px ;
  font-size: 1em;
  padding: 5px 0;
}

#search_day {
  width: 50%;
  margin-top: 20px ;
  font-size: 1em;
  padding: 5px 0;
}

#submit {
  font-size: 1em;
  margin: 20px 0 0 40%;
}

#output {
  border: 1px solid #434a52;
  border-radius: 50px;
  margin: 60px auto;
  width: 50%;
  padding: 20px 0;
  background-color: #fffcf5;
}

#table {
  margin: 30px auto;
  border: 1px solid #434a52;
  border-radius: 5px;
  width: 70%;
}

th {
  padding: 10px 10px;
}

td {
  padding: 5px 20px;
}

.button {
  font-size: 0.8em;
}
</style>
