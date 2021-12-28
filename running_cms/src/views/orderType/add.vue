<template>
    <div v-loading="loading">
        <h3>添加学生</h3><br>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="学号" prop="sNumber">
                <el-input v-model="ruleForm.sNumber"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="sname">
                <el-input v-model="ruleForm.sname"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
                <el-input v-model="ruleForm.age"></el-input>
            </el-form-item>
            <el-form-item label="姓别" prop="gender">
                <el-select v-model="ruleForm.gender" placeholder="请选择姓别" value="">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="电话号码" prop="phone">
                <el-input v-model="ruleForm.phone"></el-input>
            </el-form-item>
            <el-form-item label="家庭住址" prop="address">
                <el-input type="textarea" v-model="ruleForm.address"></el-input>
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
                    sid:'',
                    sNumber: '',
                    sname: '',
                    age: 0,
                    gender: '',
                    phone: '',
                    address: ''
                },
                rules: {
                    sname: [
                        { required: true, message: '请输姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    age: [
                        { required: true, message: '请输入年龄', trigger: 'change' }
                    ],
                    gender: [
                        { required: true, message: '请选择姓别', trigger: 'change' }
                    ],
                    phone: [
                        { required: true, message: '请输入手机号码', trigger: 'blur' }
                    ],
                    address: [
                        { required: true, message: '请输入家庭住址', trigger: 'blur' }
                    ],
                    sNumber: [
                        { required: true, message: '请输入学号', trigger: 'blur' },
                        { min: 10, max: 16, message: '长度在 10 到 16 个字符', trigger: 'blur' }
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
