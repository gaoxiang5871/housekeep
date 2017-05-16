import Vue from 'vue'
import Router from 'vue-router'
import routes from './routes.js'
Vue.use(Router)
const router = new Router({
  routes
})
// 增加一个需要强制登录的路由文件

export default router

