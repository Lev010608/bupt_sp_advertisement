<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入课件名称" style="width: 200px" v-model="name"></el-input>
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
        <el-table-column prop="img" label="课件封面" show-overflow-tooltip>
          <template v-slot="scope">
            <div style="display: flex; align-items: center">
              <el-image style="width: 60px; height: 40px; border-radius: 10px" v-if="scope.row.img"
                        :src="scope.row.img" :preview-src-list="[scope.row.img]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="课件名称" show-overflow-tooltip></el-table-column>
        <el-table-column prop="content" label="课件内容" show-overflow-tooltip>
          <template v-slot="scope">
            <el-button type="success" size="mini" @click="viewDataInit(scope.row.content)">点击查看</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="type" label="课件类型"></el-table-column>
        <el-table-column prop="video" label="课件视频" show-overflow-tooltip>
          <template v-slot="scope">
            <el-button type="warning" size="mini" @click="down(scope.row.video)" v-if="scope.row.type === 'VIDEO'">点击下载</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="file" label="课件资料" show-overflow-tooltip></el-table-column>
        <el-table-column prop="collegeName" label="所属学院" show-overflow-tooltip></el-table-column>
        <el-table-column prop="majorName" label="所属专业" show-overflow-tooltip></el-table-column>
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


    <el-dialog title="课件信息" :visible.sync="fromVisible" width="55%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" :model="form" :rules="rules" ref="formRef">
        <el-form-item label="课件封面">
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/lesson/upload'"
              :headers="{ token: user.token }"
              list-type="picture"
              :on-success="handleImgSuccess"
          >
            <el-button type="primary">上传图片</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item prop="name" label="课件名称">
          <el-input v-model="form.name" autocomplete="off" placeholder="请输入课件名称"></el-input>
        </el-form-item>
        <el-form-item prop="type" label="课件类型">
          <el-select v-model="form.type" placeholder="请选择类型" style="width: 100%">
            <el-option label="视频内容" value="VIDEO"></el-option>
            <el-option label="图文内容" value="TEXT"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="课件视频" v-if="form.type === 'VIDEO'">
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/lesson/upload'"
              :headers="{ token: user.token }"
              :on-success="handleVideoSuccess"
          >
            <el-button type="primary">上传视频(视频内容需要传)</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item prop="file" label="资料链接">
          <el-input v-model="form.file" autocomplete="off" placeholder="请输入资料链接"></el-input>
        </el-form-item>
        <el-form-item prop="collegeId" label="课件发布学院">
          <el-select v-model="form.collegeId" placeholder="请选择学院" style="width: 100%" @change="handleCollegeChange">
            <el-option v-for="item in collegeData" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="majorId" label="课件发布专业">
          <el-select v-model="form.majorId" placeholder="请选择专业" style="width: 100%" @change="handleMajorChange">
            <el-option v-for="item in majorData" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="content" label="课件内容">
          <div id="editor"></div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="课件" :visible.sync="editorVisible" width="50%" :close-on-click-modal="false" destroy-on-close>
      <div v-html="viewData" class="w-e-text w-e-text-container"></div>
    </el-dialog>

  </div>
</template>

<script>
import E from 'wangeditor'
export default {
  name: "LessonAdmin",
  data() {
    return {
      tableData: [],  // 所有的数据
      collegeData:[],
      majorData:[],
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      name: null,
      fromVisible: false,

      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      ids: [],
      showChannelInput: false, // 是否显示栏目输入框


      // 富文本
      editor: null,
      viewData: null,
      editorVisible:false,
      

      //表单规则
      rules: {
        name: [
          {required: true, message: '请输入课件名称', trigger: 'blur'},
        ],
        type: [
          {required: true, message: '请选择课件类型', trigger: 'blur'},
        ],
      },

    }
  },
  created() {
    this.load(1)
    this.loadCollege()
  },
  methods: {
    initWangEditor(content) {
      this.$nextTick(() => {
        this.editor = new E('#editor')
        this.editor.config.placeholder = '请输入内容'
        this.editor.config.uploadFileName = 'file'
        this.editor.config.uploadImgServer = this.$baseUrl+'/files/wang/lesson/upload'
        console.log("图片上传"+this.editor.config.uploadImgServer)
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
      // 初始化属性
      this.$set(this.form, 'collegeId', null);
      this.$set(this.form, 'majorId', null);
    },
    handleEdit(row) {   // 编辑数据
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.fromVisible = true   // 打开弹窗
      // 设置选择器的值为当前栏目名称或“编辑栏目”
      this.initWangEditor(this.form.content || '')

      // 初始化属性
      this.$set(this.form, 'collegeId', row.collegeId || null);
      this.$set(this.form, 'majorId', row.majorId || null);
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.form.content = this.editor.txt.html();
          if (!this.form.classIds) {
            this.form.classIds = [];
          }
          this.$request({
            url: this.form.id ? '/lesson/update' : '/lesson/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res => {
            if (res.code === '200') {  // 表示成功保存
              this.$message.success('保存成功')
              this.load(1)
              this.fromVisible = false
            } else {
              this.$message.error(res.msg)  // 弹出错误的信息
            }
          })
        }
      })
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
        this.$request.delete('/lesson/delete/' + id).then(res => {
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
    delBatch() {   // 批量删除
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/lesson/delete/batch', {data: this.ids}).then(res => {
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
      this.$request.get('/lesson/selectPage', {
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
    loadCollege(){
      this.$request.get('/college/selectAll').then(res =>{
        if(res.code === '200'){
          this.collegeData = res.data
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    reset() {
      this.name = null
      this.load(1)
    },
    handleCollegeChange(collegeId) {  //在选择学院后动态展示学院
      this.form.majorId = null;
      this.form.classId = null;
      this.majorData = [];
      if (collegeId) {
        this.$request.get('/major/selectByCollegeId', {
          params: {
            collegeId: collegeId
          }
        }).then(res => {
          if (res.code === '200') {
            this.majorData = res.data;
            console.log('Major data loaded:', this.majorData); // 调试信息
          } else {
            this.$message.error(res.msg);
          }
        });
      }
    },
    handleMajorChange(majorId) {
      this.$forceUpdate(); // 强制更新
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


</style>