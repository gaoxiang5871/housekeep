// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/config_route.js'
import store from './store'

// 引入elememntUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
Vue.use(ElementUI)
Vue.config.productionTip = false

// 引入表单验证
import VeeValidate from 'vee-validate'
Vue.use(VeeValidate)

// 引入请求 fetcher
import fetcher from 'plugin/fetcher'
fetcher(instance)

/* eslint-disable no-new */
let instance = new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})

