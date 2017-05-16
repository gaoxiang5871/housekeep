<style lang='less' src='./less/siderbar.less' scoped></style>
<template>
  <div class="siderbar" v-if='isHasSiderBar'>
    <div class="sider-header panel-body text-center">
      <div>
        <img src="../../../static/img/house.jpg">
      </div>
      <div class="bk-padding-top-10">
        <span class="sider-role">租房后台</span>
      </div>
    </div>
    <div class="divider2"></div>
    <template v-for="(item, index) in sider">
      <ul class="sider-panel"
       :class="{'choosen': item.isChoosen}"
       @click.stop.prevent="chooseItems(item, index)">
        <li
          class="icon icon-reset"
          :class="item.before" >
          <span>{{item.text}}</span>
          <span
            v-show="item.childItems&&item.childItems.length"
            class="icon i-arrow-down icon-reset"
            :class="{'icon-reset-down': item.downtag}"></span>
        </li>
      </ul>
      <div v-show="item.downtag" class="childItem">
        <ul>
          <li v-for="(childItem, index) in item.childItems"  @click.stop.prevent='jump(childItem)'>
            {{childItem.text}}
          </li>
        </ul>
      </div>
    </template>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
import siderdata from './data/siderdata.js'
export default {
  name: 'siderbar',
  data () {
    return {
      sider: []
    }
  },
  computed: mapGetters({
    telephone: 'telephone',
    isHasSiderBar: 'isHasSiderBar'
  }),
  methods: {
    ...mapActions(['setTelephone']),
    chooseItems (item, index) {
      this.sider.forEach((el, curIndex) => {
        el.isChoosen = false
        if (curIndex !== index) {
          el.downtag = false
        }
      })
      let temp = {
        isChoosen: true,
        downtag: item.childItems && item.childItems.length ? !item.downtag : false
      }
      this.$set(this.sider, index, Object.assign({}, item, temp))
      if (item.route) {
        this.$router.push(item.route)
      }
    },
    jump (item) {
      if (item.route) {
        this.$router.push(item.route)
      }
    }
  },
  created () {
    this.sider = siderdata
  }
}
</script>
<style lang='less' scoped>
.childItem{
  overflow: hidden;
  width: 240px;
  height: auto;
  background: #263033;
  ul{
    margin: 10px 0;
  }
  li{
    height: 40px;
    line-height: 40px;
    width: 240px;
    padding-left: 40px;
    text-align: left;
    border-top: 1px solid transparent;
    border-bottom: 1px solid transparent;
  }
  li:hover{
    cursor: pointer;
    border-top-color: #435D78;
    border-bottom-color: #435D78;
  }
}
</style>
