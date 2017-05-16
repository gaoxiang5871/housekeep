<template>
  <div class="bv-container">
  </div>
</template>
<script>
/* global fetcher:true */
import { mapActions } from 'vuex'
export default {
  name: 'vcode',
  data () {
    return {
    }
  },
  methods: {
    sendCode () {
      let url = '/index/login/sms'
      let data = {
        username: 'zhangzhebin',
        phone: ''
      }
      fetcher.get(url, data).then((res) => {
        console.log(res)
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
    },
    preCheck () {
      //
    }
  }
}
</script>
<style lang='less' scoped>
</style>
