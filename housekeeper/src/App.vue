<style lang="less" src="assets/less/rent.less"></style>
<template>
  <div id="app">
    <head-nent></head-nent>
    <router-view></router-view>
    <sider-bar></sider-bar>
  </div>
</template>
<script>
import { mapActions } from 'vuex'
import siderBar from 'common/siderbar'
import headNent from 'common/headNent'
export default {
  name: 'app',
  components: {siderBar, headNent},
  methods: mapActions(['hideSiderBar']),
  created () {
  }
}
</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  box-sizing: border-box;
  color: #2c3e50;
  width: 1280px;
  margin: 0 auto;
  min-height: 100%;
  background: #fff;
}
</style>
