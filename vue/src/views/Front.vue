<template>
  <div class="wrapper">
    <div>
      <nav id="navbar_all" class="navbar navbar-static-top navbar-default" :class="{ 'navbar-hidden': scrolled }">
        <div class="left-header">
          <div class="front-notice"><i class="el-icon-bell" style="margin-right: 2px"></i>公告：{{ top }}</div>
          <div class="container_index">
            <span class="navbar-brand">
              <a class="nav-logo-link" href="/">
                <div class="bupt-sp-logo-container"></div>
              </a>
            </span>
            <div class="title">北邮南太孔院信息宣传门户</div>
          </div>
        </div>
        <div class="center-header">
          <el-menu :default-active="$route.path" mode="horizontal" class="header-nav" router>
            <el-menu-item index="/front/home">首页</el-menu-item>
            <el-menu-item index="/front/course">学院动态</el-menu-item>
            <el-menu-item index="/front/channel">学院专栏</el-menu-item>
            <el-menu-item index="/front/information">南太论坛</el-menu-item>
            <el-menu-item index="/front/release">我的发布</el-menu-item>
            <el-menu-item v-if="user.role ==='USER'&&user.studentflag === '1'" index="/front/lesson">我的课件</el-menu-item>
            <el-menu-item v-if="user.role ==='ADMIN' || user.role ==='TEACHER'" index="/home">前往管理端</el-menu-item>
          </el-menu>
        </div>
        <div class="right-header">
          <div v-if="!user.username">
            <el-button class="nav-button" @click="$router.push('/login')">登录</el-button>
            <el-button class="nav-button" @click="$router.push('/register')">注册</el-button>
          </div>
          <div v-else>
            <el-dropdown>
              <div class="front-header-dropdown">
                <img :src="user.avatar|| 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" alt="">
                <div style="margin-left: 10px">
                  <span>{{ user.name }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
                </div>
                <div class="unread-badge" v-if="totalUnRead > 0">{{ totalUnRead }}</div>
              </div>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <div style="text-decoration: none" @click="navTo('/front/person')">个人中心</div>
                </el-dropdown-item>
                <el-dropdown-item>
                  <div style="text-decoration: none" @click="navTo('/chat')">我的消息</div>
                </el-dropdown-item>
                <el-dropdown-item>
                  <div style="text-decoration: none" @click="logout">退出</div>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </nav>
    </div>

    <!--主体-->
    <div class="main-body" style="padding-top: 90px;flex-grow: 1">
      <router-view ref="child" @update:user="updateUser" />
    </div>

    <!--  尾部-->
    <div class="footer">
      <div class="footer-con" style="width: 80%; margin: 0px auto; display: flex; padding-top: 5%">
        <div class="footer-left" style="width: 50%">
          <div class="attention computer">
            <div>
              <div>
                <h4 class="font-white">关注北邮南太孔院</h4>
                <h4 class="font-white">Follow BUPT-SP</h4>
              </div>
              <div class="icon">
                <span class="iconBilibili" style=""><img src="@/assets/icons/icon_bilibili-circle.svg"></span>
                <span class="iconWeibo"><img src="@/assets/icons/icon_weibo-circle.svg" ></span>
                <span class="iconTickTok"><img src="@/assets/icons/icon_TikTok-circle.svg"></span>
                <span class="iconWeixin"><img src="@/assets/icons/icon_wechat-circle.svg"></span>
              </div>
              <div class="font-white">
                <h4 class="cn">关注该项目</h4>
                <h4 class="en">Follow Us on Github</h4>
              </div>
              <div class="icon">
                <a href="https://github.com/Lev010608/bupt_sp_advertisement" target="_blank">
                  <span class="iconGithub"><img src="@/assets/icons/icon_github-circle.svg"></span>
                </a>
              </div>
            </div>
          </div>
        </div>
        <div class="footer-right">
          <h4 class="font-white">联系我们</h4>
          <h4 class="font-white">Contact Us</h4>
          <div style="padding-top: 20px">
            <h4 class="font-white">邮箱</h4>
            <h4 class="font-white">yanfool8321926@bupt.edu.cn</h4>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "FrontLayout",

  data () {
    return {
      top: '',
      notice: [],
      user: JSON.parse(localStorage.getItem("xm-user") || '{}'),
      totalUnRead: 0, // 新增变量
      scrolled: false,
      lastScrollTop: 0,
    }
  },

  mounted() {
    this.loadNotice();
    this.loadUnReadNums(); // 新增调用
    window.addEventListener('scroll', this.handleScroll);
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll);
  },

  methods: {
    loadNotice() {
      this.$request.get('/notice/selectAll').then(res => {
        this.notice = res.data;
        let i = 0;
        if (this.notice && this.notice.length) {
          this.top = this.notice[0].content;
          setInterval(() => {
            this.top = this.notice[i].content;
            i++;
            if (i === this.notice.length) {
              i = 0;
            }
          }, 2500);
        }
      });
    },
    loadUnReadNums() {
      request.get('/imsingle/unReadNums?toUsername=' + this.user.role + '_' + this.user.name).then(res => {
        this.totalUnRead = Object.values(res.data).reduce((sum, num) => sum + num, 0);
        console.log("未读消息数据: ", res.data);  // 添加日志
      });
    },
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}');   // 重新获取下用户的最新信息
      this.loadUnReadNums(); // 更新用户信息后加载未读消息数量
    },
    // 退出登录
    logout() {
      localStorage.removeItem("xm-user");
      this.$router.push("/login");
    },

    //跳转URL
    navTo(url) {
      location.href = url;
    },
    // 管理导航栏
    handleScroll() {
      const st = window.pageYOffset || document.documentElement.scrollTop;
      if (st > this.lastScrollTop) {
        // Scroll down
        this.scrolled = true;
      } else {
        // Scroll up
        this.scrolled = false;
      }
      this.lastScrollTop = st <= 0 ? 0 : st; // For Mobile or negative scrolling
    }
  }
}
</script>

<style scoped>
@import "@/assets/css/front.css";
.wrapper {
  display: flex;
  flex-direction: column;
  min-height: 100vh; /* 设置wrapper为最小高度为视口高度 */
  background-color: #f4f3f0;
}

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
