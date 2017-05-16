<template>
  <div class="excel-leading">
    <div class="header">
      <el-row>
        <el-col :span="6">
          <div class="header-tip">excel导入步骤</div>
        </el-col>
      </el-row>
    </div>
    <div class="content">
      <el-row>
        <el-col :span="10" :offset="1">
          <div class="tap-one" align="left">第一步： 下载录入房源模板，批量填写房源信息</div>
        </el-col>
        <el-col :span="4" :offset="1">
          <el-button type="danger" @click="down">下&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;载</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="10" :offset="1">
          <div class="tap-one" align="left">第二步： 上传填写好的房源信息表</div>
        </el-col>
        <el-col :span="2" :offset="2" id="change-excel">
          <el-button type="danger" id="excel-btn">选择文件</el-button>
          <input
            type="file"
            name="excel"
            id="excel"
            @change="changeExcel($event)"
            accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
        </el-col>
        <el-col :span="6" :offset="1">
          <div class="fileName">{{ excelName }}</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="10" :offset="1">
          <div class="tap-one" align="left">第三步： 上传整理好的房源图片压缩包</div>
        </el-col>
        <el-col :span="2" :offset="2" id="change-zip">
          <el-button type="danger" id="zip-btn">选择文件</el-button>
          <input
            type="file"
            name="zip"
            id="zip"
            @change="changeZip($event)"/>
        </el-col>
        <el-col :span="6" :offset="1">
          <div class="fileName">{{ zipName }}</div>
        </el-col>
      </el-row>
      <el-row class="upload">
        <el-col :span="16">
          <el-button type="danger" @click="upload">上传</el-button>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
/* global fetcher:true */
export default {
  name: 'excelLeading',
  data () {
    return {
      excelTag: false,
      excelFile: {},
      excelName: '',
      zipTag: false,
      zipFile: {},
      zipName: ''
    }
  },
  methods: {
    upload () {
      let url = '/manage/house/addHouse/excel'
      let form = new FormData()
      form.append('excel', this.excelFile)
      form.append('image', this.zipFile)
      let type = 'file'
      fetcher.post(url, form, type).then((res) => {
        if (res.errorCode === 0) {
          this.$message({ message: '上传成功' })
          setTimeout(() => {
            location.reload()
          }, 3000)
        } else {
          this.$message({ message: res.errorMsg })
        }
      }, (rej) => {
        console.log(rej)
      }).catch((err) => {
        console.log(err)
      })
    },
    down () {
//    let url = '/manage/house/公寓导入.xlsx'
      let url = 'http://localhost:8080/manage/house/downloadTemplate'
      location.href = url
//    fetcher.get(url).then((res) => {
//      if (res.errorCode === 0) {
//        console.log(1)
//      } else {
//        this.$message({ message: res.errorMsg })
//      }
//    }, (rej) => {
//      console.log(rej)
//    }).catch((err) => {
//      console.log(err)
//    })
    },
    changeExcel (event) {
      let fileName = event.target.files[0].name
      if (fileName.indexOf('.xlsx') >= 0) {
        this.excelTag = true
        this.excelFile = event.target.files[0]
        this.excelName = fileName
      } else {
        this.$message({ message: '请选择.xlsx格式的文件' })
      }
    },
    changeZip (event) {
      let fileName = event.target.files[0].name
      if (fileName.indexOf('.zip') >= 0) {
        this.zipTag = true
        this.zipFile = event.target.files[0]
        this.zipName = fileName
      } else {
        this.$message({ message: '请选择.zip格式的文件' })
      }
    }
  }
}
</script>

<style lang="less" scoped>
.excel-leading {
  padding-left: 240px;
}
.header {
  background: #FFFFFF;
}
.header-tip {
  font-size: 24px;
  height: 50px;
  line-height: 50px;
  color: #0044CC;
}
.content {
  font-size: 16px;
  margin-top: 10px;
  line-height: 36px;
}
.el-row {
  margin-bottom: 20px;
}
.upload {
  margin-top: 40px;
}
#change-excel {
  position: relative;
  height: 35px;
  overflow: hidden;
}
#excel {
  position: absolute;
  top: 0;
  left: 0;
  height: 35px;
  opacity: 0;
}
#excel-btn {
  position: absolute;
  top: 0;
  left: 0;
}
#change-zip {
  position: relative;
  height: 35px;
  overflow: hidden;
}
#zip {
  position: absolute;
  top: 0;
  left: 0;
  height: 35px;
  opacity: 0;
}
#zip-btn {
  position: absolute;
  top: 0;
  left: 0;
}
.fileName {
  line-height: 35px;
  font-size: 14px;
}
</style>
