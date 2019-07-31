<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span>航次筛选</span>
      </div>
      <div>
        <!-- <el-form-item label="活动区域"> -->
        <el-select placeholder="请选择航次" v-model="selectedLineCode">
          <el-option
            v-for="(linecode,index) in lineCodeList"
            :label="linecode"
            :value="linecode"
            v-bind:key="index"
          ></el-option>
        </el-select>
        <!-- </el-form-item> -->
      </div>
    </el-card>
  </div>
</template>
<script>
import { getLineCodeCollection } from "@/api";

export default {
  data() {
    return {
      lineCodeList: [],
      selectedLineCode: ""
    };
  },
  watch: {
    selectedLineCode(currentValue) {
      this.$store.commit("selectShipLine", currentValue);
    }
  },
  mounted() {
    let app = this;
    getLineCodeCollection()
      .then(res => {
        this.lineCodeList = res.data;
        this.lineCodeList.push(2); //为了测试用的
      })
      .catch(() => {
        app.lineCodeList = [];
      });
  }
};
</script>
<style scoped>
</style>



