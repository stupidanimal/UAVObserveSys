<template>
  <div>
    <div>
      <el-radio-group v-model="dataType" @change="typeChange">
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
import { getBalloonDataByBalloonCode } from "@/api";

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
    // let data = fakeData.balloons;
    // data = data.sort(x => x.timeStamp);
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
  computed: {
    balloonCode() {
      return this.$store.state.balloonCode;
    }
  },
  watch: {
    balloonCode: {
      //做请求并展示echarts
      immediate: true,
      handler: function() {
        let app = this;
        getBalloonDataByBalloonCode(this.balloonCode).then(res => {
          // console.log(res.state);
          // if (res.status == 200) {
          let fullData = res.data;
          let data = fullData.balloons;
          app.data = data.sort(x => x.timeStamp);
          app.drawChart();
          // }
        });
        // .catch(err => {
        //   console.error(err);
        // });
      }
    }
  },
  methods: {
    typeChange(labelName) {
      let propName = this.displayDataLabelObj[labelName]; //找到当前显示的属性
      //重新组装数组  ||可优化放进内存
      let ydata = this.data.map(x => x[propName]);
      this.chartOption.series[0].data = ydata;
    },
    drawChart() {
      let xdata = this.data.map(x => x.hum); //组装x轴
      let tmpPropName = this.displayDataLabelObj[this.displayDataType[0]];
      let ydata = this.data.map(x => x[tmpPropName]);
      this.dataType = this.displayDataType[0];
      this.chartOption.xAxis.data = xdata;
      this.chartOption.series[0].data = ydata;
    }
  }
};
</script>
<style scoped>
.echarts {
  width: 100%;
  height: 600px;
}
.chartsFrame {
  /* height: 600px; */
  height: 600px;
}
</style>


