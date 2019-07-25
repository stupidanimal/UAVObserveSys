<template>
  <div>
    <div>Here will be chart</div>
    <div>
      <el-radio-group v-model="dataType" @change="typeChange">
        <!-- <el-radio-button label="dew"></el-radio-button> -->
        <el-radio-button
          v-for="(name,index) in displayDataType"
          v-bind:label="name"
          v-bind:key="index"
        ></el-radio-button>
      </el-radio-group>
    </div>
    <div class="chartsFrame">
      <v-chart :options="chartOption"></v-chart>
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

let fakeData = {
  balloonCode: "15188603",
  lineCode: null,
  balloons: [
    {
      lineCode: "1",
      balloonCode: "15188603",
      minuteOrder: 0,
      secondOrder: 0,
      timeStamp: 1469080741,
      timeStr: "2016-07-21 13:59:01",
      pre: 16.0,
      temp: 28.39,
      hum: 76.8,
      qpm: 16.0,
      dir: 49.7,
      vel: 0.21,
      dew: 23.9,
      dis: 0.03,
      rise: 0.0,
      alt: -1.0,
      point: [104.51253, -7.505]
    },
    {
      lineCode: "1",
      balloonCode: "15188603",
      minuteOrder: 1,
      secondOrder: 0,
      timeStamp: 1469080801,
      timeStr: "2016-07-21 14:00:01",
      pre: 13.9,
      temp: 28.16,
      hum: 75.1,
      qpm: 13.8,
      dir: 354.8,
      vel: 1.32,
      dew: 23.3,
      dis: 0.03,
      rise: -0.1,
      alt: -1.0,
      point: [104.51279, -7.50521]
    },
    {
      lineCode: "1",
      balloonCode: "15188603",
      minuteOrder: 2,
      secondOrder: 0,
      timeStamp: 1469080861,
      timeStr: "2016-07-21 14:01:01",
      pre: 260.9,
      temp: 25.92,
      hum: 79.8,
      qpm: 260.2,
      dir: 345.9,
      vel: 6.07,
      dew: 22.2,
      dis: 0.38,
      rise: 4.7,
      alt: -1.0,
      point: [104.51331, -7.50818]
    },
    {
      lineCode: "1",
      balloonCode: "15188603",
      minuteOrder: 3,
      secondOrder: 0,
      timeStamp: 1469080921,
      timeStr: "2016-07-21 14:02:01",
      pre: 569.1,
      temp: 23.76,
      hum: 78.6,
      qpm: 567.6,
      dir: 328.0,
      vel: 10.08,
      dew: 19.8,
      dis: 0.95,
      rise: 4.1,
      alt: -1.0,
      point: [104.5155, -7.51227]
    },
    {
      lineCode: "1",
      balloonCode: "15188603",
      minuteOrder: 4,
      secondOrder: 0,
      timeStamp: 1469080981,
      timeStr: "2016-07-21 14:03:01",
      pre: 797.5,
      temp: 23.44,
      hum: 61.8,
      qpm: 795.3,
      dir: 327.0,
      vel: 11.18,
      dew: 15.7,
      dis: 1.6,
      rise: 3.4,
      alt: -1.0,
      point: [104.51891, -7.51735]
    }
  ]
};

export default {
  components: {
    "v-chart": ECharts
  },
  data() {
    let data = fakeData.balloons;
    data = data.sort(x => x.timeStamp);
    let xdata = data.map(x => x.hum); //组装x轴

    //初始化字典 可以拆分出去
    let displayDataLabelObj = {
      测试1: "dew",
      测试2: "dir",
      测试3: "dis",
      测试4: "pre",
      测试5: "qpm",
      测试6: "temp",
      测试7: "vel"
    };

    //组装要显示的tab
    let displayDataType = [];
    for (let name in displayDataLabelObj) {
      displayDataType.push(name);
    }

    //初始化第一批数据
    let tmpPropName = displayDataLabelObj[displayDataType[0]];
    let ydata = data.map(x => x[tmpPropName]);
    this.dataType = displayDataType[0];

    let app = this;
    let handleTooltip = function(target) {
      let idx = target.dataIndex;
      let dataObj = app.data[idx];
      let html = "";
      for (let p in dataObj) {
        html +=
          "<div><span>" + p + "</span>:<span>" + dataObj[p] + "</span></div>";
      }
      return "<div>" + html + "</div>";
    };

    return {
      dataType: "", //选择显示的数据类型
      displayDataType: displayDataType, //tab标签名称,
      displayDataLabelObj: displayDataLabelObj, //标签名称和属性的数据字典,
      data: data, //完整的数据对象数组
      chartOption: {
        xAxis: {
          type: "category",
          data: xdata
        },
        yAxis: {
          type: "value"
        },
        tooltip: {
          trigger: "item",
          //   formatter: "{a}*{b}*{c}*{d}"
          formatter: handleTooltip
        },
        series: [
          {
            data: ydata,
            type: "line",
            smooth: true
          }
        ]
      }
    };
  },
  methods: {
    typeChange(labelName) {
      let propName = this.displayDataLabelObj[labelName]; //找到当前显示的属性
      //重新组装数组  ||可优化放进内存
      let ydata = this.data.map(x => x[propName]);
      this.chartOption.series[0].data = ydata;
    }
  }
};
</script>
<style scoped>
.echarts {
  width: 100%;
  height: 100%;
}
.chartsFrame {
  height: 600px;
}
</style>


