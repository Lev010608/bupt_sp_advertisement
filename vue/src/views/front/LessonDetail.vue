<template>
  <div class="main-content" style="background-color: rgba(204,204,204,0.38); padding-bottom: 20px">

    <div>
      <div class="carousel-image-container">
        <el-carousel height="500px">
          <div class="overlay">
            <div>
              <div class="font-title">
                <h2>{{ lessonData.name }}</h2>
              </div>
              <p class="font-sub-title">BUPT南太孔院</p>
            </div>
          </div>
            <img :src=lessonData.img class="detail-carousel-image">
        </el-carousel>
      </div>
      <div  class="detail-content">
        <div style="width: 80%; margin: 20px auto;">
          <div style="text-align: center;padding-top: 30px">
            <span style="font-size: 20px; font-weight: 550; color: #333333; margin-left: 20px">{{ lessonData.name }}</span>
          </div>
          <el-divider></el-divider>
          <!--   课程视频区域   -->
          <div>
            <div style="text-align: center">
              <video :src="lessonData.video" v-if="lessonData.type === 'VIDEO'" controls style="width: 65%; height: 400px; "></video>
            </div>
          </div>
          <!--   课程介绍区域   -->
          <div style="margin-top: 20px; background-color: #fafafa">
            <div style="margin-top: 10px; background-color: #fafafa" v-html="lessonData.content" class="w-e-text w-e-text-container"></div>
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
    let lessonId = this.$route.query.id
    return {
      user: JSON.parse(localStorage.getItem("xm-user") || '{}'),
      lessonId: lessonId,
      carouselData:[
        require('@/assets/imgs/bupt_sp_bg.png'),
        require('@/assets/imgs/maobi.jpg')
      ],
      lessonData:{}
    }
  },
  mounted() {
    this.loadLesson()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadLesson() {
      this.$request.get('/lesson/selectById/' + this.lessonId).then(res => {
        if (res.code === '200') {
          this.lessonData = res.data
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
