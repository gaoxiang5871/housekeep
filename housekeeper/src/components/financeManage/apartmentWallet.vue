<template lang="html">
  <div class="appartmentWallet-container">
    <el-row>
      <el-col :span="11">
        <div class="title">现金钱包</div>
      </el-col>
      <el-col :span="11" :offset="1">
        <div class="title">保理钱包</div>
        <el-button type="text" @click="openTip">额度不够？</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="11">
        <el-row>
          <el-col :span="8" :offset="3" align="right"><div class="can">可提现余额：</div></el-col>
          <el-col :span="4"><div class="money">1000元</div></el-col>
          <el-col :span="4" :offset="4"><el-button type="danger" @click="total">提现</el-button></el-col>
        </el-row>
        <el-row>
          <el-col :span="8" :offset="3"><div class="all" align="right">总余额：</div></el-col>
          <el-col :span="4"><div class="money">1000元</div></el-col>
        </el-row>
      </el-col>
      <el-col :span="11">
        <el-row>
          <el-col :span="8" :offset="3" align="right"><div class="can">保理额度：</div></el-col>
          <el-col :span="4"><div class="money">1000元</div></el-col>
          <el-col :span="4" :offset="4"><el-button type="danger">申请借款</el-button></el-col>
        </el-row>
        <el-row>
          <el-col :span="8" :offset="3"><div class="all" align="right">可提现额度：</div></el-col>
          <el-col :span="4"><div class="money">1000元</div></el-col>
          <el-col :span="4" :offset="4"><el-button type="danger">提现</el-button></el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="11">
        <div class="title">支付</div>
      </el-col>
      <el-col :span="11" :offset="1">
        <div class="title">分期</div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="11">
        <el-row>
          <el-col :span="8" :offset="3" align="right"><div class="can">可提现余额：</div></el-col>
          <el-col :span="4"><div class="money">1000元</div></el-col>
        </el-row>
        <el-row>
          <el-col :span="8" :offset="3"><div class="all" align="right">总余额：</div></el-col>
          <el-col :span="4"><div class="money">1000元</div></el-col>
        </el-row>
      </el-col>
      <el-col :span="11">
        <el-row>
          <el-col :span="8" :offset="3" align="right"><div class="can">可提现余额：</div></el-col>
          <el-col :span="4"><div class="money">1000元</div></el-col>
        </el-row>
        <el-row>
          <el-col :span="8" :offset="3"><div class="all" align="right">总余额：</div></el-col>
          <el-col :span="4"><div class="money">1000元</div></el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-row>
      <el-row>
        <el-col :span="24" type='flex' align='left'>
          <div class="grid-content paddingTop" id="selectOption">
            <el-select v-model="timeValue" placeholder="请选择">
              <el-option
                v-for="item in timeOptions"
                :label="item.label"
                :value="item.value"
                :key="item.label">
              </el-option>
            </el-select>
          </div>
        </el-col>
      </el-row>
      <el-row type='flex' align='center'>
        <el-col :span="24">
          <div class="grid-content paddingTop" ref="ai_chart" id='chart'>
          </div>
        </el-col>
      </el-row>
    </el-row>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import option from './data/chatOption.js'
let echarts = require('echarts')
export default {
  data () {
    return {
      chartOption: [],
      timeOptions: [
        {
          label: '最近7天',
          value: '1'
        }, {
          label: '本月',
          value: '2'
        }, {
          label: '最近半年',
          value: '3'
        }
      ],
      timeValue: '',
      chart: ''
    }
  },
  created () {
    this.showSideBar
  },
  methods: {
    ...mapActions([
      'showSideBar'
    ]),
    renderChart () {
      this.chartOption = Object.assign({}, this.chartOption, option)
      let myChart = echarts.init(this.$refs.ai_chart)
      myChart.setOption(this.chartOption)
    },
    openTip () {
      this.$message({message: '额度不够，请联系400-000-0000'})
    },
    total () {
      this.$message({message: '当前为人工筑转账，系统正在对接中'})
    }
  },
  mounted () {
    // 加一个默认的初始化图表的
    this.renderChart()
  },
  watch: {
    timeValue: function () {
      this.renderChart()
    }
  }
}
</script>

<style lang="less" scoped>
  .appartmentWallet-container {
    padding: 20px;
    padding-left: 240px;
  }
  .title {
    display: inline-block;
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 10px;
  }
  .el-row {
    margin-bottom: 20px;
  }
  .can, .money, .all {
    font-size: 20px;
    line-height: 36px;
  }
  #chart{
    height: 400px;
  }
  .paddingTop{
    padding: 20px 0 0 0;
  }
  #selectOption{
    padding-left: 40px;
    position: relative;
    top: 48px;
    z-index: 1;
  }

</style>
