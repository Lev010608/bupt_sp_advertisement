<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入课件名称" style="width: 200px" v-model="name"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="table" >
      <div style="margin-bottom: 10px; font-size: 20px; font-weight: bold;">校级课程</div>
      <el-table :data="schoolLevelLessons" stripe @selection-change="handleSelectionChange">
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
            <el-button plain type="success" @click="handleAddClass(scope.row)" size="mini">添加课程</el-button>

          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
            background
            @current-change="handleSchoolLevelPageChange"
            :current-page="schoolLevelPageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="schoolLevelPageSize"
            layout="total, prev, pager, next"
            :total="schoolLevelTotal">
        </el-pagination>
      </div>
    </div>

    <div class="table" >
      <div style="margin-bottom: 10px; font-size: 20px; font-weight: bold;">院级课程</div>
      <el-table :data="collegeLevelLessons" stripe @selection-change="handleSelectionChange">
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
            <el-button plain type="success" @click="handleAddClass(scope.row)" size="mini">添加课程</el-button>

          </template>
        </el-table-column>
      </el-table>

      <div class="pagination">
        <el-pagination
            background
            @current-change="handleCollegeLevelPageChange"
            :current-page="collegeLevelPageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="collegeLevelPageSize"
            layout="total, prev, pager, next"
            :total="collegeLevelTotal">
        </el-pagination>
      </div>
    </div>

    <div class="table" >
      <div style="margin-bottom: 10px; font-size: 20px; font-weight: bold;">专业课程</div>
      <el-table :data="majorLevelLessons" stripe @selection-change="handleSelectionChange">
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
            <el-button plain type="success" @click="handleAddClass(scope.row)" size="mini">添加课程</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination">
        <el-pagination
            background
            @current-change="handleMajorLevelPageChange"
            :current-page="majorLevelPageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="majorLevelPageSize"
            layout="total, prev, pager, next"
            :total="majorLevelTotal">
        </el-pagination>
      </div>
    </div>

    <div class="operation">
      <div style="margin-bottom: 10px; font-size: 20px; font-weight: bold;">管理班级课程</div>
      <el-button type="primary" plain @click="handleAdd">上传课程</el-button>
      <el-select class="class-chooser" style="margin-left: 10px" v-model="selectedClassId" placeholder="请选择班级" @change="handleClassChange">
        <el-option
            v-for="item in myClass"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>

    </div>

    <div class="table" >
      <div v-if="selectedClassName" style="margin-bottom: 10px; font-size: 20px; font-weight: bold;">{{ selectedClassName }}课程</div>
      <el-table   :data="MyClassLessontableData" stripe  @selection-change="handleSelectionChange">
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

    <!-- Add class dialog -->
    <el-dialog title="选择班级" :visible.sync="addClassDialogVisible" width="30%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="selectedLesson" label-width="100px">
        <el-form-item label="选择班级">
          <el-select v-model="selectedClassIdForLesson" placeholder="请选择班级">
            <el-option
                v-for="item in myClass"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addClassDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmAddClass">确定</el-button>
      </div>
    </el-dialog>


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
        <el-form-item label="选择班级">
          <el-select v-model="selectedClassIdForLesson" placeholder="请选择班级">
            <el-option
                v-for="item in myClass"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
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
      //分页数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,

      schoolLevelLessons: [],
      schoolLevelPageNum: 1,
      schoolLevelPageSize: 10,
      schoolLevelTotal: 0,

      collegeLevelLessons: [],
      collegeLevelPageNum: 1,
      collegeLevelPageSize: 10,
      collegeLevelTotal: 0,

      majorLevelLessons: [],
      majorLevelPageNum: 1,
      majorLevelPageSize: 10,
      majorLevelTotal: 0,

      name: null,
      fromVisible: false,
      editorVisible:false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      ids: [],
      showChannelInput: false, // 是否显示栏目输入框

      tableData: [],  // 所有的数据
      collegeData:[],
      majorData:[],
      myClass:[],
      MyClassLessontableData:[],
      allLessons: [],  // 从后端获取的所有课程数据

      selectedClassId: null, //选择的班级 ID
      selectedClassName: '', //选择的班级名称
      addClassDialogVisible: false, // 控制添加班级的弹窗
      selectedLesson: null, // 存储当前选择的课件
      selectedClassIdForLesson: null, // 存储选择的班级ID


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
  computed: {
    filtereUnLessonData() {
      return this.tableData.filter(row => !row.collegeId && !row.majorId);
    },
    filtereCollegeLessonData() {
      return this.tableData.filter(row => row.collegeId && !row.majorId);
    },
    filtereMajorLessonData() {
      return this.tableData.filter(row => row.collegeId && row.majorId);
    },
  },
  created() {
    this.loadAllLessons();
    this.load(1)
    this.loadCollege()
    this.loadMyClass();
    this.loadSchoolLevelLessons();
    this.loadCollegeLevelLessons();
    this.loadMajorLevelLessons();
  },
  methods: {
    initWangEditor(content) {
      this.$nextTick(() => {
        this.editor = new E('#editor')
        this.editor.config.placeholder = '请输入内容'
        this.editor.config.uploadFileName = 'file'
        this.editor.config.uploadImgServer = this.$baseUrl+'/files/lesson/upload'
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
    handleAddClass(row) {   // 编辑数据
      this.selectedLesson = row;
      this.selectedClassIdForLesson = null;  // Reset the selected class ID for the lesson
      this.addClassDialogVisible = true;
    },
    confirmAddClass() { // 确定添加课件到班级
      if (this.selectedLesson && this.selectedClassIdForLesson) {
        this.$request.post('/lesson/addClass', {
          lessonId: this.selectedLesson.id,
          classId: this.selectedClassIdForLesson
        }).then(res => {
          if (res.code === '200') {
            this.$message.success('课件已添加到班级');
            this.addClassDialogVisible = false;
            this.selectedLesson = null;
            this.selectedClassIdForLesson = null;
            this.updateTableData();  // Update table data to reflect the changes
            this.loadSchoolLevelLessons();
            this.loadCollegeLevelLessons();
            this.loadMajorLevelLessons();
          } else {
            this.$message.error(res.msg);
          }
        });
      }
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          // 将选中的班级ID添加到form中
          this.form.classIds = this.selectedClassIdForLesson ? [this.selectedClassIdForLesson] : [];

          this.$request({
            url: this.form.id ? '/lesson/update' : '/lesson/addLessonInClass',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res => {
            if (res.code === '200') {  // 表示成功保存
              this.$message.success('保存成功')
              this.load(1)
              this.loadSchoolLevelLessons();
              this.loadCollegeLevelLessons();
              this.loadMajorLevelLessons();
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
            this.load(1);
            this.loadSchoolLevelLessons();
            this.loadCollegeLevelLessons();
            this.loadMajorLevelLessons();
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
            this.load(1);
            this.loadSchoolLevelLessons();
            this.loadCollegeLevelLessons();
            this.loadMajorLevelLessons();
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
    loadClassLessons(classId) {
      this.$request.get(`/lesson/selectByClassId/${classId}`).then(res => {
        if (res.code === '200') {
          this.MyClassLessontableData = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    loadMyClass(){
      this.$request.get(`/classes/selectAllByTeacherId/${this.user.id}`).then(res =>{
        if(res.code === '200'){
          this.myClass = res.data; // 将获取的班级列表设置到myClass变量中
        }else {
          this.$message.error(res.msg)
        }
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
    loadAllLessons() {
      this.$request.get('/lesson/selectAll').then(res => {
        if (res.code === '200') {
          this.allLessons = res.data;
          this.updateTableData();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    loadSchoolLevelLessons() {
      this.$request.get('/lesson/selectSchoolLevelLessons', {
        params: {
          classId: this.selectedClassId,
          name: this.name,
          pageNum: this.schoolLevelPageNum,
          pageSize: this.schoolLevelPageSize,
        }
      }).then(res => {
        if (res.code === '200') {
          this.schoolLevelLessons = res.data.list;
          this.schoolLevelTotal = res.data.total;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    handleSchoolLevelPageChange(pageNum) {
      this.schoolLevelPageNum = pageNum;
      this.loadSchoolLevelLessons();
    },
    loadCollegeLevelLessons() {
      this.$request.get('/lesson/selectCollegeLevelLessons', {
        params: {
          classId: this.selectedClassId,
          name: this.name,
          pageNum: this.collegeLevelPageNum,
          pageSize: this.collegeLevelPageSize,
        }
      }).then(res => {
        if (res.code === '200') {
          this.collegeLevelLessons = res.data.list;
          this.collegeLevelTotal = res.data.total;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    handleCollegeLevelPageChange(pageNum) {
      this.collegeLevelPageNum = pageNum;
      this.loadCollegeLevelLessons();
    },
    loadMajorLevelLessons() {
      this.$request.get('/lesson/selectMajorLevelLessons', {
        params: {
          classId: this.selectedClassId,
          name: this.name,
          pageNum: this.majorLevelPageNum,
          pageSize: this.majorLevelPageSize,
        }
      }).then(res => {
        if (res.code === '200') {
          this.majorLevelLessons = res.data.list;
          this.majorLevelTotal = res.data.total;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    handleMajorLevelPageChange(pageNum) {
      this.majorLevelPageNum = pageNum;
      this.loadMajorLevelLessons();
    },
    // 添加 updateTableData 方法，用于更新 tableData
    updateTableData() {
      const classId = this.selectedClassId;
      if (classId) {
        this.$request.get('/lesson/selectLessonsForClass', {
          params: {
            classId: classId,
            name: this.name,
            collegeId: this.user.collegeId || null,
            majorId: this.user.majorId || null,
          }
        }).then(res => {
          if (res.code === '200') {
            this.tableData = res.data;
          } else {
            this.$message.error(res.msg);
          }
        });
      } else {
        this.tableData = this.allLessons;
      }
    },
    data() {
      return {
        editor: null,
        viewData: null,
        editorVisible: false,
      }
    },
    reset() {
      this.name = null;
      this.load(1);
      this.loadSchoolLevelLessons();
      this.loadCollegeLevelLessons();
      this.loadMajorLevelLessons();
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
    handleClassChange(value) {
      const selectedClass = this.myClass.find(item => item.id === value);
      this.selectedClassId = value;
      this.selectedClassName = selectedClass ? selectedClass.name : '';
      this.loadClassLessons(value); // 加载班级学生数据
      this.loadSchoolLevelLessons();
      this.loadCollegeLevelLessons();
      this.loadMajorLevelLessons();
      this.updateTableData();
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
      this.ids = rows.map(v => v.id);
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