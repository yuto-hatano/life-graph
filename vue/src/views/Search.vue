<template>
  <div id="searchSection">
    <div>
      <Header />
    </div>
    <div class="message_3">
      You can search "LifeGraph".
    </div>
    <h1>Life Graph</h1>
    <div id="users">
      <div id="field">
        <p id="serch_tittle">
          グラフを検索
        </p>
        <div v-if="isOpenSearch">
          <button id="searchUserName" @click="searchUserName">
            ユーザー名で検索
          </button>
          <button id="searchUpdate" @click="searchUpdate">
            更新日時で検索
          </button>
        </div>
        <div v-if="isOpenUser">
          <ValidationObserver v-slot="{ invalid }" @active.prevent="active()">
            <validation-provider v-slot="{ errors }" name="ユーザー名" rules="required">
              <input id="search_name" v-model="searchUser" type="text" placeholder="ユーザー名">
              <span>{{ errors[0] }}</span>
            </validation-provider>
            <div>
              <button id="clear" @click="clear">
                クリア
              </button>
              <button id="return" @click="returnScreen">
                戻る
              </button>
              <button id="submit" :disabled="invalid" @click="search_name()">
                検索
              </button>
            </div>
          </ValidationObserver>
        </div>
        <div v-if="isOpenUpdata">
          <ValidationObserver v-slot="{ invalid }" @active.prevent="active()">
            <validation-provider v-slot="{ errors }" name="更新日時" rules="required">
              <input id="search_From" v-model="updatedFrom" type="date">
              <span>{{ errors[0] }}</span>
            </validation-provider>
            <p>〜</p>
            <validation-provider v-slot="{ errors }" name="更新日時" rules="required">
              <input id="search_To" v-model="updatedTo" type="date">
              <span>{{ errors[0] }}</span>
            </validation-provider>
            <div>
              <button id="clear" @click="clear">
                クリア
              </button>
              <button id="return" @click="returnScreen">
                戻る
              </button>
              <button id="submit" :disabled="invalid" @click="search_date()">
                検索
              </button>
            </div>
          </ValidationObserver>
        </div>
      </div>
      <div id="output">
        <p id="serch_tittle">
          検索結果
        </p>
        <div>
          <table id="table">
            <tr class="table_2">
              <th>
                ユーザー名
              </th>
              <th>
                <!-- <th :class="sortedClass('created_at')" @click="sortBy('created_at')"> -->
                登録日時
              </th>
              <th>
                <!-- <th :class="sortedClass('updated_at')" @click="sortBy('updated_at')"> -->
                更新日時
              </th>
              <th>参照</th>
            </tr>
            <tbody v-if="isActive">
              <tr v-for="user in users" :key="user.id">
                <!-- <tr v-for="user in eventedAction" :key="user.id"> -->
                <td>
                  {{ user.name }}
                </td>
                <td>
                  {{ user.created_at | moment }}
                </td>
                <td>
                  {{ user.updated_at | moment }}
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
            <button id="reset" @click="resetting()">
              リセット
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from '../views/Header.vue'
// import Footer from '../components/Footer.vue'
import moment from 'moment'
// import qs from 'qs'

