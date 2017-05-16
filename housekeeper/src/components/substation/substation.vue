<template lang="html">
  <div class="substation-container">
    <div v-if="hasSubstationData">
      <div class="apartment-name">
        <span class="title">公寓简称：</span>
        <span>{{form.name}}</span>
      </div>
      <div class="apartment-desc">
        <span class="title">公寓描述：</span>
        <span class="content">
          {{appartmentDesc}}
        </span>
      </div>
      <div class="apart-region">
        <span class="title">公寓房源覆盖：</span>
        <span>{{apartmentCity}}</span>
      </div>
      <div class="apartment-banner">
        <span class="title">活动banner：</span>
        <ul v-if="hasBanner" class="img-list">
          <li v-for="item in form.banners">
            <img v-bind:src="item" alt="" class="logo-lg">
          </li>
        </ul>
        <span v-if="!hasLogo">你还没有添加公寓logo,赶紧去添加吧</span>
      </div>
      <div class="apartment-logo" >
        <span class="title">活动logo：</span>
        <ul v-if="hasLogo" class="img-list">
          <li v-for="(item, index) in form.logos">
            <img v-bind:src="item" alt="" v-bind:class="logoClass(index)">
          </li>
        </ul>
        <span v-if="!hasLogo">你还没有添加公寓logo,赶紧去添加吧</span>
      </div>
      <div class="apartment-feature">
        <span class="title">公寓特色：</span>
        <span>{{featureDesc}}</span>
      </div>
    </div>
    <div v-if="!hasSubstationData">
      您还没有添加子站运营数据，赶紧去添加子站运营数据吧！
    </div>
    <div class="modify-apartment">
      <el-button type="primary" v-on:click="modifySubstation">{{modifyButtonContent}}</el-button>
    </div>
  </div>
</template>

<script>
import substation from './net/substation-net'
export default {
  data () {
    return {
      form: {
        backgroudPicture: '',
        banners: '',
        basePosition: '',
        city: '',
        citys: [],
        description: '',
        feature: '',
        features: '',
        firstLetter: '',
        highPrice: '',
        id: '',
        installmentFlag: '',
        installmentNum: '',
        installmentType: '',
        lowPrice: '',
        name: '',
        photo: '',
        status: '',
        substationType: '',
        substationUrl: '',
        logos: '',
        tag: '',
        tagColour: '',
        weight: '',
        weixinCode: ''
      },
      modifyButtonContent: ''
    }
  },
  methods: {
    loaddata: function () {
      substation.get({}, (data) => {
        if (!data) {
          this.modifyButtonContent = '添加'
        } else {
          this.modifyButtonContent = '修改'
          this.form = data
        }
      }, (msg) => {
        console.log('请求失败的信息', msg)
      })
    },
    modifySubstation: function () {
      this.$router.push({path: '/modifySubstation'})
    }
  },
  created () {
    this.loaddata()
  },
  computed: {
    hasBanner: function () {
      return this.form.banners.length
    },
    hasLogo: function () {
      return this.form.logos.length
    },
    featureDesc: function () {
      let features = new Array(this.form.features)
      let desc = features.join(', ')
      return desc
    },
    appartmentDesc: function () {
      if (!this.form.description) {
        let emptyMsg = '您还没有添加公寓描述'
        return emptyMsg
      }
      return this.form.description
    },
    hasSubstationData: function () {
      if (this.modifyButtonContent === '添加') {
        return false
      }
      return true
    },
    apartmentCity: function () {
      let citys = this.form.citys
      citys = citys.join(',')
      // citys = citys.join(',')
      return citys
    },
    logoClass: function (index) {
      let size = {
        1: 'lg',
        2: 'md',
        3: 'sm'
      }
      let s = size[index]
      return 'logo-' + s
    }
  }
}
</script>

<style lang="less" scoped>
  .substation-container {
    padding-left: 240px;
    text-align: left;
    padding-top: 20px;
  }
  .substation-container > div .title {
    font-size: 20px;
    display: inline-block;
    width: 150px;
    text-align: right;
  }
  .substation-container span {
    font-size: 24px;
  }
  .substation-container div + div {
    margin-top: 20px;
  }
  .apartment-name span + span {
    display: inline-block;
    border-bottom: 1px solid #232323;
    width: 200px;
    text-align: center;
  }
  .apartment-desc .title {
    font-size: 20px;
    display: inline-block;
    vertical-align: top;
  }
  .apartment-desc .content {
    display: inline-block;
    width: 500px;
  }
  div img {
    margin-right: 20px;
  }
  .apartment-banner img {
    width: 240px;
    height: 120px;
    margin-right: 20px;
  }

  .apartment-logo .logo-lg {
    width: 160px;
    height: 160px;
  }
  .apartment-logo .logo-md {
    width: 110px;
    height: 110px;
  }
  .apartment-logo .logo-sm {
    width: 76px;
    height: 76px;
  }
  .modify-apartment {
    text-align: center;
  }
  .img-list {
    display: inline-block;
  }
  .img-list li {
    display: inline-block;
  }
</style>
