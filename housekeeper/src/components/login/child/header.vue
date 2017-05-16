<template>
  <div class="index-header">
    <el-row class="index-header">
      <el-col :span="4" :offset="19">
        <ul>
          <li @click="goIndex" :class="{ change: changeTag}">
            <i class="icon i-icon-home-default"></i>
            <p>首页</p>
          </li>
          <li @click.stop.prevent="show" :class="{ change: !changeTag}">
            <i class="icon i-keyuanguanli"></i>
            <p>登录</p>
          </li>
        </ul>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data () {
    return {
      changeTag: true
    }
  },
  methods: {
    goIndex () {
      this.changeTag = true
    },
    show () {
      this.$emit('ctr_lg_dia', 'lgDialog')
      this.changeTag = false
    }
  }
}
</script>
<style lang='less' scoped>
.index-header {
  height: 50px;
  line-height: 50px;
  background: #fff;
}
.logo {
  height: 50px;
  display: flex;
  justify-content: center;
}
span {
  display: inline-block;
}
ul {
  display: flex;
  justify-content: space-around;
  font-size: 18px;
}
li {
  cursor: pointer;
}
li p {
  display: inline-block;
}
.change {
  color: #FA3232;
}
</style>
