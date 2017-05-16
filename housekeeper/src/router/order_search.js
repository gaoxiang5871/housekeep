import { ORDERSEARCH, ORDERDETAILS, ADDORDER } from './const_router_name.js'
import orderSearch from '@/components/orderSearch/orderSearch'
import orderDetails from '@/components/orderSearch/child/orderDetails'
import addOrder from '@/components/orderSearch/child/addOrder'
export default [
  {
    path: '/orderSearch',
    name: ORDERSEARCH,
    component: orderSearch
  },
  {
    path: '/orderDetails',
    name: ORDERDETAILS,
    component: orderDetails
  },
  {
    path: '/addOrder',
    name: ADDORDER,
    component: addOrder
  }
]
