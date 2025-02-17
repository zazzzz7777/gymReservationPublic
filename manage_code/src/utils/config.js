const config = {
  get() {
    return {
      url:
        process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
      name: process.env.VUE_APP_BASE_API,
    }
  },
  getProjectName() {
    return {
      projectName: '校园运动场馆预约管理系统',
    }
  },
}
export default config
