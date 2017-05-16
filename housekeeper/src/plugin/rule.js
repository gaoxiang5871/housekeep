export let telphone = /^0?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/
export let password = /^([0-9a-zA-Z]){6,20}$/
// export let isTelCode = /^0[0-9]{2,3}$/
export let telNum = /^(0\d{2,3})-?(\d{7,8})$/  // 区号+电话号码
export let email = /^\w+((-\w+)|(\.\w+))*@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/
export let address = /^[0-9a-zA-Z\u4e00-\u9fa5]{2,25}$/
export let acreage = /^([5-9]|[1-4][0-9]|50)$/