export default {
  name: 'Search',
  components: {
    Header
  },
  filters: {
    moment: function (date) {
      return moment(date).format('YYYY-MM-DD')
    }
  },
  data () {
    return {
      user_id: '',
      username: '',
      created_at: '',
      updated_at: '',
      // searchUser:'',
      // startDate:'',
      // finishDate:'',
      updatedFrom: null,
      updatedTo: null,
      isActive: false,
      isOpenUser: false,
      isOpenUpdata: false,
      isOpenSearch: true,
      // デフォルトを降順にする
      sortDesc: true,
      // 検索条件だけに沿った配列
      // eventedAction: [],
      users: [
        // {
        //   user_id: 1,
        //   name: 'ishida',
        //   created_at: '2020/05/01',
        //   updated_at: '2020/05/13'
        // },
        // {
        //   user_id: 2,
        //   name: 'sekiguti',
        //   created_at: '2020/05/03',
        //   updated_at: '2020/05/10'
        // },
        // {
        //   user_id: 3,
        //   name: 'sato',
        //   created_at: '2020/05/02',
        //   updated_at: '2020/05/11'
        // }
      ],
      // sort: {
      //   isAct: false,
      //   key: ''
      // },
      searchUser: ''
      // updatedFroｍ: '',
      // updatedTo: ''
    }
  },

  computed: {
    searchContents () {
      return this.$store.state.SearchGraph.users
    }
    //   eventedAction () {
    //     const list = this.users.slice()

    //     if (this.sort.key) {
    //       list.sort((a, b) => {
    //         a = a[this.sort.key]
    //         b = b[this.sort.key]
    //         return (a === b ? 0 : a > b ? 1 : -1) * (this.sort.isAsc ? 1 : -1)
    //       })
    //     }

    //     return list
    //   }

  },

  watch: {
    searchContents (newContents) {
      this.setUsers()
    }
  },

  methods: {
    searchUserName () {
      this.isOpenSearch = false
      this.isOpenUser = true
    },
    searchUpdate () {
      this.isOpenSearch = false
      this.isOpenUpdata = true
    },

    search_name () {
      const data = {
        likeName: this.searchUser,
        startDate: '',
        finishDate: ''
      }

      this.isActive = true
      this.$store.dispatch(
        //  storeのactionsを呼び出す
        // 'SearchGraph/SearchGraphs',
        'SearchGraph', data
      )
    },
    search_date () {
      const data = {
        likeName: '',
        startDate: this.updatedFrom,
        finishDate: this.updatedTo
      }

      this.isActive = true
      this.$store.dispatch(
        'SearchGraph', data
      )
    },

    returnScreen () {
      this.isOpenSearch = true
      this.isOpenUpdata = false
      this.isOpenUser = false
    },
    clear () {
      this.searchUser = ''
      this.updatedFrom = ''
      this.updatedTo = ''
    },
    setUsers () {
      this.users = this.$store.state.SearchGraph.users
    },
    // sortBy (key) {
    //   this.sort.isAsc = this.sort.key === key ? !this.sort.isAsc : false
    //   this.sort.key = key
    // },
    // sortedClass (key) {
    //   return this.sort.key === key ? `sorted ${this.sort.isAsc ? 'asc' : 'desc'}` : ''
    // },
    resetting () {
      this.sort.key = ''
      this.sort.isAsc = false
      this.searchUser = ''
      this.updatedFrom = ''
      this.updatedTo = ''
      this.isActive = false
      this.isOpenUser = false
      this.isOpenUpdata = false
      this.isOpenSearch = true
    }
  }
}
</script>

<style scoped>
#searchSection {
  background-color: #ffffff;
  background-image: url("https://www.transparenttextures.com/patterns/ag-square.png");
}
h1 {
  font-family: 'Roboto Slab', serif;
  font-size: 60pt;
  padding: 50px;
}
.message_3 {
  font-family: 'Courgette', cursive;
  font-style: italic;
  font-size: 15pt;
  height: 25px;
  font-weight: bold;
  padding-bottom: 5px;
}

#field {
  border: 1px solid #434a52;
  border-radius: 50px;
  margin: 40px auto;
  width: 50%;
  padding: 30px 0;
  background-color: #fffcf5;
}

p {
  color: black;
}

span {
  font-size: 10pt;
  display: block;
  margin-top: 10px;
  color: rgb(233, 0, 0);
}

#serch_tittle {
  font-size: 25px;
  font: bold;
}

#searchUserName {
  cursor: pointer;
  font-family: 'M PLUS Rounded 1c', sans-serif;
  position: relative;
  display: inline-block;
  padding: 0.6em 2em 0.6em 2em;
  margin-top: 20px;
  margin-left: 30px;
  margin-right: 30px;
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

#searchUpdate {
  cursor: pointer;
  font-family: 'M PLUS Rounded 1c', sans-serif;
  position: relative;
  display: inline-block;
  padding: 0.6em 2.1em 0.6em 2.1em;
  margin-left: 30px;
  margin-right: 30px;
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

#search_name {
  width: 50%;
  margin-top: 20px ;
  font-size: 1em;
  padding: 5px 0;
}

#search_From {
  width: 20%;
  margin-top: 20px;
  margin-bottom: 10px;
  font-size: 1em;
  padding: 5px 0;
}

#search_To {
  width: 20%;
  margin-top: 10px;
  font-size: 1em;
  padding: 5px 0;
}

#clear {
  font-size: 8pt;
  cursor: pointer;
  font-family: 'M PLUS Rounded 1c', sans-serif;
  position: relative;
  display: inline-block;
  padding: 0.6em 1em 0.6em 1em;
  margin-left: 30px;
  margin-right: 30px;
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

#return {
  cursor: pointer;
  font-size: 8pt;
  font-family: 'M PLUS Rounded 1c', sans-serif;
  position: relative;
  display: inline-block;
  padding: 0.6em 1em 0.6em 1em;
  margin-left: 30px;
  margin-right: 30px;
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

#submit {
  font-size: 8pt;
  color:rgb(158, 157, 157);
  padding: 0.6em 1em 0.6em 1em;
  margin-left: 30px;
  margin-right: 30px;
  font-family: 'M PLUS Rounded 1c', sans-serif;
  cursor: pointer;
  background-color: #dddddd;
  border-radius: 5px;
  box-shadow: 1px 2px #dddddd;
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
