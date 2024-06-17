<template>
  <div class="main-content">

    <div>
      <div class="carousel-image-container">
        <el-carousel height="600px">
          <div class="overlay">
            <div>
              <h2 class="major-font-title" style="text-align: left">北邮南太孔院信息宣传门户</h2>
              <p class="font-title" style="text-align: left">BUPTSP-Confucius Institute at The University of the South Pacific</p>
            </div>
          </div>
          <el-carousel-item v-for="item in carouselData" :key="item">
            <img :src="item" class="carousel-image">
          </el-carousel-item>
        </el-carousel>
      </div>
      <div style="width: 90%; margin: 50px auto">
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
                <el-carousel-item v-for="course in recommend" :key="course.id" style="box-shadow: 0px 2px 4px rgb(53, 77, 66);border-radius: 10px">
                  <img :src="course.img" class="push-content" alt="" @click="navTo(course.id)">
                  <div class="overflowShow" style="position: relative;bottom: 35px;color: white;background-color: rgba(0, 0, 0, 0.5);padding: 5px;border-radius: 5px;cursor: pointer;" alt="" @click="navTo(course.id)">{{course.name}}</div>
                </el-carousel-item>
              </el-carousel>
<!--              <div style="font-size: 15px;margin-top: 5px" class="overflowShow">更多斐济旅行体验,敬请关注北邮南太孔院blablablablablablablalbla</div>-->
            </div>
            <div class="second-card">
              <el-row :gutter="20">
                <el-col v-for="item in homepageData" :key="item.id" class="card-col">
                  <img :src="item.img" alt="" class="top8Course" @click="navTo(item.id)">
                  <div class="font-sub-title-whitebk overflowShow" @click="navTo(item.id)">
                    {{item.name}}
                  </div>
                </el-col>
              </el-row>
            </div>

          </div>



        </div>
      </div>
      <div style="width: 80%; margin: 50px auto">
        <div class="view-all-channel-button-container" style="display: flex;cursor: pointer;justify-content:flex-end;align-items: center;margin-top: 10px" @click="navToAllCourse()">
          <i class="el-icon-caret-right"></i>
          <div class="font-sub-title-whitebk"><h4>查看全部动态</h4></div>
        </div>
      </div>

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
          <el-row class="channel-row" :gutter="20">
            <el-col class="channel" :span="6" v-for="channel in recommendedChannels" :key="channel.id">
              <el-card class="channel-card" :body-style="{ padding: '0px' }">
                <img :src="channel.img" class="channel-image" @click="navToChannel(channel.channel)">
                <div style="padding: 14px; text-align: center" @click="navToChannel(channel.channel)">
                  <span class="channel-name font-sub-title-whitebk overflowShow"><h4>{{ channel.channel }}</h4></span>
                  <!-- 其他信息展示 -->
                </div>

              </el-card>
            </el-col>
          </el-row>

        </div>

        <div class="view-all-channel-button-container" style="display: flex;cursor: pointer;justify-content:flex-end;align-items: center" @click="navToAllChannel()">
          <i class="el-icon-caret-right"></i>
          <div class="font-sub-title-whitebk"><h4>查看全部专栏</h4></div>
        </div>
      </div>

      <div style="padding-top: 200px;">
        <div class="information-2" style="position: relative;text-align: center;">
          <div class="font-sub-title-whitebk" style="line-height: 50px;">
            <h3 class="font-effect" style=" font-size: xx-large;">#遇见北邮</h3>
          </div>
        </div>
        <div class="editable-area-4">
          <div class="tag-filter" style="margin-bottom: 20px;">
            <el-tag
                v-for="tag in tags"
                :key="tag"
                type="success"
                @click="filterByTag(tag)"
                style="cursor: pointer;margin-right: 10px">
              #{{ tag }}
            </el-tag>
            <el-tag v-if="showResetTag"  type="success" effect="dark" style="cursor: pointer;"  @click="resetTagFilter">重置#</el-tag>
          </div>

        </div>
        <div class="information-recommend-container">
          <div class="information-recommend" style="display: flex; margin-top: 20px; flex-wrap: wrap;">
            <el-card v-for="info in filteredRecommendedInfo" :key="info.id" class="box-card" :body-style="{ padding: '0px' }" style="width: 200px;  border-radius: 15px">
              <img :src="info.img" class="image" @click="openCarousel(info.id)">
              <div style="padding: 14px; text-align: center">
                <span class="font-sub-title-whitebk"><h4>{{ info.name }}</h4></span>
              </div>
            </el-card>
          </div>
        </div>



      </div>
      <div class="view-all-channel-button-container" style="display: flex;cursor: pointer;justify-content:flex-end;align-items: center" @click="navToAllInformation()">
        <i class="el-icon-caret-right"></i>
        <div class="font-sub-title-whitebk" style="margin-bottom: 10px"><h4>查看全部发布</h4></div>
      </div>

      <!--        custom-class="transparent-dialog"-->

      <el-dialog class="info-dialog" :visible.sync="carouselVisible" width="100%" :before-close="handleClose">
        <el-carousel ref="carousel" :interval="4000" arrow="always" style="height: 500px; " type="card">
          <el-carousel-item v-for="(info, index) in recommendedInfo" :key="info.id" style="height: 500px;width: 800px;background-color: #ffffff;border-radius: 15px;">
            <div style="display: flex; height: 100%;">
              <img :src="info.img" style="width: 50%; height: 100%; object-fit: cover; border-bottom-right-radius: 15px;border-top-right-radius: 15px;">
              <div class="carousel-content">
                <h1>{{ info.name }}</h1>
                <div style="display: flex;margin-top: 20px">
                  <p class="font-sub-title-whitebk" style="margin-right: 20px"><strong>时间:</strong> {{ info.addTime }}</p>
                  <p class="font-sub-title-whitebk" style="margin-right: 20px"><strong>发布作者:</strong> {{ info.userName }}</p>
                </div>

                <el-divider></el-divider>
                <div v-html="info.content" class="content-scroll"></div>
              </div>
            </div>
          </el-carousel-item>
        </el-carousel>
      </el-dialog>
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
      //专栏卡片
      recommendedChannels: [], // 存储推荐的栏目及其最新课程信息
      plogData:[],
      recommendedInfo: [],
      filteredRecommendedInfo: [],
      tags: [],
      showResetTag: false,
      selectedTag: null,
      carouselVisible: false,
      currentCarouselIndex: 0,
    }
  },
  mounted() {
    this.loadRecommendedChannels();
    this.loadRecommend();
    this.loadHomepageData();
    this.getData();
    this.loadRecommendedInfo();
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
    loadRecommendedChannels() {
      this.$request.get('/course/getRecommendedChannelsWithLatestCourse').then(res => {
        if (res.code === '200') {
          this.recommendedChannels = res.data;
        } else {
          this.$message.error('加载推荐栏目失败: ' + res.msg);
        }
      });
    },
    loadRecommendedInfo() {
      this.$request.get('/information/selectRecommendedApproved').then(res => {
        if (res.code === '200') {
          this.recommendedInfo = res.data;
          this.filteredRecommendedInfo = this.recommendedInfo;
          this.extractTags();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    navTo(id) {
      // 往课程详情页跳
        location.href = '/front/Detail?id=' + id
    },
    navToAllCourse(){
      location.href = '/front/course'
    },
    navToInformation(id) {
      location.href = '/front/informationDetail?id=' + id
    },
    navToChannel(channelName) {
      this.$router.push({ path: '/front/channel?channel='+channelName, query: { channel: channelName } });
    },
    navToAllChannel(){
      location.href = '/front/channel'
    },
    navToAllInformation(){
      location.href = '/front/information'
    },
    extractTags() {
      const allTags = new Set(this.recommendedInfo.map(info => info.tag));
      this.tags = Array.from(allTags);
    },
    filterByTag(tag) {
      this.selectedTag = tag;
      this.filteredRecommendedInfo = this.recommendedInfo.filter(info => info.tag === tag);
      this.showResetTag = true;
    },
    resetTagFilter() {
      this.selectedTag = null;
      this.filteredRecommendedInfo = this.recommendedInfo;
      this.showResetTag = false;
    },
    getTagType(tag) {
      const types = ['success', 'info', 'warning', 'danger'];
      return types[Math.floor(Math.random() * types.length)];
    },
    openCarousel(id) {
      this.currentCarouselIndex = this.recommendedInfo.findIndex(info => info.id === id);
      this.carouselVisible = true;
      this.$nextTick(() => {
        this.$refs.carousel.setActiveItem(this.currentCarouselIndex);
      });
    },
    handleClose(done) {
      this.carouselVisible = false;
      done();
    },
  }
}
</script>

<style>
@import "@/assets/css/home.css";
.info-dialog .el-dialog{
  background: transparent;
  border: transparent 1px inset;
}
.info-dialog .el-dialog__headerbtn .el-dialog__close{
  color:white;
}
.info-dialog .el-dialog__headerbtn:hover .el-dialog__close{
  color: #b92626;
}

</style>
