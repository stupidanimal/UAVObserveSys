<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-menu mode="horizontal" router>
          <!-- <el-menu-item>
            <el-image :src="'./assets/icon.png'"></el-image>
          </el-menu-item>-->
          <el-menu-item index="/map">Map</el-menu-item>
          <el-menu-item index="/balloonSearch">Menu1</el-menu-item>
          <el-menu-item index="/page2">Menu2</el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
      <el-footer>footer如果有需要的话可以加些内容，如果没有可以删除</el-footer>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "app",
  components: {}
};
</script>

<style scoped>
#app {
  width: 100%;
  margin: 0 auto;
  padding: 0 auto;
}
* {
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}
</style>
