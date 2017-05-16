let option = {
  tooltip: {
    trigger: 'axis',
    axisPointer: { // 坐标轴指示器，坐标轴触发有效
      type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
    }
  },
  legend: {
    data: ['房源', '预约量', '在租量']
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  xAxis: [{
    type: 'category',
    data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
  }],
  yAxis: [{
    type: 'value'
  }],
  series: [{
    name: '房源',
    type: 'bar',
    barWidth: 15,
    data: [10, 10, 9, 9, 9, 8, 8]
  }, {
    name: '预约量',
    type: 'bar',
    barWidth: 10,
    data: [0, 1, 1, 2, 2, 3, 3]
  }, {
    name: '在租量',
    type: 'bar',
    barWidth: 8,
    data: [0, 0, 0, 1, 1, 1, 2],
    markLine: {
      lineStyle: {
        normal: {
          type: 'dashed'
        }
      },
      data: [
        [{ type: 'min' }, { type: 'max' }]
      ]
    }
  }]
}
export default option
