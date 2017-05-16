import { APARTAUTH, ACCOUNTECURITY, APARTINDEX, SUBCOUNTMANAGE } from './const_router_name.js'
import Accountecurity from '@/components/apartment/account_security'
import Apartauth from '@/components/apartment/apart_auth'
import Apartindex from '@/components/apartment/apart_index'
import Subcountmanage from '@/components/apartment/subcount_manage'
export default [
  {
    path: '/accountecurity',
    name: ACCOUNTECURITY,
    component: Accountecurity
  }, {
    path: '/apartauth',
    name: APARTAUTH,
    component: Apartauth
  }, {
    path: '/apartIndex',
    name: APARTINDEX,
    component: Apartindex
  }, {
    path: '/subcountmanage',
    name: SUBCOUNTMANAGE,
    component: Subcountmanage
  }
]

