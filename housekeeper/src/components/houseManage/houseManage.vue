<template>
  <div class="house-manage">
    <div class="house-search">
      <el-form ref="form" :model="form" label-width="80px">
        <el-row>
          <el-col :span="6" :offset="1">
            <el-form-item label="城市">
              <el-select v-model="form.city" placeholder="请选择城市">
                <el-option :label="city.cityName" :value="city.cityCode" v-for="city in cityList" :key="city.cityCode"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6" :offset="1">
            <el-form-item label="商圈">
              <el-input v-model="form.bussiness"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6" :offset="1">
            <el-form-item label="小区/公寓">
              <el-input v-model="form.community"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6" :offset="1">
            <el-form-item label="楼栋">
              <el-input v-model="form.buildingNum"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6" :offset="1">
            <el-form-item label="单元">
              <el-input v-model="form.initNum"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6" :offset="1">
            <el-form-item label="房间号">
              <el-input v-model="form.houseNum"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6" :offset="1">
            <el-form-item label="类型">
              <el-select v-model="form.rentType">
                <el-option label="整租" value="整租"></el-option>
                <el-option label="合租" value="合租"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6" :offset="1">
            <el-form-item label="租赁状态">
              <el-select v-model="form.rentTag">
                <el-option label="待出租" value="待出租"></el-option>
                <el-option label="已租出" value="已租出"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6" :offset="1">
            <el-form-item label="管家">
              <el-select v-model="form.ownerId">
                <el-option v-for="item in userList" :key="item.id" :label="item.owner" :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="4" :offset="18">
            <el-button type="primary" @click="search">查询</el-button>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="house-content" v-if="searchTag">
    	<el-row v-if="show()">
        <el-col :span="4" :offset="1">
          <el-button type="primary" @click="change">管家变更</el-button>
        </el-col>
      </el-row>
      <el-table :data="queryData" border style="width: 100%" @selection-change="handleSelectionChange" empty-text="当前条件下无房源">
        <el-table-column type="selection"></el-table-column>
        <el-table-column prop="rentType" label="类型" align="left"></el-table-column>
        <el-table-column prop="bussiness" label="公寓区域" show-overflow-tooltip></el-table-column>
        <el-table-column prop="buildingNum" label="楼栋"></el-table-column>
        <el-table-column prop="initNum" label="单元"></el-table-column>
        <el-table-column prop="houseNum" label="房间号"></el-table-column>
        <el-table-column prop="acrea" label="面积"></el-table-column>
        <el-table-column prop="price" label="价格"></el-table-column>
        <el-table-column prop="rentTag" label="租赁状态"></el-table-column>
        <el-table-column prop="owner"  label="管家"></el-table-column>
        <el-table-column label="操作">
          <template scope="scope">
            <el-button size="mini" type="primary" @click="order(scope.row)" v-if="isShow(scope.row.rentTag)">预约</el-button>
            <el-button size="mini" type="primary" @click="upDown(scope.row, false)" v-if="isShow(scope.row.rentTag)">下架</el-button>
            <el-button size="mini" type="primary" @click="upDown(scope.row, true)" v-if="noShow(scope.row.rentTag)">上架</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="this.page"
        :page-size="15"
        layout="total, prev, pager, next"
        :total="this.totalCount">
      </el-pagination>
    </div>
    <el-dialog v-model="changeTag" size="tiny" :show-close="false">
      <el-row>
        <el-col :span="4" :offset="6">
          <div style="line-height: 36px;">管家</div>
        </el-col>
        <el-col :span="6">
          <el-select v-model="owerChange">
            <el-option :label="user.owner" :value="user.id" v-for="user in userList" :key="user.id"></el-option>
          </el-select>
        </el-col>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-col :span="4" :offset="10">
          <el-button type="primary" @click="userChange">管家变更</el-button>
        </el-col>
      </div>
    </el-dialog>

    <el-dialog v-model="orderTag" size="tiny" :show-close="false" title="用户预约">
      <el-form :model="orderForm" label-width="100px" :rules="orderRules" ref="orderForm">
        <el-form-item label="用户称呼：" prop="renterName" required>
          <el-col :span="18">
            <el-input v-model="orderForm.renterName"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item prop="renterSex" required>
          <el-radio-group v-model="orderForm.renterSex">
            <el-radio-button label="先生"></el-radio-button>
            <el-radio-button label="女士"></el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号：" required>
          <el-col :span="18">
            <el-input v-model.number="orderForm.renterPhone" v-validate="{ rules: { required: true, regex: phoneRule.tel} }" name="tel"></el-input>
            <span v-show="errors.has('tel')">请输入正确的手机号</span> 
          </el-col>
        </el-form-item>
        <el-form-item label="看房日期" prop="date" required>
          <el-col :span="18">
            <el-date-picker type="date" align="left" placeholder="选择日期" v-model="orderForm.date" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="看房时间" prop="time" required>
          <el-col :span="18">
            <el-select v-model="orderForm.time">
              <el-option label="上午" value="上午"></el-option>
              <el-option label="下午" value="下午"></el-option>
            </el-select>
          </el-col>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="putOrder('orderForm')">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
