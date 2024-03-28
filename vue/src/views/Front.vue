<template>
  <div>

    <div>
      <nav id="navbar_all" class="navbar navbar-static-top navbar-default" :class="{ 'navbar-hidden': scrolled }">
        <div class="left-header">
          <div class="front-notice"><i class="el-icon-bell" style="margin-right: 2px"></i>公告：{{ top }}</div>
          <!--头部-->
          <div class="container_index">
        <span class="navbar-brand">
        <a class="nav-logo-link" href="/">
          <div class="bupt-sp-logo-container"></div>
        </a>
      </span>
            <div class="title">北邮南太孔院宣传门户</div>
          </div>
        </div>
        <div class="center-header">
            <el-menu :default-active="$route.path" mode="horizontal" class="header-nav" router>
              <el-menu-item index="/front/home">首页</el-menu-item>
              <el-menu-item>学院概况</el-menu-item>
              <el-menu-item index="/front/course">学院动态</el-menu-item>
              <el-menu-item>南太论坛</el-menu-item>
              <el-menu-item>我的发布</el-menu-item>
              <el-menu-item>关于我们</el-menu-item>
            </el-menu>
        </div>
        <div class="right-header">
          <div v-if="!user.username">
            <el-button @click="$router.push('/login')">登录</el-button>
            <el-button @click="$router.push('/register')">注册</el-button>
          </div>
          <div v-else>
            <el-dropdown>
              <div class="front-header-dropdown">
                <img :src="user.avatar" alt="">
                <div style="margin-left: 10px">
                  <span>{{ user.name }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
                </div>
              </div>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <div style="text-decoration: none" @click="navTo('/front/person')">个人中心</div>
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

<!--    <div class="front-header">-->
<!--      <div class="front-header-left">-->
<!--        <img src="@/assets/imgs/logo.png" alt="">-->
<!--&lt;!&ndash;        <div class="title">南太宣传门户</div>&ndash;&gt;-->
<!--      </div>-->
<!--      <div class="front-header-center">-->
<!--        <div class="front-header-nav">-->
<!--          <el-menu :default-active="$route.path" mode="horizontal" router>-->
<!--						<el-menu-item index="/front/home">首页</el-menu-item>-->
<!--						<el-menu-item index="/front/person">个人中心</el-menu-item>-->
<!--          </el-menu>-->
<!--        </div>-->
<!--      </div>-->
<!--      <div class="front-header-right">-->
<!--        <div v-if="!user.username">-->
<!--          <el-button @click="$router.push('/login')">登录</el-button>-->
<!--          <el-button @click="$router.push('/register')">注册</el-button>-->
<!--        </div>-->
<!--        <div v-else>-->
<!--          <el-dropdown>-->
<!--            <div class="front-header-dropdown">-->
<!--              <img :src="user.avatar" alt="">-->
<!--              <div style="margin-left: 10px">-->
<!--                <span>{{ user.name }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>-->
<!--              </div>-->
<!--            </div>-->
<!--            <el-dropdown-menu slot="dropdown">-->
<!--              <el-dropdown-item>-->
<!--                <div style="text-decoration: none" @click="navTo('/front/person')">个人中心</div>-->
<!--              </el-dropdown-item>-->
<!--              <el-dropdown-item>-->
<!--                <div style="text-decoration: none" @click="logout">退出</div>-->
<!--              </el-dropdown-item>-->
<!--            </el-dropdown-menu>-->
<!--          </el-dropdown>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
    <!--主体-->
    <div class="main-body" style="padding-top: 90px">
      <router-view ref="child" @update:user="updateUser" />
    </div>

    <!--  尾部-->
    <div class="footer">
      <div class="footer-con" style="width: 80%; margin: 0px auto; display: flex">
        <div class="footer-left">

          <div class="attention computer">
            <div style="display: flex">
              <div>
                <h4 class="cn">关注北邮南太孔院</h4>
                <h4 class="en">Follow BUPT-SP</h4>
              </div>
              <div style="padding-left: 40%">
                <h4 class="cn">关注该项目</h4>
                <h4 class="en">Follow Us on Github</h4>
              </div>
            </div>
            <div class="icon">
              <span class="iconBilibili"><img src="@/assets/icons/icon_bilibili-circle.svg"></span>
              <span class="iconWeibo"><img src="@/assets/icons/icon_weibo-circle.svg"></span>
              <span class="iconTickTok"><img src="@/assets/icons/icon_TikTok-circle.svg"></span>
              <span class="iconWeixin"><img src="@/assets/icons/icon_wechat-circle.svg"></span>
              <span class="iconGithub"><img src="@/assets/icons/icon_github-circle.svg"></span>
            </div>

          </div>
        </div>
        <div class="footer-right">
          <ul>
            <li>
              <p>网址</p>
            </li>
            <li>
              <p>邮箱</p>
              <p>yanfool8321926@bupt.edu.cn</p>
            </li>
            <li>
              <p>地址</p>
              <p>北京邮电大学叶培大学院
              </p>
            </li>
          </ul>
          <div class="attention mobile"></div>
        </div>
      </div>
    </div>
  </div>



</template>

<script>

export default {
  name: "FrontLayout",

  data () {
    return {
      top: '',
      notice: [],
      user: JSON.parse(localStorage.getItem("xm-user") || '{}'),

      scrolled: false,
      lastScrollTop: 0
    }
  },

  mounted() {
    this.loadNotice();
    window.addEventListener('scroll', this.handleScroll);
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll);
  },


  methods: {
    loadNotice() {
      this.$request.get('/notice/selectAll').then(res => {
        this.notice = res.data
        let i = 0
        if (this.notice && this.notice.length) {
          this.top = this.notice[0].content
          setInterval(() => {
            this.top = this.notice[i].content
            i++
            if (i === this.notice.length) {
              i = 0
            }
          }, 2500)
        }
      })
    },
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}')   // 重新获取下用户的最新信息
    },
    // 退出登录
    logout() {
      localStorage.removeItem("xm-user");
      this.$router.push("/login");
    },

    //跳转URL
    navTo(url){
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
</style>