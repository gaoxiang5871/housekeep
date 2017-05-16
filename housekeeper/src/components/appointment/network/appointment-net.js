import http from './httptool'
var baseUrl = '/manage/reserve/lookHouse/'
const queryAppoint = function (params, success, failed, err) {
  let path = 'conditional'
  let url = baseUrl + path
  http.post(url, params, (data) => {
    success(data.result)
  }, failed)
}
const exportAppointExcel = function (params, success, failed, err) {
  let path = 'pushExcel'
  let url = baseUrl + path
  http.post(url, params, (data) => {
    let file = data.fileName
    let fileurl = `${baseUrl}downloadPush/${file}`
    console.log(fileurl, 'excel的文件路径')
    success(fileurl)
    // http.get(fileurl, '', (data) => {
    //   console.log('下载excel返回的数据')
    // }, (msg) => {
    //   console.log('错误的信息为------------', msg)
    // })
  }, failed)
}
const getServants = function (params, success, failed, err) {
  let url = '/manage/house/allServant'
  http.get(url, params, (data) => {
    success(data)
  }, failed)
}
const appointment = {
  'query': queryAppoint,
  'export': exportAppointExcel,
  'allServant': getServants
}

export default appointment
