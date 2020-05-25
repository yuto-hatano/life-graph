<template>
  <div id="registerSection">
    <div>
      <Header />
    </div>
    <h1 class="registerTittle">
      Life Graph
    </h1>
    <div class="message_2">
      You can register or edit your "LifeGraph".
    </div>
    <button id="mainButton" @click="addButton()">
      登録
    </button>
    <button id="mainButton" @click="editButton()">
      編集
    </button>
    <!-- 登録画面 -->
    <div v-if="isAddTable">
      <ValidationObserver v-slot="{ invalid }" @add.prevent="add()">
        <div id="input">
          <h2>登録</h2>
          <table id="field">
            <tr>
              <th scope="row">
                <label id="required" for="age">年齢</label>
              </th>
              <td>
                <validation-provider v-slot="{ errors }" name="数値" rules="required|between:0,100">
                  <input id="age" v-model="age" min="0" max="100" type="number">
                  <span>{{ errors[0] }}</span>
                </validation-provider>
              </td>
            </tr>
            <tr>
              <th scope="row">
                <label id="required" for="score">スコア</label>
              </th>
              <td>
                <validation-provider v-slot="{ errors }" name="数値" rules="required|between:-100,100">
                  <input id="score" v-model="score" min="-100" max="100" type="number">
                  <span>{{ errors[0] }}</span>
                </validation-provider>
              </td>
            </tr>
            <tr>
              <th scope="row">
                <label for="comment">コメント</label>
              </th>
              <td>
                <validation-provider v-slot="{ errors }" name="コメント" rules="max:100">
                  <textarea id="comment" v-model="comment" cols="30" rows="5" maxlength="120" placeholder="内容を入力してください" />
                  <span>{{ errors[0] }}</span>
                </validation-provider>
              </td>
            </tr>
          </table>
          <span class="attention">* は必須項目です</span>
          <div id="action">
            <button id="reset" @click="reset">
              クリア
            </button>
            <button id="submit" :disabled="invalid" @click="add">
              登録
            </button>
          </div>
        </div>
      </ValidationObserver>
    </div>
    <!-- 編集画面 -->
    <div v-if="isEditTable">
      <ValidationObserver v-slot="{ invalid }" @add.prevent="edit_1()">
        <div id="input">
          <h2>編集</h2>
          <table id="field">
            <tr>
              <th scope="row">
                <label id="required" for="age">年齢</label>
              </th>
              <td>
                <validation-provider v-slot="{ errors }" name="年齢" rules="required|between:0,100">
                  <input id="age" ref="editor" v-model="age" min="0" max="100" type="number">
                  <span>{{ errors[0] }}</span>
                </validation-provider>
              </td>
            </tr>
            <tr>
              <th scope="row">
                <label id="required" for="score">スコア</label>
              </th>
              <td>
                <validation-provider v-slot="{ errors }" name="スコア" rules="required|between:-100,100">
                  <input id="score" ref="editor" v-model="score" min="-100" max="100" type="number">
                  <span>{{ errors[0] }}</span>
                </validation-provider>
              </td>
            </tr>
            <tr>
              <th scope="row">
                <label for="comment">コメント</label>
              </th>
              <td>
                <validation-provider v-slot="{ errors }" name="コメント" rules="max:100">
                  <textarea id="comment" ref="editor" v-model="comment" cols="30" rows="5" maxlength="120" placeholder="内容を入力してください" />
                  <span>{{ errors[0] }}</span>
                </validation-provider>
              </td>
            </tr>
          </table>
          <span>* は必須項目です</span>
          <div id="action">
            <button id="reset" @click="clear()">
              削除
            </button>
            <button id="submit" :disabled="invalid" @click="edit_1">
              保存
            </button>
          </div>
        </div>
      </ValidationObserver>
    </div>
    <!-- データテーブル -->
    <div v-if="isActive" id="list">
      <h2>データー一覧</h2>
      <table>
        <thead id="dataThead">
          <tr>
            <!-- <th>ID</th> -->
            <th>年齢</th>
            <th>スコア</th>
            <th>コメント</th>
            <th>編集</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(content,index) in contents" :key="index">
            <!-- <td>
              {{ content.id }}
            </td> -->
            <td>
              {{ content.age }}
            </td>
            <td>
              {{ content.score }}
            </td>
            <td>
              {{ content.comment }}
            </td>
            <td>
              <button id="edit" @click="edit(index)">
                編集
              </button>
            </td>
            <!-- <button id="delete" @click="deleteContents(index)">
              削除
            </button> -->
          </tr>
        </tbody>
      </table>
    </div>
    <router-link to="/Top">
      <button id="update" @click="update()">
        更新
      </button>
    </router-link>
    <Footer />
  </div>
