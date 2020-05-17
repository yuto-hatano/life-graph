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
            <input id="search_name" v-model="searchUser" type="text" placeholder="ユーザー名">
          </ol>
          <ol>
            <input id="search_day" v-model="serchDay" type="date">
            <input id="search_day" v-model="serchDay" type="date">
          </ol>
          <ol>
             <button id="clear" @click="clear">
              クリア
            </button>
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
        <div>
          <table id="table">
            <tr>
              <th :class="sortedClass('name')" @click="sortBy('name')">
                ユーザー名
              </th>
              <th :class="sortedClass('created_at')" @click="sortBy('created_at')">
                登録日時
              </th>
              <th :class="sortedClass('updated_at')" @click="sortBy('updated_at')">
                更新日時
              </th>
              <th>参照</th>
            </tr>
            <tbody v-if="isActive">
              <tr v-for="user in eventedAction" :key="user.id">
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
            </tbody>
          </table>
          <div v-if="isActive">
            <button id="reset" @click="resetting()">リセット</button>
          </div>
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
      ],
      sort: {
        key: '',
        isAct: false
      },
      searchUser: '',
      searchDay: ''
    }
  },

  computed: {
    eventedAction () {
      let list = this.users.slice()

      if (this.sort.key) {
        list.sort((a, b) => {
          a = a[this.sort.key]
          b = b[this.sort.key]
          return (a === b ? 0 : a > b ? 1 : -1) * (this.sort.isAsc ? 1 : -1)
        })
      }

      if (this.searchUser) {
        list = list.filter(element => {
          return Object.keys(element).some(key => {
            if (key === 'name') {
              return element[key].indexOf(this.searchUser) > -1
            }
          })
        })
      }

      if (this.searchDay) {
        list = list.filter(element => {
          return Object.keys(element).some(key => {
            if (key === 'updated_at') {
              return element[key].indexOf(this.searchDay) > -1
            }
          })
        })
      }
      return list
    }
  },

  methods: {
    active () {
      this.isActive = true
    },
    clear () {
      this.searchUser = ''
      this.searchDay = ''
    },
    sortedClass (key) {
      return this.sort.key === key ? `sorted ${this.sort.isAsc ? 'asc' : 'desc'}` : ''
    },
    sortBy (key) {
      this.sort.isAsc = this.sort.key === key ? !this.sort.isAsc : false
      this.sort.key = key
    },
    resetting () {
      this.sort.key = ''
      this.sort.isAsc = false
      this.searchUser = ''
      this.searchDay = ''
      this.isActive = false
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
  width: 20%;
  margin-top: 20px ;
  font-size: 1em;
  padding: 5px 0;
}

#clear {
  font-size: 1em;
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

th.sorted.desc::after{
    display: inline-block;
    content: '▼';
}

th.sorted.asc::after{
    display: inline-block;
    content: '▲';
}

th {
  padding: 10px 10px;
  cursor: pointer;
}

td {
  padding: 5px 20px;
}

#reset {
  font-size: 1em;
}
</style>
