<template>

  <div class="container">
    <div class="overlay">
      <div style="text-align: center; width: 38%; padding-top: 500px; padding-left: 6%">
        <p class="font-title" style="text-align: left">欢迎访问</p>
        <h4 class="major-font-title" style="text-align: right">北邮南太孔院宣传门户</h4>

      </div>
    </div>
      <div style="width: 400px; padding: 30px; ; background-color: white; border-radius: 15px;z-index: 1">
        <div style="text-align: center; font-size: 20px; margin-bottom: 20px; color: #333">欢迎访问南太孔院宣传门户</div>
        <el-form :model="form" :rules="rules" ref="formRef">
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" show-password  v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-select v-model="form.role" placeholder="选择登录身份" style="width: 100%">
              <el-option label="管理员" value="ADMIN"></el-option>
              <el-option label="用户" value="USER"></el-option>
              <el-option label="教师" value="TEACHER"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button style="width: 100%; background-color: #467262; border-color: #333; color: white" @click="login">登 录</el-button>
          </el-form-item>
          <div style="display: flex; align-items: center">
            <div style="flex: 1"></div>
            <div style="flex: 1; text-align: right">
              还没有账号？请 <a href="/register" style="color: #467262">注册</a>
            </div>
          </div>
        </el-form>
      </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      form: { },
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ]
      }
    }
  },
  created() {

  },
  methods: {
    login() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          // 验证通过
          this.$request.post('/login', this.form).then(res => {
            if (res.code === '200') {
              localStorage.setItem("xm-user", JSON.stringify(res.data))  // 存储用户数据
              // this.$router.push('/')
              // 跳转主页,统一后台
              if (res.data.role === 'ADMIN') {
                location.href = '/home'
              } else {
                location.href = '/front/home'
              }
              this.$message.success('登录成功')
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.container {
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/imgs/bupt_sp_bg.png");
  background-size: 100%;
  display: flex;
  align-items:center;
  justify-content: center;
  color: #666;
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%; /* 左半侧宽度 */
  height: 100%;
  border-bottom-right-radius:15px;
  border-top-right-radius:15px;
  background-image:linear-gradient(to right, #418160,#FF000000);
  background-size: cover;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  //opacity:0.8;

  z-index: 0;
}

a {
  color: #2a60c9;
}
</style>