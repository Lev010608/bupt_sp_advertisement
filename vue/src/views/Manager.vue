<template>
  <div class="manager-container">
    <!--  头部  -->
    <div class="manager-header">
      <div class="manager-header-left">
        <img src="@/assets/imgs/bupt_sp_logo.png" />
        <div class="title">后台管理系统</div>
      </div>

      <div class="manager-header-center">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: $route.path }">{{ $route.meta.name }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>

      <div class="manager-header-right">
        <el-button class="nav-button" style="margin-right: 10px; font-weight: bolder" @click="$router.push('/front/home')">前往展示端</el-button>
        <el-dropdown placement="bottom">
          <div class="avatar">
            <img :src="user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" />
            <div>{{ user.name ||  '管理员' }}<i class="el-icon-arrow-down" style="margin-left: 5px"></i></div>
            <div class="unread-badge" v-if="totalUnRead > 0">{{ totalUnRead }}</div>
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="goToPerson">个人信息</el-dropdown-item>
            <el-dropdown-item @click.native="goToChat">我的消息</el-dropdown-item>
            <el-dropdown-item @click.native="$router.push('/password')">修改密码</el-dropdown-item>
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <!--  主体  -->
    <div class="manager-main">
      <!--  侧边栏  -->
      <div class="manager-main-left">
        <el-menu :default-openeds="['info', 'user', 'administration', 'teach']" router style="border: none" :default-active="$route.path">
          <el-menu-item index="/home">
            <i class="el-icon-s-home"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>
          <el-submenu v-if="user.role === 'ADMIN'" index="info">
            <template slot="title">
              <i class="el-icon-menu"></i><span>信息管理</span>
            </template>
            <el-menu-item index="/notice">公告信息</el-menu-item>
            <el-menu-item index="/course">内容管理</el-menu-item>
            <el-menu-item index="/audit">资料审核</el-menu-item>
          </el-submenu>

          <el-submenu v-if="user.role === 'ADMIN'" index="administration">
            <template slot="title">
              <i class="el-icon-menu"></i><span>行政管理</span>
            </template>
            <el-menu-item index="/college">学院信息</el-menu-item>
            <el-menu-item index="/major">专业信息</el-menu-item>
            <el-menu-item index="/classes">班级信息</el-menu-item>
            <el-menu-item index="/student">学生管理</el-menu-item>
            <el-menu-item index="/lessonAdmin">课件管理</el-menu-item>
          </el-submenu>

          <el-submenu v-if="user.role === 'ADMIN'" index="user">
            <template slot="title">
              <i class="el-icon-menu"></i><span>用户管理</span>
            </template>
            <el-menu-item index="/admin">管理员信息</el-menu-item>
            <el-menu-item index="/user">用户信息</el-menu-item>
            <el-menu-item index="/teacher">教师信息</el-menu-item>
          </el-submenu>

          <el-submenu v-if="user.role === 'TEACHER'" index="teach">
            <template slot="title">
              <i class="el-icon-menu"></i><span>教学管理</span>
            </template>
            <el-menu-item index="/classesTAdmin">我的班级</el-menu-item>
            <el-menu-item index="/lessonTAdmin">课件管理</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>

      <!--  数据表格  -->
      <div class="manager-main-right">
        <router-view @update:user="updateUser" />
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Manager",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      totalUnRead: 0 // 新增变量
    }
  },
  created() {
    if (!this.user.id) {
      this.$router.push('/front/home');
    } else {
      this.loadUnReadNums(); // 新增调用
    }
  },
  methods: {
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}');   // 重新获取下用户的最新信息
      this.loadUnReadNums(); // 更新用户信息后加载未读消息数量
    },
    loadUnReadNums() {
      request.get('/imsingle/unReadNums?toUsername=' + this.user.role + '_' + this.user.name).then(res => {
        this.totalUnRead = Object.values(res.data).reduce((sum, num) => sum + num, 0);
        console.log("未读消息数据: ", res.data);  // 添加日志
      });
    },
    goToChat() {
      this.$router.push('/chat');
    },
    goToPerson() {
      if (this.user.role === 'ADMIN') {
        this.$router.push('/adminPerson');
      }
    },
    logout() {
      localStorage.removeItem('xm-user');
      this.$router.push('/login');
    }
  }
}
</script>

<style scoped>
@import "@/assets/css/manager.css";
.unread-badge {
  position: absolute;
  top: 0;
  right: 0;
  background-color: red;
  color: white;
  border-radius: 50%;
  width: 20px;
  height: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 12px;
}
</style>