</template>

<script>
import Header from '../views/Header.vue'
import Footer from '../components/Footer.vue'

export default {
  name: 'Register',
  components: {
    Header,
    Footer
  },

  data () {
    return {
      id: '',
      age: '',
      score: '',
      comment: '',
      isActive: false,
      isAddTable: true,
      isEditTable: false,
      contents: [],
      load: true,
      editIndex: -1
    }
  },

  computed: {
    stateContents () {
      return this.$store.state.refchart.contents
    }
  //   loaded () {
  //     return this.$store.state.chart.loaded
  //   }
  //   changeButtonText () {
  //     return this.editIndex === -1 ? '追加' : '編集'
  //   }
  },

  watch: {
    stateContents (newContents) {
      this.setContents()
    }
  },

  methods: {
    setContents () {
      this.contents = this.$store.state.refchart.contents
    },
    reset () {
      this.age = ''
      this.score = ''
      this.comment = ''
    },

    add () {
      const content = {
        userId: this.$store.state.auth.userID,
        age: parseInt(this.age),
        score: parseInt(this.score),
        comment: this.comment
      }
      this.$store.dispatch('register', content)
      this.$store.dispatch('addContent', content)

      this.age = ''
      this.score = ''
      this.comment = ''
    },

    addButton () {
      this.isActive = false
      this.isAddTable = true
      this.isEditTable = false
      this.age = ''
      this.score = ''
      this.comment = ''
    },

    editButton () {
      const userId = this.$store.state.auth.userID
      this.$store.dispatch(
        'refchart/create', userId
      )
      this.age = ''
      this.score = ''
      this.comment = ''
      this.isActive = true
      this.isAddTable = false
      this.isEditTable = false
    },

    edit_1 () {
      const content = {
        userId: this.$store.state.auth.userID,
        id: this.$store.state.edit.record.id,
        age: parseInt(this.age),
        score: parseInt(this.score),
        comment: this.comment
      }
      this.$store.dispatch('register', content)
      this.age = ''
      this.score = ''
      this.comment = ''
    },

    edit (index) {
      this.isEditTable = true
      this.editIndex = index

      this.$store.dispatch(
        'edit/create',
        {
          userId: 2,
          age: this.contents[index].age
        }
      )

      this.age = this.contents[index].age
      this.score = this.contents[index].score
      this.comment = this.contents[index].comment
      // this.$refs.editor.focus() // フォーカスを設定
    },

    clear () {
      this.$store.dispatch(
        'clear/create',
        {
          parentId: this.$store.state.edit.record.parentId,
          age: this.$store.state.edit.record.age
        })
      this.age = ''
      this.score = ''
      this.comment = ''
    }
  }
}
</script>

<style scoped>
#registerSection {
  background-color: #ffffff;
  background-image: url("https://www.transparenttextures.com/patterns/ag-square.png");
}

h1 {
  font-family: 'Roboto Slab', serif;
  font-size: 60pt;
  padding: 50px;
  font-weight: bold;
}

.message_2 {
  font-family: 'Courgette', cursive;
  font-style: italic;
  font-size: 15pt;
  height: 25px;
  font-weight: bold;
  padding-bottom: 5px;
}

#mainButton {
  cursor: pointer;
  font-size: 25px;
  background-color: #fd9535;
  color: #fff;
  border: solid 2px #f59402;
  border-radius: 20px;
  padding: 10px 15px;
  margin: 30px 20px 0 20px;
  box-shadow: 1px 2px #dddddd;
  font-size: 60pt;
  padding: 50px;
}

#mainButton,#update{
  font-size: 12pt;
  font-family: 'M PLUS Rounded 1c', sans-serif;
  position: relative;
  display: inline-block;
  padding: 0.6em 2.2em 0.6em 2.2em;
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

#mainButton:active {
  box-shadow: none;
  position: relative;
  top: 2px;
  border: solid 2px #f59402;
  box-shadow: 0 0 2px rgba(0, 0, 0, 0.30);
}

