import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 解决导航栏或者底部导航tabBar中的vue-router在3.0版本以上频繁点击菜单报错的问题。
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    // Manager父级
    name: 'Manager',
    component: () => import('../views/Manager.vue'),
    redirect: '/home',  // 登录后重定向到主页
    children: [
      { path: '403', name: 'NoAuth', meta: { name: '无权限' }, component: () => import('../views/manager/403') },
      { path: 'home', name: 'Home', meta: { name: '系统首页' }, component: () => import('../views/manager/Home') },
      { path: 'admin', name: 'Admin', meta: { name: '管理员信息' }, component: () => import('../views/manager/Admin') },
      { path: 'user', name: 'User', meta: { name: '用户信息' }, component: () => import('../views/manager/User') },
      { path: 'student', name: 'Student', meta: { name: '学生管理' }, component: () => import('../views/manager/Student') },
      { path: 'teacher', name: 'Teacher', meta: { name: '教师信息' }, component: () => import('../views/manager/Teacher') },
      { path: 'adminPerson', name: 'AdminPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/AdminPerson') },
      { path: 'password', name: 'Password', meta: { name: '修改密码' }, component: () => import('../views/manager/Password') },
      { path: 'notice', name: 'Notice', meta: { name: '公告信息' }, component: () => import('../views/manager/Notice') },
      { path: 'course', name: 'Course', meta: { name: '内容管理' }, component: () => import('../views/manager/Course') },
      { path: 'lessonAdmin', name: 'LessonAdmin', meta: { name: '课件管理' }, component: () => import('../views/manager/LessonAdmin') },
      { path: 'audit', name: 'Audit', meta: { name: '资料审核' }, component: () => import('../views/manager/Information') },
      { path: 'college', name: 'College', meta: { name: '学院信息' }, component: () => import('../views/manager/College') },
      { path: 'major', name: 'Major', meta: { name: '专业信息' }, component: () => import('../views/manager/Major') },
      { path: 'classes', name: 'Classes', meta: { name: '班级信息' }, component: () => import('../views/manager/Classes') },
      { path: 'classesTAdmin', name: 'ClassesTAdmin', meta: { name: '我的班级' }, component: () => import('../views/manager/ClassesTAdmin') },
      { path: 'lessonTAdmin', name: 'LessonTAdmin', meta: { name: '班级课件管理' }, component: () => import('../views/manager/LessonTAdmin') },
    ]
  },
  {
    path: '/front',
    name: 'Front',
    component: () => import('../views/Front.vue'),
    children: [
      { path: 'home', name: 'Home', meta: { name: '系统首页' }, component: () => import('../views/front/Home') },
      { path: 'person', name: 'Person', meta: { name: '个人信息' }, component: () => import('../views/front/Person') },
      { path: 'course', name: 'Course', meta: { name: '学院动态' }, component: () => import('../views/front/Course') },
      { path: 'detail', name: 'Detail', meta: { name: '动态详情' }, component: () => import('../views/front/Detail') },
      { path: 'lesson', name: 'Lesson', meta: { name: '我的课件' }, component: () => import('../views/front/Lesson') },
      { path: 'lessonDetail', name: 'LessonDetail', meta: { name: '课件详情' }, component: () => import('../views/front/LessonDetail') },
      { path: 'release', name: 'Release', meta: { name: '我的发布' }, component: () => import('../views/front/Release') },
      { path: 'channel', name: 'Channel', meta: { name: '学院专栏' }, component: () => import('../views/front/Channel') },
    ]
  },
  //   单独
  { path: '/login', name: 'Login', meta: { name: '登录' }, component: () => import('../views/Login.vue') },
  { path: '/chat', name: 'Chat', meta: { name: '我的消息' }, component: () => import('../views/Chat.vue') },
  { path: '/register', name: 'Register', meta: { name: '注册' }, component: () => import('../views/Register.vue') },
  { path: '*', name: 'NotFound', meta: { name: '无法访问' }, component: () => import('../views/404.vue') },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


//禁止USER前往的后端
const restrictedPaths = ['/home', '/admin', '/user', '/notice', '/audit','/course','/front']; // 根据需要添加更多路径

// 路由守卫
router.beforeEach((to ,from, next) => {
  let user = JSON.parse(localStorage.getItem("xm-user") || '{}');
  if (to.path === '/' ) {
    if (user.role) {
      if (user.role === 'USER') {
        next('/front/home')
      } else {
        next('/home')
      }
    } else {
      next('/login')
    }
  }else if ((restrictedPaths.includes(to.path) &&user.role === 'USER')){
    next('/front/home');
  }
  else {
    next()
  }
})

export default router
