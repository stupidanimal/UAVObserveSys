<template>
  <div id="app">
    <MainPanel></MainPanel>
  </div>
</template>

<script>
import MainPanel from "@/components/MainPanel";

export default {
  name: "app",
  components: {
    MainPanel
  }
};
</script>

<style>
#app {
  height: 100%;
  width: 100%;
}
</style>



