<template>
    <div v-loading="loading">
        <h3>添加用户</h3><br>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

            <el-form-item label="昵称" prop="unick">
                <el-input v-model="ruleForm.unick"></el-input>
            </el-form-item>
            <el-form-item label="帐号" prop="uaccount">
                <el-input v-model="ruleForm.uaccount"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="ugender">
                <el-select v-model="ruleForm.ugender" placeholder="请选择姓别" value="">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="电话" prop="uphone">
                <el-input v-model="ruleForm.uphone"></el-input>
            </el-form-item>
            <el-form-item label="头像" prop="uheadImg">
                <el-input type="textarea" v-model="ruleForm.uheadImg"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="uemail">
                <el-input type="textarea" v-model="ruleForm.uemail"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                loading:false,
                ruleForm: {
                    unick: '',
                    uaccount: '',
                    ugender: '',
                    uphone: '',
                    uheadImg: '',
                    uemail: ''
                },
                rules: {
                    unick: [
                        { required: true, message: '请输昵称', trigger: 'blur' },
                        { min: 1, max: 5, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    uaccount: [
                        { required: true, message: '请输入帐号', trigger: 'blue' },
                        { min: 10, max: 16, message: '长度在 10 到 20 个字符', trigger: 'blur' }
                    ],
                    ugender: [
                        { required: true, message: '请选择姓别', trigger: 'change' }
                    ],
                    uphone: [
                        { required: true, message: '请输入手机号码', trigger: 'blur' }
                    ],
                    uheadImg: [
                        { required: true, message: '请上传头像', trigger: 'blur' }
                    ],
                    uemail: [
                        { required: true, message: '请输入邮箱', trigger: 'blur' },
                        { min: 10, max: 20, message: '长度在 10 到 20 个字符', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.loading=true;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(this.ruleForm)
                        let _this=this;
                        this.$http.post('student/add',this.ruleForm).then(function (res) {
                            console.log(res)
                            if (res.data.massage=="请先登陆"){
                                _this.$message.error("请先登陆");
                                _this.$router.push({path:'/login',query:{}});
                                return;
                            }
                            if (res.data.massage=="token 无效"){
                                _this.$message.error("token 无效");
                                _this.$router.push({path:'/login',query:{}});
                                return;
                            }
                            if(res.data.code===200){
                                console.log(res);
                                _this.loading=false;
                                _this.$message.success("添加成功");
                                _this.$refs[formName].resetFields();
                            } else if (res.data.code===500){
                                _this.loading=false;
                                _this.$message.error(res.data.massage);
                            }else {
                                _this.loading=false;
                                _this.$message.error(res.data);
                            }
                        });

                    } else {
                        this.loading=false;
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>
