let sider = [{
  text: '账户管理',
  isChoosen: true,
  before: 'i-icon-home-default',
  downtag: false,
  childItems: [{
    text: '账户公寓',
    route: 'apartIndex'
  }, {
    text: '账户安全',
    route: 'accountecurity'
  }]}, {
    text: '房源管理',
    isChoosen: false,
    before: 'i-fangyuanguanli',
    downtag: false,
    childItems: [{
      text: '房源管理',
      route: '/houseManage'
    }, {
      text: '新增管理',
      route: '/addHouse'
    }]
  }, {
    text: '预约管理',
    isChoosen: false,
    before: 'i-yuyueguanli',
    downtag: false,
    route: 'appointment'
  }, {
    text: '订单管理',
    isChoosen: false,
    before: 'i-dingdanguanli',
    downtag: false,
    childItems: [{
      text: '新建订单',
      route: 'addOrder'
    }, {
      text: '订单查询',
      route: 'orderSearch'
    }]
  }]
export default sider
