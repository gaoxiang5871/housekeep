 <template>
  <div class="order-container">
  <div class="order-head">
    <el-form ref="form" label-width="100px">
      <el-row>
        <el-col :span="7">
          <el-form-item label="起租时间">
               <el-date-picker v-model="formOrder.timeRange" type="daterange" placeholder="选择日期范围"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="6" :offset="1">
          <el-form-item label="订单状态">
             <el-select v-model="formOrder.value" placeholder="订单状态">
              <el-option
                v-for="item in statusList"
                :label="item.name"
                :value="item.type"
                :key="item.name">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="电话">
            <el-input v-model="formOrder.orderTel" placeholder="请输入号码"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="7">
          <el-form-item label="单号查询">
            <el-input v-model="formOrder.orderId" placeholder="请输入单号"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6" :offset="1">
          <el-form-item label="租户姓名">
            <el-input v-model="formOrder.user" placeholder="请输入姓名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="月租金">
            <el-input v-model="formOrder.money" placeholder="请输入月租金"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="4">
            <el-button type="primary" @click="search">查询</el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
  <div class="order-content">
    <el-table :data="orderContent" border style="width: 1020" @cell-click="showDetail">
   <!--      <el-table-column prop="base.orderNum" label="订单ID" align="center" width="110" class-name="orderId"></el-table-column> -->
        <el-table-column label="订单ID" align="center">
        <template scope="scope">
          <span v-if="scope.row.base" class="orderNumber">{{ scope.row.base.orderNum}}</span>
        </template>
        </el-table-column>
        <el-table-column prop="user.userCertifiedName" label="租户姓名" align="center">
        </el-table-column>
        <el-table-column prop="user.userPhone" label="电话" align="center">
        </el-table-column>
        <el-table-column prop="base.monthlyMoney" label="月租金" align="center">
        </el-table-column>
        <el-table-column prop="base.rentLease" label="租期" align="center">
        </el-table-column>
        <el-table-column prop="base.rentDate" label="起租日" align="center">
        </el-table-column>
        <el-table-column prop="base.orderStatusName" label="订单状态" align="center">
        </el-table-column>
        <el-table-column prop="base.checkResult" label="审核描述" align="center">
        </el-table-column>
      </el-table>
      <div class="order-page" v-if="pageStatus">
         <el-pagination
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="10"
            layout="total, prev, pager, next"
            :total="pageContent.totalCount">
          </el-pagination>
      </div>
      <order-details :lg-details="lgdetails" :message="lists" v-on:hello="test"></order-details>
  </div>
 </div>
</div>
</template>
<script>
/* global fetcher:true */
import orderDetails from './child/orderDetails.vue'
import { mapActions } from 'vuex'
export default {
  data () {
    return {
      lists: [],
      lgdetails: false,
      formOrder: {
        timeRange: [],
        kind: '分期',
        isOver: '',
        orderId: '',
        orderTel: '',
        money: '',
        value: '',
        user: ''
      },
      orderContent: [
      ],
      statusList: [],
      pageContent: [],
      pageStatus: false,
      currentPage: 1
    }
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
        startDate: new Date(this.formOrder.timeRange[0]).getTime(),
        endDate: new Date(this.formOrder.timeRange[1]).getTime(),
        apartmentOrderStatus: this.formOrder.value,
        orderNum: this.formOrder.orderId,
        userCertifiedName: this.formOrder.user,
        userPhone: this.formOrder.orderTel,
        monthlyMoney: this.formOrder.money,
        isOverdue: this.formOrder.isOver,
        curPage: this.currentPage - 1,
        pageSize: 10
      }
      console.log(data)
      fetcher.post(url, data).then((res) => {
        if (res.errorCode === 0) {
          if (res.data.orderList.length > 0) {
            this.pageStatus = true
          }
          this.orderContent = res.data.orderList
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
    test () {
      this.lgdetails = false
    },
    handleCurrentChange (val) {
      this.currentPage = val
      this.searchResult()
    },
    showDetail (row, column, cell, event) {
      console.log(event)
      if (event.target.className === 'orderNumber') {
        let url = '/manage/order/list'
        let data = {
          apartmentName: window.localStorage.substationName,
          orderNum: event.target.innerHTML
        }
        console.log(data)
        fetcher.post(url, data).then((res) => {
          if (res.errorCode === 0) {
            this.lists = res.data.orderList
            this.lgdetails = true
            console.log(this.lists)
          } else {
            this.$message({ message: res.errorMsg })
          }
        }, (rej) => {
          console.log(rej)
        }).catch((err) => {
          console.log(err)
        })
      }
    },
    getStats () {
      let url = '/manage/order/statusEnum'
      let params = {}
      fetcher.get(url, params).then((res) => {
        if (res.errorCode === 0) {
          this.statusList = res.data.orderStatusEnumList
        }
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
    }
  },
  components: {
    orderDetails
  },
  created () {
    this.showSideBar()
    this.getStats()
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
<style type="less" scoped>
  .order-container{
    padding-left: 260px;
    padding-top: 20px;
  }
  .order-head{
  border: 1px solid #969696;
  padding:20px;
  padding-left: 0;
}
.order-content{
  margin-top: 20px;
  height: 441px;
}
.order-page{
  margin-top: 20px;
}
.order-content .orderNumber {
    text-decoration: underline;
    color: #20A0FF;
    cursor: pointer;
}
</style>
