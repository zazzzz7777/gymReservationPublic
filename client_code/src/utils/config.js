const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '场馆信息',
					icon: '',
					child:[

						{
							name:'场馆信息',
							url:'/index/changguanxinxiList'
						},
					]
				},
				{
					name: '运动器材',
					icon: '',
					child:[

						{
							name:'运动器材',
							url:'/index/yundongqicaiList'
						},
					]
				},
				{
					name: '赛事活动',
					icon: '',
					child:[

						{
							name:'赛事活动',
							url:'/index/saishihuodongList'
						},
					]
				},
				{
					name: '论坛交流',
					icon: 'icon-common38',
					child:[

						{
							name:'论坛交流',
							url:'/index/forumList'
						},
					]
				},
				{
					name: '新闻资讯',
					icon: '',
					child:[

						{
							name:'新闻资讯',
							url:'/index/newsList'
						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: "校园运动场馆预约管理系统"
        } 
    }
}
export default config
