<template>
  <div>
    <div>Here will be chart</div>
    <div class="chartsFrame">
      <v-chart :options="polar"></v-chart>
    </div>
  </div>
</template>
<script>
import Vue from "vue";
import ECharts from "vue-echarts";

import "echarts/lib/chart/bar";
import "echarts/lib/component/tooltip";

import "echarts/lib/chart/line";
import "echarts/lib/component/polar";

Vue.component("v-chart", ECharts);

export default {
  components: {
    "v-chart": ECharts
  },
  data() {
    let data = [];

    for (let i = 0; i <= 360; i++) {
      let t = (i / 180) * Math.PI;
      let r = Math.sin(2 * t) * Math.cos(2 * t);
      data.push([r, i]);
    }

    return {
      polar: {
        title: {
          text: "极坐标双数值轴"
        },
        legend: {
          data: ["line"]
        },
        polar: {
          center: ["50%", "54%"]
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross"
          }
        },
        angleAxis: {
          type: "value",
          startAngle: 0
        },
        radiusAxis: {
          min: 0
        },
        series: [
          {
            coordinateSystem: "polar",
            name: "line",
            type: "line",
            showSymbol: false,
            data: data
          }
        ],
        animationDuration: 2000
      }
    };
  }
};
</script>
<style scoped>
.echarts {
  width: 100%;
  height: 100%;
}
.chartsFrame {
  height: 800px;
}
</style>


