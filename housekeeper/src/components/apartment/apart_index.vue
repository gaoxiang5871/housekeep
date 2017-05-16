<style lang='less' src="./less/apart_index.less" scoped></style>
<template>
  <div class="apartIndex">
    <el-row>
      <el-col :span="12">
        <div class="grid-content form-head">
          账户信息
        </div></el-col>
      <el-col :span="12">
        <div class="grid-content form-head">
          公寓数据
        </div></el-col>
      <el-col :span="12" class='text-left'>
        <div class="grid-content form-head">
          <el-col :span="24">
            <div class="grid-content bg-purple padding-left">
              公司名称：
              <span v-if="servantInfo.companyName">{{servantInfo.companyName}}</span>
              <span v-else>
                <el-button type="text" @click.stop.prevent="jump()">请进行公寓认证</el-button>
              </span>
            </div>
          </el-col>
          <el-col :span="24"><div class="grid-content bg-purple-light padding-left">
              商户名: {{servantInfo.apartmentName}}
          </div></el-col>
          <el-col :span="24">
            <div class="grid-content bg-purple padding-left">
              简称：{{servantInfo.apartmentName}}
            </div></el-col>
          <el-col :span="24">
            <div class="grid-content bg-purple-light  padding-left">
              账户名：{{servantInfo.username}}
            </div>
          </el-col>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content form-head">
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-col :span="24"><div class="grid-content bg-purple-light">在租量</div></el-col>
              <el-col :span="24"><div class="grid-content bg-purple-light"></div></el-col>
              <el-col :span="24"><div class="grid-content bg-purple-light">{{servantInfo.rentNum}}</div></el-col>
              <el-col :span="24"><div class="grid-content bg-purple-light"></div></el-col>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <el-col :span="24"><div class="grid-content bg-purple">预约量</div></el-col>
              <el-col :span="24"><div class="grid-content bg-purple"></div></el-col>
              <el-col :span="24"><div class="grid-content bg-purple">{{servantInfo.appointNum}}</div></el-col>
              <el-col :span="24"><div class="grid-content bg-purple"></div></el-col>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light">
              <el-col :span="24"><div class="grid-content bg-purple-light">出租量</div></el-col>
              <el-col :span="24"><div class="grid-content bg-purple-light"></div></el-col>
              <el-col :span="24"><div class="grid-content bg-purple-light">{{servantInfo.allNum}}</div></el-col>
              <el-col :span="24"><div class="grid-content bg-purple-light"></div></el-col>
            </div>
          </el-col>
        </div>
      </el-col>
    </el-row>
    <el-row>
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
/* global fetcher:true */
import { mapActions } from 'vuex'
import option from './data/chartoption.js'
let echarts = require('echarts')
export default {
  name: '',
  data () {
    return {
      servantInfo: {},
      tableData: [],
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
      chart: '',
      operationDataCurrentMonth: {},
      operationDataLast7Days: {},
      operationDataLastHalfAYear: {}
    }
  },
  methods: {
    ...mapActions([
      'showSideBar'
    ]),
    renderChart (data) {
      let username = window.localStorage.getItem('username')
      if (username === 'test2') {
        this.chartOption = Object.assign({}, this.chartOption, option)
        let myChart = echarts.init(this.$refs.ai_chart)
        myChart.setOption(this.chartOption)
      }
    },
    getAccount () {
      let url = '/manage/apartment/search'
      let apartment = window.localStorage.getItem('apartmentId')
      if (apartment) {
        let data = {
          apartment
        }
        fetcher.get(url, data).then((res) => {
          console.log(res)
          if (res.success) {
            this.servantInfo = res.result[0]
            this.servantInfo.username = window.localStorage.getItem('username')
          } else {
            this.$message({ message: '还未进行公寓认证，请进行公寓认证' })
          }
        }, (rej) => {
          console.log(rej)
        }).catch((err) => {
          console.log(err)
        })
      }
    },
    jump () {
      this.$router.push('/apartauth')
    }
  },
  created () {
    this.showSideBar()
    this.getAccount()
  },
  mounted () {
    // 加一个默认的初始化图表的
    this.renderChart()
  },
  watch: {
    timeValue: function () {
      let value = this.timeValue
      if (value === '1') {
        this.renderChart(this.operationDataLast7Days)
      }
      if (value === '2') {
        this.renderChart(this.operationDataCurrentMonth)
      }
      if (value === '3') {
        this.renderChart(this.operationDataLastHalfAYear)
      }
    },
    operationDataLast7Days: function () {
      this.renderChart(this.operationDataLast7Days)
    }
  }
}
</script>
<style lang='less' scoped>

</style>
