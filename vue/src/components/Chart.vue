<script>
// Importing Line class from the vue-chartjs wrapper
import { Line } from 'vue-chartjs'
// Exporting this so it can be used in other components
export default {
  name: 'Chart',
  extends: Line,
  data () {
    return {
      data: {
        // datacollecrionだとmountedがだめだった...dataに変更
        // Data to be represented on x-axis
        // 年齢
        labels: [],
        datasets: [
          {
            // Data to be represented on y-axis
            // 値（スコア）
            data: [],
            borderColor: '#FFA500',
            pointBackgroundColor: '#FF4500',
            borderWidth: 1.5,
            pointBorderColor: '#FF4500',
            // 線の下の影
            fill: false
          }
        ]
      },
      // Chart.js options that controls the appearance of the chart
      options: {
        tooltips: {
          // 元のやつ出さない
          enabled: false,
          // callbacks: {
          //   label: (labels, data) => {
          //     const { label } =
          //     data.datasets[labels.datasetsIndex]
          //     const value = labels.value.eplace('null', '')
          //     return value && `${label}: ${value}`
          //   }
          // },
          // ここにカスタムしたものをつめる
          // 多分最後表示されず困ってたのは、customで定義しているのに、tooltipをちゃんと入れ込めていなかったから・・・？
          custom: []
          // ホバー時の動作（ツールチップ）
          // カーソルが合ったときに全ての値が表示される「near set」と迷い中
        //   callbacks: {
        //     afterbody: function (data) {
        //       var commentText = ['コメント１', 'コメント２', 'コメント3', 'コメント4', 'コメント5', 'コメント6', 'コメント7', 'コメント8', 'コメント9', 'コメント10', 'コメント11', 'コメント12', 'コメント13', 'コメント14', 'コメント15', 'コメント16', 'コメント17', 'コメント18', 'コメント19', 'コメント20', 'コメント21', 'コメント22', 'コメント23']
        //       return commentText
        //     }
        //   }
        // },
        },
        // 元のcanvasのサイズを保つか保たないのか（おそらく適用してくれていない）
        // おそらく処理の順番の関係で適用されていなかったっぽい？位置こっちにずらしたらいけた
        maintainAspectRatio: false,
        // 値にnullがあっても自然となるように埋めてくれる(適用されていなかったみたいなので位置をずらしたらいけた)
        spanGaps: true,
        scales: {
          // y軸
          yAxes: [{
            ticks: {
              beginAtZero: true,
              min: -100,
              max: 100,
              // 目盛間隔
              stepSize: 10
            },
            gridLines: {
              display: true
            }
          }],
          // x軸
          xAxes: [{
            gridLines: {
              display: false
            }
          }]
        },
        legend: {
          // 凡例
          display: false
        }
        // tooltips: {
        //   comment: [],
        // ホバー時の動作（ツールチップ）
        // カーソルが合ったときに全ての値が表示される「near set」と迷い中
        // mode: 'point'
        //   callbacks: {
        //     afterbody: function (data) {
        //       var commentText = ['コメント１', 'コメント２', 'コメント3', 'コメント4', 'コメント5', 'コメント6', 'コメント7', 'コメント8', 'コメント9', 'コメント10', 'コメント11', 'コメント12', 'コメント13', 'コメント14', 'コメント15', 'コメント16', 'コメント17', 'コメント18', 'コメント19', 'コメント20', 'コメント21', 'コメント22', 'コメント23']
        //       return commentText
        //     }
        //   }
        // },
      },
      responsive: true
    }
  },
  computed: {
    checkContents () {
      return this.$store.state.chart.contents
    }
  },

  mounted () {
    this.setAge()
    this.setScore()
    this.setComment()
    this.renderChart(this.data, this.options)
  },

  methods: {
    setAge () {
      const age = []
      this.checkContents.map((Years) => {
        age.push(Years.age)
      })
      this.data.labels = age
    },
    setScore () {
      const score = []
      this.checkContents.map((Score) => {
        score.push(Score.score)
      })
      this.data.datasets[0].data = score
    },
    // functionの中からは直接storeに保存できない？
    // dataの中では定義できないので、ここで定義する？
    setComment () {
      // 用意したcommentsという箱に、contents.ageという配列を持ったageと、contents.commentという配列を持った
      // commentをObjectとしてpushする
      // const comments = []
      // this.checkContents.map((contents) => {
      //   comments.push({ age: contents.age, comment: contents.comment })
      // })
      // ↓こっちの方が簡潔かつシンプル
      const comments = this.checkContents.map((content) => {
        return { age: content.age, comment: content.comment }
      })
      this.options.tooltips.custom = function (tooltipModel) {
        // ツールチップ要素
        // サイトから持ってきた(https://misc.0o0o.org/chartjs-doc-ja/configuration/tooltip.html#%E5%A4%96%E9%83%A8%E3%82%AB%E3%82%B9%E3%82%BF%E3%83%A0%E3%83%84%E3%83%BC%E3%83%AB%E3%83%81%E3%83%83%E3%83%97)
        var tooltipEl = document.getElementById('chartjs-tooltip')

        // 最初のレンダリング時に要素を作成する
        if (!tooltipEl) {
          tooltipEl = document.createElement('div')
          tooltipEl.id = 'chartjs-tooltip'
          tooltipEl.innerHTML = '<table></table>'
          document.body.appendChild(tooltipEl)
        }

        // ツールチップがない場合は非表示
        if (tooltipModel.opacity === 0) {
          tooltipEl.style.opacity = 0
          return
        }

        // キャレット(ツールチップが指し示すもの)の位置を設定する
        tooltipEl.classList.remove('above', 'below', 'no-transform')
        if (tooltipModel.yAlign) {
          tooltipEl.classList.add(tooltipModel.yAlign)
        } else {
          tooltipEl.classList.add('no-transform')
        }

        function getBody (bodyItem) {
          return bodyItem.lines
        }

        // テキストを設定する
        if (tooltipModel.body) {
          var titleLines = tooltipModel.title
          var bodyLines = tooltipModel.body.map(getBody)
          var innerHtml = '<thead>'

          titleLines.forEach(function (age) {
            // 何歳スタートか（x軸）
            // var comNum = age - 1
            var comment = comments.find(contents => contents.age === age).comment
            innerHtml += '<tr><th>' + age + '歳' + '</th></tr>'
            bodyLines.forEach(function (body, i) {
              var colors = tooltipModel.labelColors[i]
              var style = 'background:' + colors.backgroundColor
              style += '; border-color:' + colors.borderColor
              style += '; border-width: 2px'
              var span = '<span style="' + style + '"></span>'
              // nullなら表示させないようにしてくれてるところ？
              // console.log(com)
              if (comment) {
                innerHtml += '<tr><td>' + span + '満足度：' + body + ' ポイント' + '</td></tr>' + 'コメント：' + comment
              } else {
                innerHtml += '<tr><td>' + span + '満足度：' + body + ' ポイント' + '</td></tr>'
              }
            })
          })
          innerHtml += '</tbody>'
          var tableRoot = tooltipEl.querySelector('table')
          tableRoot.innerHTML = innerHtml
        }

        // `this` はツールチップ全体
        var position = this._chart.canvas.getBoundingClientRect()

        // 表示、配置、およびフォントスタイルの設定
        tooltipEl.style.opacity = 1
        tooltipEl.style.position = 'absolute'
        tooltipEl.style.left = position.left + window.pageXOffset + tooltipModel.caretX + 'px'
        tooltipEl.style.top = position.top + window.pageYOffset + tooltipModel.caretY + 'px'
        // 背景色指定
        tooltipEl.style.backgroundColor = 'rgba(240, 248, 255, 0.8)'
        tooltipEl.style.fontFamily = tooltipModel._bodyFontFamily
        tooltipEl.style.fontSize = tooltipModel.bodyFontSize
        tooltipEl.style.fontStyle = tooltipModel._bodyFontStyle
        tooltipEl.style.padding = tooltipModel.yPadding + 'px ' + tooltipModel.xPadding + 'px'
        tooltipEl.style.pointerEvents = 'none'
      }
    }
  }
}

</script>
