<template>
  <div class="hello">
    <el-row>
      <el-col :span="24"><div class="grid-content bg-purple-dark"></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="12"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="12"><div class="grid-content bg-purple-light"></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="8"><div class="grid-content bg-purple-light"></div></el-col>
      <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light"></div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light"></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
      <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
      <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
    </el-row>
    <el-row type="flex" justify="center">
      <el-col :span="4" >
        <el-input v-model="input" placeholder="请输入内容" v-validate="'required|email'" type="text" name="email"></el-input>
        <span v-show="errors.has('email')">{{ errors.first('email') }}</span>
      </el-col>
      <el-col :span="4" >
        <el-input v-model="tel" placeholder="请输入内容" v-validate="{ rules: { required: true, regex: rule.tel} }" type="text" name="tel"></el-input>
        <!-- <span v-show="errors.has('tel')">{{ errors.first('tel') }}</span> -->
      </el-col>
    </el-row>
  </div>
</template>
<script>
/* global fetcher:true */
import { mapGetters, mapActions } from 'vuex'
import { telphone } from 'plugin/rule'
export default {
  name: 'app',
  data () {
    return {
      msg: '',
      input: '',
      tel: '',
      rule: {}
    }
  },
  computed: mapGetters({
    telephone: 'telephone'
  }),
  methods: mapActions([
    'setTelephone',
    'showSideBar'
  ]),
  created () {
    this.rule = Object.assign({}, this.rule, {tel: telphone})
    this.showSideBar()
    let url = '/manage/index/login'
    let params = {
      username: 'zhangzhebin',
      password: '111111'
    }
    fetcher.get(url, params).then((res) => {
      console.log(res)
    }, (rej) => {
      console.log(rej)
    }).catch((err) => {
      console.log(err)
    })
  },
  beforeRouteEnter (to, from, next) {
    next()
  },
  watch: {
    errors: {
      handler: function () {
        console.log(this.errors)
      },
      deep: true
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang='less' scoped>
  .hello{
    width: 1280px;
    padding-left: 240px;
    height: 100%;
  }
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>
