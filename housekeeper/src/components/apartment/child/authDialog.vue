<!-- <style lang='less' scoped></style> -->
<template>
<div class="authdialog" v-show='isShow'>
  <div class="panel">
    <div class="close" @click.stop.prevent='close()'>
      X
    </div>
    <div class="content">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="子账号" prop="username">
          <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="子账号密码" prop="password">
          <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="管家姓名" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="type">
          <el-checkbox-group v-model="ruleForm.type"  type="flex" justify='center'>
            <el-checkbox label="公寓首页" name="type" class="el-cb"></el-checkbox>
            <el-checkbox label="房源管理" name="type" class="el-cb"></el-checkbox>
            <el-checkbox label="客源管理" name="type" class="el-cb"></el-checkbox>
            <el-checkbox label="预约管理" name="type" class="el-cb"></el-checkbox>
            <el-checkbox label="订单管理" name="type" class="el-cb"></el-checkbox>
            <el-checkbox label="财务管理" name="type" class="el-cb"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item  type="flex" justify='center'>
          <el-button type="primary" size="large" class="authbtn" @click.stop.prevent='changeRight'>确定</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</div>
</template>
<script>
/* global fetcher:true */
export default {
  props: {
    isShow: Boolean,
    type: Number,
    info: Object
  },
  data () {
    return {
      ruleForm: { username: '', password: '', name: '', type: [] },
      rules: {
        username: [
          { required: true, message: '请输入子账号', trigger: 'blur' }
          // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入管家姓名', trigger: 'blur' }
        ],
        date2: [
          { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
        ],
        type: [
          { type: 'array', required: true, message: '请至少选择一个项目', trigger: 'change' }
        ],
        resource: [
          { required: true, message: '请选择活动资源', trigger: 'change' }
        ],
        desc: [
          { required: true, message: '请填写活动形式', trigger: 'blur' }
        ]
      },
      typeMap: {},
      numMap: {}
    }
  },
  methods: {
    close () {
      this.$emit('ctl_auth_dia', '2')
    },
    add () {
      let url = '/manage/substation/addServant'
      let data = {
        username: this.ruleForm.username,
        password: this.ruleForm.password,
        name: this.ruleForm.name,
        limits: this.computeLimit()
      }
      console.log(data)
      fetcher.post(url, data).then((res) => {
        if (res.errorCode === 0) {
          this.$emit('ctl_auth_dia', '2')
        }
      }, (rej) => {
        console.log(rej)
      })
    },
    getRight () {
      let url = '/manage/substation/limits'
      fetcher.get(url).then((res) => {
        if (res.errorCode === 0) {
          let limits = res.data.limits
          this.numMap = Object.assign({}, this.numMap, limits)
          Object.keys(limits).forEach((key) => {
            this.typeMap[res.data.limits[key]] = key
            console.log()
          })
        }
      })
    },
    computeLimit () {
      let limits = 0
      this.ruleForm.type.map((el) => {
        limits += Number(this.typeMap[el])
        return limits
      })
      return limits
    },
    safeAndright () {
      // let url = '/manage/substation/updateServant'
      // let data = {
      //   username: this.ruleForm.username,
      //   password: this.ruleForm.password,
      //   name: this.ruleForm.name,
      //   limits: this.computeLimit()
      // }
      // fetcher.post(url, data).then((res) => {
      //   console.log(res)
      // })
      console.log(this.info)
    },
    changeRight () {
      console.log(this.type)
      switch (this.type) {
        case (0):
          this.add()
          break
        case (1):
          this.safeAndright()
          break
        case (2):
          this.safeAndright()
          break
        default:
          break
      }
    },
    getValue () {
      this.ruleForm.username = this.info.username
      this.ruleForm.password = this.info.password || ''
      this.ruleForm.name = this.info.name
      this.info.limitsStr.split(',').forEach((key, index) => {
        this.$set(this.ruleForm.type, index, this.numMap[key])
      })
      console.log(this.ruleForm)
    }
  },
  created () {
    this.getRight()
  },
  watch: {
    type: function () {
      if (this.type) {
        this.getValue()
      }
    }
  }
}
</script>
<style lang='less' scoped>
  .authdialog{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);
    z-index: 3;
    .panel{
      width: 500px;
      height: 380px;
      background: #fff;
      border-radius: 5px;
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      .close{
        position:absolute;
        top: 12px;
        right: 12px;
        font-size: 20px;
        z-index: 1;
      }
      .close:hover{
        cursor: pointer;
      }
      .content{
        padding: 40px;
      }
      .authbtn{
        width: 200px;
        margin-left: -100px;
      }
      .el-cb{
        float: left;
        width: 100px;
        padding-right: 20px;
        margin: 0;
        padding: 0;
      }
    }
  }
</style>
