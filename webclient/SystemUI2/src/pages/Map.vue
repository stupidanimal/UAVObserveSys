<template>
  <el-row>
    <el-col>
      <div class="block" v-bind:style="{height:style.height+'px'}">
        <MapComp></MapComp>
      </div>
    </el-col>
  </el-row>
</template>
<script>
import MapComp from "./components/MapComp";
export default {
  data() {
    return {
      style: {
        height: 0
      }
    };
  },
  components: {
    MapComp
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
</style>


