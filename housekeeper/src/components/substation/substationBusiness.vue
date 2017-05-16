<template lang="html">
  <div class="business-container">
    <div class="form-container">
      <el-form ref="form" label-width="80px">
        <el-row :gutter="20">
          <el-col :span="4">
            <el-form-item label="粒度" align="left">
              <el-select placeholder="请选择粒度">
                <el-option label="日"></el-option>
                <el-option label="月"></el-option>
                <el-option label="季"></el-option>
                <el-option label="年"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="14">
            <el-form-item label="时间查询" align="left">
              <el-date-picker type="date" placeholder="请选择开始时间"></el-date-picker>
              <el-date-picker type="date" placeholder="请选择结束时间"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="最近：" align="left">
          <el-row>
            <el-col :span="10">
              <el-button-group>
                <el-button type="primary">今天</el-button>
                <el-button type="primary">1个月</el-button>
                <el-button type="primary">3个月</el-button>
                <el-button type="primary">1年</el-button>
              </el-button-group>
            </el-col>
            <el-col :span="8">
              <el-button>查询</el-button>
              <el-button>导出</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
    </div>
    <div class="business-data-container">
      <el-table border style="width: 100%">
        <el-table-column prop="time" label="时间" align="center">
        </el-table-column>
        <el-table-column prop="time" label="全部房源" align="center">
        </el-table-column>
        <el-table-column prop="time" label="在租房源" align="center">
        </el-table-column>
        <el-table-column prop="time" label="已出租" align="center">
        </el-table-column>
        <el-table-column prop="time" label="浏览量" align="center">
        </el-table-column>
        <el-table-column prop="time" label="电话量" align="center">
        </el-table-column>
        <el-table-column prop="time" label="预约量" align="center">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
export default {
}
</script>

<style lang="less" scoped>
  .business-container {
    padding: 20px;
    padding-left: 240px;
  }
  .form-container {
    border: 1px solid #323232;
    padding: 10px;
  }
  .business-data-container {
    margin-top: 20px;
  }
</style>
