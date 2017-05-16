import { INDEX } from './const_router_name.js'
import Index from '@/components/index'
export default [
  {
    path: '/',
    redirect: '/index'
  }, {
    path: '/index',
    name: INDEX,
    component: Index
  }
]
