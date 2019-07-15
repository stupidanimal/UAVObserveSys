<template>
  <div>
    <el-container>
      <el-header>
        Header
        <el-menu
          :default-active="this.$route.path"
          router
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
        >
          <el-menu-item index="/map">Map</el-menu-item>
          <el-menu-item index="/">Menu2</el-menu-item>
          <el-menu-item index="3">Menu3</el-menu-item>
          <el-menu-item index="4">Menu4</el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>



<script>
export default {
  name: "LastRowFixed",
  data: () => ({
    menuVisible: false
  })
};
</script>

<style scoped>
.md-app {
  height: 100%;
  width: 100%;
  border: 1px solid rgba(#000, 0.12);
}

/* .md-drawer {
  width: 230px;
  max-width: calc(100vw - 125px);
} */
.md-content {
  height: 100%;
  width: 100%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
}
</style>