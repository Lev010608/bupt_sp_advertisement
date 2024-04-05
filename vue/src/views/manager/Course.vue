<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入内容名称" style="width: 200px" v-model="name"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="operation">
      <el-button type="primary" plain @click="handleAdd">新增</el-button>
      <el-button type="danger" plain @click="delBatch">批量删除</el-button>
    </div>

    <div class="table" >
      <el-table   :data="tableData" stripe  @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="img" label="内容封面" show-overflow-tooltip>
          <template v-slot="scope">
            <div style="display: flex; align-items: center">
              <el-image style="width: 60px; height: 40px; border-radius: 10px" v-if="scope.row.img"
                        :src="scope.row.img" :preview-src-list="[scope.row.img]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="内容名称" show-overflow-tooltip></el-table-column>
        <el-table-column prop="content" label="内容" show-overflow-tooltip>
          <template v-slot="scope">
            <el-button type="success" size="mini" @click="viewDataInit(scope.row.content)">点击查看</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="type" label="内容类型"></el-table-column>
        <el-table-column prop="video" label="内容视频" show-overflow-tooltip>
          <template v-slot="scope">
            <el-button type="warning" size="mini" @click="down(scope.row.video)" v-if="scope.row.type === 'VIDEO'">点击下载</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="file" label="内容资料" show-overflow-tooltip></el-table-column>
        <el-table-column prop="recommend" label="是否推荐"></el-table-column>
        <el-table-column prop="channel" label="栏目"></el-table-column>
        <el-table-column prop="channelRecommend" label="栏目是否推荐"></el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template v-slot="scope">
            <el-button plain type="primary" @click="handleEdit(scope.row)" size="mini">编辑</el-button>
            <el-button plain type="danger" size="mini" @click=del(scope.row.id)>删除</el-button>
          </template>
        </el-table-column>
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

    <div class="channel-manage">
      <div class="channel-manage-content" style="margin: 20px auto;padding: 20px 20px 20px 20px">
        <h3 class="channel-manage-title">栏目首页推送管理</h3>
        <p class="channel-manage-note">最多推送四个栏目至首页</p>
        <el-divider></el-divider>
        <el-checkbox-group v-model="selectedChannels" @change="updateChannelRecommend">
          <el-checkbox v-for="channel in channels" :label="channel" :key="channel" :disabled="selectedChannels.length >= 4 && !selectedChannels.includes(channel)">
            {{ channel }}
          </el-checkbox>
        </el-checkbox-group>
      </div>
    </div>


    <el-dialog title="内容信息" :visible.sync="fromVisible" width="55%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" :model="form" :rules="rules" ref="formRef">
        <el-form-item label="内容封面">
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :headers="{ token: user.token }"
              list-type="picture"
              :on-success="handleImgSuccess"
          >
            <el-button type="primary">上传图片</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item prop="name" label="内容名称">
          <el-input v-model="form.name" autocomplete="off" placeholder="请输入内容名称"></el-input>
        </el-form-item>
        <el-form-item prop="type" label="内容类型">
          <el-select v-model="form.type" placeholder="请选择类型" style="width: 100%">
            <el-option label="视频内容" value="VIDEO"></el-option>
            <el-option label="图文内容" value="TEXT"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="recommend" label="是否推荐">
          <el-select v-model="form.recommend" placeholder="请选择" style="width: 100%" @change="handleRecommendChange">
            <el-option label="是" value="是"></el-option>
            <el-option label="否" value="否"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设置栏目">
          <el-select v-model="selectedChannel" placeholder="请选择" style="width: 100%" @change="handleChannelChange">
            <el-option v-for="channel in channels" :key="channel" :label="channel" :value="channel"></el-option>
            <el-option label="编辑栏目" value="newChannel"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="channel" label="栏目名称" v-if="showChannelInput">
          <el-option v-for="channel in channels" :key="channel" :label="channel" :value="channel"></el-option>
          <el-input v-model="form.channel" autocomplete="off" placeholder="请输入栏目名称"></el-input>
        </el-form-item>
        <el-form-item label="内容视频">
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :headers="{ token: user.token }"
              :on-success="handleVideoSuccess"
          >
            <el-button type="primary">上传视频(视频内容需要传)</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item prop="file" label="资料链接">
          <el-input v-model="form.file" autocomplete="off" placeholder="请输入资料链接"></el-input>
        </el-form-item>
        <el-form-item prop="content" label="内容介绍">
          <div id="editor"></div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="内容" :visible.sync="editorVisible" width="50%" :close-on-click-modal="false" destroy-on-close>
      <div v-html="viewData" class="w-e-text w-e-text-container"></div>
    </el-dialog>

  </div>
