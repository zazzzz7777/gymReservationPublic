<template>
  <div>
    <div class="login_view">
      <el-form :model="loginForm" class="login_form">
        <div class="title_view">校园运动场馆预约管理系统登录</div>
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">账号：</div>
          <input
            class="list_inp"
            v-model="loginForm.username"
            name="username"
            placeholder="请输入账号"
          />
        </div>
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">密码：</div>
          <input
            class="list_inp"
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            @keydown.enter.native="handleLogin"
          />
        </div>
        <div class="list_type" v-if="userList.length > 1">
          <div class="list_label">用户类型：</div>
          <el-select v-model="loginForm.role" placeholder="请选择用户类型">
            <el-option
              v-for="(item, index) in userList"
              :label="item.roleName"
              :value="item.roleName"
            ></el-option>
          </el-select>
        </div>
        <div class="btn_view">
          <el-button
            class="login"
            v-if="loginType == 1"
            type="success"
            @click="handleLogin"
            >登录</el-button
          >
          <el-button
            class="register"
            type="primary"
            @click="handleRegister('yonghu')"
            >注册用户</el-button
          >
        </div>
      </el-form>
    </div>
    <Vcode
      :show="isShow"
      @success="success"
      @close="close"
      @fail="fail"
    ></Vcode>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick, onMounted } from 'vue'
import { useStore } from 'vuex'
const store = useStore()
import menu from '@/utils/menu'
const userList = ref([])
const menus = ref([])
const loginForm = ref({
  role: '',
  username: '',
  password: '',
})
const tableName = ref('')
const loginType = ref(1)
const context = getCurrentInstance()?.appContext.config.globalProperties
//注册
const handleRegister = (tableName) => {
  context?.$router.push(`/${tableName}Register`)
}
const handleLogin = () => {
  if (!loginForm.value.username) {
    context?.$toolUtil.message('请输入用户名', 'error')
    return
  }
  if (!loginForm.value.password) {
    context?.$toolUtil.message('请输入密码', 'error')
    return
  }
  if (userList.value.length > 1) {
    if (!loginForm.value.role) {
      context?.$toolUtil.message('请选择角色', 'error')
      verifySlider.reset()
      return
    }
    for (let i = 0; i < menus.value.length; i++) {
      if (menus.value[i].roleName == loginForm.value.role) {
        tableName.value = menus.value[i].pathName || menus.value[i].tableName
      }
    }
  } else {
    tableName.value = userList.value[0].pathName || userList.value[0].tableName
    loginForm.value.role = userList.value[0].roleName
  }
  login()
}
const login = () => {
  context
    ?.$http({
      url: `${tableName.value}/login?username=${loginForm.value.username}&password=${loginForm.value.password}`,
      method: 'post',
    })
    .then(
      (res) => {
        context?.$toolUtil.storageSet('frontToken', res.data.token)
        context?.$toolUtil.storageSet('frontRole', loginForm.value.role)
        context?.$toolUtil.storageSet('frontSessionTable', tableName.value)
        store.dispatch('user/getSession')
        let path = context?.$toolUtil.storageGet('toPath')
        if (path && path != '/login') {
          context?.$router.push(path)
          context?.$toolUtil.storageRemove('toPath')
          return
        }
        context?.$router.push(`/index/${tableName.value}Center`)
      },
      (err) => {}
    )
}
//获取菜单
const getMenu = async () => {
  let arr = menu.list()
  if (!arr) {
    let res = await context?.$http.get('menu/list')
    context?.$toolUtil.storageSet('menus', res.data.data.list[0].menujson)
    arr = JSON.parse(res.data.data.list[0].menujson)
  }
  menus.value = arr
  for (let i = 0; i < menus.value.length; i++) {
    if (menus.value[i].hasFrontLogin == '是') {
      userList.value.push(menus.value[i])
    }
  }
}
//初始化
const init = async () => {
  await getMenu()
  loginForm.value.role = userList.value[0].roleName
}
onMounted(() => {
  init()
})
</script>

