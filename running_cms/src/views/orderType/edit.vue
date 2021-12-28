<template>
    <div v-loading="loading">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-user">

            <el-form-item label="ID" prop="otId">
                <el-input v-model="ruleForm.otId" disabled="disabled"></el-input>
            </el-form-item>
            <el-form-item label="类型名" prop="otName">
                <el-input v-model="ruleForm.otName"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name:"edit",
        props:['otId'],
        data() {
            return {
                order: {},
                loading:true,
                ruleForm: {},
                rules: {
                    otName: [
                        { required: true, message: '请输入类型名'},
                        { min: 2, max: 20, message: '长度在 2 到 20 个字符'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.loading=true;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let _this=this;
                        this.$http.put("/orderType?"+this.$qs.stringify(this.ruleForm)).then(function (res) {
                            _this.$myRequest(res);//判断请求是否合法
                            if (res.data.code===200){
                                _this.$parent.handleClose();
                                _this.$message.success(res.data.message);
                            }
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
        },
        mounted() {
            let admin=this.$mySetToken();//判断用户是否登陆
            let _this=this;
            this.$http.get("/orderType/"+this.otId).then(function (res) {
                _this.$myRequest(res);//判断请求是否合法
                _this.ruleForm=res.data.data;
            });
        }
    }
</script>
