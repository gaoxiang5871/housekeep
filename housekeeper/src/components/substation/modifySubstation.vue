<template lang="html">
  <div class="modify-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="150px">
      <el-form-item label="公寓名称：">
        <el-input placeholder="请输入公寓名称" v-model="form.name" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="公寓描述：" prop="description">
        <el-input type="textarea" v-model="form.description" autosize placeholder="请输入公寓描述，描述字数应该在70~100之间"></el-input>
      </el-form-item>
      <el-form-item label="公寓房源覆盖：" prop="citys">
        <el-checkbox-group align="left" v-model="selectedCity">
          <el-checkbox v-for="city in form.citys" :label="city.cityName" :key="city.id">{{city.cityName}}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="活动banner：" align="left">
        <div class="apartment-banner">
          <ul v-if="hasBanner" class="img-list" id="id-ul-banner">
            <li v-for="(item, index) in form.banners">
              <img v-bind:src="item" class="img-banner">
              <input v-bind:id="bannerId(index)" name="frontFile"  type="file" accept="image/*" v-on:change="setImage"/>
            </li>
          </ul>
          <p>点击图片修改/添加banner图片</p>
          <span v-if="!hasLogo">你还没有添加公寓logo,赶紧去添加吧</span>
        </div>
      </el-form-item>
      <el-form-item label="活动logo：" align="left">
        <div class="apartment-logo" >
          <ul v-if="hasLogo" class="img-list">
            <li v-for="(item, index) in form.logos">
              <img v-bind:src="item" v-bind:class="logoClass(index)">
              <input v-bind:id="logoId(index)" name="frontFile"  type="file" accept="image/*" v-on:change="setImage"/>
            </li>
          </ul>
          <p>点击图片修改/添加logo图片</p>
          <span v-if="!hasLogo">你还没有添加公寓logo,赶紧去添加吧</span>
        </div>
      </el-form-item>
      <el-form-item label="公寓特色：" prop="features">
        <el-input v-model="form.features"></el-input>
      </el-form-item>
    </el-form>
    <el-row>
      <el-col :span="24">
        <el-button v-on:click="updateSubstation('form')">保存</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import substation from './net/substation-net'
