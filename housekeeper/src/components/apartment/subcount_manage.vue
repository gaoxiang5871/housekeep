<style lang='less' src="./less/aubcount.less" scoped></style>
<template>
  <div class="subContainer">
    <div class="fisrtrow grid-content">
      <el-row type="flex" justify='center'>
        <el-col :span="3">
        </el-col>
        <el-col :span="20">
          <div class="grid-content form-head">
            <template v-for="item in tableDataTH">
              <el-col :span="4">
                <div class="grid-content borderTop bg-purple-light"
                  :class="{'point':item.isNeedHandle}"
                  @click.stop.prevent='add(item.isNeedHandle)'>
                  {{item.text}}
                </div>
              </el-col>
            </template>
          </div>
        </el-col>
      </el-row>
      <template v-for="item in tableData">
        <el-row type="flex" justify='center'>
         <el-col :span="3">
         </el-col>
          <el-col :span="20">
              <el-col :span="4">
                <div class="grid-content borderTop" :class="{'borderBottom':item.last}">{{item.username}}</div>
              </el-col>
              <el-col :span="4">
                <div class="grid-content borderTop" :class="{'borderBottom':item.last}">{{item.name}}</div>
              </el-col>
              <el-col :span="4">
                <div class="grid-content borderTop" :class="{'borderBottom':item.last}">{{item.state}}</div>
              </el-col>
              <el-col :span="4">
                <div class="grid-content borderTop" :class="{'borderBottom':item.last}" @click.stop.prevent='changAuth($event, item)'>
                  <span class="subBtn forbidden">禁用</span>
                  <span class="subBtn right">权限</span>
                  <span class="subBtn security">安全</span>
                </div>
              </el-col>
              <el-col :span="4">
                <div class="grid-content borderTop" :class="{'borderBottom':item.last}"></div>
              </el-col>
          </el-col>
        </el-row>
      </template>
      <div class="block">
        <el-pagination
          layout="prev, pager, next"
          :page-count="totalPage"
          @current-change="currChange($event)">
        </el-pagination>
      </div>
    </div>
    <auth-dialog :is-show="isShow" :type='type' :info='info' @ctl_auth_dia="ctrAuthDia"></auth-dialog>
  </div>
</template>
<script>
/* global fetcher:true */
import { mapActions } from 'vuex'
import authDialog from './child/authDialog'
export default {
  name: '',
  data () {
    return {
      tableDataTH: [
        {
          text: '子账号管理'
        }, {
          text: '管家姓名'
        }, {
          text: '状态'
        }, {
          text: '操作'
        }, {
          text: '新增',
          isNeedHandle: true
        }
      ],
      tableData: [],
      isShow: false,
      index: -1,
      ishowPassword: true,
      type: 0,
      info: {},
      totalPage: 0
    }
  },
  components: {
    authDialog
  },
  methods: {
    ...mapActions([
      'showSideBar'
    ]),
    render (index) {
      let url = '/manage/substation/servantList'
      let data = {
        curPage: index,
        size: 10
      }
      fetcher.get(url, data).then((res) => {
        if (res.errorCode === 0) {
          let len = res.data.pageBean.beanList.length
          if (len === 0) {
            this.tableData
            return
          }
          res.data.pageBean.beanList.forEach((el, index) => {
            this.$set(this.tableData, index, Object.assign({}, {
              username: el.username,
              name: el.name,
              state: el.status === 1 ? '启用' : '禁用',
              limits: el.limits,
              limitsStr: el.limitsStr,
              password: el.password
            }))
            if (index === len - 1) {
              this.$set(this.tableData, index, Object.assign({}, this.tableData[index], {last: true}))
            }
            this.totalPage = res.data.pageBean.totalPage
          })
        }
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
    },
    changAuth (event, item) {
      console.log(event.target.className === 'subBtn')
      let className = event.target.className
      if (className.indexOf('forbidden') > -1) {
        // 调用禁用接口
        this.forbidden(item)
      }
      if (className.indexOf('right') > -1) {
        this.type = 1
        this.isShow = true
        this.info = Object.assign({}, this.info, item)
      }
      if (className.indexOf('security') > -1) {
        // 调用禁用接口
        this.type = 2
        this.isShow = true
        this.info = Object.assign({}, this.info, item)
      }
    },
    forbidden (item) {
      let url = '/manage/substation/updateStatus'
      let data = {
        username: item.username
      }
      fetcher.post(url, data).then((res) => {
        console.log(res)
      })
    },
    add () {
      this.isShow = true
    },
    ctrAuthDia (flag) {
      if (flag === '1') {
        this.isShow = true
        return
      }
      if (flag === '2') {
        this.isShow = false
        return
      }
    },
    currChange (index) {
      this.render(index)
    }
  },
  created () {
    this.showSideBar()
    this.render(0)
  }
}
</script>
<style lang='less' scoped>
</style>
