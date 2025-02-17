	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import news from '@/views/news/list'
	import discusschangguanxinxi from '@/views/discusschangguanxinxi/list'
	import discusssaishihuodong from '@/views/discusssaishihuodong/list'
	import discussforum from '@/views/discussforum/list'
	import storeup from '@/views/storeup/list'
	import users from '@/views/users/list'
	import changguanxinxi from '@/views/changguanxinxi/list'
	import qicaifenlei from '@/views/qicaifenlei/list'
	import qicaizujie from '@/views/qicaizujie/list'
	import forum from '@/views/forum/list'
	import changguanleixing from '@/views/changguanleixing/list'
	import saishihuodong from '@/views/saishihuodong/list'
	import yonghu from '@/views/yonghu/list'
	import changguanyuyue from '@/views/changguanyuyue/list'
	import discussyundongqicai from '@/views/discussyundongqicai/list'
	import config from '@/views/config/list'
	import yundongqicai from '@/views/yundongqicai/list'
	import huodongleixing from '@/views/huodongleixing/list'
	import usersCenter from '@/views/users/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/usersCenter',
			name: '管理员个人中心',
			component: usersCenter
		}
		,{
			path: '/news',
			name: '新闻资讯',
			component: news
		}
		,{
			path: '/discusschangguanxinxi',
			name: '场馆信息评论-评论',
			component: discusschangguanxinxi
		}
		,{
			path: '/discusssaishihuodong',
			name: '赛事活动评论-评论',
			component: discusssaishihuodong
		}
		,{
			path: '/discussforum',
			name: '论坛交流-评论',
			component: discussforum
		}
		,{
			path: '/storeup',
			name: '我的收藏',
			component: storeup
		}
		,{
			path: '/users',
			name: '管理员',
			component: users
		}
		,{
			path: '/changguanxinxi',
			name: '场馆信息',
			component: changguanxinxi
		}
		,{
			path: '/qicaifenlei',
			name: '器材分类',
			component: qicaifenlei
		}
		,{
			path: '/qicaizujie',
			name: '器材租借',
			component: qicaizujie
		}
		,{
			path: '/forum',
			name: '我的发布',
			component: forum
		}
		,{
			path: '/changguanleixing',
			name: '场馆类型',
			component: changguanleixing
		}
		,{
			path: '/saishihuodong',
			name: '赛事活动',
			component: saishihuodong
		}
		,{
			path: '/yonghu',
			name: '用户',
			component: yonghu
		}
		,{
			path: '/changguanyuyue',
			name: '场馆预约',
			component: changguanyuyue
		}
		,{
			path: '/discussyundongqicai',
			name: '运动器材评论-评论',
			component: discussyundongqicai
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		,{
			path: '/yundongqicai',
			name: '运动器材',
			component: yundongqicai
		}
		,{
			path: '/huodongleixing',
			name: '活动类型',
			component: huodongleixing
		}
		]
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
