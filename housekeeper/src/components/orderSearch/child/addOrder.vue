<template>
  <div class="add-house">
    <div class="add-content">
      <el-form ref="form" :model="form" label-width="100px" :rules="rules">
        <el-row>
          <el-col :span="5" :offset="1">
            <el-form-item label="管家名称" required>
              <el-select v-model="form.ownerId">
                <el-option :label="user.owner" :value="user.id" v-for="user in userList" :key="user.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="管家电话" required>
              <el-button type="text" v-model="ownerTel">{{ownerTel}}</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="租客姓名" required prop="renterName">
              <el-input v-model="form.renterName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="租客电话" required prop="renterTel">
              <el-input v-model="form.renterTel"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="5" :offset="1">
            <el-form-item label="房屋ID" required prop="houseId">
              <el-input v-model="form.houseId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="租金" required prop="orderPrice">
              <el-input v-model="form.orderPrice"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="租期" required prop="orderType">
              <el-select v-model="form.orderType">
                <el-option :label="tag.name" :value="tag.id" v-for="tag in tagList" :key="tag.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6" :offset="1">
            <el-form-item label="起租日期" required prop="orderDate">
              <el-date-picker type="date" align="left" placeholder="选择日期" v-model="form.orderDate" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-button type="primary" @click="submitForm('form')">创建订单</el-button>
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
          renterName: '',
          renterTel: '',
          houseId: '',
          orderPrice: '',
          orderType: '',
          orderDate: ''
        },
        tagList: [{
          name: '3个月',
          id: '3个月'
        }, {
          name: '6个月',
          id: '6个月'
        }, {
          name: '9个月',
          id: '9个月'
        }, {
          name: '12个月',
          id: '12个月'
        }],
        renttype: [],
        rules: {
          renterName: [
            { validator: isRequired, trigger: 'blur' }
          ],
          renterTel: [
            { validator: isRequired, trigger: 'blur' }
          ],
          houseId: [
            { validator: isRequired, trigger: 'blur' }
          ],
          orderPrice: [
            { validator: isRequired, trigger: 'blur' }
          ],
          orderType: [
            { validator: isRequired, trigger: 'blur' }
          ],
          orderDate: [
            { validator: isRequired, trigger: 'blur' }
          ]
        },
        userList: []
      }
    },
    created () {
      this.showSideBar()
      this.getServant()
    },
    computed: {
      ownerTel: function () {
        let num = this.form.ownerId
        if (num === '') {
          return ''
        } else {
          return this.userList[num - 1].ownerTel
        }
      }
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
            console.log(this.form)
            this.form = Object.assign(this.form, { ownerTel: this.ownerTel, orderTag: '待审核' })
            let url = '/manage/order/addOrder'
            fetcher.post(url, this.form).then((res) => {
              console.log(res)
              if (res.success) {
                this.$message({ message: '创建订单成功' })
                setTimeout(() => {
                  location.reload()
                }, 3000)
              } else {
                this.$message({ message: '生成订单失败' })
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
.el-row {
  margin: 10px 0;
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
