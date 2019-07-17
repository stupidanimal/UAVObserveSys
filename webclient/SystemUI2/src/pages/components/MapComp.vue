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
      <l-tile-layer :url="url" :attribution="attribution" />
      <l-circle
        v-for="(circle,index) in circles"
        :lat-lng="circle.center"
        :radius="circle.radius"
        :color="circle.color"
        :fillColor="circle.color"
        v-bind:key="index"
      ></l-circle>
      <l-polyline :lat-lngs="points" :color="lineColor"></l-polyline>
    </l-map>
  </div>
</template>
<script>
import { latLng } from "leaflet";
import {
  LMap,
  LTileLayer,
  // LMarker,
  // LPopup,
  // LTooltip,
  LCircleMarker,
  LPolyline
} from "vue2-leaflet";
import "leaflet/dist/leaflet.css";
// import { setTimeout } from "timers";
import { getBalloonDataByBalloonCode } from "@/api";

export default {
  data() {
    return {
      zoom: 8,
      currentZoom: 10,
      center: latLng(104.51253, -7.5053),
      url: "http://{s}.tile.osm.org/{z}/{x}/{y}.png",
      mapOptions: {
        zoomSnap: 0.5
      },
      circleColor: "#FF9900",
      circleFillColor: "#FF9900",
      circleRadius: 5,
      circles: [],
      points: [],
      lineColor: "#FFFF00",
      balloonCode: "15188603"
    };
  },
  components: {
    "l-tile-layer": LTileLayer,
    "l-map": LMap,
    "l-circle": LCircleMarker,
    "l-polyline": LPolyline
  },
  methods: {
    zoomUpdate(zoom) {
      this.currentZoom = zoom;
    },
    centerUpdate(center) {
      this.currentCenter = center;
    },
    drawPoints(data) {
      //画点方法
      let bArr = data.balloons;
      bArr = bArr.sort(x => x.timeStamp);
      let n = 0;
      let app = this;

      //递归延时顺序画点 //如果不需要完全可以直接画全部
      let drawPoint = function() {
        if (n < bArr.length) {
          let p = bArr[n];
          app.points.push(p.point);
          app.circles.push({
            center: p.point,
            color: app.circleColor,
            radius: app.circleRadius
          });
          n++;
          setTimeout(drawPoint, 500);
        }
      };
      drawPoint();
    },
    getBalloonData() {
      let app = this;
      getBalloonDataByBalloonCode(app.balloonCode)
        .then(res => {
          let data = res.data;
          //画点
          app.drawPoints(data);
        })
        .catch(err => {
          alert(JSON.stringify(err));
        });
    }
  },
  mounted() {
    this.getBalloonData();
  }
};
</script>
<style scoped>
.map-simple-main {
  height: 100%;
}
</style>


