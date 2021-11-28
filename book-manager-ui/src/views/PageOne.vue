<template>
  <div>
    <el-table
        :data="tableData"
        border
        style="width: 38%">
      <el-table-column
          fixed
          prop="id"
          label="ID"
          width="100">
      </el-table-column>
      <el-table-column
          prop="name"
          label="书名"
          width="200">
      </el-table-column>
      <el-table-column
          prop="author"
          label="作者"
          width="200">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="100">
        <template slot-scope="scope">
          <el-button @click="edit(scope.row);dialogFormVisible = true" type="text" size="small">修改</el-button>
          <el-button  @click="remove(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <br/>

    <el-pagination
        :page-size="6"
        layout="prev, pager, next"
        :total="total"
        @current-change="page">
    </el-pagination>

    <el-dialog title="编辑书籍信息" :visible.sync="dialogFormVisible" width="25%">
      <el-form :model="formData">
        <el-form-item label="书名" :label-width="formLabelWidth">
          <el-input v-model="formData.name" autocomplete="off">{{formData.name}}</el-input>
        </el-form-item>
        <el-form-item label="作者" :label-width="formLabelWidth">
          <el-input v-model="formData.author" autocomplete="off">{{formData.name}}</el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel;dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  methods: {
    cancel(){
      this.$message({
        type: 'info',
        message: '已取消编辑'
      });
    },
    edit(row) {
      const _this = this;
      this.formData.id = row.id;
      this.formData.name = row.name;
      this.formData.author = row.author;
    },
    remove(row) {
      const _this = this;
      this.$confirm('《'+row.name+'》将被永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete("http://localhost:8181/book/remove/" + row.id).then(function (resp) {
          _this.$message({
            type: 'success',
            message: '删除成功!'
          });
          window.location.reload();
        });
      }).catch(() => {
        _this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    page(currentPage) {
      const _this = this;
      axios.get("http://localhost:8181/book/all/" + currentPage + "/6").then(function (resp) {
        _this.tableData = resp.data.content;
        _this.total = resp.data.totalElements;
      })
    },
  },
  created() {
    const  _this = this;
    axios.get("http://localhost:8181/book/all/1/6").then(function (resp){
      _this.tableData = resp.data.content;
      _this.total = resp.data.totalElements;
    })
  },
  data() {
    return {
      total: null,
      tableData: null,
      dialogFormVisible: false,
      formLabelWidth: '80px',
      formData: {
        id: null,
        name: null,
        author: null
      }
    }
  }
}
</script>