<style lang='less' scoped></style>
<template>
  <div class="acoountsecurity">
    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple display padding">
          登录密码: 已设置
          <el-button type="primary" @click.stop.prevent="ctrlgdia('changeWord')">修改</el-button>
        </div>
      </el-col>
    </el-row>
    <login :is-show='isShow' :cur-view='curView' @ctr_lg_dia='ctrlgdia'></login>
  </div>
</template>
<script>
import { mapActions } from 'vuex'
import login from '../login/login'
export default {
  data () {
    return {
      isShow: false,
      curView: ''
    }
  },
  methods: {
    ...mapActions([
      'showSideBar'
    ]),
    ctrlgdia (msg) {
      this.isShow = !this.isShow
      this.curView = msg
    }
  },
  components: {
    login
  },
  created () {
    this.showSideBar()
  }
}
</script>
<style lang="less" scoped>
.acoountsecurity{
  width: 1040px;
  min-height: 100%;
  margin-left: 240px;
  overflow: hidden;
  padding: 20px 20px 20px 20px;
  background: #fff;
}
.grid-content{
  min-height: 40px;
  line-height: 40px;
}
.bg-purple {
  background: #d3dce6;
}
.display{
  display: flex;
  justify-content: space-between;
}
.padding{
  padding: 10px 20px;
}
</style>

