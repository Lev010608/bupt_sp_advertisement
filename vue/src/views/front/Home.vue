<template>
  <div class="main-content">

    <div>
      <div class="carousel-image-container">
        <el-carousel height="600px">
          <div class="overlay">
            <div>
              <h2 class="major-font-title" style="text-align: left">北邮南太孔院宣传门户</h2>
              <p class="font-title" style="text-align: left">BUPTSP-Confucius Institute at The University of the South Pacific</p>
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
            <div class="font-title-whitebk">
              <h2>南太平洋大学孔子学院</h2>
              <p>know everything about BUPT-SP</p>
            </div>

            <div>
              <el-tabs @tab-click="handleTabChange">
                  <el-tab-pane label="图文" name="first">图文内容</el-tab-pane>
                  <el-tab-pane label="视频" name="second">视频内容</el-tab-pane>
                <el-tab-pane label="全部" name="all">全部内容</el-tab-pane>
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
<!--              <div style="font-size: 15px;margin-top: 5px" class="overflowShow">更多斐济旅行体验,敬请关注北邮南太孔院blablablablablablablalbla</div>-->
            </div>
            <div class="second-card">
              <el-row :gutter="20">
<!--                一行24份-->
                <el-col :span="6" style="margin-bottom:50px; margin-top:15px" v-for="item in homepageData">
                  <img :src="item.img" alt="" style="width: 100%; height:100px; border-radius: 5px; object-fit: cover;">
                  <div style="color: #333333; margin-top: 10px" class="overflowShow">{{item.name}}</div>
                </el-col>
              </el-row>
            </div>
          </div>
          </div>

<!--        <div>-->
<!--          <h2>体验南太异域风情</h2>-->
<!--          <p>Discover amazing content</p>-->
<!--          <el-divider></el-divider>-->
<!--          <div class="editable-area-2">-->
<!--            <div class="first-card">-->

<!--              <el-row :gutter="20">-->
<!--                &lt;!&ndash;                一行24份&ndash;&gt;-->
<!--                <el-col :span="6" style="margin-bottom:50px; margin-top:15px">-->
<!--                  <img src="@/assets/imgs/test.png" alt="" style="width: 100%; height:100px; border-radius: 5px">-->
<!--                  <div style="color: #333333; margin-top: 10px" class="overflowShow">更多斐济旅行体验,敬请关注北邮南太孔院blablablablablablablalbla</div>-->
<!--                </el-col>-->
<!--              </el-row>-->

<!--            </div>-->
<!--            <div class="second-card">-->

<!--              <el-carousel  :interval="4000" type="card" >-->
<!--                <el-carousel-item v-for="item1 in courseImg" :key="item">-->
<!--                  <img :src="item1" class="push-content">-->
<!--                </el-carousel-item>-->
<!--              </el-carousel>-->
<!--              <div style="font-size: 15px;margin-top: 5px" class="overflowShow">更多斐济旅行体验,敬请关注北邮南太孔院blablablablablablablalbla</div>-->

<!--            </div>-->
<!--          </div>-->
<!--        </div>-->


        <div class="" style="text-align: center">
          <div class="font-sub-title-whitebk">
            <h3>体验南太异域风情</h3>

          </div>

        </div>
        <div style="padding-top: 200px">
          <div class="font-title-whitebk" style="text-align: right">
            <h2>体验南太异域风情</h2>
            <p>Discover amazing content</p>
          </div>
          <el-divider></el-divider>
          <div class="editable-area-3">
          </div>
        </div>

        <div>

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
      type:''
    }
  },
  mounted() {
    this.loadRecommend()
    this.loadHomepageData()
    this.getData()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    initValue(type) {
      this.type = type
      this.getData()
    },
    getData() {
      // 积分专区这边的数据
      if ('VIDEO' === this.type) {
        this.getHomepageData('/course/selectTop8?type=' + this.type)
      }else if('TEXT' === this.type){
        this.getHomepageData('/course/selectTop8?type=' + this.type)
      }else if('ALL' === this.type){
        this.getHomepageData('/course/selectFresh8')
      }
    },
    handleTabChange(tab) {
      if (tab.name === 'first') {
        this.initValue('TEXT');
      } else if (tab.name === 'second') {
        this.initValue('VIDEO');
      }else{
        this.initValue('ALL');
        // this.initValue('/course/selectFresh8')
      }
    },
    getHomepageData(url) {
      this.$request.get(url).then(res => {
        if (res.code === '200') {
          this.homepageData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
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