</template>

<script>
import E from 'wangeditor'
export default {
  name: "Course",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      name: null,
      fromVisible: false,
      editorVisible:false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      ids: [],
      channels: [],        // 栏目列表
      selectedChannel: '', // 用户选择的栏目
      showChannelInput: false, // 是否显示栏目输入框
      selectedChannels: [], // 被选中的栏目

      //表单规则
      rules: {
        name: [
          {required: true, message: '请输入内容名称', trigger: 'blur'},
        ],
        type: [
          {required: true, message: '请选择内容类型', trigger: 'blur'},
        ],
        recommend: [
          {required: true, message: '请选择是否推荐', trigger: 'blur'},
        ],
      },

    }
  },
  created() {
    this.load(1)
    this.extractChannelsFromTableData() // 从 tableData 中提取栏目数据
    this.loadRecommendedChannels(); // 加载已推荐栏目
  },
  methods: {
    initWangEditor(content) {
      this.$nextTick(() => {
        this.editor = new E('#editor')
        this.editor.config.placeholder = '请输入内容'
        this.editor.config.uploadFileName = 'file'
        this.editor.config.uploadImgServer = 'http://localhost:9090/files/wang/upload'
        this.editor.create()
        setTimeout(() => {
          this.editor.txt.html(content)
        })
      })
    },
    handleAdd() {   // 新增数据
      this.form = {}  // 新增数据的时候清空数据
      this.fromVisible = true   // 打开弹窗
      this.initWangEditor('')
    },
    handleEdit(row) {   // 编辑数据
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.fromVisible = true   // 打开弹窗
      // 设置选择器的值为当前栏目名称或“编辑栏目”
      this.selectedChannel = row.channel ? row.channel : '';
      this.showChannelInput = false; // 默认不显示栏目名称输入框
      this.initWangEditor(this.form.content || '')
    },
    isRecommendFull() {  // 判断推荐位是否已满
      // 假设推荐位上限为3
      const recommendLimit = 3
      // 统计当前已推荐课程数量
      const recommendedCount = this.tableData.filter(item => item.recommend === '是').length
      // 如果已推荐课程数量达到上限，返回true；否则返回false
      return recommendedCount >= recommendLimit
    },
    handleRecommendChange() {
      if (this.form.recommend === '是') {
        // 判断推荐位是否已满
        if (this.isRecommendFull()) {
          // 推荐位已满，弹出提醒框
          this.$confirm('您已达到推荐课程上限（3个），是否继续推荐？如果选择`继续`将取消推荐最早推荐的课程', '推荐课程上限', {
            confirmButtonText: '继续',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            // 继续推荐，不执行任何操作
          }).catch(() => {
            // 取消推荐，将下拉框选项设为“否”
            this.form.recommend = '否'
          })
        }
      }
    },
    // 从 tableData 中提取栏目数据
    extractChannelsFromTableData() {
      const uniqueChannels = [...new Set(this.tableData.map(item => item.channel).filter(channel => channel))];
      this.channels = uniqueChannels;
    },
    // 栏目选择发生变化时的处理函数
    handleChannelChange(value) {
      if (value === 'newChannel') {
        this.showChannelInput = true;
        // this.form.channel = this.form.channel || '';
        // this.$nextTick(() => {
        //   // 在下一个 Vue tick 后聚焦输入框
        //   this.$refs.channelInput.focus();
        // });
      } else {
        this.showChannelInput = false;
        this.form.channel = value; // 将选择的栏目名称设置到form.channel中
      }
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.form.content = this.editor.txt.html();
          // Step 1: 检查栏目是否已存在
          if (this.channels.includes(this.form.channel)) {
            // Step 2: 栏目已存在，获取栏目推荐状态
            this.$request.get('/course/channelRecommendStatus', {
              params: {
                channel: this.form.channel,
              }
            }).then(res => {
              if (res.code === '200') {
                // 应用栏目的推荐状态到课程
                console.log("添加的栏目已经能够查到")
                console.log(res.data)
                this.form.channelRecommend = res.data === '1' ? '1' : null;
                this.updateCourse();
              } else {
                this.$message.error('获取栏目推荐状态失败');
              }
            })
          } else {
            console.log("添加的栏目为空或为新的栏目")
            // Step 3: 新栏目或清空栏目名，设置channelRecommend为空
            this.form.channelRecommend = null;
            this.updateCourse();
          }
          console.log(this.form.channelRecommend)
        }
      })
    },
    updateCourse() {
      this.$request({
        url: this.form.id ? '/course/update' : '/course/add',
        method: this.form.id ? 'PUT' : 'POST',
        data: this.form
      }).then(res => {
        if (res.code === '200') {  // 表示成功保存
          console.log(this.form)
          this.$message.success('保存成功');
          this.load(1);
          this.fromVisible = false;
          this.selectedChannel = ''; // 重置选择器到默认状态
          this.showChannelInput = false; // 隐藏栏目名称输入框
        } else {
          this.$message.error(res.msg);  // 弹出错误的信息
        }
      });
    },
    viewDataInit(data) {
      this.viewData = data
      this.editorVisible = true
    },
    viewData(data) {
      this.viewData = data
      this.editorVisible = true
    },
    del(id) {   // 单个删除
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/course/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.ids = rows.map(v => v.id)   //  [1,2]
    },
    loadRecommendedChannels() {
      this.$request.get('/course/recommendedChannels').then(res => {
        if (res.code === '200') {
          this.selectedChannels = res.data;
        }
      });
    },
    updateChannelRecommend(){
// 处理栏目推荐的逻辑
      if (this.selectedChannels.length > 4) {
        this.$message.warning('最多只能推送四个栏目至首页');
        return;
      }
      this.$request.put('/course/updateChannelRecommend', {
        channels: this.selectedChannels,
        channelRecommend: true
      }).then(res => {
        if (res.code !== '200') {
          this.$message.error('更新栏目推荐状态失败');
        }
      });
      // 处理取消推荐的栏目
      const channelsToUnrecommend = this.channels.filter(channel => !this.selectedChannels.includes(channel));
      if (channelsToUnrecommend.length > 0){
        this.$request.put('/course/updateChannelRecommend', {
          channels: channelsToUnrecommend,
          channelRecommend: false
        }).then(res => {
          if (res.code !== '200') {
            this.$message.error('更新栏目取消推荐状态失败');
          }
        });
      }
    },
    delBatch() {   // 批量删除
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/course/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
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
        this.extractChannelsFromTableData();
      })
    },
    data() {
      return {
        editor: null,
        viewData: null,
        editorVisible: false,
      }
    },
    reset() {
      this.name = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
    handleImgSuccess(res) {
      this.form.img = res.data
    },
    handleVideoSuccess(res) {
      this.form.video = res.data
    },
    down(url) {
      location.href = url
    }
  }
}
</script>

<style scoped>
.search,
.operation,
.table,
.channel-manage {
  background-color: #ffffff; /* 为这些块设置白色背景 */
  margin: 20px auto; /* 在块之间增加一些边距 */
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* 添加阴影效果 */
}

.channel-manage-title {
  font-size: 18px;
  margin-bottom: 10px;
}

.channel-manage-note {
  color: #999999;
  margin-bottom: 20px;
}


</style>