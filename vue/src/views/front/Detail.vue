<template>
  <div class="main-content" style="background-color: rgba(204,204,204,0.38); padding-bottom: 20px">

    <div>
      <div class="carousel-image-container">
        <el-carousel height="500px">
          <div class="overlay">
            <div>
              <div class="font-title">
                <h2>{{ courseData.name }}</h2>
              </div>
              <p class="font-sub-title">BUPT南太孔院</p>
            </div>
          </div>
            <img :src=courseData.img class="detail-carousel-image">
        </el-carousel>
      </div>
      <div  class="detail-content">
        <div style="width: 80%; margin: 20px auto;">
          <div style="text-align: center;padding-top: 30px">
            <span style="font-size: 20px; font-weight: 550; color: #333333; margin-left: 20px">{{ courseData.name }}</span>
          </div>
          <div style="text-align: left; padding-top: 40px; ">
            <span style="color: #666666; margin-left: 50px">发布时间：{{ courseData.addTime }}</span>
            <span style="color: #666666; margin-left: 50px" v-if="courseData.file">
              相关链接：
              <a :href="courseData.file" target="_blank" style="color: #7aaf90; text-decoration: underline;">
                {{ courseData.file }}
              </a>
            </span>
          </div>
          <el-divider></el-divider>
          <!--   课程视频区域   -->
          <div>
            <div style="text-align: center">
              <video :src="courseData.video" v-if="courseData.type === 'VIDEO'" controls style="width: 65%; height: 400px; "></video>
            </div>
          </div>
          <!--   课程介绍区域   -->
          <div style="margin-top: 20px; background-color: #fafafa">
            <div style="margin-top: 10px; background-color: #fafafa" v-html="courseData.content" class="w-e-text w-e-text-container"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import E from 'wangeditor'

export default {

  data() {
    let courseId = this.$route.query.id
    return {
      user: JSON.parse(localStorage.getItem("xm-user") || '{}'),
      courseId: courseId,
      carouselData:[
        require('@/assets/imgs/bupt_sp_bg.png'),
        require('@/assets/imgs/maobi.jpg')
      ],
      courseData:{}
    }
  },
  mounted() {
    this.loadCourse()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadCourse() {
      this.$request.get('/course/selectById/' + this.courseId).then(res => {
        if (res.code === '200') {
          this.courseData = res.data
          console.log(this.courseData)
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
@import "@/assets/css/detail.css";
</style>
