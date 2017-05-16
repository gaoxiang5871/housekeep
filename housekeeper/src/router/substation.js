import SubstationView from '@/components/substation/substation'
import modifySubstation from '@/components/substation/modifySubstation'
import Business from '@/components/substation/substationBusiness'
import { SUBSTATIONDETAIL, SUBSTATIONMODIFY, SUBSTATIONBUSINESS } from './const_router_name.js'
export default [
  {
    path: '/substation',
    name: SUBSTATIONDETAIL,
    component: SubstationView
  },
  {
    path: '/modifySubstation',
    name: SUBSTATIONMODIFY,
    component: modifySubstation
  },
  {
    path: '/business',
    name: SUBSTATIONBUSINESS,
    component: Business
  }
]
