import 'whatwg-fetch'
export default instance => {
  // preset request headers
  const reqHeaders = {
    Accept: 'application/json',
    channelCode: '',
    source: '',
    version: ''
  }
  /**
   * convert to query string
   *
   * @param {Object} params
   * @returns param=value&param1=value
   */
  const convertQuery = (params, contentType) => {
    if (!params) {
      return ''
    }
    if (contentType.indexOf('urlencoded') > -1 || contentType.indexOf('get') > -1) {
      return Object.keys(params).reduce((pre, key) => (pre + `${key}=${params[key]}&`), '').slice(0, -1)
    } if (contentType.indexOf('json') > -1) {
      return window.JSON.stringify(params)
    } else {
      return params
    }
  }

  /**
   * send request for specify url and params
   *
   * @param {String} url
   * @param {Object} init
   */
  const request = (url, init, type, postType) => new Promise((resolve, reject) => {
    let data = {...init, credentials: 'include'}
    fetch(url, data).then(res => {
      if (res.ok) {
        switch (type) {
          case 'text':
            resolve(res.text())
            break
          case 'blob':
            resolve(res.blob())
            break
          default:
            resolve(res.json())
            break
        }
      } else {
        res.status === -10003 ? '' : reject(res)
      }
    }).catch(err => {
      reject(err)
    })
  })
  window.fetcher = {

    /**
     * send get request
     *
     * @param {String} url
     * @param {Object} params
     * @returns Promise object
     */
    get (url, params, type) {
      return request(url + '?' + convertQuery(params, 'get'), {
        headers: Object.assign({}, reqHeaders)
      }, type)
    },

    /**
     * send post request
     *
     * @param {String} url
     * @param {Object} params
     * @param {String} type
     * @returns Promise object
     */
    post (url, params, type, contentType) {
      let conType = contentType || (type === 'file' ? 'multipart/form-data' : 'application/json')
      return request(url, {
        method: 'POST',
        headers: getHeader(type, contentType),
        body: convertQuery(params, conType)
      }, type)
    }
  }
  /*
  *
  */
  const getHeader = (type, contentType) => {
    if (contentType) {
      return Object.assign({}, reqHeaders, { 'Content-Type': contentType })
    } else if (type === 'file') {
      return Object.assign({}, reqHeaders)
    } else {
      return Object.assign({}, reqHeaders, { 'Content-Type': 'application/json' })
    }
  }
}
