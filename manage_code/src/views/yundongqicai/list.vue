<template>
	<div>
		<div class="center_view">
			<div class="list_search_view">
				<el-form :model="searchQuery" class="search_form" >
					<div class="search_view">
						<div class="search_label">
							器材名称：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.qicaimingcheng" placeholder="器材名称"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_view">
						<div class="search_label">
							器材分类：
						</div>
						<div class="search_box">
							<el-select
								class="search_sel"
								clearable
								v-model="searchQuery.qicaifenlei"
								placeholder="器材分类"
								>
								<el-option v-for="item in qicaifenleiLists" :label="item" :value="item"></el-option>
							</el-select>
						</div>
					</div>
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<div class="btn_view">
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('yundongqicai','新增')">
						新增
					</el-button>
                    <el-button class="statis_btn" type="warning" @click="echartClick1" v-if="btnAuth('yundongqicai','器材分类统计')">
                        器材分类统计
                    </el-button>
                    <el-button class="statis_btn" type="warning" @click="echartClick2" v-if="btnAuth('yundongqicai','器材库存统计')">
                        器材库存统计
                    </el-button>
				</div>
			</div>
			<div class="dataList"
				 v-if="btnAuth('yundongqicai','查看')"
			>
				<div class="item" v-for="(item,index) in list" :key="item.id">
                    <template v-if="item.fengmian">
                        <el-image v-if="item.fengmian.substring(0,4)=='http'" preview-teleported
                                  :preview-src-list="[item.fengmian.split(',')[0]]"
                                  :src="item.fengmian.split(',')[0]"></el-image>
                        <el-image v-else preview-teleported
                                  :preview-src-list="[$config.url+item.fengmian.split(',')[0]]"
                                  :src="$config.url+item.fengmian.split(',')[0]">
                        </el-image>
                    </template>
					<div class="title">器材名称：{{item.qicaimingcheng}}</div>
					<div class="title">器材分类：{{item.qicaifenlei}}</div>
					<div class="btns">
						<el-button class="view_btn" type="info" v-if=" btnAuth('yundongqicai','查看')" @click="infoClick(item.id)">
							详情
						</el-button>
						<el-button class="edit_btn" type="primary" @click="editClick(item.id)" v-if=" btnAuth('yundongqicai','修改')">
							修改
						</el-button>
						<el-button class="del_btn" type="danger" @click="delClick(item.id)"  v-if="btnAuth('yundongqicai','删除')">
							删除
						</el-button>
						<el-button class="cross_btn" v-if="btnAuth('yundongqicai','租借')" type="success" @click="qicaizujieCrossAddOrUpdateHandler(item,'cross','','','','')">
							租借
						</el-button>
						<el-button class="operate_btn" v-if="btnAuth('yundongqicai','查看评论')" type="warning" @click="commentClick(item.id)">
							查看评论
						</el-button>
					</div>
				</div>
			</div>
			<el-pagination
				background
				:layout="layouts.join(',')"
				:total="total"
				:page-size="listQuery.limit"
                v-model:current-page="listQuery.page"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="false"
				:style='{}'
				:page-sizes="[10, 20, 30, 40, 50, 100]"
				@size-change="sizeChange"
				@current-change="currentChange"  />
		</div>
		<formModel ref="formRef" @formModelChange="formModelChange"></formModel>
		<!-- 统计图弹窗 -->
		<el-dialog v-model="echartVisible" title="统计图" width="70%">
			<el-tabs v-model="echartActive" class="demo-tabs" @tab-change="echartTabClick" type="card">
                <el-tab-pane label="器材分类统计" name="1" v-if="btnAuth('yundongqicai','器材分类统计')"></el-tab-pane>
                <el-tab-pane label="器材库存统计" name="2" v-if="btnAuth('yundongqicai','器材库存统计')"></el-tab-pane>
			</el-tabs>
            <div v-if="echartActive==1" id="qicaifenleiEchart1" style="width:100%;height:600px;"></div>
            <div v-if="echartActive==2" id="shuliangEchart2" style="width:100%;height:600px;"></div>
			<template #footer>
				<span class="formModel_btn_box">
					<el-button class="cancel_btn" @click="echartVisible=false">取消</el-button>
				</span>
			</template>
		</el-dialog>
		<qicaizujieFormModel ref="qicaizujieFormModelRef" @formModelChange="formModelChange"></qicaizujieFormModel>
	</div>
