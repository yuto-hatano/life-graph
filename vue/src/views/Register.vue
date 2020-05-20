<template>
  <div id="registerSection">
    <div>
      <Header />
    </div>
    <h1>Life Graph</h1>
    <div id="input">
      <table id="field">
        <tr>
          <th scope="row">
            <label for="age">年齢</label>
          </th>
          <td>
            <input id="age" ref="editor" v-model="age" type="number" @keyup.enter="changeContents">
          </td>
        </tr>
        <tr>
          <th scope="row">
            <label for="score">スコア</label>
          </th>
          <td>
            <input id="score" ref="editor" v-model="score" type="number" @keyup.enter="changeContents">
          </td>
        </tr>
        <tr>
          <th scope="row">
            <label for="comment">コメント</label>
          </th>
          <td>
            <textarea id="comment" ref="editor" v-model="comment" cols="30" rows="5" placeholder="内容を入力してください。" @keyup.enter="changeContents" />
          </td>
        </tr>
      </table>
      <div id="action">
        <button id="reset" @click="reset">
          クリア
        </button>
        <button id="submit" @click="add">
          {{ changeButtonText }}
        </button>
      </div>
    </div>
    <div id="list">
      <table>
        <thead>
          <tr>
            <th>年齢</th>
            <th>スコア</th>
            <th>コメント</th>
          </tr>
        </thead>
        <tbody v-if="isActive">
          <tr v-for="(content,index) in contents" :key="index">
            <td>{{ content.age }}</td>
            <td>{{ content.score }}</td>
            <td>{{ content.comment }}</td>
            <button @click="edit(index)">
              編集
            </button>
            <button @click="deleteContents(index)">
              削除
            </button>
          </tr>
        </tbody>
      </table>
    </div>
    <router-link to="/Register">
      <button @click="update()">
        更新
      </button>
    </router-link>
    <div v-if="loaded" id="chart">
      <Chart />
    </div>
  </div>
</template>

<script>
import Header from '../views/Header.vue'
import Chart from '../components/Chart.vue'

export default {
  name: 'Register',
  components: {
    Header,
    Chart
  },

  data () {
    return {
      isActive: false,
      contents: [
        {
          age: '',
          score: '',
          comment: ''
        }
      ],
      load: true,
      editIndex: 0
    }
  },

  computed: {
    loaded () {
      return this.$store.state.chart.loaded
    },
    changeButtonText () {
      return this.editIndex === -1 ? '追加' : '編集'
    }
  },

  methods: {
    reset () {
      this.age = ''
      this.score = ''
      this.comment = ''
    },
    add () {
      this.isActive = true
      if (this.editIndex === -1) {
        this.contents.push({ age: this.age, score: this.score, comment: this.comment })
        this.age = ''
        this.score = ''
        this.comment = ''
        // console.log('コンテンツ')
      } else {
        this.contents.splice(this.editIndex, 1, { age: this.age, score: this.score, comment: this.comment })
        this.age = ''
        this.score = ''
        this.comment = ''
        this.editIndex = -1
      }

      const content = {
        age: this.age,
        score: this.score,
        comment: this.comment
      }
      this.$store.dispatch('addContent', content)
    },

    edit (index) {
      this.editIndex = index
      this.age = this.contents[index].age
      this.score = this.contents[index].score
      this.comment = this.contents[index].comment
      this.$refs.editor.focus() // フォーカスを設定
    },

    deleteContents (index) {
      this.contents.splice(index, 1)
    }
  }
}
</script>

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
  margin: 50px auto;
  background-color: #fffcf5;
}

#field {
  width: 60%;
  margin: 45px auto 20px auto;
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
  margin-left: 40%;
  margin-bottom: 30px;
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

#list {
  border: 1px solid #434a52;
  width: 60%;
  margin: 50px auto;
  background-color: #fffcf5;
}

button {
  cursor: pointer;
  font-size: 1em;
  margin-right: 10px;
}

table {
  width: 60%;
  margin: 45px auto 20px auto;
  background-color: #fff;
}

#chart {
  width: 60%;
  margin: 100px auto;
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
</style>
