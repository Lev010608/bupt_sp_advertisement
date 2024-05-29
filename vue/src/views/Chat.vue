<template>
  <div class="chat-container">
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
            <div>{{ user.name || user.username }}<i class="el-icon-arrow-down" style="margin-left: 5px"></i></div>
            <div class="unread-badge" v-if="totalUnRead > 0">{{ totalUnRead }}</div> <!-- 添加未读消息总数 -->
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="goToPerson">个人信息</el-dropdown-item>
            <el-dropdown-item @click.native="$router.push('/password')">修改密码</el-dropdown-item>
            <el-dropdown-item @click.native="backToHome">回到首页</el-dropdown-item>
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>

          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <div class="main-body-content">
      <div class="contact-list">
        <div style="padding: 20px 10px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee">在线用户</div>

        <div style="padding: 10px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee">管理员</div>
        <div class="user-list-box">
          <div class="user-list-item" v-for="item in users.admin" :key="item.id" @click="selectToUser(item)">
            <img :src="item.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" style="width: 30px; height: 30px; border-radius: 50%">
            <span style="flex: 1; margin-left: 10px;" :style="{ color: item.role + '_' + item.name === toUser ? '#3a8ee6' : '' }">{{ item.name || item.username }}</span>
            <div class="user-list-item-badge" v-if="unRead?.[item.role + '_' + item.name]">{{ unRead?.[item.role + '_' + item.name] }}</div>
          </div>
        </div>

        <div style="padding: 10px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee">教师</div>
        <div class="user-list-box">
          <div class="user-list-item" v-for="item in users.teacher" :key="item.id" @click="selectToUser(item)">
            <img :src="item.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" style="width: 30px; height: 30px; border-radius: 50%">
            <span style="flex: 1; margin-left: 10px;" :style="{ color: item.role + '_' + item.username === toUser ? '#3a8ee6' : '' }">{{ item.name || item.username }}</span>
            <div class="user-list-item-badge" v-if="unRead?.[item.role + '_' + item.name]">{{ unRead?.[item.role + '_' + item.name] }}</div>
          </div>
        </div>

        <div style="padding: 10px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee">学生</div>
        <div class="user-list-box" style="height: 30%; overflow-y: auto">
          <div class="user-list-item" v-for="item in users.student" :key="item.id" @click="selectToUser(item)" >
            <img :src="item.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" style="width: 30px; height: 30px; border-radius: 50%">
            <span style="flex: 1; margin-left: 10px;" :style="{ color: item.role + '_' + item.username === toUser ? '#3a8ee6' : '' }">{{ item.name || item.username }}</span>
            <div class="user-list-item-badge" v-if="unRead?.[item.role + '_' + item.name]">{{ unRead?.[item.role + '_' + item.name] }}</div>
          </div>
        </div>

        <div style="padding: 10px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee">用户</div>
        <div class="user-list-box" style="height: 30%; overflow-y: auto">
          <div class="user-list-item" v-for="item in users.notstudent" :key="item.id" @click="selectToUser(item)">
            <img :src="item.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" style="width: 30px; height: 30px; border-radius: 50%">
            <span style="flex: 1; margin-left: 10px;" :style="{ color: item.role + '_' + item.username === toUser ? '#3a8ee6' : '' }">{{ item.name || item.username }}</span>
            <div class="user-list-item-badge" v-if="unRead?.[item.role + '_' + item.name]">{{ unRead?.[item.role + '_' + item.name] }}</div>
          </div>
        </div>
      </div>

      <!--  中间部分  -->
      <div class="middle-area">
        <div style="padding: 20px 0; text-align: center; border-bottom: 1px solid #ddd; border-top-left-radius: 20px; border-top-right-radius: 20px; color: #000; background-color: #eee; height: 60px">
          {{ toUser?.substring(toUser.indexOf('_') + 1) }}
        </div>
        <div class="im-message-box">
          <div v-for="item in messages" :key="item.id">

            <!-- 右边的气泡 自己的输入部分-->
            <div style="display: flex; flex-direction: row-reverse; align-items: flex-start" v-if="item.fromuser === fromUser && item.touser === toUser">
              <img :src="item.fromavatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" alt=""
                   style="width: 40px; height: 40px; border-radius: 50%; margin-left: 10px">
              <div style="flex: 1; display: flex; flex-direction: column; align-items: flex-end;">
                <div style="color: #888; font-size: 12px; text-align: right;">
                  {{ item.fromuser?.substring(item.fromuser.indexOf('_') + 1) }}
                </div>
                <div class="im-message im-message-right" v-html="item.content" v-if="item.type === 'text'"></div>
                <div class="im-message" style="padding: 0" v-if="item.type === 'img'">
                  <el-image style="width: 100px" :src="item.content" alt="" :preview-src-list="[item.content]" @load="scrollToBottom"></el-image>
                </div>
                <div class="im-message im-message-download" v-if="item.type === 'file'" @click="download(item.content)">
                  <div><i class="el-icon-folder-opened"></i>
                    <span>{{ item.content.substring(item.content.indexOf('-') + 1) }}</span>
                  </div>
                </div>
              </div>
            </div>

            <!--  左边的气泡 -->
            <div style="display: flex; align-items: flex-start" v-if="item.fromuser === toUser && item.touser === fromUser">
              <img :src="item.fromavatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" alt=""
                   style="width: 40px; height: 40px; border-radius: 50%; margin-right: 10px">
              <div style="flex: 1;">
                <div style="color: #888; font-size: 12px;">
                  {{ item.fromuser?.substring(item.fromuser.indexOf('_') + 1) }}
                </div>
                <div class="im-message im-message-left" v-html="item.content" v-if="item.type === 'text'"></div>
                <div class="im-message" style="padding: 0" v-if="item.type === 'img'">
                  <el-image style="width: 100px" :src="item.content" alt="" :preview-src-list="[item.content]" @load="scrollToBottom"></el-image>
                </div>
                <div class="im-message im-message-download" v-if="item.type === 'file'" @click="download(item.content)">
                  <div><i class="el-icon-folder-opened"></i>
                    <span>{{ item.content.substring(item.content.indexOf('-') + 1) }}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 输入区域 -->
        <div style="padding: 10px 5px; border-top: 1px solid #ddd; display: flex; align-items: center;">
          <el-popover placement="top" width="300" trigger="click">
            <div class="emoji-box">
              <span v-for="(item, index) in emojis" :key="index" style="margin-right: 5px; font-size: 20px; cursor: pointer" v-html="item" @click="clickEmoji(item)">
              </span>
            </div>
            <i slot="reference" class="fa fa-smile-o" style="font-size: 22px; color: #666;"></i>
          </el-popover>
          <div style="margin-left: 5px">
            <el-upload :action="`${this.$baseUrl}/files/chat/upload`" :show-file-list="false" :on-success="handleFile">
              <i class="fa fa-folder-open-o" style="font-size: 20px; color: #666;"></i>
            </el-upload>
          </div>
          <div id="im-content" contenteditable style="flex: 1; background-color: #fff; margin: 0 5px; padding: 10px; border: 1px solid #ddd; border-radius: 2px; outline: none; font-size: 14px;"></div>
          <el-button type="primary" @click="send" style="border: none">发送</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import emojis from "@/assets/emoji";
