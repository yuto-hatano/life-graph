<script>
// Importing Line class from the vue-chartjs wrapper
import { Line } from 'vue-chartjs'
// Exporting this so it can be used in other components
export default {
  name: 'Chart',
  extends: Line,
  data () {
    return {
      datacollection: {
        // Data to be represented on x-axis
        // 年齢
        labels: [],
        datasets: [
          {
            // Data to be represented on y-axis
            // 値（スコア）
            data: [],
            label: 'Data One',
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
        },
        tooltips: {
          // ホバー時の動作（ツールチップ）
          // カーソルが合ったときに全ての値が表示される「near set」と迷い中
          mode: 'point',
          label: function (data) {
            var commentText = ['コメント１', 'コメント２', 'コメント3', 'コメント4']
            return commentText
          }
        },
        responsive: true,
        // 元のcanvasのサイズを保つか保たないのか
        maintainAspectRatio: false,
        // 値にnullがあっても自然となるように埋めてくれる
        spanGaps: true
      }
    }
  },
  mounted () {
    this.setYears()
    this.setScore()
    this.renderChart(this.datacollection, this.options)
  },
  // mounted () {
  //   // renderChart function renders the chart with the datacollection and options object.
  //   this.renderChart(this.datacollection, this.options)
  // }
  methods: {
    setYears () {
      const years = []
      this.$store.state.contents.forEach((Years) => {
        years.push(Years.years)
      })
      this.datacollection.labels = years
    },
    setScore () {
      const score = []
      this.$store.state.contents.forEach((Score) => {
        score.push(Score.score)
      })
      this.datacollection.datasets[0].data = score
    }
  }

}
</script>
