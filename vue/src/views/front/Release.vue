<template>
  <div class="main-content">
    <div style="width: 80%; margin: 30px auto">
      <el-tabs v-model="activeTab">
        <el-tab-pane label="发布内容" name="first" @click="handleAdd">
          <div class="publish-content">
            <el-form label-width="100px" style="padding-right: 50px" :model="form" :rules="rules" ref="formRef">
              <el-form-item label="资料封面">
                <el-upload
                    class="avatar-uploader"
                    :action="$baseUrl + '/files/plog/upload'"
                    :headers="{ token: user.token }"
                    list-type="picture"
                    :on-success="handleImgSuccess"
                    :on-remove="handleImgRemove"
                    v-model="form.img"
                >
                  <el-button type="primary">上传图片</el-button>
                  <div v-if="form.img">
                    <el-image :src="form.img" style="width: 100px; height: 100px; margin-top: 10px;"></el-image>
                  </div>
                </el-upload>
              </el-form-item>
              <el-form-item prop="name" label="资料名称">
                <el-input v-model="form.name" autocomplete="off" placeholder="请输入资料名称"></el-input>
              </el-form-item>
              <el-form-item prop="file" label="资料链接">
                <el-input v-model="form.file" autocomplete="off" placeholder="请输入资料链接"></el-input>
              </el-form-item>
              <el-form-item prop="tag" label="资料标签">
                <el-input v-model="form.tag" autocomplete="off" placeholder="请输入标签"></el-input>
              </el-form-item>
              <el-form-item prop="content" label="资料介绍">
                <div id="editor"></div>
              </el-form-item>
              <div class="dialog-footer" style="display: flex; justify-content: center">
                <el-button @click="resetForm">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
              </div>
            </el-form>
          </div>
        </el-tab-pane>
        <el-tab-pane label="我的审核" name="second">
          <div style="margin: 20px 0">
            <el-input placeholder="请输入资料名称" style="width: 200px" size="mini" v-model="name"></el-input>
            <el-button class="button-style" plain style="margin-left: 10px" size="mini" @click="load(1)">查询</el-button>
            <el-button class="button-style" plain style="margin-left: 10px" size="mini" @click="reset">重置</el-button>
            <el-button class="button-style" plain size="mini" @click="delBatch">批量删除</el-button>
          </div>
          <div class="table">
            <el-table class="custom-table" :data="tableData" stripe  @selection-change="handleSelectionChange">
              <el-table-column type="selection" width="55" align="center"></el-table-column>
              <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
              <el-table-column prop="img" label="资料封面" width="100">
                <template v-slot="scope">
                  <div style="display: flex; align-items: center">
                    <el-image style="width: 60px; height: 40px; border-radius: 10px" v-if="scope.row.img"
                              :src="scope.row.img" :preview-src-list="[scope.row.img]"></el-image>
                  </div>
                </template>
              </el-table-column>
              <el-table-column prop="name" label="资料名称" width="250" show-overflow-tooltip></el-table-column>
              <el-table-column prop="userName" label="上传用户"></el-table-column>
              <el-table-column prop="addTime" label="上传时间"></el-table-column>
              <el-table-column prop="recommend" label="是否推荐"></el-table-column>
              <el-table-column prop="status" label="审核状态"></el-table-column>
              <el-table-column prop="descr" label="审核说明"></el-table-column>
              <el-table-column prop="tag" label="标签"></el-table-column>
              <el-table-column label="操作" width="180" align="center">
                <template v-slot="scope">
<!--                  <el-button plain type="primary" @click="handleEdit(scope.row)" size="mini">编辑</el-button>-->
                  <el-button plain type="danger" size="mini" @click=del(scope.row.id)>删除</el-button>
                </template>
              </el-table-column>
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
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import E from 'wangeditor'
export default {
  data() {
    return {
      activeTab: 'first',
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      editor: null,
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 5,  // 每页显示的个数
      total: 0,
      name: null,
      recommend: null,
      fromVisible: false,
      form: {},
      rules: {
        name: [
          {required: true, message: '请输入资料名称', trigger: 'blur'},
        ]
      },
      ids: []
    }
  },
  mounted() {
    this.load(1)
    this.handleAdd()
    this.initWangEditor('')
  },
  methods: {
    initWangEditor(content) {
      this.$nextTick(() => {
        this.editor = new E('#editor')
        this.editor.config.placeholder = '请输入内容'
        this.editor.config.uploadFileName = 'file'
        this.editor.config.uploadImgServer = this.$baseUrl+'/files/wang/plog/upload'
        this.editor.create()
        setTimeout(() => {
          this.editor.txt.html(content)
        })
      })
    },
    handleAdd() {
      this.form = {
        userId: this.user.id,
        status: '待审核'
      }
      this.initWangEditor('')
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.initWangEditor(this.form.content || '')
    },
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.form.content = this.editor.txt.html()
          this.$request({
            url: this.form.id ? '/information/update' : '/information/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res => {
            if (res.code === '200') {
              this.$message.success('保存成功')
              this.load(1)
              this.resetForm()
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
    del(id) {
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/information/delete/' + id).then(res => {
          if (res.code === '200') {
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)
          }
        })
      }).catch(() => {
      })
    },
    delBatch() {
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/information/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)
          }
        })
      }).catch(() => {
      })
    },
    load(pageNum) {
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/information/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          userId: this.user.id
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
    handleSelectionChange(rows) {
      this.ids = rows.map(v => v.id)
    },
    handleImgSuccess(res) {
      this.form.img = res.data
    },
    handleImgRemove() {
      this.form.img = null
    },
    reset() {
      this.name = null
      this.load(1)
    },
    resetForm() {
      this.form = {}
      if (this.editor) {
        this.editor.txt.clear()
      }
      this.handleImgRemove()
    }
  }
}
</script>

<style scoped>

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

/deep/.pagination .is-background .el-pager li:not(.disabled).active {
  background-color: #9fd0b6;
  cursor: pointer;
}

/deep/.pagination .is-background .el-pager li:hover {
  background-color: #467262;
  cursor: pointer;
  color: #fafafa;
}

/deep/.pagination .el-pagination button:hover {
  background-color: #467262;
}

/deep/  .custom-table {
  border-radius: 15px;
}

.publish-content {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 15px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

#editor {
  border: 1px solid #ddd;
  border-radius: 4px;
  min-height: 200px;
  padding: 10px;
  background-color: #fff;
}

.dialog-footer {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.el-upload-list {
  display: none;
}
</style>
