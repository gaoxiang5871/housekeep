<template>
  <div class="tophead" v-if="isHasSiderBar">
    <header>
      <span  @click.stop.prevent='lgOut'>{{headerData.loginOut}}</span>
      <span>{{headerData.role}}</span>
      <span>{{headerData.profile}}</span>
    </header>
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
export default {
  name: 'tophead',
  data () {
    return {
      headerData: {
        profile: '',
        role: '',
        loginOut: '登录'
      }
    }
  },
  computed: mapGetters({
    isHasSiderBar: 'isHasSiderBar'
  }),
  created () {
    let roleText = window.localStorage.getItem('apartmentId')
    let username = window.localStorage.getItem('username')
    this.headerData = Object.assign({}, this.headerData, {
      profile: username,
      role: roleText === '0' ? '用户' : '管理员',
      loginOut: username ? '退出' : '登录'
    })
  },
  methods: {
    lgOut () {
      window.localStorage.clear()
      this.$router.push('/index')
    }
  }
}
</script>
<style lang='less' scoped>
  .tophead{
    box-sizing: border-box;
    width: 1280px;
    height: 60px;
    background: #34495E;
    overflow: hidden;
    padding-left: 240px;
    header{
      width: 1040px;
      height: 80px;
      border-bottom: 1px solid #ccc;
      box-shadow: 4px 0 0 inset;
      span{
        display: block;
        color: #fff;
        float: right;
        width: 80px;
        height: 60px;
        line-height: 60px;
      }
      span:hover{
        cursor: pointer;
      }
    }
  }
</style>