<style lang="scss" scoped>
.login_view {
  background-image: url('http://localhost:8080/gymReservation/file/8078e310b766407e990e16bc4492e5ae.jpg');
  // 表单盒子
  .login_form {
  }
  .title_view {
    padding: 0 0 30px;
    color: #333;
    font-weight: 600;
    width: 100%;
    font-size: 22px;
    text-align: center;
  }
  // item盒子
  .list_item {
    // label
    .list_label {
    }
    // 输入框
    .list_inp {
    }
  }
  .list_type {
    .list_label {
    }
    // 下拉框样式
    :deep(.el-select) {
      //去掉默认样式
      .select-trigger {
        height: 100%;
        .el-input {
          height: 100%;
        }
      }
    }
  }
  // 按钮盒子
  .btn_view {
    // 登录
    .login {
    }
    // 注册
    .register {
    }
  }
}
</style>
<style>
/*背景*/
.login_view {
  min-height: 100vh;
  position: relative;
  background: url(http://localhost:8080/gymReservation/file/8078e310b766407e990e16bc4492e5ae.jpg)
    no-repeat center center / 100% 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
/*表单盒子*/
.login_view .login_form {
  width: 750px;
  box-shadow: rgb(187, 187, 187) 0px 4px 9px;
  padding: 30px 60px 30px 40px;
  margin: 0 0 0 40%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  border-radius: 10px;
  background: rgba(255, 255, 255, 1);
  box-shadow: none;
  font-size: 24px;
}
/*外部标题*/
.login_view .outTitle_view {
  display: flex;
  align-items: center;
  padding: 0px 0px 60px;
  position: absolute;
  left: 40px;
  top: 20px;
}
.login_view .outTitle_view .outTilte {
  color: #2dc6cc;
  font-size: 24px;
  font-weight: 600;
}
/*item*/
.login_view .login_form .list_item {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: center;
  margin: 0px 10px 30px 0px;
}
.login_view .login_form .list_item .list_label {
  width: auto;
  text-align: right;
  font-size: 16px;
  white-space: nowrap;
  min-width: 120px;
}
.login_view .login_form .list_item .list_inp {
  height: 40px;
  line-height: 40px;
  border: none;
  border: 1px solid rgb(221, 221, 221);
  padding: 0px 10px;
  width: calc(100% - 60px);
  font-size: 16px;
}
/*用户类型 盒子*/
.login_view .login_form .list_type {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: center;
  margin: 0px 10px 20px 0px;
}
.login_view .login_form .list_type .list_label {
  width: auto;
  text-align: right;
  font-size: 16px;
  white-space: nowrap;
  min-width: 120px;
}
.login_view .login_form .list_type .el-select {
  line-height: 40px;
  border: none;
  border: 1px solid rgb(221, 221, 221);
  box-sizing: border-box;
  width: calc(100% - 60px);
  padding: 0px 10px;
  border-radius: 0px;
  font-size: 16px;
  color: rgb(102, 102, 102);
}
.login_view .login_form .list_type .el-select .el-input__inner {
  font-size: 16px;
}

/*验证码 盒子*/
.login_view .login_form .listCode_view {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: center;
  margin: 0px 10px 10px 0px;
}
.login_view .login_form .listCode_view .listCode_label {
  width: 60px;
  text-align: right;
  font-size: 16px;
}
.login_view .login_form .listCode_view .listCode_inp {
  height: 40px;
  line-height: 40px;
  border: none;
  border: 1px solid rgb(221, 221, 221);
  padding: 0px 10px;
  width: calc(100% - 160px);
  font-size: 16px;
}
.login_view .login_form .listCode_view .listCode_btn {
  width: 100px;
  border-image: initial;
  height: 40px;
  line-height: 40px;
  text-align: center;
  border: 1px solid rgb(221, 221, 221);
}
/*按钮 盒子*/
.login_view .login_form .btn_view {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  padding: 20px 0px 0px 120px;
  width: 100%;
}
.login_view .login_form .btn_view .login {
  margin: 0px 10px 10px;
  padding: 0px 24px;
  width: auto;
  height: 42px;
  border-radius: 0px;
  border: 1px solid #828bd6;
  border-width: 1px 0;
  background: none;
  cursor: pointer;
  color: #828bd6;
  font-size: 16px;
  background-repeat: no-repeat;
  background-position: bottom;
  background-image: -webkit-linear-gradient(left, #828bd6, #828bd6);
  background-image: linear-gradient(to left, #828bd6, #828bd6);
  background-size: 100% 0;
  transition: all 0.1s;
  clip-path: polygon(10% 0, 100% 0, 90% 100%, 0% 100%);
}
.login_view .login_form .btn_view .login:hover {
  background-size: 100% 100%;
  color: #fff;
  border-color: #828bd6;
}

.login_view .login_form .btn_view .register {
  margin: 0px 10px 10px;
  padding: 0px 24px;
  width: auto;
  height: 42px;
  border-radius: 0px;
  border: 1px solid var(--theme);
  border-width: 1px 0;
  background: none;
  cursor: pointer;
  color: var(--theme);
  font-size: 16px;
  background-repeat: no-repeat;
  background-position: bottom;
  background-image: -webkit-linear-gradient(left, var(--theme), var(--theme));
  background-image: linear-gradient(to left, var(--theme), var(--theme));
  background-size: 100% 0;
  transition: all 0.1s;
  clip-path: polygon(10% 0, 100% 0, 90% 100%, 0% 100%);
}
.login_view .login_form .btn_view .register:hover {
  background-size: 100% 100%;
  color: #fff;
  border-color: var(--theme);
}
.login_view .login_form .btn_view .forget {
  display: inline-block;
  margin: 20px 10px 12px 0px;
  padding: 0px;
  width: 100%;
  height: 40px;
  font-size: 16px;
  color: rgb(102, 102, 102);
  border-radius: 4px;
  border: 0px;
  background: none;
  cursor: pointer;
  text-align: right;
  text-decoration: underline;
}
.login_view .login_form .btn_view .forget:hover {
  text-decoration: underline;
}
.login_view .login_form .face {
  font-size: 16px;
  color: rgb(102, 102, 102);
  cursor: pointer;
  padding: 0px 0px 0px 120px;
  text-align: left;
  width: 100%;
  text-decoration: underline;
}
</style>
