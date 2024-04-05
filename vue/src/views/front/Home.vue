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
                  <img :src="course.img" class="push-content" alt="" @click="navTo(course.id)">
                  <div class="overflowShow" style="
                  position: relative;
  bottom: 35px;
  color: white;
  background-color: rgba(0, 0, 0, 0.5);
  padding: 5px;
  border-radius: 5px;
  cursor: pointer;
" alt="" @click="navTo(course.id)">{{course.name}}</div>
                </el-carousel-item>
              </el-carousel>
<!--              <div style="font-size: 15px;margin-top: 5px" class="overflowShow">更多斐济旅行体验,敬请关注北邮南太孔院blablablablablablablalbla</div>-->
            </div>
            <div class="second-card">
              <el-row :gutter="20">
<!--                一行24份-->
                <el-col :span="6" style="margin-bottom:50px; margin-top:15px" v-for="item in homepageData">
                  <img :src="item.img" alt="" style="width: 100%; height:100px; border-radius: 5px; object-fit: cover;cursor: pointer" @click="navTo(item.id)">
                  <div style="color: #333333; margin-top: 10px;cursor: pointer;" class="overflowShow" alt="" @click="navTo(item.id)">{{item.name}}</div>
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


        <div class="information" style="position: relative;text-align: center;padding-top: 100px;margin-top: 200px">
          <div class="font-sub-title-whitebk" style="line-height: 50px;">
            <h3 class="font-effect" style="color: #545353; font-size: xx-large;">北京邮电大学南太平洋大学孔子学院</h3>
            <h3 class="font-effect" style="color: #545353; font-size: x-large; transition: transform 0.3s;">致力于推广跨文化交流与中文国际教学</h3>
            <p class="font-effect">目前，于南太平洋大学孔子学院形成本部下设三个孔子课堂</p>
            <P class="font-effect">以“三国四地”的办学格局</P>
            <p class="font-effect">积极在南太平洋地区开展中文教学和各类文化交流活动</p>
            <h3 class="font-effect" style="font-size: xx-large;">成为推动南太平洋岛国中文教育的重要力量</h3>
            <!-- 半透明色块 -->
<!--            position: absolute; left: 20%; top: 80px; width: 200px; height: 100px; -->
            <div style="z-index: 100">
              <div class="block-overlay" style="background-color: rgba(205,217,208,0.3);left: 32%;top: 15%;"></div>
              <div class="block-overlay" style="background-color: rgba(176,210,191,0.55);left: 30%;top: 10%"></div>
              <div class="block-overlay" style="background-color: rgba(205,217,208,0.3);left: 18%;top: 85%;"></div>
              <div class="block-overlay" style="background-color: rgba(176,210,191,0.55);left: 20%;top: 90%"></div>
              <div class="block-overlay" style="background-color: rgba(205,217,208,0.3);left: 62%;top: 85%;"></div>
              <div class="block-overlay" style="background-color: rgba(122,175,144,0.41);left: 60%;top: 80%"></div>
              <div class="block-overlay" style="background-color: rgba(205,217,208,0.3);left: 68%;top: 0%;"></div>
              <div class="block-overlay" style="background-color: rgba(122,175,144,0.41);left: 70%;top: 5%"></div>
            </div>
          </div>
        </div>
        <div style="padding-top: 200px">
          <div class="font-title-whitebk" style="text-align: right">
            <h2>体验南太异域风情</h2>
            <p>Discover amazing content</p>
          </div>
          <el-divider></el-divider>
          <div class="editable-area-3">
            <el-row :gutter="20">
              <el-col class="channel" :span="6" v-for="(o, index) in 4" :key="index">
                <el-card class="channel-card" :body-style="{ padding: '0px' }">
                  <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="channel-image">
                  <div style="padding: 14px;text-align: center">
                    <span>好吃的汉堡</span>
                    <div class="bottom clearfix">
                    </div>
                  </div>
                </el-card>
              </el-col>
            </el-row>
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
      type:'',
      //专栏轮播图
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
    },
    navTo(id) {
      // 往课程详情页跳
        location.href = '/front/Detail?id=' + id
      // if (this.type === 'SCORE') {
      //   // 我们往积分课程详情页跳
      //   location.href = '/front/scoreDetail?id=' + id
      // } else {
      //   // 往课程详情页跳
      //   location.href = '/front/Detail?id=' + id
      // }
    },
    navToInformation(id) {
      location.href = '/front/informationDetail?id=' + id
    },
  }
}
</script>

<style scoped>
@import "@/assets/css/home.css";
</style>
