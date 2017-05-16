<template lang="html">
  <div class="appointment-container">
    <div class="appointment-search">
      <el-form ref="form" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="商圈:">
              <el-input v-model="queryForm.bussiness" placeholder="请输入商圈名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="小区名称:">
              <el-input v-model="queryForm.community" placeholder="请输入小区名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="看房时间:" class="right">
                <el-date-picker type="date" align="left" placeholder="选择日期" v-model="queryForm.date" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="房客电话:">
              <el-input v-model="queryForm.renterPhone" placeholder="请输入房客电话"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="管家:">
              <el-select v-model="queryForm.ownerId" placeholder="请选择管家" style="width: 233px;">
                <el-option
                  v-for="item in userList"
                  :label="item.owner"
                  :value="item.id" :key="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item>
              <el-button type="primary" @click="search">查询</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="appointment-result">
      <el-table :data="beanList" border style="width: 100%" empty-text="当前条件下无预约记录">
        <el-table-column prop="date" label="看房时间" align="center">
        </el-table-column>
        <el-table-column prop="bussiness" label="商圈" align="center">
        </el-table-column>
        <el-table-column prop="community" label="小区/公寓" align="center">
        </el-table-column>
        <el-table-column prop="owner" label="管家" align="center">
        </el-table-column>
        <el-table-column prop="ownerTel" label="管家电话" align="center">
        </el-table-column>
        <el-table-column prop="renterName" label="房客" align="center">
        </el-table-column>
        <el-table-column prop="renterPhone" label="房客电话" align="center">
        </el-table-column>
      </el-table>
      <div class="page-block" v-if="searchTag">
        <el-pagination
          :current-page="1"
          :page-size="15"
          layout="total, prev, pager, next"
          :total="totalCount">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
/* global fetcher:true */
import { mapActions } from 'vuex'
export default {
  data () {
    return {
      queryForm: {
        bussiness: '',
        community: '',
        date: '',
        renterPhone: '',
        ownerId: ''
      },
      searchTag: true,
      beanList: [],
      totalCount: 0,
      userList: []
    }
  },
  methods: {
    ...mapActions([
      'showSideBar'
    ]),
    getServant () {
      let url = '/manage/apartment/allServant'
      fetcher.get(url).then((res) => {
        if (res.success) {
          this.userList = res.result
        } else {
          this.$message({ message: '未知错误' })
        }
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
    },
    search () {
      this.searchTag = false
      this.beanList = []
      let url = '/manage/appoint/search'
      console.log(this.queryForm)
      fetcher.post(url, this.queryForm).then((res) => {
        this.searchTag = true
        if (res.success) {
          this.totalCount = res.result.length
          for (let i = 0; i < this.totalCount; i++) {
            res.result[i].date = this.dealDate(res.result[i].date)
          }
          this.beanList = res.result
        } else {
          this.$message({ message: res.errors.messageCn })
        }
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
    },
    dealDate (date) {
      let day = new Date(date)
      let y = day.getFullYear() + '-'
      let m = (day.getMonth() + 1 < 10 ? '0' + (day.getMonth() + 1) : day.getMonth() + 1) + '-'
      let d = day.getDate()
      return y + m + d
    }
  },
  created () {
    this.showSideBar()
    // 获取所有管家
    this.getServant()
  },
  watch: {
    queryForm: {
      handler: function () {
      },
      deep: true
    }
  }
}
</script>

<style lang="less">
  .appointment-container {
    padding-left: 240px;
    padding-top: 20px;
  }
  .page-block {
    margin-top: 15px;
  }
</style>
