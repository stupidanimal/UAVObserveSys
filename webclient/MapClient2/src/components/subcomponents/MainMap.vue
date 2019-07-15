<template>
  <div id="simpleMap">
    <div></div>
    <l-map
      :zoom="zoom"
      :center="center"
      :options="mapOptions"
      @update:center="centerUpdate"
      @update:zoom="zoomUpdate"
    >
      <l-tile-layer :url="url" :attribution="attribution" />
      <l-marker :lat-lng="withPopup">
        <l-popup>
          <div @click="innerClick">
            I am a popup
            <p v-show="showParagraph">Nothing</p>
          </div>
        </l-popup>
      </l-marker>
      <l-marker :lat-lng="withTooltip">
        <l-tooltip :options="{ permanent: true, interactive: true }">
          <div @click="innerClick">
            I am a tooltip
            <p v-show="showParagraph">Nothing</p>
          </div>
        </l-tooltip>
      </l-marker>

      <l-circle
        v-for="(circle,index) in circles"
        v-bind:key="index"
        :lat-lng="circle.center"
        :radius="circle.radius"
        :color="circle.color"
      ></l-circle>

      <l-polyline :lat-lngs="lines.points" :color="lines.color"></l-polyline>
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
export default {
  name: "Example",
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LPopup,
    LTooltip,
    LCircle,
    LPolyline
  },
  data() {
    return {
      zoom: 8,
      center: latLng(22.41322, 113),
      url: "http://{s}.tile.osm.org/{z}/{x}/{y}.png",
      withPopup: latLng(47.41322, -1.219482),
      withTooltip: latLng(47.41422, -1.250482),
      currentZoom: 11.5,
      currentCenter: latLng(47.41322, -1.219482),
      showParagraph: false,
      mapOptions: {
        zoomSnap: 0.5
      },
      circles: [],
      lines: { points: [], color: "blue" },
      points: [],
      fullPoints: []
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
    innerClick() {
      alert("Click!");
    },
    generalPoints() {
      let lan = 22.41322;
      let lng = 113;
      let points = [];
      for (let i = 20; i >= 0; i--) {
        let ran = Math.floor(Math.random() * 10) / (100 * Math.random() + 0.1);
        points.push([lan + ran, lng + i * 0.1]);
      }
      this.fullPoints = points;
    },
    generalCircle() {
      let radius = 400;
      let color = "blue";
      let circles = [];
      for (let i in this.points) {
        circles.push({
          center: this.points[i],
          color,
          radius
        });
      }
      this.circles = circles;
    },
    generalLines() {
      this.lines.points = this.points;
      this.lines.color = "green";
    },
    generalRoute() {
      this.generalPoints();
      let glines = this.generalLines,
        gcircle = this.generalCircle;
      let points = this.points,
        num = 0,
        fullPoints = this.fullPoints;
      function gNextPoint() {
        num++;
        if (num + 1 < points.length) {
          setTimeout(gNextPoint, 1000);
        }
        points = fullPoints.slice(0, num);
        glines();
        gcircle();
      }
      gNextPoint();
    }
  },
  mounted() {
    this.generalRoute();
  }
};
</script>
<style scoped>
#simpleMap {
  height: 800px;
}
</style>
