<template>
  <div class="main-content">
    <div>
      <div>
        <div class="carousel-image-container">
          <el-carousel height="300px">
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
      </div>

      <div style="width: 80%; margin: 50px auto ">
        <div>
          <div class="search" style="margin-bottom: 20px">
            <el-input placeholder="请输入内容/内容/课程名称" style="width: 200px" size="mini" v-model="name"></el-input>
            <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
            <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
            <el-button type="warning" plain style="margin-left: 10px" @click="initValue('VIDEO')">仅查看视频</el-button>
            <el-button type="warning" plain style="margin-left: 10px" @click="initValue('TEXT')">仅查看图文</el-button>
          </div>
        </div>
        <div class="table">
          <el-table  :data="tableData" stripe>
            <el-table-column prop="img" label="内容/课程封面" show-overflow-tooltip>
              <template v-slot="scope">
                <div style="display: flex; align-items: center">
                  <el-image style="width: 120px; height: 80px; border-radius: 5px; object-fit: cover; border: 1px solid #cccccc" v-if="scope.row.img"
                            :src="scope.row.img" :preview-src-list="[scope.row.img]"></el-image>
                </div>
              </template>
            </el-table-column>
            <el-table-column   prop="name" label="内容/课程名称" show-overflow-tooltip width="600">
              <template v-slot="scope">
                <a :href="'/front/Detail?id=' + scope.row.id">{{ scope.row.name }}</a>
              </template>
            </el-table-column>
            <el-table-column   prop="type" label="类型" >
              <template v-slot="scope">
                <span v-if="scope.row.type === 'VIDEO'" style="color: #7fa0df">视频</span>
                <span v-else style="color: #4fa977">图文</span>
              </template>
            </el-table-column>
            <el-table-column prop="addTime" label="发布时间" ></el-table-column>

          </el-table>

          <div class="pagination">
            <el-pagination
                background
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[5, 10, 20]"
                :page-size="pageSize"
                layout="total, prev, pager, next"
                :total="total">
            </el-pagination>
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
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 8,  // 每页显示的个数
      total: 0,
      name: null,
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      type:'',

      carouselData:[
        require('@/assets/imgs/bupt_sp_bg.png'),
        require('@/assets/imgs/maobi.jpg')
      ],
    }
  },
  mounted() {
    this.load(1)
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
        console.log('VIDEO')

        // this.getHomepageData('/course/selectTop8?type=' + this.type)
      }else if('TEXT' === this.type){
        // this.getHomepageData('/course/selectTop8?type=' + this.type)
      }else if('ALL' === this.type){
        // this.getHomepageData('/course/selectFresh8')
      }
    },
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/course/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    reset() {
      this.name = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },

  }
}
</script>

<style scoped>
@import "@/assets/css/course.css";
</style>
