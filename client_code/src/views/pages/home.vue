<template>
  <div>
    <div class="home_box">
      <!-- 运动器材推荐 -->
      <div class="recomList_view">
        <div class="recomList_title">
          <span>{{ tableName }}推荐</span>
        </div>
        <div class="categoryList">
          <div
            class="item"
            @click="yundongqicaiRecomCategoryChange(-1)"
            style="cursor: pointer"
            :class="{ active: yundongqicaiRecom_index == -1 }"
          >
            全部
          </div>
          <div
            class="item"
            v-for="(item, index) in yundongqicaiRecomCategorys"
            @click="yundongqicaiRecomCategoryChange(index)"
            :class="{ active: yundongqicaiRecom_index == index }"
            style="cursor: pointer"
          >
            {{ item }}
          </div>
        </div>

        <div class="tjlist">
          <div
            class="item"
            v-for="(item, index) in yundongqicaiRecomList"
            @click="detailClick('yundongqicai', item.id)"
          >
            <div class="imgbox"><img :src="item.imgUrls[0]" alt="" /></div>

            <div class="infobox">
              <div class="titles">
                <div class="title">器材名称：{{ item.qicaimingcheng }}</div>
                <div class="title">器材分类：{{ item.qicaifenlei }}</div>
              </div>
              <div class="price"></div>
              <div class="xys">
                <div class="like">
                  <span class="iconfont icon-thumb-up-line1"></span>
                  <div class="num">{{ item.thumbsupNumber }}</div>
                </div>

                <div class="collect">
                  <span class="iconfont icon-likeline4"></span>
                  <div class="num">{{ item.storeupNumber }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="homeList_more_text" @click="moreClick('yundongqicai')">
          查看更多 +
        </div>
      </div>
      <!-- 场馆信息首页展示 -->
      <div class="homeList_view">
        <div class="homeList_title">
          <span>场馆信息展示</span>
        </div>
        <div class="categoryList">
          <div
            class="item"
            @click="changguanxinxiCategoryChange(-1)"
            style="cursor: pointer"
            :class="{ active: changguanxinxi_index == -1 }"
          >
            全部
          </div>
          <div
            class="item"
            v-for="(item, index) in changguanxinxiCategorys"
            @click="changguanxinxiCategoryChange(index)"
            :class="{ active: changguanxinxi_index == index }"
            style="cursor: pointer"
          >
            {{ item }}
          </div>
        </div>
        <div class="homeList">
          <div
            class="item"
            v-for="(item, index) in changguanxinxiHomeList"
            :key="index"
            @click="detailClick('changguanxinxi', item.id)"
          >
            <div class="img-box">
              <img
                v-if="isHttp(item.fengmian)"
                :src="item.fengmian.split(',')[0]"
                alt=""
              />
              <img
                v-else
                :src="
                  item.fengmian ? $config.url + item.fengmian.split(',')[0] : ''
                "
                alt=""
              />
            </div>
            <div class="content-box">
              <div class="title">场馆名称：{{ item.changguanmingcheng }}</div>
              <div class="statistic">
                <div class="like">
                  <span class="iconfont icon-thumb-up-line1"></span>
                  <div class="num">{{ item.thumbsupNumber }}</div>
                </div>
                <div class="collect">
                  <span class="iconfont icon-likeline4"></span>
                  <div class="num">{{ item.storeupNumber }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="homeList_more_view" @click="moreClick('changguanxinxi')">
          <span class="homeList_more_text">查看更多 +</span>
        </div>
      </div>
      <!-- 赛事活动首页展示 -->
      <div class="homeList_view">
        <div class="homeList_title">
          <span>赛事活动展示</span>
        </div>
        <div class="categoryList">
          <div
            class="item"
            @click="saishihuodongCategoryChange(-1)"
            style="cursor: pointer"
            :class="{ active: saishihuodong_index == -1 }"
          >
            全部
          </div>
          <div
            class="item"
            v-for="(item, index) in saishihuodongCategorys"
            @click="saishihuodongCategoryChange(index)"
            :class="{ active: saishihuodong_index == index }"
            style="cursor: pointer"
          >
            {{ item }}
          </div>
        </div>
        <div class="homeList">
          <div
            class="item"
            v-for="(item, index) in saishihuodongHomeList"
            :key="index"
            @click="detailClick('saishihuodong', item.id)"
          >
            <div class="img-box">
              <img
                v-if="isHttp(item.tupian)"
                :src="item.tupian.split(',')[0]"
                alt=""
              />
              <img
                v-else
                :src="
                  item.tupian ? $config.url + item.tupian.split(',')[0] : ''
                "
                alt=""
              />
            </div>
            <div class="content-box">
              <div class="title">活动名称：{{ item.huodongmingcheng }}</div>
              <div class="statistic">
                <div class="like">
                  <span class="iconfont icon-thumb-up-line1"></span>
                  <div class="num">{{ item.thumbsupNumber }}</div>
                </div>
                <div class="collect">
                  <span class="iconfont icon-likeline4"></span>
                  <div class="num">{{ item.storeupNumber }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="homeList_more_view" @click="moreClick('saishihuodong')">
          <span class="homeList_more_text">查看更多 +</span>
        </div>
      </div>
      <!-- 新闻资讯 -->
      <div class="newsList_view">
        <div class="ntitle">
          <div class="n2">NEWS INFORMATION</div>
          <div class="n1">新闻资讯</div>
        </div>

        <div class="nlist">
          <ul>
            <li
              v-for="(item, index) in newsList"
              :key="index"
              @click="newsDetailClick(item)"
            >
              <div class="infobox">
                <div class="nam">{{ item.title }}</div>
                <div class="info">{{ item.introduction }}</div>
                <div class="tim">
                  {{ moment(item.addtime).format('YYYY-MM-DD') }}
                </div>
              </div>

              <div class="imgbox">
                <img :src="item.imgUrl" />
              </div>
            </li>
          </ul>
        </div>

        <div class="nmore" @click="moreClick('news')" style="cursor: pointer">
          查看更多 +
        </div>
      </div>
    </div>
    <formModel ref="newsFormModelRef"></formModel>
  </div>
</template>

<script setup>
import { ref, getCurrentInstance } from 'vue'
import moment from 'moment'
import { useRouter } from 'vue-router'
const context = getCurrentInstance()?.appContext.config.globalProperties
const router = useRouter()
//场馆信息首页展示
const changguanxinxiCategorys = ref([])
const changguanxinxi_index = ref(-1)
const getchangguanxinxiCategorys = () => {
  context
    ?.$http({
      url: 'option/changguanleixing/changguanleixing',
      method: 'get',
    })
    .then((res) => {
      changguanxinxiCategorys.value = res.data.data
    })
}
getchangguanxinxiCategorys()
const changguanxinxiCategoryChange = (index) => {
  changguanxinxi_index.value = index
  getchangguanxinxiHomeList()
}
const changguanxinxiHomeList = ref([])
const getchangguanxinxiHomeList = () => {
  let params = {
    page: 1,
    limit: 6,
  }
  if (changguanxinxi_index.value >= 0) {
    params.changguanleixing =
      changguanxinxiCategorys.value[changguanxinxi_index.value]
  }
  context
    ?.$http({
      url: 'changguanxinxi/list',
      method: 'get',
      params: params,
    })
    .then((res) => {
      changguanxinxiHomeList.value = res.data.data.list
      changguanxinxiHomeList.value.forEach((item) => {
        if (!isHttp(item.fengmian)) {
          item.imgUrls = item.fengmian
            .split(',')
            .map((item) => context.$config.url + item)
        }
      })
    })
}
//赛事活动首页展示
const saishihuodongCategorys = ref([])
const saishihuodong_index = ref(-1)
const getsaishihuodongCategorys = () => {
  context
    ?.$http({
      url: 'option/huodongleixing/huodongleixing',
      method: 'get',
    })
    .then((res) => {
      saishihuodongCategorys.value = res.data.data
    })
}
getsaishihuodongCategorys()
const saishihuodongCategoryChange = (index) => {
  saishihuodong_index.value = index
  getsaishihuodongHomeList()
}
const saishihuodongHomeList = ref([])
const getsaishihuodongHomeList = () => {
  let params = {
    page: 1,
    limit: 6,
  }
  if (saishihuodong_index.value >= 0) {
    params.huodongleixing =
      saishihuodongCategorys.value[saishihuodong_index.value]
  }
  context
    ?.$http({
      url: 'saishihuodong/list',
      method: 'get',
      params: params,
    })
    .then((res) => {
      saishihuodongHomeList.value = res.data.data.list
      saishihuodongHomeList.value.forEach((item) => {
        if (!isHttp(item.tupian)) {
          item.imgUrls = item.tupian
            .split(',')
            .map((item) => context.$config.url + item)
        }
      })
    })
}
//运动器材推荐
const yundongqicaiRecomCategorys = ref([])
const yundongqicaiRecom_index = ref(-1)
const getyundongqicaiRecomCategorys = () => {
  context
    ?.$http({
      url: 'option/qicaifenlei/qicaifenlei',
      method: 'get',
    })
    .then((res) => {
      yundongqicaiRecomCategorys.value = res.data.data
    })
}
getyundongqicaiRecomCategorys()
const yundongqicaiRecomCategoryChange = (index) => {
  yundongqicaiRecom_index.value = index
  getyundongqicaiRecomList()
}
const yundongqicaiRecomList = ref([])
const getyundongqicaiRecomList = () => {
  let autoSortUrl = 'yundongqicai/autoSort'
  if (context?.$toolUtil.storageGet('frontToken')) {
    autoSortUrl = 'yundongqicai/autoSort2'
  }
  let params = {
    page: 1,
    limit: 8,
  }
  if (yundongqicaiRecom_index.value >= 0) {
    params.qicaifenlei =
      yundongqicaiRecomCategorys.value[yundongqicaiRecom_index.value]
  }
  context
    ?.$http({
      url: autoSortUrl,
      method: 'get',
      params: params,
    })
    .then((res) => {
      yundongqicaiRecomList.value = res.data.data.list
      yundongqicaiRecomList.value.forEach((item) => {
        if (!isHttp(item.fengmian)) {
          item.imgUrls = item.fengmian
            .split(',')
            .map((item) => context.$config.url + item)
        }
      })
    })
}
//新闻资讯弹窗
import formModel from './news/formModel'
const newsFormModelRef = ref(null)
//新闻资讯
const newsList = ref([])
const getNewsList = () => {
  context
    ?.$http({
      url: 'news/list',
      method: 'get',
      params: {
        page: 1,
        limit: 4,
      },
    })
    .then((res) => {
      newsList.value = res.data.data.list
      newsList.value.forEach((item) => {
        if (!isHttp(item.picture)) {
          item.imgUrl = context.$config.url + item.picture.split(',')[0]
        }
      })
    })
}
const newsDetailClick = (item) => {
  if (item && item.id) {
    newsFormModelRef.value.init(item.id)
  }
}
//判断图片链接是否带http
const isHttp = (str) => {
  return str && str.substr(0, 4) == 'http'
}
//跳转详情
const detailClick = (table, id) => {
  router.push(`/index/${table}Detail?id=${id}`)
}
const moreClick = (table) => {
  router.push(`/index/${table}List`)
}
const init = () => {
  //场馆信息首页展示
  getchangguanxinxiHomeList()
  //赛事活动首页展示
  getsaishihuodongHomeList()
  //运动器材推荐
  getyundongqicaiRecomList()
  //新闻资讯
  getNewsList()
}
init()
</script>

<style lang="scss">
.home_box {
}

// 推荐
.recomList_view {
  .recomList_title {
  }
  // list
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1.05) skew(0deg, 0deg)
      translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  // animation
  // 更多
  .recommend_more_view {
    cursor: pointer;
    top: 60px;
    position: absolute;
    right: calc((100% - 1180px) / 2);
    .recommend_more_text {
    }
  }
}
// 推荐
// 新闻资讯
.newsList_view {
  .newsList_title {
  }
  // list
  .news_list_one {
    display: flex;
    flex-wrap: wrap;
    .news_item {
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
      margin: 0 10px 10px;
      background: #fff;
      display: flex;
      width: calc(33% - 20px);
      align-items: center;
      .news_img_box {
        width: 40%;
        font-size: 0;
        .news_img {
          object-fit: cover;
          width: 100%;
          height: 200px;
        }
      }
      .news_content {
        margin: 0 0 0 20px;
        width: calc(60% - 20px);
        .news_title {
          font-weight: bold;
          font-size: 20px;
        }
        .news_text {
          font-size: 14px;
          line-height: 1.5;
        }
        .news_time {
          color: #999;
          width: 100%;
          text-align: right;
        }
      }
    }
  }
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  // animation
  // 更多
  .news_more_view {
    .news_more_text {
    }
    .el-icon {
    }
  }
}
// 新闻资讯
// 首页展示
.homeList_view {
  .homeList_title {
  }
  // list
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  // animation
  // 更多
  .homeList_more_view {
    cursor: pointer;
    .homeList_more_text {
    }
  }
}
// 首页展示
</style>
<style>
.home_box {
  width: 100%;
  margin: 0px auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
.home_box .appendBox1 {
  width: 100%;
  height: 299px;
  background: url(http://localhost:8080/gymReservation/file/708c7343aeeb4d728265133426b664a4.jpg)
    no-repeat center top / 100% 100%;
  order: 5;
}
/* 总盒子 */
.recomList_view {
  width: 100%;
  margin: 0;
  padding: 20px 0 50px;
  background: url() fixed no-repeat center top / cover, #fff;
  overflow: hidden;
  position: relative;
  order: 1;
  display: block;
}
/* 标题 */
.recomList_view .recomList_title {
  width: 1200px;
  margin: 30px auto;
  background: none;
  text-align: left;
  border-bottom: 2px solid var(--theme);
}
.recomList_view .recomList_title span {
  display: inline-block;
  background: var(--theme);
  padding: 10px 40px 10px 40px;
  font-size: 22px;
  color: #fff;
}
/* 分类 */
.recomList_view .categoryList {
  display: none;
  flex-wrap: wrap;
}
.recomList_view .categoryList .item {
}

/* 自定义盒子 */
.recomList_view .tjlist {
  max-width: 1200px;
  margin: 0 auto;
  display: grid;
  grid-template-areas:
    ' . a1 a1. .'
    '. . . a8 a8';
  grid-gap: 20px;
  padding: 0px;
  border: 0px solid var(--theme) 80;
  background: #fff;
}
.recomList_view .tjlist .item:first-child {
  grid-area: a1;
  clip-path: polygon(2% 0, 100% 0, 98% 100%, 0% 100%);
}
.recomList_view .tjlist .item:last-child {
  grid-area: a8;
  clip-path: polygon(2% 0, 100% 0, 98% 100%, 0% 100%);
}

.recomList_view .tjlist .item {
  width: auto;
  margin: 0;
  background: none;
  cursor: pointer;
  height: 250px;
  overflow: hidden;
  position: relative;
  border-radius: 0;
  clip-path: polygon(5% 0, 100% 0, 95% 100%, 0% 100%);
}
.recomList_view .tjlist .item .imgbox {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.recomList_view .tjlist .item .imgbox img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.recomList_view .tjlist .item .infobox {
  cursor: pointer;
  overflow: hidden;
  transition: 0.3s;
  position: absolute;
  left: 0px;
  bottom: -100%;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  background: none;
}
.recomList_view .tjlist .item:hover .infobox {
  padding: 0px;
  bottom: 0;
  height: 100%;
  border-radius: 0;
  background: rgba(0, 0, 0, 0.3);
}

.recomList_view .tjlist .item .infobox .titles {
}
.recomList_view .tjlist .item .infobox .title {
  width: 100%;
  line-height: 24px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: center;
}
.recomList_view .tjlist .item:hover .infobox .title {
  color: #fff;
}
.recomList_view .tjlist .item .infobox .price {
  width: 100%;
  text-align: center;
  color: #f00;
  font-size: 16px;
}
.recomList_view .tjlist .item:hover .infobox .price {
}
.recomList_view .tjlist .item .infobox .xys {
  width: 100%;
  margin: 10px 0 0;
}
.recomList_view .tjlist .item:hover .infobox .xys {
  color: #ccc;
  text-align: center;
}
.recomList_view .tjlist .item .infobox .xys div {
  display: inline-block;
}
.recomList_view .tjlist .item .infobox .xys .iconfont {
  color: inherit;
  margin: 0px 4px 0px 0px;
  display: inline-block;
}
.recomList_view .tjlist .item .infobox .xys .num {
  display: inline-block;
  color: inherit;
}
.recomList_view .tjlist .item .infobox .xys .like {
  display: flex;
  align-items: center;
  color: inherit;
  font-size: 16px;
  margin: 0px 10px 0px 0px;
}
.recomList_view .tjlist .item .infobox .xys .collect {
  color: inherit;
  font-size: 16px;
  margin: 0px 10px 0px 0px;
}
.recomList_view .tjlist .item .infobox .xys .clickNum {
  display: flex;
  align-items: center;
  color: inherit;
  font-size: 16px;
  margin: 0px 10px 0px 0px;
}
.recomList_view .homeList_more_text {
  position: absolute;
  top: 50px;
  right: calc((100% - 1200px) / 2);
  cursor: pointer;
  padding: 10px;
  background-repeat: no-repeat;
  background-position: bottom;
  background-image: -webkit-linear-gradient(left, var(--theme), var(--theme));
  background-image: linear-gradient(to left, var(--theme), var(--theme));
  background-size: 100% 0;
  transition: all 0.5s;
  clip-path: polygon(5% 0, 100% 0%, 95% 100%, 0% 100%);
}
.recomList_view .homeList_more_text:hover {
  background-size: 100% 100%;
  color: #fff;
}

/* 总盒子 */
.homeList_view {
  width: 100%;
  margin: 0;
  padding: 20px calc((100% - 1200px) / 2) 50px;
  background: #fff;
  overflow: hidden;
  position: relative;
  order: 8;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
/* 标题 */
.homeList_view .homeList_title {
  width: 101%;
  margin: 30px auto;
  background: none;
  text-align: left;
  border-bottom: 2px solid var(--theme);
}
.homeList_view .homeList_title span {
  display: inline-block;
  background: var(--theme);
  padding: 10px 40px 10px 40px;
  font-size: 22px;
  color: #fff;
}

/* 分类 */
.homeList_view .categoryList {
  width: 240px;
  display: block;
  flex-wrap: wrap;
  margin-left: 50px;
  order: 4;
}
.homeList_view .categoryList .item {
  background: var(--theme);
  line-height: 50px;
  color: #fff;
  text-align: center;
  margin: 0 0 15px;
  cursor: pointer;
  font-size: 16px;
  background-repeat: no-repeat;
  background-position: left;
  background-image: -webkit-linear-gradient(right, #828bd6, #828bd6);
  background-image: linear-gradient(to right, #828bd6, #828bd6);
  background-size: 0 100%;
  transition: all 0.5s;
  clip-path: polygon(5% 0, 100% 0%, 95% 100%, 0% 100%);
}
.homeList_view .categoryList .item:hover {
  background-size: 100% 100%;
  color: #fff;
}

/* 样式七 总盒子 */
.homeList_view .homeList {
  width: calc(100% - 320px);
  margin: 0;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  order: 2;
  flex: 1;
}
.homeList_view .homeList .item {
  width: calc(32% - 0px);
  margin: 0px 0px 20px;
  background: none;
  cursor: pointer;
  overflow: hidden;
  position: relative;
  clip-path: polygon(5% 0, 100% 0, 95% 100%, 0% 100%);
}
.homeList_view .homeList .item .img-box {
  width: 100%;
  height: 250px;
  overflow: hidden;
  margin: 0px;
  text-align: center;
}
.homeList_view .homeList .item .img-box img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.homeList_view .homeList .item .content-box {
  width: 100%;
  position: absolute;
  left: 0px;
  bottom: -100%;
  background: rgba(255, 255, 255, 0.8);
  padding: 0px 0px;
  transition: all 0.5s;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  text-align: center;
}
.homeList_view .homeList .item:hover .content-box {
  bottom: 0px;
  background: rgba(0, 0, 0, 0.5);
  height: 250px;
}

.homeList_view .homeList .item .content-box .title {
  width: 100%;
  line-height: 24px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.homeList_view .homeList .item:hover .content-box .title {
  color: #eee;
}
.homeList_view .homeList .item .content-box .price {
  width: 100%;
  text-align: center;
  color: #f00;
  font-size: 16px;
}
.homeList_view .homeList .item:hover .content-box .price {
}
.homeList_view .homeList .item .content-box .statistic {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 10px 0 0;
}
.homeList_view .homeList .item:hover .content-box .statistic {
  color: #ccc;
}
.homeList_view .homeList .item .content-box .statistic .iconfont {
  color: inherit;
  margin: 0px 4px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .num {
  color: inherit;
}
.homeList_view .homeList .item .content-box .statistic .like {
  display: flex;
  align-items: center;
  color: inherit;
  font-size: 16px;
  margin: 0px 10px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .collect {
  display: flex;
  align-items: center;
  color: inherit;
  font-size: 16px;
  margin: 0px 10px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .clickNum {
  display: flex;
  align-items: center;
  color: inherit;
  font-size: 16px;
  margin: 0px 10px 0px 0px;
}

/* 查看更多 */
.homeList_view .homeList_more_view {
  position: absolute;
  top: 50px;
  right: calc((100% - 1200px) / 2);
  cursor: pointer;
  padding: 10px;
  background-repeat: no-repeat;
  background-position: bottom;
  background-image: -webkit-linear-gradient(left, var(--theme), var(--theme));
  background-image: linear-gradient(to left, var(--theme), var(--theme));
  background-size: 100% 0;
  transition: all 0.5s;
  clip-path: polygon(5% 0, 100% 0%, 95% 100%, 0% 100%);
}
.homeList_view .homeList_more_view:hover {
  background-size: 100% 100%;
  color: #fff;
}

/* 总盒子 */
.newsList_view {
  width: 100%;
  margin: 0;
  padding: 40px 0 60px;
  background: url(http://localhost:8080/gymReservation/file/77f8f685f76e429aa7449bb4230906e0.jpg)
    fixed no-repeat center top / cover;
  overflow: hidden;
  position: relative;
  order: 6;
  display: block;
  text-align: center;
}
/* 自定义 start*/
.newsList_view .ntitle {
  width: 1200px;
  margin: 30px auto;
  background: none;
  text-align: left;
  border-bottom: 2px solid var(--theme);
}
.newsList_view .ntitle .n1 {
  display: inline-block;
  background: var(--theme);
  padding: 10px 40px 10px 40px;
  font-size: 22px;
  color: #fff;
}
.newsList_view .ntitle .n2 {
  display: none;
  font-size: 18px;
  line-height: 30px;
  color: #ccc;
  margin-left: 5px;
}
.newsList_view .nlist {
  width: 1200px;
  margin: 30px auto 10px;
}
.newsList_view .nlist ul {
  margin: 0;
  padding: 0;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
}
.newsList_view .nlist ul li {
  width: 49%;
  text-align: left;
  background: none;
  border: 0px solid #fff;
  cursor: pointer;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
  margin: 0 0 30px;
  padding: 0px;
}
.newsList_view .nlist ul li:hover {
  border: 0px solid var(--theme);
}

.newsList_view .nlist ul li:last-child {
}
.newsList_view .nlist ul li .imgbox {
  width: 180px;
  height: 150px;
  margin-left: 20px;
  clip-path: polygon(5% 0, 100% 0, 95% 100%, 0% 100%);
}
.newsList_view .nlist ul li .imgbox img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 0;
}

.newsList_view .nlist ul li .infobox {
  width: calc(100% - 200px);
  flex: 1;
  background: #fff;
  padding: 0 20px;
  clip-path: polygon(2% 0, 100% 0, 98% 100%, 0% 100%);
  background-repeat: no-repeat;
  background-position: bottom;
  background-image: -webkit-linear-gradient(left, var(--theme), var(--theme));
  background-image: linear-gradient(to left, var(--theme), var(--theme));
  background-size: 100% 0;
  transition: all 0.5s;
}
.newsList_view .nlist ul li:hover .infobox {
  background-size: 100% 100%;
  color: #fff;
}
.newsList_view .nlist ul li .infobox .nam {
  line-height: 40px;
  color: #333;
  font-size: 16px;
  font-weight: 600;
}
.newsList_view .nlist ul li:hover .infobox .nam {
  color: #fff;
}
.newsList_view .nlist ul li .infobox .info {
  color: #666;
  font-size: 14px;
  line-height: 24px;
  height: 72px;
  overflow: hidden;
  margin-top: 5px;
}
.newsList_view .nlist ul li:hover .infobox .info {
  color: #fff;
}
.newsList_view .nlist ul li .infobox .tim {
  line-height: 40px;
  color: #999;
  font-size: 15px;
  text-align: right;
}
.newsList_view .nlist ul li:hover .infobox .tim {
  color: #fff;
}

/* 更多 */
.newsList_view .nmore {
  position: absolute;
  top: 60px;
  color: #fff;
  right: calc((100% - 1200px) / 2);
  cursor: pointer;
  padding: 10px;
  background-repeat: no-repeat;
  background-position: bottom;
  background-image: -webkit-linear-gradient(left, var(--theme), var(--theme));
  background-image: linear-gradient(to left, var(--theme), var(--theme));
  background-size: 100% 0;
  transition: all 0.5s;
  clip-path: polygon(5% 0, 100% 0%, 95% 100%, 0% 100%);
}
.newsList_view .nmore:hover {
  background-size: 100% 100%;
  color: #fff;
}
/* 自定义 end*/
</style>
