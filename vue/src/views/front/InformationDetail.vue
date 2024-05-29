<template>
  <div class="main-content" style="background-color: rgba(204,204,204,0.38); padding-bottom: 20px">

    <div>
      <div class="carousel-image-container">
        <el-carousel height="500px">
          <div class="overlay">
            <div>
              <div class="font-title">
                <h2>{{ informationData.name }}</h2>
              </div>
              <p class="font-sub-title">BUPT南太孔院</p>
            </div>
          </div>
            <img :src=informationData.img class="detail-carousel-image">
        </el-carousel>
      </div>
      <div  class="detail-content">
        <div style="width: 80%; margin: 20px auto;">
          <div style="text-align: center;padding-top: 30px">
            <span style="font-size: 20px; font-weight: 550; color: #333333; margin-left: 20px">{{ informationData.name }}</span>
          </div>
          <el-divider></el-divider>
          <div style="margin-top: 20px; background-color: #fafafa">
            <div style="margin-top: 10px; background-color: #fafafa" v-html="informationData.content" class="w-e-text w-e-text-container"></div>
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
    let informationId = this.$route.query.id
    return {
      user: JSON.parse(localStorage.getItem("xm-user") || '{}'),
      informationId: informationId,
      carouselData:[
        require('@/assets/imgs/bupt_sp_bg.png'),
        require('@/assets/imgs/maobi.jpg')
      ],
      informationData:{}
    }
  },
  mounted() {
    this.loadInformation()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadInformation() {
      this.$request.get('/information/selectById/' + this.informationId).then(res => {
        if (res.code === '200') {
          this.informationData = res.data
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
