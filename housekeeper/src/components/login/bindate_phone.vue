
<template>
  <div class="content">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item prop="telephone" class="marginleft">
        <el-input v-model="ruleForm.telephone" placeholder="请输入手机号码"></el-input>
      </el-form-item>
      <el-form-item prop="sms" class="marginleft align">
        <el-input v-model="ruleForm.sms" placeholder="请输入验证码" class="qcodeput"></el-input>
        <el-button
          type="primary"
          class="qcodebtn"
          :class="{isGrey: isGrey}"
          @click.stop.prevent="sendCode">{{text}}</el-button>
      </el-form-item>
      <el-form-item class="marginleft paddingTop">
        <el-button type="primary" size="large" class="width" @click.stop.prevent='bindPhone'>确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
/* global fetcher:true */
export default {
  name: 'bindate',
  data () {
    return {
      ruleForm: { telephone: '', sms: '' },
      rules: {
        account: [
          { required: true, message: '请输入公寓账号', trigger: 'blur' }
        ],
        sms: [
          { required: true, message: '请输入验证码', trigger: 'blur' }
        ]
      },
      text: '获取验证码',
      txtArr: ['获取验证码', '重新获取'],
      isGrey: false
    }
  },
  methods: {
    sendCode () {
      let username = window.localStorage.getItem('username') || ''
      if (this.isGrey) return
      let url = '/manage/index/login/sms'
      let data = {
        username,
        phone: this.ruleForm.telephone
      }
      fetcher.get(url, data).then((res) => {
        console.log(res)
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
      this.countDown()
    },
    bindPhone () {
      let newer = window.localStorage.getItem('newer')
      let username = window.localStorage.getItem('username')
      let url = newer === 'true' ? '/manage/index/phone/bind' : '/manage/index/code/login'
      let data = {
        username,
        phone: this.ruleForm.telephone,
        code: this.ruleForm.sms
      }
      fetcher.get(url, data).then((res) => {
        console.log(res.errorCode === 0 && newer === 'false')
        if (res.errorCode === 0 && newer === 'false') {
          window.localStorage.setItem('substationName', res.data.user.substationName)
          window.localStorage.setItem('substationId', res.data.user.substationId)
          window.localStorage.setItem('level', res.data.user.level)
          window.localStorage.setItem('telephone', this.ruleForm.telephone)
          window.localStorage.setItem('limitsStr', res.data.user.limitsStr)
          this.$router.push({path: '/apartIndex'})
          return
        }
        if (res.errorCode === 0 && newer === 'true') {
          newer && this.$emit('ctrl_dia_type', 'changeWord')
          return
        }
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
    },
    preCheck () {
      //
    },
    countDown () {
      if (this.isGrey) return
      this.isGrey = true
      this.text = '60 S'
      let temp = 60
      this.timer = setInterval(() => {
        temp--
        this.text = temp + ' S'
        if (temp === 0) {
          this.text = this.txtArr[1]
          clearInterval(this.timer)
          this.isGrey = false
        }
      }, 1000)
    }
  },
  created () {
    let newer = window.localStorage.getItem('newer')
    let telephone = window.localStorage.getItem('telephone') || ''
    if (newer === 'false' && telephone) {
      this.ruleForm.telephone = telephone
    }
  }
}
</script>
<style lang='less' scoped>
 .content{ padding: 40px 40px 0 40px; }
 .marginleft{ margin-left: -100px; }
 .width{ width: 300px }
 .paddingTop{ margin-top:30px; }
 .qcodeput{width:160px}
 .qcodebtn{width:100px;margin-left:36px;}
 .align{display: flex;}
 .isGrey{background: #ccc;}
</style>