</template>
<script setup>
	import axios from 'axios'
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		onMounted,
		watch,
		computed,
		inject
	} from 'vue'
	import {
		useRoute,
		useRouter
	} from 'vue-router'
	import {
		ElMessageBox
	} from 'element-plus'
	import {
		useStore
	} from 'vuex';
	const store = useStore()
	const user = computed(()=>store.getters['user/session'])
	const avatar = ref(store.state.user.avatar)
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	import formModel from './formModel.vue'
	//基础信息

	const tableName = 'yundongqicai'
	const formName = '运动器材'
	const route = useRoute()
	//基础信息
	onMounted(()=>{
	})
	//列表数据
	const list = ref(null)
	const table = ref(null)
	const listQuery = ref({
		page: 1,
		limit: 20,
		sort: 'id',
		order: 'desc'
	})
	const searchQuery = ref({})
	const selRows = ref([])
	const listLoading = ref(false)
	const listChange = (row) =>{
		nextTick(()=>{
			//table.value.clearSelection()
			table.value.toggleRowSelection(row)
		})
	}
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		params['sort'] = 'id'
		params['order'] = 'desc'
		if(searchQuery.value.qicaimingcheng&&searchQuery.value.qicaimingcheng!=''){
			params['qicaimingcheng'] = '%' + searchQuery.value.qicaimingcheng + '%'
		}
		if(searchQuery.value.qicaifenlei&&searchQuery.value.qicaifenlei!=''){
			params['qicaifenlei'] = searchQuery.value.qicaifenlei
		}
		context.$http({
			url: `${tableName}/page`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
		})
	}
	//删
	const delClick = (id) => {
		let ids = ref([])
		if (id) {
			ids.value = [id]
		} else {
			if (selRows.value.length) {
				for (let x in selRows.value) {
					ids.value.push(selRows.value[x].id)
				}
			} else {
				return false
			}
		}
		ElMessageBox.confirm(`是否删除选中${formName}`, '提示', {
			confirmButtonText: '是',
			cancelButtonText: '否',
			type: 'warning',
		}).then(() => {
			context.$http({
				url: `${tableName}/delete`,
				method: 'post',
				data: ids.value
			}).then(res => {
				context?.$toolUtil.message('删除成功', 'success',()=>{
					getList()
				})
			})
		})
	}
	//多选
	const handleSelectionChange = (e) => {
		selRows.value = e
	}
	//列表数据
	//分页
	const total = ref(0)
	const layouts = ref(["total","prev","pager","next","sizes","jumper"])
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	//分页
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	//搜索
	const qicaifenleiLists = ref([])
	const getqicaifenleiLists = () => {
		context.$http({
			url: 'option/qicaifenlei/qicaifenlei',
			method:'get'
		}).then(res=>{
			qicaifenleiLists.value = res.data.data
		})
	}
	getqicaifenleiLists()
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	//表单
	const formRef = ref(null)
	const formModelChange=()=>{
		searchClick()
	}
	const addClick = ()=>{
		formRef.value.init()
	}
	const editClick = (id=null)=>{
		if(id){
			formRef.value.init(id,'edit')
			return
		}
		if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'edit')
		}
	}

	const infoClick = (id=null)=>{
		if(id){
			formRef.value.init(id,'info')
		}
		else if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'info')
		}
	}
	// 表单
	// 预览文件
	const preClick = (file) =>{
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		window.open(context?.$config.url + file)
	}
	// 下载文件
	const download = (file) => {
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		let arr = file.replace(new RegExp('file/', "g"), "")
		axios.get((location.href.split(context?.$config.name).length>1 ? location.href.split(context?.$config.name)[0] :'') + context?.$config.name + '/file/download?fileName=' + arr, {
			headers: {
				token: context?.$toolUtil.storageGet('Token')
			},
			responseType: "blob"
		}).then(({
			data
		}) => {
			const binaryData = [];
			binaryData.push(data);
			const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
				type: 'application/pdf;chartset=UTF-8'
			}))
			const a = document.createElement('a')
			a.href = objectUrl
			a.download = arr
			// a.click()
			// 下面这个写法兼容火狐
			a.dispatchEvent(new MouseEvent('click', {
				bubbles: true,
				cancelable: true,
				view: window
			}))
			window.URL.revokeObjectURL(data)
		})
	}
	// 统计图1
	const echarts = inject("echarts")
	const echartVisible = ref(false)
	const echartClick1 = ()=>{
		echartActive.value = '1'
		echartVisible.value = true
		nextTick(()=>{
			var qicaifenleiEchart1 = echarts.init(document.getElementById("qicaifenleiEchart1"),'theme');
			context.$http({
				url: `${tableName}/group/qicaifenlei`,
				method: 'get'
			}).then(res=>{
				let obj = res.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<obj.length;i++){
				    xAxis.push(obj[i].qicaifenlei);
				    yAxis.push(parseFloat((obj[i].total)));
                    dataList.push({
				        value: parseFloat((obj[i].total)),
				        name: obj[i].qicaifenlei
				    })
				}
				var option = {};
				option = {
    title:{
        text: '器材分类统计',
        left: 'center'
    },
    legend: {
        orient: 'vertical',
        left: 'left'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c} ({d}%)'
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
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
}
				// 使用刚指定的配置项和数据显示图表。
				qicaifenleiEchart1.setOption(option);
				  //根据窗口的大小变动图表
				window.onresize = function() {
				    qicaifenleiEchart1.resize();
				};
			})
		})
	}
	// 统计图2
    const echartActive = ref('1')
    const echartTabClick = () =>{
		if(echartActive.value==1){
			echartClick1()
		}
		else if(echartActive.value==2){
			echartClick2()
		}
	}
	const echartClick2 = ()=>{
		echartActive.value = '2'
		echartVisible.value = true
		nextTick(()=>{
			var shuliangEchart2 = echarts.init(document.getElementById("shuliangEchart2"),'theme');
			context.$http({
				url: `${tableName}/value/qicaimingcheng/shuliang`,
				method: 'get'
			}).then(res=>{
				let obj = res.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<obj.length;i++){
				    xAxis.push(obj[i].qicaimingcheng);
				    yAxis.push(parseFloat((obj[i].total)));
                    dataList.push({
				        value: parseFloat((obj[i].total)),
				        name: obj[i].qicaimingcheng
				    })
				}
				var option = {};
                option = {
    title: {
        text: '器材库存统计',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c}'
    },
    xAxis: {
        data: xAxis,
        type: 'category',
    },
    yAxis: {
        type: 'value'
    },
    series:{
        data: yAxis,
        type: 'line'
    }
}
                Object.assign(option.series,{smooth: true})
				// 使用刚指定的配置项和数据显示图表。
				shuliangEchart2.setOption(option);
				  //根据窗口的大小变动图表
				window.onresize = function() {
				    shuliangEchart2.resize();
				};
			})
		})
	}
    // 查看评论
	const commentClick=(id)=>{
		context?.$router.push('/discussyundongqicai?refid=' + id)
	}
	import qicaizujieFormModel from '@/views/qicaizujie/formModel'
	const qicaizujieFormModelRef = ref(null)
    const qicaizujieCrossAddOrUpdateHandler = (row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue) => {
		if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
			var obj = row
			for (var o in obj){
				if(o==statusColumnName && obj[o]==statusColumnValue){
					context?.$toolUtil.message(tips,'error')
					return;
				}
			}
		}
		nextTick(()=>{
			qicaizujieFormModelRef.value.init(row.id,'cross','租借',row,'yundongqicai',statusColumnName,tips,statusColumnValue)
		})
    }
	//初始化
	const init = () => {
		getList()
	}
	init()
