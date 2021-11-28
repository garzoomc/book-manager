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
          <el-button @click="edit(scope.row)" type="text" size="small">修改</el-button>
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
  </div>
</template>

<script>
export default {
  methods: {
    edit(row) {
      const _this = this;
      const h = this.$createElement;
      let pojo = {id:null,name:"不是吧",author:null};
      this.$msgbox({
        title: '修改内容',
        message: h('el-form', {props:{labelWidth: '80px',size: 'mini'}}, [
          h('el-form-item',  {props:{ label: '名称：'}},[
              h('el-col',{props:{span: 24}},[
                  h('el-input',{
                    // props:{
                    //   value: row.name
                    // },
                    domProps: {
                      value: this.value,
                      type: "text"
                    },
                    on: {
                      input: function (event) {
                        self.$emit('el-input', event.target.value)
                      }
                  }
                  })
              ])
          ]),
          h('el-form-item',  {props:{ label: '作者：'}},[
            h('el-col',{props:{span: 24}},[
                h('el-input',{
                  props:{
                    // value: row.author
                },
                  domProps: {
                    value: pojo.author,
                    type: "text"
                  },
                  on: {
                    input: function (event) {
                      self.$emit('input', event.target.value)
                    }
                  }
                })
            ]),
          ]),
        ]),
        showCancelButton: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = '执行中...';
            setTimeout(() => {
              done(
                  alert(pojo.name),
                  _this.$message({
                    type: 'success',
                    message: '编辑成功!'
                  })
              );
              setTimeout(() => {
                instance.confirmButtonLoading = false;
              }, 50);
            }, 500);
          } else {
            done(
                _this.$message({
                  type: 'info',
                  message: '已取消编辑'
                })
            );
          }
        }
      }).then(action => {
      });
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
        });
        window.location.reload();
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
      pojo: {
        id: '',
        name: '',
        author: ''
      },
    }
  }
}
</script>