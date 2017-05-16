<template>
  <div class="content">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item prop="username" class="marginleft">
        <el-input v-model="ruleForm.username" placeholder="请输入账号"></el-input>
      </el-form-item>
      <el-form-item prop="password" class="marginleft">
        <el-input v-model="ruleForm.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item class="marginleft paddingTop">
        <el-button type="primary" size="large" class="width" @click.stop.prevent="change('ruleForm')">确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
/* global fetcher:true */
export default {
  name: 'newLogin',
  data () {
    return {
      ldData: {
        username: '',
        password: ''
      },
      ruleForm: { password: '', username: '' },
      rules: {
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    change (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = '/manage/index/login'
          let data = {
            username: this.ruleForm.username,
            password: this.ruleForm.password
          }
          fetcher.get(url, data).then((res) => {
            if (res.success) {
              let userList = res.result
              window.localStorage.setItem('username', userList[0].userName)
              window.localStorage.setItem('apartmentId', userList[0].apartmentId)
              this.$router.push({path: '/apartIndex'})
              window.location.reload()
            } else {
              this.$message({ message: res.errors.messageCn })
            }
          }, (rej) => {
            console.log(rej)
          }).catch((err) => {
            console.log(err)
          })
        } else {
          return false
        }
      })
    }
  },
  created () {
  }
}
</script>
<style lang='less' scoped>
 .content{ padding: 40px 40px 0 40px; }
 .marginleft{ margin-left: -100px; }
 .width{ width: 300px }
 .paddingTop{ margin-top:30px; }
</style>
