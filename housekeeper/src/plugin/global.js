window.globalInfo = {
  CUSTOMER_ID: null,
  userInfo: {},
  isApp: false,
  channel: null,
  sourceMark: 'weixin',
  investorId: 123,
  investorCode: 'QKD',
  appVersion: null,
  version: null,
  channelCode: null,
  token(token) {
    return token ? localStorage.setItem('token', token) : localStorage.getItem('token')
  },

  /**
   * login
   *
   * @param {any} custId
   */
  login(custId) {
    this.CUSTOMER_ID = custId
    localStorage.setItem('CUSTOMER_ID', custId)
  },

  /**
   * cache user information
   *
   * @param {any} userInfo
   */
  cacheUserInfo(userInfo) {
    this.userInfo = userInfo
    localStorage.setItem('userInfo', JSON.stringify(userInfo))
  },

  /**
   * logout system
   *
   */
  logout() {
    localStorage.clear()
    sessionStorage.clear()
    this.CUSTOMER_ID = null
  }
}
