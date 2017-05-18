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
    <el-row v-if="show()">
      <el-col :span="2" :offset="21">
        <el-button type="text" @click="search">公寓操作记录</el-button>
      </el-col>
    </el-row>
    <el-dialog v-model="searchTag" :show-close="false">
      <el-table :data="queryData" border style="width: 100%" empty-text="无操作记录">
        <el-table-column prop="userName" label="操作账户" align="left"></el-table-column>
        <el-table-column prop="houseId" label="操作房屋ID"></el-table-column>
        <el-table-column prop="activityTag" label="操作内容"></el-table-column>
        <el-table-column prop="activityDate" label="操作时间"></el-table-column>
      </el-table>
    </el-dialog>
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
      chartOption: [],
      chart: '',
      searchTag: false,
      queryData: []
    }
  },
  methods: {
    ...mapActions([
      'showSideBar'
    ]),
    show () {
      let apartment = window.localStorage.getItem('apartmentId')
      if (apartment !== '0') {
        return true
      } else {
        return false
      }
    },
    getData () {
      let url = '/manage/apartment/data'
      let apartment = window.localStorage.getItem('apartmentId')
      if (apartment !== '0') {
        let data = {
          apartmentId: apartment
        }
        fetcher.get(url, data).then((res) => {
          if (res.success) {
            let info = res.result
            let infoDay = []
            let infoAll = []
            let infoAppoint = []
            let infoRent = []
            for (let i = 0; i < info.length; i++) {
              infoDay.push(this.dealDate(info[i].apartmentDay))
              infoAll.push(info[i].rentAll)
              infoAppoint.push(info[i].appointAll)
              infoRent.push(info[i].rentNumber)
            }
            this.chartOption = Object.assign({}, this.chartOption, option)
            this.chartOption.xAxis[0].data = infoDay
            this.chartOption.series[0].data = infoAll
            this.chartOption.series[1].data = infoAppoint
            this.chartOption.series[2].data = infoRent
            let myChart = echarts.init(this.$refs.ai_chart)
            myChart.setOption(this.chartOption)
          } else {
            this.$message({ message: res.errors.messageCn })
          }
        }, (rej) => {
          console.log(rej)
        }).catch((err) => {
          console.log(err)
        })
      }
    },
    getAccount () {
      let url = '/manage/apartment/search'
      let apartment = window.localStorage.getItem('apartmentId')
      if (apartment) {
        let data = {
          apartment: apartment
        }
        fetcher.get(url, data).then((res) => {
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
    },
    dealDate (date) {
      let day = new Date(date)
      let y = day.getFullYear() + '-'
      let m = (day.getMonth() + 1 < 10 ? '0' + (day.getMonth() + 1) : day.getMonth() + 1) + '-'
      let d = day.getDate()
      return y + m + d
    },
    search () {
      let url = '/manage/activity/search'
      let apartment = window.localStorage.getItem('apartmentId')
      let data = {
        apartmentId: apartment
      }
      fetcher.get(url, data).then((res) => {
        this.searchTag = true
        if (res.success) {
          console.log(res)
          for (let i = 0; i < res.result.length; i++) {
            res.result[i].activityDate = this.dealDate(res.result[i].activityDate)
          }
          this.queryData = res.result
        } else {
          this.$message({ message: '未知错误' })
        }
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
    }
  },
  created () {
    this.showSideBar()
    this.getAccount()
  },
  mounted () {
    // 加一个默认的初始化图表的
    this.getData()
  }
}
</script>
<style lang='less' scoped>
.el-row {
  margin-bottom: 20px;
}
</style>
