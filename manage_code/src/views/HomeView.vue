<template>
  <div class="home_view">
    <div class="projectTitle">欢迎使用 {{ projectName }}</div>
    <div class="count_list">
      <el-collapse-transition v-if="btnAuth('forum', '首页总数')">
        <el-card
          v-show="countTypeList.closeforumCountType"
          class="card_view count-item"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">论坛交流</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenforumCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenforumCountType ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closeforumCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div class="count_item" v-show="countTypeList.hiddenforumCountType">
              <div class="count_title">论坛交流总数</div>
              <div class="count_num">{{ forumCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('changguanyuyue', '首页总数')">
        <el-card
          v-show="countTypeList.closechangguanyuyueCountType"
          class="card_view count-item"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">场馆预约</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenchangguanyuyueCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenchangguanyuyueCountType
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closechangguanyuyueCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddenchangguanyuyueCountType"
            >
              <div class="count_title">场馆预约总数</div>
              <div class="count_num">{{ changguanyuyueCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('yundongqicai', '首页总数')">
        <el-card
          v-show="countTypeList.closeyundongqicaiCountType"
          class="card_view count-item"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">运动器材</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenyundongqicaiCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenyundongqicaiCountType
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closeyundongqicaiCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddenyundongqicaiCountType"
            >
              <div class="count_title">运动器材总数</div>
              <div class="count_num">{{ yundongqicaiCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
    </div>
    <div class="card_list">
      <el-collapse-transition v-if="btnAuth('forum', '首页统计')">
        <el-card
          v-show="cardTypeList.closeforumChartType1"
          class="card_view chart-item"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">论坛交流</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenforumChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenforumChartType1 ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closeforumChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div class="card_item" v-show="cardTypeList.hiddenforumChartType1">
              <div
                id="forumthumbsupNumberEchart1"
                class="Echart"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('changguanyuyue', '首页统计')">
        <el-card
          v-show="cardTypeList.closechangguanyuyueChartType1"
          class="card_view chart-item"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">场馆预约</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenchangguanyuyueChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenchangguanyuyueChartType1
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closechangguanyuyueChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="card_item"
              v-show="cardTypeList.hiddenchangguanyuyueChartType1"
            >
              <div
                id="changguanyuyuechangguanmingchengEchart1"
                class="Echart"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('yundongqicai', '首页统计')">
        <el-card
          v-show="cardTypeList.closeyundongqicaiChartType1"
          class="card_view chart-item"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">运动器材</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenyundongqicaiChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenyundongqicaiChartType1
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closeyundongqicaiChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="card_item"
              v-show="cardTypeList.hiddenyundongqicaiChartType1"
            >
              <div
                id="yundongqicaiqicaifenleiEchart1"
                class="Echart"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('yundongqicai', '首页统计')">
        <el-card
          v-show="cardTypeList.closeyundongqicaiChartType2"
          class="card_view chart-item"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">运动器材</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddenyundongqicaiChartType2')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddenyundongqicaiChartType2
                      ? 'showIcons2'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closeyundongqicaiChartType2')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="card_item"
              v-show="cardTypeList.hiddenyundongqicaiChartType2"
            >
              <div
                id="yundongqicaishuliangEchart2"
                class="Echart"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
    </div>
  </div>
</template>

<script setup>
import { inject, nextTick, ref, getCurrentInstance } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const projectName = context.$project.projectName
const countTypeList = ref({})
const getCountList = () => {
  countTypeList.value.closeforumCountType = true
  countTypeList.value.hiddenforumCountType = true
  if (btnAuth('forum', '首页总数')) {
    getforumCount()
  }
  countTypeList.value.closechangguanyuyueCountType = true
  countTypeList.value.hiddenchangguanyuyueCountType = true
  if (btnAuth('changguanyuyue', '首页总数')) {
    getchangguanyuyueCount()
  }
  countTypeList.value.closeyundongqicaiCountType = true
  countTypeList.value.hiddenyundongqicaiCountType = true
  if (btnAuth('yundongqicai', '首页总数')) {
    getyundongqicaiCount()
  }
}
const forumCount = ref(0)
const getforumCount = () => {
  context
    ?.$http({
      url: 'forum/count',
      method: 'get',
    })
    .then((res) => {
      forumCount.value = res.data.data
    })
}
const changguanyuyueCount = ref(0)
const getchangguanyuyueCount = () => {
  context
    ?.$http({
      url: 'changguanyuyue/count',
      method: 'get',
    })
    .then((res) => {
      changguanyuyueCount.value = res.data.data
    })
}
const yundongqicaiCount = ref(0)
const getyundongqicaiCount = () => {
  context
    ?.$http({
      url: 'yundongqicai/count',
      method: 'get',
    })
    .then((res) => {
      yundongqicaiCount.value = res.data.data
    })
}
const countTypeClick = (e) => {
  countTypeList.value[e] = !countTypeList.value[e]
}
//权限验证
const btnAuth = (e, a) => {
  return context?.$toolUtil.isAuth(e, a)
}
let echarts = inject('echarts')
const cardTypeClick = (e) => {
  cardTypeList.value[e] = !cardTypeList.value[e]
  setTimeout(() => {
    getCardList()
  }, 1000)
}
const cardTypeList = ref({
  closeforumChartType1: true,
  hiddenforumChartType1: true,
  closechangguanyuyueChartType1: true,
  hiddenchangguanyuyueChartType1: true,
  closeyundongqicaiChartType1: true,
  hiddenyundongqicaiChartType1: true,
  closeyundongqicaiChartType2: true,
  hiddenyundongqicaiChartType2: true,
})
const getCardList = () => {
  if (btnAuth('forum', '首页统计')) {
    getforumChart1()
  }
  if (btnAuth('changguanyuyue', '首页统计')) {
    getchangguanyuyueChart1()
  }
  if (btnAuth('yundongqicai', '首页统计')) {
    getyundongqicaiChart1()
  }
  if (btnAuth('yundongqicai', '首页统计')) {
    getyundongqicaiChart2()
  }
}
import '@/assets/js/echarts-theme'
const getforumChart1 = () => {
  nextTick(() => {
    var thumbsupNumberEchart1 = echarts.init(
      document.getElementById('forumthumbsupNumberEchart1'),
      'theme'
    )
    context
      ?.$http({
        url: `forum/value/title/thumbsupNumber`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let dataList = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].title)
          yAxis.push(parseFloat(res[i].total))
          dataList.push({
            value: parseFloat(res[i].total),
            name: res[i].title,
          })
        }
        var option = {}
        option = {
          title: {
            text: '热门帖子统计',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            data: xAxis,
            type: 'category',
          },
          yAxis: {
            type: 'value',
          },
          series: {
            data: yAxis,
            type: 'line',
          },
        }
        thumbsupNumberEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        thumbsupNumberEchart1.setOption(option)
        //根据窗口的大小变动图表
        thumbsupNumberEchart1.resize()
      })
  })
}
const getchangguanyuyueChart1 = () => {
  nextTick(() => {
    var changguanmingchengEchart1 = echarts.init(
      document.getElementById('changguanyuyuechangguanmingchengEchart1'),
      'theme'
    )
    context
      ?.$http({
        url: 'changguanyuyue/group/changguanmingcheng',
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let dataList = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].changguanmingcheng)
          yAxis.push(parseFloat(res[i].total))
          dataList.push({
            value: parseFloat(res[i].total),
            name: res[i].changguanmingcheng,
          })
        }
        var option = {}
        option = {
          title: {
            text: '场馆预约统计',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            data: xAxis,
            type: 'category',
          },
          yAxis: {
            type: 'value',
          },
          series: {
            data: yAxis,
            type: 'bar',
          },
        }

        changguanmingchengEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        changguanmingchengEchart1.setOption(option)
        //根据窗口的大小变动图表
        changguanmingchengEchart1.resize()
      })
  })
}
const getyundongqicaiChart1 = () => {
  nextTick(() => {
    var qicaifenleiEchart1 = echarts.init(
      document.getElementById('yundongqicaiqicaifenleiEchart1'),
      'theme'
    )
    context
      ?.$http({
        url: 'yundongqicai/group/qicaifenlei',
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let dataList = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].qicaifenlei)
          yAxis.push(parseFloat(res[i].total))
          dataList.push({
            value: parseFloat(res[i].total),
            name: res[i].qicaifenlei,
          })
        }
        var option = {}
        option = {
          title: {
            text: '器材分类统计',
            left: 'center',
          },
          legend: {
            orient: 'vertical',
            left: 'left',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c} ({d}%)',
          },
          series: [
            {
              type: 'pie',
              radius: '55%',
              center: ['50%', '60%'],
              data: dataList,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)',
                },
              },
            },
          ],
        }
        qicaifenleiEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        qicaifenleiEchart1.setOption(option)
        //根据窗口的大小变动图表
        qicaifenleiEchart1.resize()
      })
  })
}
const getyundongqicaiChart2 = () => {
  nextTick(() => {
    var shuliangEchart2 = echarts.init(
      document.getElementById('yundongqicaishuliangEchart2'),
      'theme'
    )
    context
      ?.$http({
        url: `yundongqicai/value/qicaimingcheng/shuliang`,
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let dataList = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].qicaimingcheng)
          yAxis.push(parseFloat(res[i].total))
          dataList.push({
            value: parseFloat(res[i].total),
            name: res[i].qicaimingcheng,
          })
        }
        var option = {}
        option = {
          title: {
            text: '器材库存统计',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            data: xAxis,
            type: 'category',
          },
          yAxis: {
            type: 'value',
          },
          series: {
            data: yAxis,
            type: 'line',
          },
        }
        Object.assign(option.series, { smooth: true })
        shuliangEchart2.clear()
        // 使用刚指定的配置项和数据显示图表。
        shuliangEchart2.setOption(option)
        //根据窗口的大小变动图表
        shuliangEchart2.resize()
      })
  })
}

