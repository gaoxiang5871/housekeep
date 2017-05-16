import home from './home'
import appointment from './appointment'
import houseManage from './house_manage'
import substation from './substation'
import financeManage from './financeManage'
import apartment from './apartment'
import guestManage from './guest_manage'
import orderSearch from './order_search'

export default [
  ...home,
  ...houseManage,
  ...appointment,
  ...substation,
  ...guestManage,
  ...orderSearch,
  ...apartment,
  ...financeManage
]
