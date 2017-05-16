<template>
  <div class="index">
    <index-header @ctr_lg_dia='login'></index-header>
    <div class="index-logo">
      <el-row class="index-tip">租房后台管理系统</el-row>
    </div>
    <login :is-show='isShow' :cur-view='curView' @ctr_lg_dia='login'></login>
  </div>
</template>
<script>
// vuex2的调用api的新方式，
import indexHeader from './login/child/header'
import login from './login/login'
import { mapActions } from 'vuex'
export default {
  name: 'index',
  data () {
    return {
      isShow: false,
      curView: ''
    }
  },
  methods: {
    ...mapActions(['hideSiderBar']),
    login (msg) {
      this.isShow = !this.isShow
      this.curView = msg
    }
  },
  // ready已经在vue取消
  created () {
    this.hideSiderBar()
  },
  components: {
    indexHeader,
    login
  }
}
</script>

<style lang='less' scoped>
.index {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}

.index-logo {
  height: 100%;
  background: url(../../static/img/banner.png) no-repeat;
  background-size: 100% 100%;
}
.index-tip {
  position: absolute;
  top: 250px;
  width: 100%;
  height: 155px;
  font-size: 50px;
  color: #FFFFFF;
}
</style>
