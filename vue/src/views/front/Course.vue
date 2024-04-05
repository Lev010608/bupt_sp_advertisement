<template>
  <div class="main-content">
    <div>
      <div>
        <div class="carousel-image-container">
          <el-carousel height="300px">
            <div class="overlay">
              <div class="overlay-content">
                <div>
                  <h2 class="font-title" style="text-align: left">北邮南太孔院宣传门户</h2>
                  <p class="font-sub-title" style="text-align: left">BUPTSP-Confucius Institute at The University of the South Pacific</p>
                </div>
              </div>
            </div>
            <el-carousel-item v-for="item in carouselData" :key="item">
              <img :src="item" class="course-carousel-image">
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>

      <div style="width: 80%; margin: 50px auto ;">
        <div style="display: flex">
          <div class="search" style="margin-bottom: 20px">
            <el-input placeholder="请输入内容/内容/课程名称" style="width: 200px" size="mini" v-model="name"></el-input>
            <el-button class="button-style" plain style="margin-left: 10px !important;" @click="load(1)">查询</el-button>
            <el-button class="button-style" plain style="margin-left: 10px !important;" @click="reset">重置</el-button>
          </div>
          <div  class="type-select" style="padding-left: 60%">
            <el-select v-model="value" placeholder="选择内容类型"  @change="filterData">
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
            <el-button class="button-style" plain style="margin-left: 10px; !important;" @click="resetTypeFilter">重置</el-button>
          </div>
        </div>
        <div class="table" >
          <el-table class="custom-table" :data="filteredTableData" stripe>
            <el-table-column  class="custom-table__expanded-cell" prop="img" show-overflow-tooltip>
              <template v-slot="scope">
                <div style="display: flex; align-items: center; min-height: 80px">
                  <el-image style="width: 120px; height: 80px; border-radius: 5px; object-fit: cover; border: 1px solid #cccccc" v-if="scope.row.img"
                            :src="scope.row.img" :preview-src-list="[scope.row.img]"></el-image>
                </div>
              </template>
            </el-table-column>
            <el-table-column  class="custom-table__expanded-cell" prop="name" show-overflow-tooltip width="600">
              <template v-slot="scope">
                <a :href="'/front/Detail?id=' + scope.row.id" style="color: rgba(51,51,51,0.78);font-size: 15px">{{ scope.row.name }}</a>
              </template>
            </el-table-column>
            <el-table-column class="custom-table__expanded-cell"  prop="type">
              <template v-slot="scope">
                <span v-if="scope.row.type === 'VIDEO'" style="color: #7fa0df">视频</span>
                <span v-else style="color: #4fa977">图文</span>
              </template>
            </el-table-column>
            <el-table-column class="custom-table__expanded-cell" prop="addTime" label="发布时间" ></el-table-column>

          </el-table>

          <div class="pagination" style="margin-top: 40px; text-align: center">
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
      // type:'',
      value:null, //内容类型选择器的值
      filteredTableData: [], // 过滤后的数据

      carouselData:[
        require('@/assets/imgs/bupt_sp_bg.png'),
        require('@/assets/imgs/maobi.jpg')
      ],
      options: [{
        value: '选项1',
        label: '视频资源'
      }, {
        value: '选项2',
        label: '图文资源'
      }],
    }
  },
  mounted() {
    this.load(1)
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
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
        this.filterData() // 在加载完数据后立即进行一次过滤
      })
    },
    reset() {
      this.name = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
    filterData() {
      if (this.value === '选项1') { // 选择器选择了视频资源
        this.filteredTableData = this.tableData.filter(item => item.type === 'VIDEO')
      } else if (this.value === '选项2') { // 选择器选择了图文资源
        this.filteredTableData = this.tableData.filter(item => item.type === 'TEXT')
      } else { // 选择器未选择或者选择了其他选项
        this.filteredTableData = [...this.tableData]
      }
    },
    resetTypeFilter(){
      this.value = null
      this.filterData()
    }

  }
}
</script>

<style scoped>
@import "@/assets/css/course.css";
@import "@/assets/css/global.css";


.button-style{
  margin-left: 10px;
  background-color: #9fd0b6;
  color: #698173;
}

.button-style:hover{
  margin-left: 10px;
  background-color: #467262;
  border-color: #467262;
  color: rgb(255, 255, 255)
}

.el-button:focus {
  margin-left: 10px;
  background-color: #9fd0b6;
  color: #698173;
  border-color:#f0f0f0;
}

/deep/.pagination .is-background .el-pager li:not(.disabled).active {
  background-color: #9fd0b6; /* 按钮背景颜色 */
  cursor: pointer; /* 鼠标指针样式为手型 */
}

/deep/.pagination .is-background .el-pager li:hover {
  background-color: #467262; /* 按钮背景颜色 */
  cursor: pointer; /* 鼠标指针样式为手型 */
  color: #fafafa;
}


/* 自定义分页按钮悬停时的样式 */
/deep/.pagination .el-pagination button:hover {
  background-color: #467262; /* 按钮悬停时背景颜色 */
}
/deep/  .custom-table {
  border-radius: 15px;
}
</style>