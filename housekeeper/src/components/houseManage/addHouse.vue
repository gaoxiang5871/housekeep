<template>
  <div class="add-house">
    <div class="add-content">
      <el-form ref="form" :model="form" label-width="100px" :rules="rules">
        <el-row>
          <el-col :span="6">
            <el-form-item label="租赁类型">
              <el-select v-model="form.rentType">
                <el-option label="整租" value="整租"></el-option>
                <el-option label="合租" value="合租"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="管家名称">
              <el-select v-model="form.ownerId">
                <el-option :label="user.owner" :value="user.id" v-for="user in userList" :key="user.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="装修类型" required prop="decoration">
              <el-radio v-model="form.decoration" label="精装" value="精装">精装</el-radio>
              <el-radio v-model="form.decoration" label="简装" value="简装">简装</el-radio>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="城市" required prop="city">
              <el-input v-model="form.city"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="行政区" required prop="district">
              <el-input v-model="form.district"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="商圈名" required prop="business">
              <el-input v-model="form.bussiness"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="小区" required prop="community">
              <el-input v-model="form.community"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="楼栋" required prop="buildingNum">
              <el-input v-model="form.buildingNum"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="单元" required prop="initNum">
              <el-input v-model.number="form.initNum"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="房间号" required prop="houseNum">
              <el-input v-model.number="form.houseNum"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="楼层" required prop="floor">
              <el-input v-model="form.floor"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="面积" required prop="acrea">
              <el-input v-model.number="form.acrea"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="价格" required prop="price">
              <el-input v-model.number="form.price"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="朝向" required prop="orient">
              <el-radio v-model="form.orient" label="东" value="东">东</el-radio>
              <el-radio v-model="form.orient" label="南" value="南">南</el-radio>
              <el-radio v-model="form.orient" label="西" value="西">西</el-radio>
              <el-radio v-model="form.orient" label="北" value="北">北</el-radio>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          
        </el-row>
        <el-row>
          <el-col>
            <el-button type="primary" @click="submitForm('form')">提 交</el-button>
          </el-col>
        </el-row>
      </el-form>
    </div>
  </div>
</template>

<script>
  /* global fetcher:true */
  import { mapActions } from 'vuex'
  export default {
    name: 'addHouse',
    data () {
      const isRequired = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('该项为必填项'))
        } else {
          callback()
        }
      }
      return {
        form: {
          ownerId: '',
          rentType: '',
          orient: '',
          city: '',
          district: '',
          bussiness: '',
          community: '',
          buildingNum: '',
          initNum: '',
          houseNum: '',
          acrea: '',
          price: '',
          floor: '',
          decoration: ''
        },
        renttype: [],
        rules: {
          city: [
            { validator: isRequired, trigger: 'blur' }
          ],
          district: [
            { validator: isRequired, trigger: 'blur' }
          ],
          business: [
            { validator: isRequired, trigger: 'blur' }
          ],
          community: [
            { validator: isRequired, trigger: 'blur' }
          ],
          buildingNum: [
            { validator: isRequired, trigger: 'blur' }
          ],
          initNum: [
            { validator: isRequired, trigger: 'blur' }
          ],
          houseNum: [
            { validator: isRequired, trigger: 'blur' }
          ],
          floor: [
            { validator: isRequired, trigger: 'blur' }
          ],
          orient: [
            { required: true, message: '请选择房屋朝向', trigger: 'change' }
          ],
          decoration: [
            { required: true, message: '请选择装修类型', trigger: 'change' }
          ],
          acrea: [
            { validator: isRequired, trigger: 'blur' }
          ],
          price: [
            { validator: isRequired, trigger: 'blur' }
          ]
        },
        housetype: [{
          value: '整租',
          label: '整租'
        }, {
          value: '合租',
          label: '合租'
        }],
        userList: []
      }
    },
    created () {
      this.showSideBar()
      this.getServant()
    },
    methods: {
      ...mapActions([
        'showSideBar'
      ]),
      getServant () {
        let url = '/manage/apartment/allServant'
        fetcher.get(url).then((res) => {
          if (res.success) {
            this.userList = res.result
          } else {
            this.$message({ message: '未知错误' })
          }
        }, (rej) => {
          console.log(rej)
        }).catch((err) => {
          console.log(err)
        })
      },
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.form = Object.assign(this.form, {rentTag: '待出租'})
            console.log(this.form)
            let url = '/manage/house/addHouse'
            fetcher.post(url, this.form).then((res) => {
              if (res.success) {
                this.$message({ message: '提交成功' })
                setTimeout(() => {
                  location.reload()
                }, 3000)
              } else {
                this.$message({ message: '提交失败' })
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
      }
    }
  }
</script>

<style lang='less' scoped>
.add-house {
  padding-left: 240px;
}
.add-header {
  height: 50px;
  line-height: 50px;
  border-bottom: 1px solid midnightblue;
  border-right: 1px solid midnightblue;
}
.add-content {
  background: #FFFFFF;
  padding: 20px 0;
}
#button {
  position: relative;
  height: 35px;
  overflow: hidden;
}
#change-img {
  position: absolute;
  top: 0;
  left: 0;
}
#house-img{
  position: absolute;
  top: 0;
  left: 0;
  height: 35px;
  opacity: 0;
}
#img-box {
  width: 100px;
  height: 100px;
  position: relative;
  margin: 0 10px;
}
#img {
  width: 100px;
  height: 100px;
}
.close-img {
  display: inline-block;
  line-height: 20px;
  cursor: pointer;
  width: 20px;
  height: 20px;
  position: absolute;
  top: 0;
  right: 0;
}
</style>
