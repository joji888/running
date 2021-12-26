<template>
    <div v-loading="loading">
        <h3>添加课程</h3><br>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="课名" prop="cName">
                <el-input v-model="ruleForm.cName"></el-input>
            </el-form-item>

            <el-form-item label="老师名" prop="tName">
                <el-input v-model="ruleForm.tName"></el-input>
            </el-form-item>



            <el-form-item label="上课时间" prop="cTime">
                <el-time-picker placeholder="选择时间" v-model="ruleForm.cTime" style="width: 30%;">
                </el-time-picker>
            </el-form-item>



            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
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
                    tName: '',
                    cName: '',
                    cTime: '',
                },
                rules: {
                    cName: [
                        { required: true, message: '请输入课名', trigger: 'blur' },
                        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    tName: [
                        { required: true, message: '请选择老师', trigger: 'blur' },
                        { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
                    ],
                    cTime: [
                        { required: true, message: '请输入上课时间', trigger: 'change' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(this.ruleForm)
                        this.loading=true;
                        this.$refs[formName].validate((valid) => {
                            if (valid) {
                                console.log(this.ruleForm)
                                let _this=this;
                                this.$http.post('course/add',this.ruleForm).then(function (res) {
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
    }
</script>
