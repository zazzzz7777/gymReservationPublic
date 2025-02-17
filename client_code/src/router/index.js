import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import newsList from '@/views/pages/news/list'
import forumList from '@/views/pages/forum/list'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import changguanleixingList from '@/views/pages/changguanleixing/list'
import changguanleixingDetail from '@/views/pages/changguanleixing/formModel'
import changguanleixingAdd from '@/views/pages/changguanleixing/formAdd'
import changguanxinxiList from '@/views/pages/changguanxinxi/list'
import changguanxinxiDetail from '@/views/pages/changguanxinxi/formModel'
import changguanxinxiAdd from '@/views/pages/changguanxinxi/formAdd'
import storeupList from '@/views/pages/storeup/list'
import changguanyuyueList from '@/views/pages/changguanyuyue/list'
import changguanyuyueDetail from '@/views/pages/changguanyuyue/formModel'
import changguanyuyueAdd from '@/views/pages/changguanyuyue/formAdd'
import huodongleixingList from '@/views/pages/huodongleixing/list'
import huodongleixingDetail from '@/views/pages/huodongleixing/formModel'
import huodongleixingAdd from '@/views/pages/huodongleixing/formAdd'
import saishihuodongList from '@/views/pages/saishihuodong/list'
import saishihuodongDetail from '@/views/pages/saishihuodong/formModel'
import saishihuodongAdd from '@/views/pages/saishihuodong/formAdd'
import qicaifenleiList from '@/views/pages/qicaifenlei/list'
import qicaifenleiDetail from '@/views/pages/qicaifenlei/formModel'
import qicaifenleiAdd from '@/views/pages/qicaifenlei/formAdd'
import yundongqicaiList from '@/views/pages/yundongqicai/list'
import yundongqicaiDetail from '@/views/pages/yundongqicai/formModel'
import yundongqicaiAdd from '@/views/pages/yundongqicai/formAdd'
import qicaizujieList from '@/views/pages/qicaizujie/list'
import qicaizujieDetail from '@/views/pages/qicaizujie/formModel'
import qicaizujieAdd from '@/views/pages/qicaizujie/formAdd'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'forumList',
			component: forumList
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'changguanleixingList',
			component: changguanleixingList
		}, {
			path: 'changguanleixingDetail',
			component: changguanleixingDetail
		}, {
			path: 'changguanleixingAdd',
			component: changguanleixingAdd
		}
		, {
			path: 'changguanxinxiList',
			component: changguanxinxiList
		}, {
			path: 'changguanxinxiDetail',
			component: changguanxinxiDetail
		}, {
			path: 'changguanxinxiAdd',
			component: changguanxinxiAdd
		}
        , {
            path: 'storeupList',
            component: storeupList
        }
		, {
			path: 'changguanyuyueList',
			component: changguanyuyueList
		}, {
			path: 'changguanyuyueDetail',
			component: changguanyuyueDetail
		}, {
			path: 'changguanyuyueAdd',
			component: changguanyuyueAdd
		}
		, {
			path: 'huodongleixingList',
			component: huodongleixingList
		}, {
			path: 'huodongleixingDetail',
			component: huodongleixingDetail
		}, {
			path: 'huodongleixingAdd',
			component: huodongleixingAdd
		}
		, {
			path: 'saishihuodongList',
			component: saishihuodongList
		}, {
			path: 'saishihuodongDetail',
			component: saishihuodongDetail
		}, {
			path: 'saishihuodongAdd',
			component: saishihuodongAdd
		}
		, {
			path: 'qicaifenleiList',
			component: qicaifenleiList
		}, {
			path: 'qicaifenleiDetail',
			component: qicaifenleiDetail
		}, {
			path: 'qicaifenleiAdd',
			component: qicaifenleiAdd
		}
		, {
			path: 'yundongqicaiList',
			component: yundongqicaiList
		}, {
			path: 'yundongqicaiDetail',
			component: yundongqicaiDetail
		}, {
			path: 'yundongqicaiAdd',
			component: yundongqicaiAdd
		}
		, {
			path: 'qicaizujieList',
			component: qicaizujieList
		}, {
			path: 'qicaizujieDetail',
			component: qicaizujieDetail
		}, {
			path: 'qicaizujieAdd',
			component: qicaizujieAdd
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
