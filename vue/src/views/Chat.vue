<template>
  <div class="chat-container">
<!--    导航栏-->
    <div class="chat-header">
      <div class="chat-header-left">
        <img src="@/assets/imgs/bupt_sp_logo.png" />
        <div class="title">我的消息</div>
      </div>

      <div class="chat-header-center">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">聊天系统</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: $route.path }">{{ $route.meta.name }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>

      <div class="chat-header-right">
        <el-dropdown placement="bottom">
          <div class="avatar">
            <img :src="user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" />
            <div>{{ user.name ||  '管理员' }}<i class="el-icon-arrow-down" style="margin-left: 5px"></i></div>
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="goToPerson">个人信息</el-dropdown-item>
            <el-dropdown-item @click.native="$router.push('/password')">修改密码</el-dropdown-item>
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
<!--    聊天室UI-->
    <div class="main-body-content">
      <div style="display: flex; align-items: flex-start">
        <div class="contact-list">
          <div style="padding: 20px 10px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee">在线用户</div>
          <div style="padding: 10px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee">管理员</div>
          <div class="user-list-box">
            <div class="user-list-item" v-for="item in users.admin" @click="selectToUser(item)">
              <img :src="item.avatar" style="width: 30px; height: 30px; border-radius: 50%">
              <span style="flex: 1; margin-left: 10px;" :style="{ color: item.role+ '_' + item.name === toUser ? '#3a8ee6' : '' }">{{ item.name }}</span>
              <div class="user-list-item-badge" v-if="unRead?.[item.role + '_' + item.name]">{{ unRead?.[item.role + '_' + item.name] }}</div>
            </div>
          </div>
          <div style="padding: 10px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee">教师</div>
          <div class="user-list-box" style="height: 30%">
            <div class="user-list-item" v-for="item in users.teacher" @click="selectToUser(item)">
              <img :src="item.avatar" style="width: 30px; height: 30px; border-radius: 50%">
              <span style="flex: 1; margin-left: 10px;" :style="{ color: item.role+ '_' + item.name === toUser ? '#3a8ee6' : '' }">{{ item.name }}</span>
              <div class="user-list-item-badge" v-if="unRead?.[item.role + '_' + item.name]">{{ unRead?.[item.role + '_' + item.name] }}</div>
            </div>
          </div>
          <div style="padding: 10px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee">学生</div>
          <div class="user-list-box" style="height: 30%; overflow-y: scroll">
            <div class="user-list-item" v-for="item in users.student" @click="selectToUser(item)">
              <img :src="item.avatar" style="width: 30px; height: 30px; border-radius: 50%">
              <span style="flex: 1; margin-left: 10px;" :style="{ color: item.role+ '_' + item.name === toUser ? '#3a8ee6' : '' }">{{ item.name }}</span>
              <div class="user-list-item-badge" v-if="unRead?.[item.role + '_' + item.name]">{{ unRead?.[item.role + '_' + item.name] }}</div>
            </div>
          </div>
          <div style="padding: 10px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee">用户</div>
          <div class="user-list-box" style="height: 30%; overflow-y: scroll">
            <div class="user-list-item" v-for="item in users.student" @click="selectToUser(item)">
              <img :src="item.avatar" style="width: 30px; height: 30px; border-radius: 50%">
              <span style="flex: 1; margin-left: 10px;" :style="{ color: item.role+ '_' + item.name === toUser ? '#3a8ee6' : '' }">{{ item.name }}</span>
              <div class="user-list-item-badge" v-if="unRead?.[item.role + '_' + item.name]">{{ unRead?.[item.role + '_' + item.name] }}</div>
            </div>
          </div>
          <div style="padding: 10px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee">我的群聊（班级）</div>
          <div class="user-list-box">
            <div class="user-list-item" v-for="(item, index) in users" :key="index">
              <img :src="item.avatar" style="width: 30px; height: 30px; border-radius: 50%">
              <span style="margin-left: 10px">{{ item.name }}</span>
            </div>
          </div>
        </div>

        <!--  中间部分  -->
        <div class="chat-area" >
          <div style="padding: 20px 0; text-align: center; border-bottom: 1px solid #ddd; color: #000; background-color: #eee; height: 60px">
            {{ toUser?.substring(toUser.indexOf('_') + 1) }}
          </div>
          <div class="im-message-box">
            <!--  右边的气泡(对方信息） -->
            <div style="display: flex; flex-direction: row-reverse; align-items: flex-start">

            </div>

            <!--  左边的气泡（自己发的信息） -->
            <div style="display: flex; align-items: flex-start">

            </div>


          </div>
          <!-- 输入区域 -->
          <div style="padding: 10px 5px; border-top: 1px solid #ddd; display: flex; align-items: center; width: 100%;">
            <el-popover placement="top" width="300" trigger="click">
              <div class="emoji-box">
                <span v-for="(item, index) in emojis" :key="index"
                      style="margin-right: 5px; font-size: 20px; cursor: pointer" v-html="item"
                      @click="clickEmoji(item)">
                </span>
              </div>
              <i slot="reference" class="fa fa-smile-o" style="font-size: 22px; color: #666;"></i>
            </el-popover>
            <div style="margin-left: 5px">
              <el-upload action="http://localhost:8080/files/upload" :show-file-list="false" :on-success="handleFile">
                <i class="fa fa-folder-open-o" style="font-size: 20px; color: #666;"></i>
              </el-upload>
            </div>
            <div id="im-content" contenteditable
                 style="flex: 1; background-color: #fff; margin: 0 5px; padding: 10px; border: 1px solid #ddd; border-radius: 2px; outline: none; font-size: 14px;"></div>
            <el-button type="primary" @click="send" style="border: none">发送</el-button>
          </div>
        </div>
        <!--  中间部分结束  -->
      </div>
    </div>
  </div>

</template>

<script>
import request from "@/utils/request";
import emojis from "@/assets/emoji";

export default {
  name: "Chat",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      users: [],
    };
  },
  methods: {
    goToPerson() {
      if (this.user.role === 'ADMIN') {
        this.$router.push('/adminPerson')
      }
    },
    logout() {
      localStorage.removeItem('xm-user')
      this.$router.push('/login')
    }

  },
}
</script>



<style scoped>
@import "@/assets/css/chat.css";


</style>