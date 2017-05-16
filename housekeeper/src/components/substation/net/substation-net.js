import http from './http'
let baseUrl = '/manage/substation/'

var get = function (params, success, failed) {
  let url = baseUrl + 'get'
  http.get(url, params, (data) => {
    let substation = data.substation
    success(substation)
  }, failed)
}
var update = function (params, success, failed) {
  let url = baseUrl + 'update'
  console.log('修改子站信息的url是--------------', url)
  http.post(url, params, success, failed)
}
var citys = function (params, success, failed) {
  let path = 'city/get'
  let url = baseUrl + path
  http.get(url, params, (data) => {
    // console.log('city data ------------', data)
    let citys = data.cityList
    success(citys)
  }, failed)
}
export default {
  'get': get,
  'update': update,
  'citys': citys
}
