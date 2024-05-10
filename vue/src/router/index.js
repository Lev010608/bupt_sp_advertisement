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
      { path: 'teacher', name: 'Teacher', meta: { name: '教师信息' }, component: () => import('../views/manager/Teacher') },
      { path: 'adminPerson', name: 'AdminPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/AdminPerson') },
      { path: 'password', name: 'Password', meta: { name: '修改密码' }, component: () => import('../views/manager/Password') },
      { path: 'notice', name: 'Notice', meta: { name: '公告信息' }, component: () => import('../views/manager/Notice') },
      { path: 'course', name: 'Course', meta: { name: '内容管理' }, component: () => import('../views/manager/Course') },
      { path: 'audit', name: 'Audit', meta: { name: '资料审核' }, component: () => import('../views/manager/Information') },
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
      { path: 'release', name: 'Release', meta: { name: '我的发布' }, component: () => import('../views/front/Release') },
      { path: 'channel', name: 'Channel', meta: { name: '学院专栏' }, component: () => import('../views/front/Channel') },
    ]
  },
  //   单独
  { path: '/login', name: 'Login', meta: { name: '登录' }, component: () => import('../views/Login.vue') },
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







// 路由守卫1.0
// router.beforeEach((to ,from, next) => {
//   let user = JSON.parse(localStorage.getItem("xm-user") || '{}');
//   if (to.path === '/' || to.path === '/home' ) {
//     if (user.role) {
//       console.log(user.role)
//       if (user.role === 'USER') {
//         next('/front/home')
//       } else if(user.role === 'ADMIN') {
//         next('/home')
//       }
//     } else {
//       next('/login')
//     }
//   } else {
//     next()
//   }
// })


// 路由守卫2.0
// router.beforeEach((to, from, next) => {
//   let user = JSON.parse(localStorage.getItem("xm-user") || '{}');
//   if (!user.role) { // 假设没有角色表示是访客
//     user.role = 'GUEST'; // 默认角色为GUEST
//   }
//
//
//   if (to.path === '/' || to.path === '/home') {
//     if (user.role === 'ADMIN') {
//       console.log(user.role)
//       next('/home');
//     } else {
//       console.log(user.role)
//       next('/front/home');
//     }
//   } else if (user.role === 'GUEST' && ![ '/front/home', '/front/detail' ].includes(to.path)) {
//     console.log(user.role)
//     // 如果是访客并且访问的不是允许的页面，则重定向到登录
//     next('/login');
//   } else {
//     next();
//   }
// });

export default router
