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
      <l-marker :lat-lng="withPopup">
        <l-popup>
          <div @click="innerClick">
            I am a popup
            <p v-show="showParagraph">Nothing more</p>
          </div>
        </l-popup>
      </l-marker>
      <l-marker :lat-lng="withTooltip">
        <l-tooltip :options="{ permanent: true, interactive: true }">
          <div @click="innerClick">
            I am a tooltip
            <p v-show="showParagraph">Nothing More</p>
          </div>
        </l-tooltip>
      </l-marker>

      <l-circle
        v-for="(circle,index) in circles"
        :lat-lng="circle.center"
        :radius="circle.radius"
        :color="circle.color"
        :fillColor="circle.color"
        :click="circleClick"
        v-bind:key="index"
      ></l-circle>

      <l-polyline :lat-lngs="points" :color="lineColor" :click="lineClick"></l-polyline>
    </l-map>
  </div>
</template>

<script>
import { latLng } from "leaflet";
import {
  LMap,
  LTileLayer,
  LMarker,
  LPopup,
  LTooltip,
  LCircle,
  LPolyline
} from "vue2-leaflet";
import "leaflet/dist/leaflet.css";
import { setTimeout } from "timers";
import { getBalloonDataByBalloonCode } from "@/api";
export default {
  name: "Example",
  components: {
    "l-map": LMap,
    "l-tile-layer": LTileLayer,
    "l-marker": LMarker,
    "l-popup": LPopup,
    "l-tooltip": LTooltip,
    "l-circle": LCircle,
    "l-polyline": LPolyline
  },
  data() {
    return {
      zoom: 9,
      center: latLng(39.89758, 116.393763),
      url: "http://{s}.tile.osm.org/{z}/{x}/{y}.png",
      withPopup: latLng(47.41322, -1.219482),
      withTooltip: latLng(47.41422, -1.250482),
      currentZoom: 4,
      currentCenter: latLng(47.41322, -1.219482),
      circleCenter: latLng(39.89758, 116.393763),
      showParagraph: false,
      mapOptions: {
        zoomSnap: 0.5
      },
      allPoints: [],
      points: [],
      pointNum: 20,
      circleColor: "red",
      circleRadius: 200,
      circles: [],
      lineColor: "gray",
      balloonCode: "15188603"
    };
  },
  methods: {
    zoomUpdate(zoom) {
      this.currentZoom = zoom;
    },
    centerUpdate(center) {
      this.currentCenter = center;
    },
    showLongText() {
      this.showParagraph = !this.showParagraph;
    },
    lineClick() {
      alert("this is line");
    },
    circleClick() {
      alert("this is circle");
    },
    getRand(scale) {
      return Math.random() * scale;
    },
    innerClick() {
      alert("Click!");
    },
    generatePoints() {
      let i = this.pointNum,
        points = [];
      let baselan = 39.897,
        baselat = 116.393763;
      for (; i >= 0; i--) {
        points.push([
          baselan + (1 * this.getRand(20)) / 100 - 0.005,
          baselat + (1 * this.getRand(20)) / 100 - 0.005
        ]);
      }
      this.allPoints = points;
    },
    generateCircles() {
      let circles = [];
      for (let i in this.points) {
        circles.push({
          center: this.points[i],
          color: this.circleColor,
          radius: this.circleRadius
        });
      }
      this.circles = circles;
    },
    getBalloonData() {
      let app = this;
      getBalloonDataByBalloonCode(app.balloonCode)
        .then(res => {
          alert(JSON.stringify(res));
        })
        .catch(err => {
          alert(JSON.stringify(err));
        });
    }
  },
  drawDemo() {
    let num = 0;
    this.generatePoints();
    let app = this;

    let writePoint = function() {
      if (num > app.allPoints.length) {
        return false;
      } else {
        num++;
        app.points = app.allPoints.slice(0, num);
        app.generateCircles();
        setTimeout(writePoint, 1000);
      }
    };
    writePoint();
  },
  watch: {
    // points() {
    //   this.generatePoints();
    // }
  },
  mounted() {
    this.getBalloonData();
  }
};
</script>
<style>
.map-simple-main {
  height: 100%;
}
</style>
