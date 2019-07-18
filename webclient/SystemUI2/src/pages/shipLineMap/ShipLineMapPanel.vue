<template>
  <el-row>
    <el-col>
      <div class="block" v-bind:style="{height:style.height+'px'}">
        <ShipLineMapComp></ShipLineMapComp>
      </div>
      <div class="condMenu">
        <ShipLineMapCond></ShipLineMapCond>
      </div>
    </el-col>
  </el-row>
</template>
<script>
import ShipLineMapComp from "./ShipLineMapComp";
import ShipLineMapCond from "./ShipLineMapCond";
export default {
  data() {
    return {
      style: {
        height: 0
      }
    };
  },
  components: {
    ShipLineMapComp, //地图组件
    ShipLineMapCond //地图筛选条件
  },
  mounted() {
    let height = window.innerHeight;
    this.style.height = Math.ceil(height * 0.75);
  }
};
</script>
<style scoped>
.pageContainer {
  display: block;
  background: pink;
}
.block {
  height: 80%;
  background: pink;
}
.condMenu {
  position: absolute;
  top: 5%;
  right: 1%;
  background: lightseagreen;

  z-index: 500;
  opacity: 0.3;
  transition: 0.3s;
}
.condMenu:hover {
  opacity: 1;
}
</style>


