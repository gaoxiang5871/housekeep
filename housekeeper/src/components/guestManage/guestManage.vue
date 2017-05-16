<style type="less"  scoped>
.tourists-head{
  padding-left: 260px;
  padding-top: 20px;
}
.tourists-top{
  border: 1px solid #969696;
  padding:20px;
}
.tourists-content{
  margin-top:20px;
}
.order-page{
  margin-top:20px;
}
</style>
<template>
<div class="tourists-head">
  <div class="tourists-top">
   <el-form ref="form" label-width="100px">
        <el-row >
          <el-col :span="6">
            <el-form-item label="租客姓名:">
              <el-input v-model="formInline.user" placeholder="请输入租房姓名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="租客手机号：">
              <el-input v-model="formInline.tel" placeholder="请输入手机号码"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="租赁情况：">
               <el-select v-model="formInline.situation" placeholder="租赁情况">
                <el-option label="在租" value="-2"></el-option>
                <el-option label="退组" value="-3"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item>
              <el-button type="primary" @click="search">查询</el-button>
            </el-form-item>
          </el-col>
        </el-row>
    </el-form>
  </div>
  <div class="tourists-content">
    <el-table :data="formSearch" border style="width: 100%">
        <el-table-column prop="user.userCertifiedName" label="租客姓名" align="center">
        </el-table-column>
        <el-table-column prop="user.userPhone" label="租客手机号" align="center" width="125">
        </el-table-column>
        <el-table-column prop="base.orderStatusName4IsInRent" label="租赁情况" align="center">
        </el-table-column>
        <el-table-column prop="servant.servantName" label="管家" align="center">
        </el-table-column>
        <el-table-column prop="servant.servantPhone" label="管家手机号" align="center">
        </el-table-column>
      </el-table>
  </div>
  <div class="order-page" v-if="pageStatus">
     <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="10"
        layout="total, prev, pager, next"
        :total="pageContent.totalCount">
      </el-pagination>
  </div>
</div>
</template>
<script>
/* global fetcher:true */
import { mapActions } from 'vuex'
export default {
  data () {
    return {
      pageStatus: false,
      formInline: {
        user: '',
        tel: '',
        situation: '',
        order: '分期',
        isOver: '',
        isAboutpay: ''
      },
      formSearch: [],
      pageContent: [],
      currentPage: 1
    }
  },
  created () {
    this.showSideBar()
  },
  methods: {
    ...mapActions([
      'showSideBar'
    ]),
    search () {
      this.searchResult()
    },
    searchResult () {
      let url = '/manage/order/list'
      let data = {
        apartmentName: window.localStorage.substationName,
        userCertifiedName: this.formInline.user,
        userPhone: this.formInline.tel,
        apartmentOrderStatus: this.formInline.situation,
        isOverdue: this.formInline.isOver,
        isAboutToPay: this.formInline.isAboutpay,
        curPage: this.currentPage - 1,
        pageSize: 10
      }
      console.log(data)
      fetcher.post(url, data).then((res) => {
        if (res.errorCode === 0) {
          if (res.data.orderList.length > 0) {
            this.pageStatus = true
          }
          this.formSearch = res.data.orderList
          this.pageContent = res.data.pageBean
        } else {
          this.$message({ message: res.errorMsg })
        }
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
    },
    exportExecl () {
      let url = '/manage/order/list/export?exportType=2'
      let data = {
        apartmentName: window.localStorage.substationName,
        userCertifiedName: this.formInline.user,
        userPhone: this.formInline.tel,
        apartmentOrderStatus: this.formInline.situation,
        isOverdue: this.formInline.isOver,
        isAboutToPay: this.formInline.isAboutpay
      }
      console.log(data)
      fetcher.post(url, data, 'blob').then((res) => {
        var blob = new Blob([res], { type: 'application/vnd.ms-excel' })
        var downloadUrl = URL.createObjectURL(blob)
        var a = document.createElement('a')
        a.href = downloadUrl
        a.download = '客源管理.xls'
        document.body.appendChild(a)
        a.click()
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
    },
    handleCurrentChange (val) {
      this.currentPage = val
      this.searchResult()
    }
  },
  filters: {
    tansForm: function (val) {
      if (val === 0) {
        return '否'
      } else if (val === 1) {
        return '是'
      } else {
        return ''
      }
    }
  }
}
</script>
