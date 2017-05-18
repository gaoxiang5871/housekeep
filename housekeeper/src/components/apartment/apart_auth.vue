<style lang='less' scoped></style>
<template>
  <div class="acoountsecurity">
    <el-form :model="form" :rules="rules" ref="form"
      label-width="140px" class='acoount_form'>
      <el-row>
        <el-col>
          <el-form-item label="公司名称" prop="companyName">
            <el-input v-model="form.companyName"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-form-item label="公寓名称" prop="apartmentName">
            <el-input v-model="form.apartmentName"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="在租房源数量" prop="allNum">
            <el-input v-model="form.allNum"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="已租房源数量" prop="rentNum">
            <el-input v-model="form.rentNum"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="房源预约数量" prop="appointNum">
            <el-input v-model="form.appointNum"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-form-item class="tend">
            <el-button type="primary" class='submit' @click.stop.prevent="submit('form')">提交</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
/* global fetcher:true */
import { mapActions } from 'vuex'
export default {
  name: '',
  data () {
    return {
      form: {
        companyName: '',
        apartmentName: '',
        allNum: '',
        rentNum: '',
        appointNum: ''
      },
      rules: {
        companyName: [
          { required: true, message: '请输入公司名称', trigger: 'blur' }
        ],
        apartmentName: [
          { required: true, message: '请输入公寓名称', trigger: 'blur' }
        ],
        allNum: [
          { required: true, message: '请输入在租房源数量', trigger: 'blur' }
        ],
        rentNum: [
          { required: true, message: '请输入已租房源数量', trigger: 'blur' }
        ],
        appointNum: [
          { required: true, message: '请输入房源预约数量', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    ...mapActions([
      'showSideBar'
    ]),
    submit (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = '/manage/substation/auth'
          let data = this.form
          fetcher.post(url, data).then((res) => {
            console.log(res)
          }, (rej) => {
            console.log(rej)
          })
        } else {
          return false
        }
      })
    }
  },
  created () {
    this.showSideBar()
  }
}
</script>
<style lang='less' scoped>
.acoountsecurity{
  width: 1040px;
  min-height: 100%;
  margin-left: 240px;
  overflow: hidden;
  padding: 50px 250px 20px 100px;
  background: #fff;
}
.grid-content{
  min-height: 40px;
  line-height: 40px;
}
.bg-purple{
  background: #d3dce6;
}
.upload{
  position: relative;
  height: 150px;
  width: 100%;
}
#openBankscence, #license{
  display: block;
  top: 0;
  left: 0;
  width: 100px;
  height: 100px;
  border-radius: 5px;
  border: none;
}
#upfile_licese_btn{
  display: block;
  position: relative;
  top: 20px;
  width: 100px;
}
#upfile_licese, #upfile_openBankscence{
  position: absolute;
  left: 0;
  top: 120px;
  width: 100px;
  height: 40px;
  opacity: 0;
}
.plus{
  font-size: 40px;
  position: absolute;
  top: 30px;
  left: 40px;
}
.acoount_form{
  border: 1px solid #bfcbd9;
  border-radius: 5px;
  padding: 40px;
}
.tip{
  position: absolute;
  left: 120px;
  top: 0;
  line-height: 25px;
  text-align: left;
}
.submit{
  position: absolute;
  width: 100px;
  left: 0px;
  top: 20px;
}
.tend{
  border-top: 1px solid #ccc;
  height: 20px;
}
.star{
  position: absolute;
  left: -80px;
  top: 0;
}
.star:before{
  content: '*';
  color: #ff4949;
  margin-right: 4px;
}
.reset:before{
  position: absolute;
  left: -80px;
  top: 0;
}
</style>