export default {
  data () {
    var checkDescription = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('公寓描述不能为空'))
      }
      if (value.length < 70) {
        return callback(new Error('描述太少啦，公寓描述应该在70-100之间'))
      }
      if (value.length > 100) {
        return callback(new Error('描述太多啦，公寓描述应该在70-100之间'))
      }
      callback()
    }
    let checkFeatureDesc = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入公寓特色描述'))
      }
      var featureArray = value.split('，')
      if (featureArray.length === 1) {
        featureArray = value.split(',')
      }
      for (var i = 0; i < featureArray.length; i++) {
        let feature = featureArray[i]
        if (feature.length < 2 || feature.length > 4) {
          return callback(new Error('公寓特色描述长度应该在2-4个字，每个公寓特色应以逗号分隔'))
        }
      }
      callback()
    }
    let checkCitys = (rule, value, callback) => {
      if (this.selectedCity.length === 0 && value.length > 0) {
        return callback(new Error('请至少选择一个公寓覆盖范围'))
      }
      callback()
    }
    return {
      form: {
        backgroudPicture: '',
        banners: '',
        basePosition: '',
        city: '',
        citys: '',
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
      selectedCity: [],
      defaultLogos: '',
      rules: {
        description: [
          { validator: checkDescription, trigger: 'blur' }
        ],
        features: [
          { validator: checkFeatureDesc, trigger: 'blur' }
        ],
        citys: [
          { validator: checkCitys, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    bannerId: function (index) {
      return 'id-banner-' + index
    },
    logoId: function (index) {
      return 'id-logo-' + index
    },
    logoClass: function (index) {
      return 'img-logo-' + index
    },
    base64Img: function (element, callback) {
      var file = element.files[0]
      var reader = new FileReader()
      reader.onload = (e) => {
        var result = e.target.result
        callback(result)
      }
      reader.readAsDataURL(file)
    },
    setImage: function (event) {
      let target = event.target
      let id = target.id
      let arr = id.split('-')
      let len = arr.length
      let index = arr[len - 1]
      let tag = arr[1]
      let liElement = target.parentElement
      let imgElement = liElement.querySelector('img')
      if (tag === 'banner') {
        this.base64Img(target, (base64) => {
          this.form.banners[index] = base64
          imgElement.src = base64
        })
      } else {
        this.base64Img(target, (base64) => {
          this.form.logos[index] = base64
          imgElement.src = base64
        })
      }
    },
    updatedParams: function () {
      let params = {}
      params.city = this.selectedCity
      let banners = this.form.banners.slice(0)
      banners = banners.join('$')
      params.backgroudPicture = banners
      let city = this.selectedCity
      // city = city.join(',')
      params.city = city
      let description = this.form.description
      if (!description) {
        description = ''
      }
      params.description = description
      params.feature = this.form.features
      let logos = {}
      for (var i = 0; i < this.form.logos.length; i++) {
        let imageKey = 'image' + i
        let logo = this.form.logos[i]
        let defaultLogo = this.defaultLogos[i]
        if (logo !== defaultLogo) {
          logos[imageKey] = logo
          console.log('变化的图片-----------', i)
        } else {
          logos[imageKey] = ''
        }
      }
      params.photo = logos
      return params
    },
    setSubstation: function () {
      substation.get(null, (form) => {
        let featureArr = form.features
        let featureStr = featureArr.join('，')
        form.features = featureStr
        this.form = form
        this.getCitys()
        let defaultLogos = this.form.logos
        this.defaultLogos = defaultLogos.slice(0)
      }, (msg) => {
        console.log('修改信息页面错误信息为---------', msg)
      })
    },
    updateSubstation (formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid) {
          return false
        } else {
          let params = this.updatedParams()
          console.log('修改子站信息的图片------params', JSON.stringify(params))
          substation.update(params, (data) => {
            console.log('修改子站信息成功，data--------------', data)
          }, (msg) => {
            console.log('修改子站信息错误提示为----------', msg)
          })
        }
      })
    },
    getCitys: function () {
      substation.citys('', (data) => {
        this.form.citys = data
      }, (msg) => {
        console('获取城市列表错误的信息---------------', msg)
      })
    }
  },
  created () {
    this.setSubstation()
    // this.getCitys()
    // let body = document.querySelector('body')
  },
  computed: {
    hasBanner: function () {
      return this.form.banners.length
    },
    hasLogo: function () {
      return this.form.logos.length
    }
  },
  watch: {
    form: {
      handler: function () {
        // console.log(this.form.description)
      },
      deep: true
    },
    modifySubstationForm: {
      handler: function () {
        // console.log(this.form.description)
        console.log(JSON.stringify(this.modifySubstationForm))
      },
      deep: true
    }
  }
}
</script>

<style lang="less" scoped>
.modify-container {
  padding: 20px;
  padding-left: 240px;
}
.chexbox-notice {
  text-indent: 50px;
  display: inline-block;
}
.apartment-banner img {
  width: 240px;
  height: 120px;
  margin-right: 20px;
}

.apartment-logo .img-logo-0 {
  width: 160px;
  height: 160px;
}
.apartment-logo .img-logo-1 {
  width: 110px;
  height: 110px;
}
.apartment-logo .img-logo-2 {
  width: 76px;
  height: 76px;
}
.img-list {
  display: inline-block;
}
.img-list li {
  display: inline-block;
  position: relative;
}
.img-list li input{
  display: inline-block;
  position: absolute;
  background: red;
  width: 240px;
  height: 120px;
  top: 0px;
  left: 0px;
  opacity: 0;
}
.img-list li + li {
  margin-left: 20px;
}
</style>
