<template>
  <div class="lg-container" v-show='isShow'>
    <div class="panel">
      <div class="close" @click.stop.prevent='close'>X</div>
      <component :is="currentView" @ctrl_dia_type="ctrlDiaType"></component>
    </div>
  </div>
</template>

<script>
import lgDialog from './lgdialog'
import newLogin from './new_login'
import addUser from './add_user'
import changeWord from './change_word'
export default {
  props: {
    isShow: Boolean,
    curView: String
  },
  components: {
    newLogin,
    addUser,
    lgDialog,
    changeWord
  },
  data () {
    return {
      ldData: {
        username: '',
        password: ''
      },
      ruleForm: { account: '', password: '' },
      rules: {
        account: [
          { required: true, message: '请输入公寓账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入公寓密码', trigger: 'blur' }
        ]
      },
      currentView: ''
    }
  },
  methods: {
    close () {
      this.$emit('ctr_lg_dia', '')
    },
    ctrlDiaType (type) {
      this.currentView = type || 'lgDialog'
    }
  },
  created () {
    this.ctrlDiaType(this.curView)
  },
  watch: {
    curView: function () {
      this.ctrlDiaType(this.curView)
    }
  }
}
</script>
<style lang='less' scoped>
 .lg-container{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);
    z-index: 3;
    .panel{
      width: 380px;
      min-height: 270px;
      background: #fff;
      border-radius: 5px;
      position: absolute;
      left: 50%;
      top: 40%;
      transform: translate(-50%, -50%);
      .close{
        position:absolute;
        top: 12px;
        right: 12px;
        font-size: 20px;
        z-index: 1;
      }
      .close:hover{
        cursor: pointer;
      }
      .content{
        padding: 40px 40px 0 40px;
      }
      .marginleft{
        margin-left: -100px;
      }
      .width{
        width: 300px
      }
      .paddingTop{
        margin-top: -10px;
      }
    }
  }
</style>
