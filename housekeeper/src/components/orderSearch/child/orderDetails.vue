<template>
  <!-- <div class="orderDet">orderDetails</div> -->
  <div v-show="lgDetails">
    <div class="orderDetails-header" v-for="item in message">
    <div class="orderDetails-close" @click.stop.prevent="closeOrder">
      <i class="el-icon-close close"></i>
    </div>
      <div class="orderDetails-user">
        <p>用户基本信息</p>
        <p><span>租户姓名：<span v-if="item.user">{{item.user.userCertifiedName}}</span></span>
        <span>电话：<span v-if="item.user">{{item.user.userPhone}}</span></span></p>
      </div>
      <div class="orderDetails-tenant">
        <p>租户信息</p>
        <p><span>租金：<span v-if="item.base">{{item.base.monthlyMoney}}</span></span>
        <span>地址：<span v-if="item.house">{{item.house.address}}</span></span>
        <span>起租日：<span v-if="item.base">{{item.base.rentDate}}</span></span></p>
      </div>
      <div class="orderDetails-order">
        <p>订单信息</p>
        <p><span>交易时间：<span v-if="item.base">{{item.base.createTime}}</span></span>
        <span>审核描述：<span v-if="item.base">{{item.base.checkResult}}</span></span></p>
        <p><span>订单类型：分期</span>
        <span>订单状态：<span v-if="item.base">{{item.base.orderStatusName}}</span></span></p>
      </div>
      <div class="orderDetails-payment" >
        <p>订单分期信息</p>
        <p><span>剩余还款金额：<span v-if="item.bills.summary">{{item.bills.summary.remainingAmount}}</span></span></p>
        <p><span>完成期数：<span v-if="item.bills.summary">{{item.bills.summary.completedPeriods}}</span></span>
        <span >剩余期数：<span v-if="item.bills.summary">{{item.bills.summary.remainingPeriods}}</span></span></p>
        <p><span>是否逾期：<span v-if="item.bills.summary">{{item.bills.summary.isOverdue}}</span></span>
        <span >逾期天数：<span v-if="item.bills.summary">{{item.bills.summary.overdueDay}}</span></span></p>
        <p>最近完成还款时间：<span v-if="item.bills.summary">{{item.bills.summary.previousPayDate}}</span></p>
        <p>下次还款时间：<span v-if="item.bills.summary">{{item.bills.summary.nextPayDate}}</span></p>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    lgDetails: false,
    message: Array
  },
  data () {
    return {
    }
  },
  methods: {
    closeOrder (event) {
      if (event.target.className.indexOf('close') > -1) {
        this.$emit('hello')
      }
    }
  }
}
</script>
<style lang="less" scoped>
.orderDetails-header{
  z-index:10;
  position: fixed;
  left:50%;
  top:50%;
  margin-top:90px;
  transform: translate(-50%,-50%);
  width: 600px;
  background-color: white;
  padding: 20px;
  background: #ffffff;
}
p{
  line-height: 30px;
  text-align: left;
  padding-left: 10px;
  font-size: 16px;
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  color: #48576a;
}
.orderDetails-user,.orderDetails-tenant,.orderDetails-payment,.orderDetails-order{
  border: 1px solid #ccc;
  margin-bottom: 20px;
}
.orderDetails-close{
  text-align: right;
  margin-bottom: 10px;
}
p span {
  margin-right: 30px;
}
</style>
