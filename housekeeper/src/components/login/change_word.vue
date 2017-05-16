<template>
  <div class="content">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item prop="password" class="marginleft">
        <el-input type="password" v-model="ruleForm.password" placeholder="请输入原密码"></el-input>
      </el-form-item>
      <el-form-item prop="pass" class="marginleft">
        <el-input type="password" v-model="ruleForm.pass" placeholder="请输入新密码"></el-input>
      </el-form-item>
      <el-form-item class="marginleft paddingTop">
        <el-button type="primary" size="large" class="width" @click.stop.prevent="bindPhone('ruleForm')">确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
/* global fetcher:true */
export default {
  name: 'bindate',
  data () {
    const check = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入新密码'))
      } else if (value === this.ruleForm.password) {
        callback(new Error('两次输入的一致'))
      } else {
        callback()
      }
    }
    return {
      ruleForm: { password: '', pass: '' },
      rules: {
        password: [
          { required: true, message: '请输入原密码', trigger: 'blur' }
        ],
        pass: [
          { validator: check, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    bindPhone (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = '/manage/user/changeWord'
          let data = {
            username: window.localStorage.getItem('username'),
            password: this.ruleForm.password,
            pass: this.ruleForm.pass
          }
          fetcher.get(url, data).then((res) => {
            console.log(res)
            if (res.success) {
              this.$message({ message: '修改成功' })
              this.$router.push({path: '/apartIndex'})
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
