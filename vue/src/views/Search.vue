<template>
  <div id="searchSection">
    <div>
      <Header />
    </div>
    <h1>Life Graph</h1>
    <div class="message_3">
      You can search "LifeGraph".
    </div>
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
              <span id="validate">{{ errors[0] }}</span>
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
              <span id="validate">{{ errors[0] }}</span>
            </validation-provider>
            <p>〜</p>
            <validation-provider v-slot="{ errors }" name="更新日時" rules="required">
              <input id="search_To" v-model="updatedTo" type="date">
              <span id="validate">{{ errors[0] }}</span>
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
      <div v-if="isActive" id="output">
        <p id="serch_tittle">
          検索結果
        </p>
        <div>
          <table id="table">
            <tr class="table_2">
              <th @click="sortBy('name')">
                ユーザー名
              </th>
              <th :class="['sortable',{active: sortKey == 'created_at' }]" @click="sortBy(&quot;created_at&quot;)">
                登録日時
                <span :class="sortOrders['created_at'] > 0 ? 'asc' : 'desc'">▼</span>
              </th>
              <th :class="{ active: sortKey == 'updated_at' }" class="sortable" @click="sortBy(&quot;updated_at&quot;)">
                更新日時
                <span :class="sortOrders['updated_at'] > 0 ? 'asc' : 'desc'">▼</span>
              </th>
              <th>参照</th>
            </tr>
            <tbody>
              <tr v-for="(user,index) in filteredData" :key="index">
                <!-- <tr v-for="user in eventedAction" :key="user.id"> -->
                <!-- 非表示 -->
                <td hidden>
                  {{ user.id }}
                </td>
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
                  <router-link :to="{name: 'Reference',params:{userId:user.id}}">
                    <button id="reference" @click="reference(index)">
                      参照
                    </button>
                  </router-link>
                  <!-- <router-link to="'/Reference/' + user.id" tag="button" class="button">
                    参照
                  </router-link> -->
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
    <Footer />
  </div>
</template>

<script>
import Header from '../views/Header.vue'
import Footer from '../components/Footer.vue'
import moment from 'moment'
// import qs from 'qs'

export default {
  name: 'Search',
  components: {
    Header,
    Footer
  },
  filters: {
    moment: function (date) {
      return moment(date).format('YYYY-MM-DD')
    }
  },
  data () {
    const columnsData = {
      colmuns: [
        'name',
        'created_at',
        'updated_at'
      ],
      users: [],
      sortOrders () {
        const sortOrder = {}
        columnsData.colmuns.forEach(function (key) {
          sortOrder[key] = 1
        })
        return sortOrder
      }
    }
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
      sortKey: '', // ソート対象
      sortOrders: columnsData.sortOrders(),
      // 検索条件だけに沿った配列
      // eventedAction: [],
      users: [],
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
    },
    filteredData () {
      var data = this.users
      var sortKey = this.sortKey
      var wordKey = this.word && this.word.toLowerCase()
      var order = this.sortOrders[sortKey] || 1
      // 検索テキストがある場合
      if (wordKey) {
        data = data.filter(function (row) {
          return Object.keys(row).some(function (key) {
            return String(row[key]).toLowerCase().indexOf(wordKey) > -1
          })
        })
      }
      // ソートが選択されている場合
      if (sortKey) {
        data = data.slice().sort(function (a, b) {
          a = a[sortKey]
          b = b[sortKey]
          return (a === b ? 0 : a > b ? 1 : -1) * order
        })
      }
      return data
    }
  },

  watch: {
    searchContents (newContents) {
      this.setUsers()
    }
  },

  methods: {
    // ソート対象、ソートの値を変更する
    sortBy: function (key) {
      this.sortKey = key
      this.sortOrders[key] = this.sortOrders[key] * -1
    },
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
    reference (index) {
      this.editIndex = index
      const id = this.users[index].id
      this.$store.dispatch(
        'refchart/create', id
      )
    },
    // sortBy (key) {
    //   this.sort.isAsc = this.sort.key === key ? !this.sort.isAsc : false
    //   this.sort.key = key
    // },
    // sortedClass (key) {
    //   return this.sort.key === key ? `sorted ${this.sort.isAsc ? 'asc' : 'desc'}` : ''
    // },
    resetting () {
      this.isActive = false
      this.isOpenUser = false
      this.isOpenUpdata = false
      this.isOpenSearch = true
      this.sort.key = ''
      this.sort.isAsc = false
      this.searchUser = ''
      this.updatedFrom = ''
      this.updatedTo = ''
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
  border: dashed 2px #666f7a;
  border-radius: 30px;
  margin: 40px auto;
  width: 50%;
  padding: 30px 0;
  background-color: #fffcf5;
}

p {
  color: black;
}

#validate {
  font-size: 10pt;
  display: block;
  margin-top: 10px;
  color: rgb(233, 0, 0);
  margin-bottom: 10px;
}

#serch_tittle {
  font-family: 'M PLUS Rounded 1c', sans-serif;
  font-size: 20px;
  color: #2c3e50;
  padding-bottom: 15px;
  font-weight: bold;
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
  /* 背景色 */
  background: #ffb350;
  /*少し濃い目の色*/
  border: solid 2px #ffb355;
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
  /* 背景色 */
  background: #ffb350;
  /*少し濃い目の色*/
  border: solid 2px #ffb355;
  /*角の丸みをもたせる*/
  border-radius: 4px;
  box-shadow: inset 0 2px 0 rgba(255,255,255,0.2), 0 2px 2px rgba(0, 0, 0, 0.19);
  font-weight: bold;
}

#submit {
  font-size: 8pt;
  padding: 0.6em 1em 0.6em 1em;
  font-weight: bold;
  margin-left: 30px;
  margin-right: 30px;
  font-family: 'M PLUS Rounded 1c', sans-serif;
  cursor: pointer;
  background: #ffb350;
  border: solid 2px #ffb355;
  border-radius: 4px;
  box-shadow: inset 0 2px 0 rgba(255,255,255,0.2), 0 2px 2px rgba(0, 0, 0, 0.19);
}

#output {
  border: dashed 2px #666f7a;
  border-radius: 30px;
  margin: 40px auto;
  width: 50%;
  padding: 30px 0;
  background-color: #fffcf5;
}

#table {
  border-collapse: separate;
  border: solid 1px #666f7a;
  border-radius: 30px;
  width: 80%;
  margin: 30px auto 20px auto;
  background-color: #fff;
  word-wrap: break-word;
  overflow-wrap: break-word;
  vertical-align: middle;
}

th.sorted.desc::after{
    font-family: 'M PLUS Rounded 1c', sans-serif;
    display: inline-block;
    content: '▼';
}

th.sorted.asc::after{
    font-family: 'M PLUS Rounded 1c', sans-serif;
    display: inline-block;
    content: '▲';
}

th {
  font-family: 'M PLUS Rounded 1c', sans-serif;
  padding: 10px 10px;
  cursor: pointer;
}

td {
  padding: 5px 20px;
}

.button_ref {
  cursor: pointer;
  font-family: 'M PLUS Rounded 1c', sans-serif;
  position: relative;
  display: inline-block;
  padding: 0.6em 1em 0.6em 1em;
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
}

#reset {
  cursor: pointer;
  font-family: 'M PLUS Rounded 1c', sans-serif;
  position: relative;
  display: inline-block;
  padding: 0.6em 1em 0.6em 1em;
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
}
</style>
