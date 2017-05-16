<template>
  <div class="content">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item prop="account" class="marginleft">
        <el-input v-model="ruleForm.account" placeholder="请输入账号"></el-input>
      </el-form-item>
      <el-form-item prop="password" class="marginleft">
        <el-input type="password" v-model="ruleForm.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item class="marginleft paddingTop">
        <el-button type="text" class="new" style="text-align:right" @click.stop.prevent='jump'>新账号注册</el-button>
      </el-form-item>
      <el-form-item class="marginleft paddingTop">
        <el-button type="primary" size="large" class="width" @click.stop.prevent="login('ruleForm')">确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
/* global fetcher:true */
export default {
  name: 'login',
  props: {
    isShow: Boolean
  },
  data () {
    return {
      ldData: {
        username: '',
        password: ''
      },
      ruleForm: { account: '', password: '' },
      rules: {
        account: [
          { required: true, message: '请输入公寓账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入公寓密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    login (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = '/manage/user/login'
          let data = {
            username: this.ruleForm.account,
            password: this.ruleForm.password
          }
          fetcher.get(url, data).then((res) => {
            console.log(res)
            if (res.success) {
              let userList = res.result
              window.localStorage.setItem('username', userList[0].userName)
              window.localStorage.setItem('apartmentId', userList[0].apartmentId)
              this.$router.push({path: '/apartIndex'})
              window.location.reload()
            } else {
              this.$message({ message: res.errors.messageCn })
              this.ruleForm.account = ''
              this.ruleForm.password = ''
            }
          }, (rej) => {
            console.log(rej)
          }).catch((err) => {
            console.log(err)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    jump () {
      this.$emit('ctrl_dia_type', 'addUser')
    }
  }
}
</script>
<style lang='less' scoped>
 .content{ padding: 40px 40px 0 40px; }
 .marginleft{ margin-left: -100px; }
 .new{ width: 70px;}
 .width{ width: 300px }
 .paddingTop{ margin-top: -10px; }
</style>
