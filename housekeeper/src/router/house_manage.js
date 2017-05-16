import { HOUSE_MANAGE, ADD_HOUSE, EXCEL_LEADING } from './const_router_name.js'
import houseManage from '@/components/houseManage/houseManage'
import addHouse from '@/components/houseManage/addHouse'
import excelLeading from '@/components/houseManage/excelLeading'
export default [
  {
    path: '/houseManage',
    name: HOUSE_MANAGE,
    component: houseManage
  }, {
    path: '/addHouse',
    name: ADD_HOUSE,
    component: addHouse
  }, {
    path: '/excelLeading',
    name: EXCEL_LEADING,
    component: excelLeading
  }
]
