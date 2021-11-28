<template>
  <div>
    <el-form :model="sizeForm" :rules="rules" ref="sizeForm" label-width="80px" size="mini">
      <el-form-item label="书籍名称" prop="name">
        <el-col :span="5">
        <el-input v-model="sizeForm.name"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-col :span="5">
        <el-input v-model="sizeForm.author"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm('sizeForm')">创建</el-button>
        <el-button @click="resetForm('sizeForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style>
  .el-form-item label:after {
    content: "";
    display: inline-block;
    width: 100%;
  }
  .el-form-item label {
    text-align: justify;
    height: 20px;
  }

</style>

<script>
export default {
  data() {
    return {
      sizeForm: {
        name: '',
        author: ''
      },
      rules: {
        name: [
          { required: true, message: '书籍名称不能为空', trigger: 'blur' },
          { min: 1,max: 20, message: '长度至少为一个字符', trigger: 'blur'}
        ],
        author: [
          { required: true, message: '作者不能为空', trigger: 'blur' },
          { min: 1,max: 20, message: '长度至少为一个字符',trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        const _this = this;
        if (valid) {
          axios.post('http://localhost:8181/book/save',this.sizeForm).then(function (resp){
            if(resp.data == 'success'){
              _this.$alert('《'+_this.sizeForm.name+ '》添加成功！','消息提示', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/pageOne')
                }
              });
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>