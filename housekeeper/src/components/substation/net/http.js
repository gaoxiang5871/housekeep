/* global fetcher:true */
var req = function (url, method, params, success, failed) {
  fetcher[method](url, params).then((res) => {
    let code = res.errorCode
    let msg = res.errorMsg
    let data = res.data
    if (code === 0) {
      success(data)
    } else {
      failed(msg)
    }
  }, (rej) => {
    console.log(rej, '---------------拒绝访问接口')
  }).catch((err) => {
    console.log(err)
  })
}
var get = function (url, params, success, failed) {
  req(url, 'get', params, success, failed)
}
var post = function (url, params, success, failed) {
  req(url, 'post', params, success, failed)
}

export default {
  'get': get,
  'post': post
}