</script>
<style lang="scss" scoped>

	// 操作盒子
	.list_search_view {
		// 搜索盒子
		.search_form {
			// 子盒子
			.search_view {
				// 搜索label
				.search_label {
				}
				// 搜索item
				.search_box {
					// 输入框
					:deep(.search_inp) {
					}
					// 下拉框
					:deep(.search_sel) {
						//去掉默认样式
						.select-trigger{
							height: 100%;
							.el-input{
								height: 100%;
							}
						}
					}
				}
			}
			// 搜索按钮盒子
			.search_btn_view {
				// 搜索按钮
				.search_btn {
				}
				// 搜索按钮-悬浮
				.search_btn:hover {
				}
			}
		}
		//头部按钮盒子
		.btn_view {
			// 其他
			:deep(.el-button--default){
			}
			// 其他-悬浮
			:deep(.el-button--default:hover){
			}
			// 新增
			:deep(.el-button--success){
			}
			// 新增-悬浮
			:deep(.el-button--success:hover){
			}
			// 删除
			:deep(.el-button--danger){
			}
			// 删除-悬浮
			:deep(.el-button--danger:hover){
			}
			// 统计
			:deep(.el-button--warning){
			}
			// 统计-悬浮
			:deep(.el-button--warning:hover){
			}
		}
	}
	// 表格样式
	.el-table {
		:deep(.el-table__header-wrapper) {
			thead {
				tr {
					th {
						.cell {
						}
					}
				}
			}
		}
		:deep(.el-table__body-wrapper) {
			tbody {
				tr {
					td {
						.cell {
							// 编辑
							.el-button--primary {
							}
							// 编辑-悬浮
							.el-button--primary:hover {
							}
							// 详情
							.el-button--info {
							}
							// 详情-悬浮
							.el-button--info:hover {
							}
							// 删除
							.el-button--danger {
							}
							// 删除-悬浮
							.el-button--danger:hover {
							}
							// 跨表
							.el-button--success {
							}
							// 跨表-悬浮
							.el-button--success:hover {
							}
							// 操作
							.el-button--warning {
							}
							// 操作-悬浮
							.el-button--warning:hover {
							}
						}
					}
				}
				tr:hover {
					td {
					}
				}
			}
		}
	}
	// 分页器
	.el-pagination {
		// 总页码
		:deep(.el-pagination__total) {
		}
		// 上一页
		:deep(.btn-prev) {
		}
		// 下一页
		:deep(.btn-next) {
		}
		// 上一页禁用
		:deep(.btn-prev:disabled) {
		}
		// 下一页禁用
		:deep(.btn-next:disabled) {
		}
		// 页码
		:deep(.el-pager) {
			// 数字
			.number {
			}
			// 数字悬浮
			.number:hover {
			}
			// 选中
			.number.is-active {
			}
		}
		// sizes
		:deep(.el-pagination__sizes) {
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
			.el-select {
			}
		}
		// 跳页
		:deep(.el-pagination__jump) {
			// 输入框
			.el-input {
			}
		}
	}
</style>
