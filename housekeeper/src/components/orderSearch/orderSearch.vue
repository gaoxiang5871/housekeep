 <template>
  <div class="order-container">
  <div class="order-head">
    <el-form ref="form" label-width="100px">
      <el-row>
        <el-col :span="7">
          <el-form-item label="起租时间">
               <el-date-picker v-model="timeRange" type="daterange" placeholder="选择日期范围"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="6" :offset="1">
          <el-form-item label="订单状态">
             <el-select v-model="formOrder.orderType" placeholder="订单状态">
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
          <el-form-item label="租客电话">
            <el-input v-model="formOrder.renterTel" placeholder="请输入号码"></el-input>
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
            <el-input v-model="formOrder.renterName" placeholder="请输入姓名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="月租金">
            <el-input v-model="formOrder.orderPrice" placeholder="请输入月租金"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="4">
            <el-button type="primary" @click="searchResult">查询</el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
  <div class="order-content">
    <el-table :data="orderContent" border style="width: 1020">
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
            :total="totalCount">
          </el-pagination>
      </div>
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
      formOrder: {
        orderType: '',
        renterTel: '',
        orderId: '',
        renterName: '',
        orderPrice: ''
      },
      timeRange: [],
      orderContent: [],
      statusList: [{
        name: '待审核',
        type: '待审核'
      }, {
        name: '待支付',
        type: '待支付'
      }, {
        name: '已生效',
        type: '已生效'
      }, {
        name: '已过期',
        type: '已过期'
      }],
      totalCount: 0,
      pageStatus: false,
      currentPage: 1
    }
  },
  methods: {
    ...mapActions([
      'showSideBar'
    ]),
    searchResult () {
      let url = '/manage/order/search'
      if (this.timeRange.length > 0) {
        let startDate = this.timeRange[0]
        let endDate = this.timeRange[1]
        let data = {
          orderType: this.formOrder.orderType,
          renterTel: this.formOrder.renterTel,
          id: this.formOrder.orderId,
          renterName: this.formOrder.renterName,
          orderPrice: this.formOrder.orderPrice,
          startDate: startDate,
          endDate: endDate
        }
        fetcher.post(url, data).then((res) => {
          if (res.success) {
            console.log(res.result)
            this.orderContent = res.result
            this.totalCount = res.result.length
          } else {
            this.$message({ message: res.errors.messageCn })
          }
        }, (rej) => {
          console.log(rej)
        }).catch((err) => {
          console.log(err)
        })
      } else {
        fetcher.post(url, this.formOrder).then((res) => {
          if (res.success) {
            console.log(res.result)
            this.orderContent = res.result
            this.totalCount = res.result.length
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
    handleCurrentChange (val) {
      this.currentPage = val
      this.searchResult()
    }
  },
  created () {
    this.showSideBar()
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
