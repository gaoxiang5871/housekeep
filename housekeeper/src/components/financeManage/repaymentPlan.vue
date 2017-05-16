<template lang="html">
  <div class="repaymentPlan-container">
    <el-row :gutter="20" class="repaymentPlan-desc-container" type="flex" justify="between" >
      <el-col :span="8" align="left">
        <div class="bg-purple grid-content">
          <span class="item-title">贷款总额:</span>
          <span class="item-content">12000</span>
        </div>
      </el-col>
      <el-col :span="8" align="left">
        <div class="bg-purple grid-content">
          <span class="item-title">剩余欠款:</span>
          <span class="item-content">12000</span>
        </div>
      </el-col>
      <el-col :span="8" align="left">
        <div class="bg-purple grid-content">
          <span class="item-title">还款方式:</span>
          <span class="item-content">等额本金</span>
        </div>
      </el-col>
    </el-row>
    <el-table border style="width: 100%">
      <el-table-column prop="time" label="期数" align="center">
      </el-table-column>
      <el-table-column prop="time" label="还款本金" align="center">
      </el-table-column>
      <el-table-column prop="time" label="还款利息" align="center">
      </el-table-column>
      <el-table-column prop="time" label="还款总额" align="center">
      </el-table-column>
    </el-table>
    <el-upload class="upload-demo" action="//jsonplaceholder.typicode.com/posts/" :on-preview="handlePreview" :on-remove="handleRemove" :file-list="fileList">
      <el-button size="small" type="primary">点击上传</el-button>
    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
</el-upload>
  </div>
</template>

<script>
export default {
}
</script>

<style lang="less" scoped>
  .repaymentPlan-container {
    padding: 20px;
    padding-left: 260px;
  }
  .repaymentPlan-desc-container {
    margin-bottom: 20px;
    border: 1px solid #dcdcdc;
    height: 100px;
  }
  .repaymentPlan-desc-container span {
    line-height: 100px;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    height: 100%;
    text-align: center;
  }
  .item-title {
    font-size: 20px;
  }
  .item-content {
    font-size: 22px;
    text-indent: 20px;
    display: inline-block;
  }
</style>