const init = () => {
  getCountList()
  getCardList()
}
init()
</script>
<style lang="scss">
// 首页盒子
.home_view {
}
.home_view .projectTitle {
}

.showIcons {
  transition: transform 0.3s;
  margin-right: 10px;
}

.showIcons1 {
  transform: rotate(-180deg);
}

// 总数盒子
.count_list {
  // 总数card
  .card_view {
    // card头部
    .el-card__header {
      // 头部盒子
      .index_card_head {
        // 标题
        .card_head_title {
        }
        // 按钮盒子
        .card_head_right {
          // 按钮
          .el-icon {
          }
        }
      }
    }
    // body
    .el-card__body {
      // body盒子
      .count_item {
        // 总数标题
        .count_title {
        }
        // 总数数字
        .count_num {
        }
      }
    }
  }
}

// 统计图盒子
.card_list {
  // 统计图card
  .card_view {
    // 头部
    .el-card__header {
      // 头部盒子
      .index_card_head {
        // 标题
        .card_head_title {
        }
        // 按钮盒子
        .card_head_right {
          // 按钮
          .el-icon {
          }
        }
      }
    }
    // body
    .el-card__body {
      // body盒子
      .card_item {
      }
    }
  }
}
</style>
<style>
.home_view .projectTitle {
  display: none;
}

