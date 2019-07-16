<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span>航次筛选</span>
      </div>
      <div>
        <!-- <el-form-item label="活动区域"> -->
        <el-select placeholder="请选择航次">
          <el-option
            v-for="(linecode,index) in lineCodeList"
            label="linecode"
            value="linecode"
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
      lineCodeList: []
    };
  },
  mounted() {
    let app = this;
    getLineCodeCollection()
      .then(res => {
        this.lineCodeList = res;
        alert(JSON.stringify(res));
      })
      .catch(err => {
        app.lineCodeList = [];
        alert(JSON.stringify(err));
      });
  }
};
</script>
<style scoped>
/* .text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
} */
</style>



