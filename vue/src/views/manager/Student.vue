<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入账号查询" style="width: 200px" v-model="username"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="operation">
    </div>

    <div class="table">
      <div style="margin-bottom: 10px; font-size: 20px; font-weight: bold;">普通用户</div>
      <el-table :data="filtereNonStuData" strip @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="序号" width="70" align="center" sortable></el-table-column>
        <el-table-column label="头像">
          <template v-slot="scope">
            <div style="display: flex; align-items: center">
              <el-image style="width: 40px; height: 40px; border-radius: 50%" v-if="scope.row.avatar"
                        :src="scope.row.avatar" :preview-src-list="[scope.row.avatar]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="username" label="账号"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>

        <el-table-column label="操作" align="center" width="180">
          <template v-slot="scope">
            <el-button size="mini" type="primary" plain @click="handleEdit(scope.row)">设置学生身份</el-button>
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

    <div class="table" style="padding-top: 20px">
      <div style="margin-bottom: 10px; font-size: 20px; font-weight: bold;">学生</div>
      <el-table :data="filtereStuData" strip @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="序号" width="70" align="center" sortable></el-table-column>
        <el-table-column label="头像">
          <template v-slot="scope">
            <div style="display: flex; align-items: center">
              <el-image style="width: 40px; height: 40px; border-radius: 50%" v-if="scope.row.avatar"
                        :src="scope.row.avatar" :preview-src-list="[scope.row.avatar]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="username" label="账号"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="collegeName" label="学院"></el-table-column>
        <el-table-column prop="majorName" label="专业"></el-table-column>
        <el-table-column prop="className" label="班级"></el-table-column>

        <el-table-column label="操作" align="center" width="180">
          <template v-slot="scope">
            <el-button size="mini" type="danger" plain @click="del(scope.row.id)">删除该学生</el-button>
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



    <el-dialog title="用户" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="form" label-width="100px" style="padding-right: 50px" :rules="rules" ref="formRef">
        <el-form-item prop="collegeId" label="设置学院">
          <el-select v-model="form.collegeId" placeholder="请选择学院" style="width: 100%" @change="handleCollegeChange">
            <el-option v-for="item in collegeData" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="majorId" label="设置专业">
          <el-select v-model="form.majorId" placeholder="请选择专业" style="width: 100%" @change="handleMajorChange">
            <el-option v-for="item in majorData" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="classId" label="设置班级">
          <el-select v-model="form.classId" placeholder="请选择班级" style="width: 100%">
            <el-option v-for="item in classData" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
export default {
  name: "Student",
  data() {
    return {
      tableData: [],  // 所有的数据
      tableDataBySF0:[],
      tableDataBySF1:[],
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      username: null,
      fromVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
        ]
      },
      ids: [],
      collegeData:[],
      majorData:[],
      classData:[],
    }
  },
  computed:{
    filtereNonStuData() {
      return this.tableData.filter(row => row.studentflag === '0');
    },
    filtereStuData() {
      return this.tableData.filter(row => row.studentflag === '1');
    },
  },
  created() {
    this.load(1)
    this.loadCollege()
  },
  methods: {
    handleEdit(row) {   // 将用户设置为学生
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.fromVisible = true   // 打开弹窗
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url:'/user/updateWithSF',
            method:'PUT',
            data: this.form
          //   data: this.form    data传给Controller
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
    del(id) {   // 修改studentflag为0
      this.$confirm('您确定删除该学生？', '确认操作', {type: "warning"}).then(() => {
        const userData = {
          id: id,
          studentflag: '0',
          collegeId: null,
          majorId: null,
          classId: null
        };
        this.$request.put('/user/updateSFTo0', userData).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('用户身份已取消');
            this.load(1);
          } else {
            this.$message.error(res.msg);  // 弹出错误的信息
          }
        });
      }).catch(() => {
        // 用户取消操作
      });
    },
    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.ids = rows.map(v => v.id)
    },
    delBatch() {   // 批量删除
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/user/delete/batch', {data: this.ids}).then(res => {
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
      this.$request.get('/user/selectPageRelate', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
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
    handleCollegeChange(collegeId) {
      this.form.majorId = null;
      this.form.classId = null;
      this.majorData = [];
      this.classData = [];
      if (collegeId) {
        this.$request.get('/major/selectByCollegeId', {
          params: {
            collegeId: collegeId
          }
        }).then(res => {
          if (res.code === '200') {
            this.majorData = res.data;
          } else {
            this.$message.error(res.msg);
          }
        });
      }
    },
    handleMajorChange(majorId) {
      this.form.classId = null;
      this.classData = [];
      if (majorId) {
        this.$request.get('/classes/selectByMajorId', {
          params: {
            majorId: majorId
          }
        }).then(res => {
          if (res.code === '200') {
            this.classData = res.data;
          } else {
            this.$message.error(res.msg);
          }
        });
      }
    },
    loadByStudentFlag(pageNum, studentflag) {  // 分页查询
      if (pageNum) this.pageNum = pageNum;
      this.$request.get('/user/selectPageByStudentFlag', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          studentflag: studentflag  // 新增传递studentflag参数
        }
      }).then(res => {
        this.tableDataBySF0 = res.data?.list;
        this.total = res.data?.total;
      });
    },

    reset() {
      this.username = null
      this.load(1)

    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)

    },
    handleAvatarSuccess(response, file, fileList) {
      // 把头像属性换成上传的图片的链接
      this.form.avatar = response.data
    },
  }
}
</script>

<style scoped>

</style>