.home_view .el-card__header {
  display: none;
}

.home_view .count_list {
  display: flex;
  column-gap: 30px;
  order: 1;
  flex: 1;
}

.home_view {
  padding: 20px 60px;
  display: flex;
  flex-wrap: wrap;
  row-gap: 30px;
  column-gap: 30px;
}

.home_view .count_list .card_view {
  flex: 1;
  height: 100px;
  box-shadow: none;
  background-image: url(http://localhost:8080/gymReservation/file/51775843f83145358ee7bceba9093325.png);
  background-color: transparent;
  border-radius: 0;
  background-size: 100% 100%;
}

.home_view .count_title {
  font-weight: 400;
  font-size: 14px;
  order: 1;
  color: #969696;
}

.home_view .count_num {
  font-size: 30px;
  color: #000000;
}

.home_view .count_item {
  display: flex;
  flex-direction: column;
}

.home_view .card_list {
  order: 3;
  display: grid;
  grid-template-columns: 1fr 1fr;
  column-gap: 30px;
  row-gap: 30px;
  position: relative;
  width: 100%;
}

.count_list .el-card__body {
  position: relative;
}

.chart-item {
  box-shadow: none !important;
}
.home-calendar {
  border: 0px solid rgb(238, 238, 238);
  box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 0px;
  margin: 0px auto 30px;
  border-radius: 0px;
  color: rgb(102, 102, 102);
  background: none;
  width: calc(100% - 310px);
  font-size: inherit;
  height: auto;
}
.home-calendar .header {
  padding: 17px 40px;
  border-color: rgba(126, 96, 16, 0.1);
  flex-wrap: wrap;
  background: linear-gradient(135deg, var(--theme) 0%, #6cacff 100%);
  border-width: 0px 0px 1px;
  display: flex;
  width: 100%;
  border-style: solid;
  justify-content: space-between;
  height: auto;
  border-radius: 10px 10px 10px 10px;
}
.home-calendar .header .btn {
  cursor: pointer;
  border: 0px solid rgb(153, 153, 153);
  padding: 0px 0px 0px 12px;
  align-items: center;
  color: rgb(102, 102, 102);
  border-radius: 4px;
  display: flex;
  width: auto;
  font-size: inherit;
  justify-content: center;
}
.home-calendar .header .title {
  padding: 0px 10px;
  font-size: 24px;
  align-items: center;
  justify-content: center;
  display: flex;
  color: rgb(51, 51, 51);
}
.home-calendar table {
  width: 100%;
  padding: 0px 0px 20px;
  height: auto;
}
.home-calendar tbody,
thead {
  width: 100%;
}
.home-calendar tr {
  width: 100%;
  align-items: center;
  justify-content: center;
  display: flex;
}
.home-calendar th {
  align-items: center;
  flex: 1;
  display: flex;
  line-height: 50px;
  justify-content: center;
}
.home-calendar td {
  cursor: pointer;
  padding: 6px 12px 12px;
  flex: 1;
  display: flex;
  justify-content: center;
  text-align: center;
}
.home-calendar {
  order: 2;
  width: 100%;
  margin: 0;
  height: 435px;
  background: #fff;
  padding: 20px;
}

.home-calendar .header * {
  color: #fff !important;
}

.home-calendar table {
  font-size: 12px;
  line-height: 12px;
}
</style>
