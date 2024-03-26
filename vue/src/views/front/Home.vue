<template>
  <div class="main-content">

    <div>
      <div class="carousel-image-container">
        <el-carousel height="600px">
          <div class="overlay">
            <div class="overlay-content">
              <h2>Welcome to my website</h2>
              <p>Discover amazing content</p>
            </div>
          </div>
          <el-carousel-item v-for="item in carouselData" :key="item">
            <img :src="item" class="carousel-image">
          </el-carousel-item>
        </el-carousel>
      </div>
      <div style="width: 80%; margin: 50px auto">
        <div>
          <div >
            <div><h2>体验南太异域风情</h2>
              <p>Discover amazing content</p>
            </div>

            <div style="">
              <el-tabs>
                <el-tab-pane label="图文内容" name="first">图文内容</el-tab-pane>
                <el-tab-pane label="视频内容" name="second">视频内容</el-tab-pane>
              </el-tabs>
            </div>
          </div>

          <div class="editable-area-1" >
            <div class="first-card">
              <el-carousel  :interval="4000" type="card" >
                <el-carousel-item v-for="course in recommend" :key="course.id">
                  <img :src="course.img" class="push-content">
                  <div class="overflowShow" style="
                  position: relative;
  bottom: 35px;
  color: white;
  background-color: rgba(0, 0, 0, 0.5);
  padding: 5px;
  border-radius: 5px;
">{{course.name}}</div>
                </el-carousel-item>
              </el-carousel>
              <div style="font-size: 15px;margin-top: 5px" class="overflowShow">更多斐济旅行体验,敬请关注北邮南太孔院blablablablablablablalbla</div>
            </div>
            <div class="second-card">
              <el-row :gutter="20">
<!--                一行24份-->
                <el-col :span="6" style="margin-bottom:50px; margin-top:15px" v-for="item in homepageData">
                  <img :src="item.img" alt="" style="width: 100%; height:100px; border-radius: 5px">
                  <div style="color: #333333; margin-top: 10px" class="overflowShow">{{item.name}}</div>
                </el-col>
              </el-row>
            </div>
          </div>
          </div>

        <div>
          <h2>体验南太异域风情</h2>
          <p>Discover amazing content</p>
          <el-divider></el-divider>
          <div class="editable-area-2">
            <div class="first-card">

              <el-row :gutter="20">
                <!--                一行24份-->
                <el-col :span="6" style="margin-bottom:50px; margin-top:15px">
                  <img src="@/assets/imgs/test.png" alt="" style="width: 100%; height:100px; border-radius: 5px">
                  <div style="color: #333333; margin-top: 10px" class="overflowShow">更多斐济旅行体验,敬请关注北邮南太孔院blablablablablablablalbla</div>
                </el-col>
              </el-row>

            </div>
            <div class="second-card">

              <el-carousel  :interval="4000" type="card" >
                <el-carousel-item v-for="item1 in courseImg" :key="item">
                  <img :src="item1" class="push-content">
                </el-carousel-item>
              </el-carousel>
              <div style="font-size: 15px;margin-top: 5px" class="overflowShow">更多斐济旅行体验,敬请关注北邮南太孔院blablablablablablablalbla</div>

            </div>
          </div>
        </div>

        </div>

      </div>

    </div>
</template>

<script>

export default {

  data() {
    return {
      carouselData:[
          require('@/assets/imgs/bupt_sp_bg.png'),
          require('@/assets/imgs/maobi.jpg')
      ],
      courseImg:[
        require('@/assets/imgs/view1.jpg'),
        require('@/assets/imgs/view2.jpg'),
        require('@/assets/imgs/view3.jpg')
      ],
      recommend:[],
      homepageData:[],
    }
  },
  mounted() {
    this.loadRecommend()
    this.loadHomepageData()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadRecommend(){
      this.$request.get('/course/getRecommend').then(res =>{
        if(res.code === '200'){
          this.recommend = res.data
        }else {
          this.$message.error(res.msg)
        }
      });
    },
    loadHomepageData(){
      this.$request.get('/course/selectFresh8',{
        params:{
          recommend:'否'
        }
      }).then(res =>{
        if (res.code === '200'){
          this.homepageData = res.data
        }else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
@import "@/assets/css/home.css";
</style>