#update {
  cursor: pointer;
  margin: 0 auto 35px auto;
}

h2 {
  font-family: 'M PLUS Rounded 1c', sans-serif;
  font-size: 28px;
  color: #2c3e50;
  margin-top: 50px;
  margin-right: 70%;

}

#input {
  font-family: 'M PLUS Rounded 1c', sans-serif;
  color: #2c3e50;
  border: dashed 2px #666f7a;
  border-radius: 30px;
  width: 60%;
  margin: 50px auto 20px auto;
  background-color: #fffcf5;
}

#field {
  border-collapse: separate;
  border: solid 1px #666f7a;
  border-radius: 30px;
  width: 60%;
  margin: 30px auto 20px auto;
  background-color: #fff;
  word-wrap : break-word;
  overflow-wrap : break-word;
  vertical-align: middle;
}

th {
  font-family: 'M PLUS Rounded 1c', sans-serif;
  vertical-align: middle;
  padding:10px 15px;
  /* border:1px solid #666; */
  word-wrap : break-word;
  overflow-wrap : break-word;
}

#required {
  font-family: 'M PLUS Rounded 1c', sans-serif;
  color: #2c3e50;
}

#required:after {
  color: rgb(233, 0, 0);
  content: " *";
}

label {
  font-family: 'M PLUS Rounded 1c', sans-serif;
  color: #2c3e50
}

td {
  vertical-align: middle;
  padding:10px 15px;
  /* border:1px solid #666; */
  word-wrap : break-word;
  overflow-wrap : break-word;
}

span {
  font-size: 10pt;
  display: block;
  margin-top: 10px;
  color: rgb(233, 0, 0);
}

#age,#score,#comment {
  width: 100%;
  font-size: 1em;
}

#action {
  margin-left: 40%;
  margin-bottom: 30px;
}

#reset {
  cursor: pointer;
  font-size: 9pt;
  font-family: 'M PLUS Rounded 1c', sans-serif;
  position: relative;
  display: inline-block;
  padding: 0.5em 2em 0.5em 2em;
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

#reset:active {
  box-shadow: none;
  position: relative;
  top: 2px;
}

#submit {
  color:rgb(158, 157, 157);
  font-family: 'M PLUS Rounded 1c', sans-serif;
  cursor: pointer;
  padding: 0.5em 2em 0.5em 2em;
  font-size: 9pt;
  background-color: #dddddd;
  border-radius: 5px;
  box-shadow: 1px 2px #dddddd;
}

#submit:active {
  color:#FFF;
  background: #fd9535;
  /*少し濃い目の色*/
  border: solid 2px #f59402;
  box-shadow: inset 0 2px 0 rgba(255,255,255,0.2), 0 2px 2px rgba(0, 0, 0, 0.19);
  position: relative;
  top: 2px;
}

#list {
  border-radius: 30px;
  width: 80%;
  margin: 50px auto;
  background-color: #fffcf5;
}

#edit {
  vertical-align: middle;
  cursor: pointer;
  font-size: 0.8em;
  background-color: #dddddd;
  padding: 5px 10px;
  margin: 5px;
}

#delete {
  vertical-align: middle;
  cursor: pointer;
  font-size: 0.8em;
  background-color: #dddddd;
  padding: 5px 10px;
  margin: 5px;
}

table {
  width: 60%;
  margin: 45px auto 45px auto;
  background-color: #fff;
}

#id {
  border: none;
}

#Graph_2 {
  background-color: #FFFcf5;
  border: dashed 2px #666f7a;
  border-radius: 30px;
  width: 700px;
  margin: 0 auto;
  padding-top: 30pt;
  padding-bottom: 10pt;
}

#add {
  cursor: pointer;
  font-size: 1em;
  background-color: #dddddd;
  border-radius: 5px;
  box-shadow: 1px 2px #dddddd;
  margin: 20px auto;
}

#add:active {
  box-shadow: none;
  position: relative;
  top: 2px;
}

/* #update {
  cursor: pointer;
  font-size: 20px;
  padding: 5px 20px;
  background-color: #fd9535;
  color: #fff;
  border-bottom: solid 2px #d27d00;
  border-radius: 20px;
  font-weight: bold;
} */

#update:active {
  box-shadow: none;
  position: relative;
  border: solid 2px #f59402;
  top: 2px;
}

</style>