/* global fetcher:true */
import { mapActions } from 'vuex'
import { telphone } from 'plugin/rule'
export default {
  name: 'houseManage',
  data () {
    const isRequired = (rule, value, callback) => {
      if (!value) {
        callback(new Error('该项为必填项'))
      } else {
        callback()
      }
    }
    return {
      form: {
        city: '',
        bussiness: '',
        community: '',
        buildingNum: '',
        initNum: '',
        houseNum: '',
        rentType: '',
        rentTag: '',
        ownerId: ''
      },
      totalCount: 0,
      cityList: [{
        cityName: '北京',
        cityCode: '北京'}],
      housetype: [{
        value: '整租',
        label: '整租'
      }, {
        value: '合租',
        label: '合租'
      }],
      userList: [],
      owerChange: '',
      multipleSelection: [],
      queryData: [],
      page: 1,
      searchTag: true,
      changeTag: false,
      orderForm: {
        renterName: '',
        renterSex: '',
        renterPhone: '',
        date: '',
        time: ''
      },
      orderTag: false,
      orderData: {},
      phoneRule: {},
      orderRules: {
        renterName: [
          { validator: isRequired, trigger: 'blur' }
        ],
        renterSex: [
          { required: true, message: '请选择称呼', trigger: 'change' }
        ],
        date: [
          { validator: isRequired, trigger: 'blur' }
        ],
        time: [
          { validator: isRequired, trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    this.phoneRule = Object.assign({}, this.phoneRule, {tel: telphone})
    this.showSideBar()
    // 获取所有管家
    this.getServant()
  },
  methods: {
    ...mapActions([
      'showSideBar'
    ]),
    show () {
      let apartment = window.localStorage.getItem('apartmentId')
      if (apartment !== '0') {
        return true
      } else {
        return false
      }
    },
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
    postData (url, data) {
      this.searchTag = false
      this.queryData = []
      fetcher.post(url, data).then((res) => {
        this.searchTag = true
        if (res.success) {
          this.queryData = res.result
          this.totalCount = this.queryData.length
        } else {
          this.$message({ message: res.errors.messageCn })
        }
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
    },
    search () {
      this.postData('/manage/house/search', this.form)
    },
    handleCurrentChange (val) {
      this.page = val
      this.postData('/manage/house/search', this.form)
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    change () {
      if (this.multipleSelection.length === 0) {
        this.$message({ message: '请先选择房源' })
      } else if (this.multipleSelection.length > 1) {
        this.$message({ message: '请选择一个房源' })
      } else {
        this.changeTag = true
      }
    },
    userChange () {
      let url = '/manage/house/changeOwner'
      let data = {
        id: this.multipleSelection[0].id,
        ownerId: this.owerChange
      }
      fetcher.post(url, data).then((res) => {
        if (res.success) {
          this.$message({ message: '修改成功' })
          this.changeTag = false
          this.searchTag = false
          let act = '修改管家'
          this.setActivity(this.multipleSelection[0].id, act)
        } else {
          this.$message({ message: '修改失败' })
        }
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
    },
    setActivity (houseId, activityTag) {
      let username = window.localStorage.getItem('username')
      let url = '/manage/activity/addActivity'
      let data = {
        userName: username,
        houseId: houseId,
        activityTag: activityTag
      }
      fetcher.post(url, data).then((res) => {
        if (res.success) {
          console.log(res)
        } else {
          this.$message({ message: '修改失败' })
        }
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
    },
    upDown (obj, tag) {
      if (obj) {
        let url = '/manage/house/changeStyle'
        let data = {
          id: obj.id,
          rentTag: tag ? '待出租' : '已租出'
        }
        fetcher.post(url, data).then((res) => {
          if (res.success) {
            this.$message({ message: '修改成功' })
            this.searchTag = false
          } else {
            this.$message({ message: res.errorMsg })
          }
        }, (rej) => {
          console.log(rej)
        }).catch((err) => {
          console.log(err)
        })
      } else {
        if (this.multipleSelection.length === 0) {
          this.$message({ message: '请先选择房源' })
        } else {
          let idList = []
          for (let i = 0; i < this.multipleSelection.length; i++) {
            idList.push(this.multipleSelection[i].id)
          }
          let url = '/manage/house/changeHoseOverdue'
          let data = {
            houseId: idList.join(','),
            changeStatus: tag ? '待出租' : '已租出'
          }
          fetcher.post(url, data).then((res) => {
            if (res.errorCode === 0) {
              this.searchTag = false
            } else {
              this.$message({ message: res.errorMsg })
            }
          }, (rej) => {
            console.log(rej)
          }).catch((err) => {
            console.log(err)
          })
        }
      }
    },
    isShow (status) {
      let apartment = window.localStorage.getItem('apartmentId')
      if (apartment !== '0') {
        if (status === '待出租') {
          return true
        } else {
          return false
        }
      } else {
        return false
      }
    },
    noShow (status) {
      let apartment = window.localStorage.getItem('apartmentId')
      if (apartment !== '0') {
        if (status === '已租出') {
          return true
        } else {
          return false
        }
      } else {
        return false
      }
    },
    order (obj) {
      this.orderTag = true
      this.orderData = obj
      console.log(this.orderData.ownerId)
    },
    putOrder (formName) {
      if (this.orderForm.renterPhone === '') {
        this.$message({ message: '请输入手机号' })
        return
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = '/manage/house/addAppoint'
          let orderPush = {}
          Object.assign(orderPush,
            {
              houseId: this.orderData.id,
              ownerId: this.orderData.ownerId,
              renterName: this.orderForm.renterName,
              renterSex: this.orderForm.renterSex,
              renterPhone: this.orderForm.renterPhone,
              date: this.orderForm.date,
              time: this.orderForm.time
            })
          fetcher.post(url, orderPush).then((res) => {
            if (res.success) {
              this.$message({ message: '提交预约信息成功' })
              this.orderTag = false
              this.searchTag = false
              this.orderForm.renterName = ''
              this.orderForm.renterSex = ''
              this.orderForm.renterPhone = ''
              this.orderForm.date = ''
              this.orderForm.time = ''
            } else {
              this.$message({ message: res.errors.messageCn })
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
.house-manage {
  padding-left: 240px;
}
.house-search {
  overflow: hidden;
  padding: 10px 0;
}
.el-row {
  margin-bottom: 20px;
}
.house-content {
  margin-top: 20px;
}
.dialog-footer {
  padding-bottom: 50px;
}
</style>