import Vue from 'vue';
import { Popover } from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(Popover);


let client
export default {
  name: "Chat",
  data() {
    return {
      permission: [],
      emojis: [],
      messages: [],
      fromUser: '',
      toUser: '',
      toAvatar: '',
      totalUnRead: 0, // 新增变量
      unRead: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      users: {
        admin: [],
        teacher: [],
        student: [],
        notstudent: []
      },
    };
  },
  mounted() {

    this.emojis = emojis.split(',')
    console.log("当前用户")
    this.fromUser = this.user.role + '_' + this.user.name
    this.loadUnReadNums();

    client = new WebSocket(`${this.$baseUrl.replace('http', 'ws')}/imserverSingle`)
    client.onopen = () => {
      console.log('websocket open')
      this.loadUnReadNums();
    }
    client.onclose = () => {  // 页面刷新的时候和后台websocket服务关闭的时候
      console.log('websocket close')
    }
    client.onmessage = (msg) => {
      if (msg.data) {
        let json = JSON.parse(msg.data)
        if (json.content && (json.fromuser === this.fromUser && json.touser === this.toUser)
            || json.touser === this.fromUser && json.fromuser === this.toUser) {  // 聊天消息
          this.messages.push(json)
          this.scrollToBottom()  // 滚动页面到最底部
        }
        // 加载消息数字loadUnReadNums
        if (this.toUser === json.fromuser){
          this.setUnReadNums()
        }else {
          this.loadUnReadNums();
        }

      }
    }

    // 加载聊天数据
    this.load();

    // 查询用户
    this.loadUsers();
  },
  beforeDestroy() {
    if (client) {
      client.close()
    }
  },
  methods: {
    load() {
      request.get('/imsingle?fromUser=' + this.fromUser + '&toUser=' + this.toUser).then(res => {
        if (res.code === '200') {
          this.messages = res.data;
          this.scrollToBottom();  // 滚动条滚动到最底部
          this.setUnReadMessagesAsRead();  // 设置消息为已读
        } else {
          this.$notify.error(res.msg);
        }
      });
    },
    setUnReadNums() {
      request.get('/imsingle/unReadNums?toUsername=' + this.fromUser).then(res => {
        this.unRead = res.data;
        this.totalUnRead = Object.values(this.unRead).reduce((sum, num) => sum + num, 0); // 计算总未读消息数量
        console.log("未读消息数据: ", this.unRead);  // 添加日志
      });
    },
    setUnReadMessagesAsRead() {
      request.post('/imsingle/setRead', {
        fromUser: this.toUser,
        toUser: this.fromUser
      }).then(res => {
        if (res.code === '200') {
          this.loadUnReadNums();  // 更新未读消息数量
        }
      });
    },
    loadUnReadNums() {
      // 查询未读数量
      request.get('/imsingle/unReadNums?toUsername=' + this.fromUser).then(res => {
        this.unRead = res.data;
        this.totalUnRead = Object.values(this.unRead).reduce((sum, num) => sum + num, 0); // 计算总未读消息数量
        console.log("未读消息数据: ", this.unRead);  // 添加日志
      });
    },
    loadUsers() {
      request.get('/user/selectAll').then(res => {
        this.users.student = res.data.filter(v => v.studentflag === '1');
        this.users.notstudent = res.data.filter(v => v.studentflag === '0');
      });

      request.get('/admin/selectAll').then(res => {
        this.users.admin = res.data;
      });

      request.get('/teacher/selectAll').then(res => {
        this.users.teacher = res.data;
      });
    },
    send() {
      if (!this.toUser) {
        this.$notify.error('请选择聊天用户');
        return;
      }
      if (client) {
        let message = this.getMessage('text');
        client.send(JSON.stringify(message));
      }
      document.getElementById('im-content').innerHTML = '';  // 清空输入框
    },
    scrollToBottom() {
      this.$nextTick(() => {
        let imMessageBox = document.getElementsByClassName("im-message-box")[0];
        imMessageBox.scrollTop = imMessageBox.scrollHeight;
      });
    },
    selectToUser(item) {
      this.toUser = item.role + '_' + item.name;
      this.toAvatar = item.avatar;
      this.setUnReadNums();
      this.setUnReadMessagesAsRead(); // 标记为已读
      this.load(); // 加载聊天记录
      this.loadUnReadNums();
    },
    download(file) {
      window.open(file);
    },
    getMessage(type) {
      let inputBox = document.getElementById('im-content');
      const content = inputBox.innerHTML;
      if (!content && type === 'text') {
        this.$notify.error('请输入聊天内容');
        return;
      }
      return {
        fromuser: this.fromUser,
        fromavatar: this.user.avatar,
        touser: this.toUser,
        toavatar: this.toAvatar,
        content: content,
        type: type
      };
    },
    handleFile(file) {
      if (client) {
        let message = this.getMessage('img');
        message.content = file.data;
        let extName = file.data.substring(file.data.lastIndexOf('.') + 1);
        if (['png', 'jpg', 'jpeg', 'gif', 'bmp', 'tiff', 'svg', 'webp'].includes(extName)) {
          message.type = 'img';
        } else {
          message.type = 'file';
        }
        client.send(JSON.stringify(message));
      }
    },
    clickEmoji(emoji) {
      document.getElementById('im-content').innerHTML += emoji;
    },
    goToPerson() {
      if (this.user.role === 'ADMIN') {
        this.$router.push('/adminPerson');
      } else {
        this.$router.push('/front/person');
      }
    },
    logout() {
      localStorage.removeItem('xm-user');
      this.$router.push('/login');
    },
    backToHome() {
      if (this.user.role === 'USER') {
        this.$router.push('/front/home');
      } else {
        this.$router.push('/home');
      }
    }
},
}
</script>



<style scoped>
@import "@/assets/css/chat.css";
.emoji-box {
  height: 200px;
  overflow-y: scroll;
  line-height: 30px;
}

.emoji-box::-webkit-scrollbar{
  width: 4px;
}
.emoji-box::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background: rgba(0, 0, 0, 0.1);
}

.emoji-box::-webkit-scrollbar-track {
  border-radius: 0;
  background: rgba(0, 0, 0, 0.1);
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

.user-list-item-badge {
  background-color: red;
  color: white;
  border-radius: 50%;
  width: 20px;
  height: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 12px;
  margin-left: 10px;
}

</style>
