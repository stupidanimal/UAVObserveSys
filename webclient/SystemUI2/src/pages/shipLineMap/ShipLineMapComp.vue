<template>
  <div class="map-simple-main">
    <l-map
      :zoom="zoom"
      :center="center"
      :options="mapOptions"
      style="height: 100%"
      @update:center="centerUpdate"
      @update:zoom="zoomUpdate"
    >
      <l-tile-layer :url="url" />
      <l-circle
        v-for="(circle,index) in circles"
        :lat-lng="circle.center"
        :radius="circle.radius"
        :color="circle.color"
        :fillColor="circle.color"
        @click="handleClick(circle)"
        :popupclose="true"
        v-bind:key="index"
      >
        <l-popup>
          <!-- 循环显示位置 -->
          <el-card>
            <div slot="header" class="clearfix">{{circle.info.lineCode}}-{{circle.info.balloonCode}}</div>
            <div>avgRise：{{circle.info.avgRise}}</div>
            <div>balloonCode：{{circle.info.balloonCode}}</div>
            <div>date：{{circle.info.date}}</div>
            <div>endTime：{{circle.info.endTime}}</div>
            <div>lineCode：{{circle.info.lineCode}}</div>
            <div>locationName：{{circle.info.locationName}}</div>
            <div>maxHeight：{{circle.info.maxHeight}}</div>
            <div>maxHeightTime：{{circle.info.maxHeightTime}}</div>
            <div>point：{{circle.info.point}}</div>
            <div>startTime：{{circle.info.startTime}}</div>
          </el-card>
        </l-popup>
      </l-circle>

      <!--连线-->
      <l-polyline :lat-lngs="points" :color="lineColor"></l-polyline>
    </l-map>
  </div>
</template>·
<script>
import { latLng } from "leaflet";
import {
  LMap,
  LTileLayer,
  LPopup,
  LCircleMarker,
  LPolyline
} from "vue2-leaflet";
import "leaflet/dist/leaflet.css";
import { getShipLocation } from "@/api";

export default {
  data() {
    return {
      zoom: 8,
      currentZoom: 10,
      center: latLng(-7.50861, 104.56972),
      url:
        "//map.geoq.cn/ArcGIS/rest/services/ChinaOnlineStreetPurplishBlue/MapServer/tile/{z}/{y}/{x}",

      mapOptions: {
        zoomSnap: 0.5
      },
      circleColor: "#FF9900",
      circleFillColor: "#FF9900",
      circleRadius: 5,
      lineColor: "#FFFF00",
      circles: [], //所有的船舶出现点
      points: [], //需要连线的点
      targetPoint: null // TODO:[*] 19-07-21 当前选择的站位 obj
    };
  },
  components: {
    "l-tile-layer": LTileLayer, //组件重命名，虽然我不知道为什么要这么做，但官方就是这样给的
    "l-map": LMap,
    "l-circle": LCircleMarker,
    "l-polyline": LPolyline,
    "l-popup": LPopup
  },
  computed: {
    shipLineCode() {
      //vuex触发的ShipLine选择的更改值
      return this.$store.state.shipLineCode;
    }
  },
  watch: {
    shipLineCode() {
      //监听变化生成路径和点
      this.getBalloonData();
    }
  },
  methods: {
    zoomUpdate(zoom) {
      this.currentZoom = zoom;
    },
    centerUpdate(center) {
      this.currentCenter = center;
    },
    handleClick(circle) {
      this.$store.commit("choiceShipPoint", circle.info.balloonCode);
    },
    // setCenter(circles) {
    //   //重新校准center避免点跑到显示不见的地方..貌似不起作用，这tm有bug
    //   if (!circles || circles.length == 0) return;
    //   let lat = 0,
    //     lng = 0;
    //   for (let i in circles) {
    //     let point = circles[i];
    //     lat += point.point[0];
    //     lng += point.point[1];
    //   }

    //   lat = Math.floor((lat / circles.length) * 10000) / 10000;
    //   lng = Math.floor((lng / circles.length) * 10000) / 10000;
    //   console.log(lat, lng);
    //   // this.centerUpdate(latLng(lat, lng));
    //   this.center.lat = lat;
    //   this.center.lng = lng;
    //   // this.center = latLng(lat / circles.length, lng / circles.length);
    // },
    drawPoints(data) {
      let bArr = data.shipLocationVos;
      bArr = bArr.sort(x => x.timeStamp);
      if (!bArr || bArr.length == 0) {
        this.circles = [];
        this.points = [];
        return;
      }
      // this.setCenter(bArr);
      let n = 0;
      let app = this;

      let drawPoint = function() {
        //递归延时顺序画点 //如果不需要完全可以直接画全部//不可以while因为没有settimeout效果
        if (n < bArr.length) {
          let p = bArr[n];
          app.points.push(p.point.reverse());
          // app.points.push([p.point[1], p.point[0]]);
          app.circles.push({
            center: p.point,
            color: app.circleColor,
            radius: app.circleRadius,
            info: p
          });
          n++;
          setTimeout(drawPoint, 500);
        }
      };
      drawPoint();
    },
    getBalloonData() {
      //与后台交互方法
      let app = this;
      getShipLocation(app.shipLineCode)
        .then(res => {
          let data = res.data;
          //画点
          app.drawPoints(data);
        })
        .catch(err => {
          alert(err);
        });
    }
  }
};
</script>
<style scoped>
.map-simple-main {
  height: 100%;
}
</style>


