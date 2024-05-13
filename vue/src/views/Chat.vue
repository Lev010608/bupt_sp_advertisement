<template>
  <div class="chat-container">
    <el-container>
      <!-- 左侧联系人栏 -->
      <el-aside width="300px">
        <el-menu default-active="1" class="user-list">
          <el-menu-item index="1">
            <i class="el-icon-user-solid"></i>
            管理员
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-user-solid"></i>
            老师
          </el-menu-item>
          <el-menu-item index="3">
            <i class="el-icon-user"></i>
            用户
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-chat-dot-round"></i>
            群聊
          </el-menu-item>
        </el-menu>
      </el-aside>

      <!-- 右侧聊天区域 -->
      <el-main>
        <!-- 聊天内容显示区 -->
        <div class="chat-messages">
          <el-card class="message" v-for="msg in messages" :key="msg.id">
            <div>{{ msg.name }}: {{ msg.content }}</div>
          </el-card>
        </div>

        <!-- 消息输入区 -->
        <div class="message-input">
          <el-input
              type="textarea"
              v-model="newMessage"
              placeholder="输入消息..."
              class="input-area"
          />
          <div class="controls">
            <el-button icon="el-icon-face-smile" @click="selectEmoji">表情</el-button>
            <el-upload action="" class="upload-btn">
              <el-button icon="el-icon-upload2">文件</el-button>
            </el-upload>
            <el-button type="primary" @click="sendMessage">发送</el-button>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Chat",
  data() {
    return {
      newMessage: '',
      messages: [
        { id: 1, name: '管理员', content: '欢迎大家加入聊天室！' },
        { id: 2, name: '老师', content: '请大家遵守聊天室规则。' },
      ],
    };
  },
  methods: {
    sendMessage() {
      if (this.newMessage.trim()) {
        this.messages.push({
          id: this.messages.length + 1,
          name: '用户', // 这里可以根据实际情况调整发送者
          content: this.newMessage,
        });
        this.newMessage = '';
      }
    },
    selectEmoji() {
      // 这里可以添加选择表情包的逻辑
      alert('选择表情功能尚未实现');
    },
  },
}
</script>



<style scoped>
.chat-container {
  height: 100vh;
  display: flex;
}

.user-list {
  height: 100%;
  border-right: 1px solid #eee;
}

.chat-messages {
  height: calc(100vh - 150px);
  overflow-y: auto;
}

.message-input {
  display: flex;
  flex-direction: column;
}

.input-area {
  margin-bottom: 10px;
}

.controls {
  display: flex;
  justify-content: space-between;
}

</style>