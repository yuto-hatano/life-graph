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
// import qs from 'qs'

export default {
  name: 'Search',
  components: {
    Header,
    Footer
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

  // computed: {
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

  // },

  methods: {
    searchUserName () {
      this.isOpenSearch = false
      this.isOpenUser = true
    },
    searchUpdate () {
      this.isOpenSearch = false
      this.isOpenUpdata = true
    },
    // search_name () {
    //   this.isActive = true
    //   this.$store.dispatch(
    //     //  storeのactionsを呼び出す
    //     'SearchGraphs',
    //     {
    //       likeName: this.searchUser,
    //       startDate: '',
    //       finishDate: ''
    //     }
    //   )
    // },
    // search_date () {
    //   this.isActive = true
    //   this.$store.dispatch(
    //     //  storeのactionsを呼び出すss
    //     'SearchGraphs',
    //     {
    //       likeName: '',
    //       startDate: this.updatedFrom,
    //       finishDate: this.updatedTo
    //     }
    //   )
    // },
    search_name () {
      // const likeName = this.searchUser
      // const startDate = ''
      // const finishDate = ''
      const data = {
        likeName: this.searchUser,
        startDate: '',
        finishDate: ''
      }

      this.isActive = true
      // const graphInfo = {
      //   Info: {
      //     $in: [likeName, startDate, finishDate]
      //   }
      // }

      // const dataSerializer = (graphInfo) => qs.stringify(graphInfo)
      this.$store.dispatch(
        //  storeのactionsを呼び出す
        // 'SearchGraph/SearchGraphs',
        'SearchName', data
      )
    },
    search_date () {
      // const likeName = this.searchUser
      // const startDate = ''
      // const finishDate = ''
      const data = {
        likeName: '',
        startDate: this.updatedFrom,
        finishDate: this.updatedTo
      }

      this.isActive = true
      // const graphInfo = {
      //   Info: {
      //     $in: [likeName, startDate, finishDate]
      //   }
      // }

      // const dataSerializer = (graphInfo) => qs.stringify(graphInfo)
      this.$store.dispatch(
        //  storeのactionsを呼び出す
        // 'SearchGraph/SearchGraphs',
        'SearchDate', data
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

span {
  display: block;
  margin-top: 10px;
  color: red;
}

#serch_tittle {
  font-family: 'M PLUS Rounded 1c', sans-serif;
  color: #2c3e50;
  font-size: 25px;
  font: bold;
}

#searchUserName {
  font-family: 'M PLUS Rounded 1c', sans-serif;
  color: #2c3e50;
  cursor: pointer;
  margin: 40px 20px;
  padding: 20px 20px;
  font-size: 1em;
}

#searchUpdate {
  font-family: 'M PLUS Rounded 1c', sans-serif;
  color: #2c3e50;
  cursor: pointer;
  margin: 40px 20px;
  padding: 20px 20px;
  font-size: 1em;
}

#search_name {
  cursor: pointer;
  width: 50%;
  margin-top: 20px ;
  font-size: 1em;
  padding: 5px 0;
}

#search_From {
  cursor: pointer;
  width: 20%;
  margin-top: 20px;
  margin-bottom: 10px;
  font-size: 1em;
  padding: 5px 0;
}

#search_To {
  cursor: pointer;
  width: 20%;
  margin-top: 10px;
  font-size: 1em;
  padding: 5px 0;
}

#clear {
  cursor: pointer;
  font-family: 'M PLUS Rounded 1c', sans-serif;
  color: #2c3e50;
  font-size: 1em;
}

#return {
  cursor: pointer;
  font-family: 'M PLUS Rounded 1c', sans-serif;
  color: #2c3e50;
  font-size: 1em;
  margin: 20px 10% 20px 10%;
}

#submit {
  cursor: pointer;
  font-size: 1em;
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
  font-family: 'M PLUS Rounded 1c', sans-serif;
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
