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
        <el-table-column prop="id" label="订单ID" align="center">
        </el-table-column>
        <el-table-column prop="renterName" label="租户姓名" align="center">
        </el-table-column>
        <el-table-column prop="renterTel" label="电话" align="center">
        </el-table-column>
        <el-table-column prop="orderPrice" label="月租金" align="center">
        </el-table-column>
        <el-table-column prop="orderType" label="租期" align="center">
        </el-table-column>
        <el-table-column prop="orderDate" label="起租日" align="center">
        </el-table-column>
        <el-table-column prop="orderTag" label="订单状态" align="center">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template scope="scope">
            <el-button size="mini" type="primary" @click="change(scope.row)">状态更改</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="order-page">
         <el-pagination
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="10"
            layout="total, prev, pager, next"
            :total="totalCount">
          </el-pagination>
      </div>
      <el-dialog v-model="changeTag" size="tiny" :show-close="false">
        <el-row>
          <el-col :span="4" :offset="6">
            <div style="line-height: 36px;">订单状态</div>
          </el-col>
          <el-col :span="6" :offset="1">
            <el-select v-model="orderChange">
              <el-option :label="tag.name" :value="tag.type" v-for="tag in statusList" :key="tag.type"></el-option>
            </el-select>
          </el-col>
        </el-row>
        <div slot="footer" class="dialog-footer">
          <el-col :span="4" :offset="10">
            <el-button type="primary" @click="tagChange">状态变更</el-button>
          </el-col>
        </div>
      </el-dialog>
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
      changeTag: false,
      orderChange: '',
      orderData: {},
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
            this.totalCount = res.result.length
            for (let i = 0; i < this.totalCount; i++) {
              res.result[i].orderDate = this.dealDate(res.result[i].orderDate)
            }
            this.orderContent = res.result
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
            this.totalCount = res.result.length
            for (let i = 0; i < this.totalCount; i++) {
              res.result[i].orderDate = this.dealDate(res.result[i].orderDate)
            }
            this.orderContent = res.result
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
    },
    dealDate (date) {
      let day = new Date(date)
      let y = day.getFullYear() + '-'
      let m = (day.getMonth() + 1 < 10 ? '0' + (day.getMonth() + 1) : day.getMonth() + 1) + '-'
      let d = day.getDate()
      return y + m + d
    },
    change (obj) {
      this.changeTag = true
      this.orderData = obj
    },
    tagChange () {
      if (this.orderData.orderTag === this.orderChange) {
        this.$message({ message: '请正确修改订单状态' })
      } else {
        let url = '/manage/order/tagChange'
        let data = {
          id: this.orderData.id,
          orderTag: this.orderChange
        }
        fetcher.post(url, data).then((res) => {
          if (res.success) {
            this.$message({ message: '订单修改成功' })
            this.changeTag = false
          } else {
            this.$message({ message: res.errors.messageCn })
          }
        }, (rej) => {
          console.log(rej)
        }).catch((err) => {
          console.log(err)
        })
      }
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
.dialog-footer {
  padding-bottom: 50px;
}
</style>
