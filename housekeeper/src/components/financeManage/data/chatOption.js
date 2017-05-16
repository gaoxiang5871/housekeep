let option = {
  tooltip: {
    trigger: 'axis',
    axisPointer: { // 坐标轴指示器，坐标轴触发有效
      type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
    }
  },
  legend: {
    data: ['分期收入', '支付收入']
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  calculable: true,
  xAxis: [
    {
      type: 'category',
      data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
    }
  ],
  yAxis: [{
    type: 'value'
  }],
  series: [{
    name: '分期收入',
    type: 'line',
    data: [3200, 3320, 3010, 3340, 3900, 3300, 3200],
    markPoint: {
      data: [
        { type: 'max', name: '最大值' },
        { type: 'min', name: '最小值' }
      ]
    },
    markLine: {
      data: [
        { type: 'average', name: '平均值' }
      ]
    }
  }, {
    name: '支付收入',
    type: 'line',
    data: [1200, 1320, 1010, 1340, 1900, 1300, 1200],
    markPoint: {
      data: [
        { type: 'max', name: '最大值' },
        { type: 'min', name: '最小值' }
      ]
    },
    markLine: {
      data: [
        { type: 'average', name: '平均值' }
      ]
    }
  }]
}
export default option
