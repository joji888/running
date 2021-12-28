<template>
    <div v-loading="loading">
        <h3>添加用户</h3><br>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

            <el-form-item label="开始时间" prop="rbeginTime">
                <el-input v-model="ruleForm.rbeginTime"></el-input>
            </el-form-item>

            <el-form-item label="结束时间" prop="rendTime">
                <el-input v-model="ruleForm.rendTime"></el-input>
            </el-form-item>

            <el-form-item label="跑腿状态" prop="rseate">
                <el-input type="textarea" v-model="ruleForm.rseate"></el-input>
            </el-form-item>

            <el-form-item label="用户id" prop="uid">
                <el-input type="textarea" v-model="ruleForm.uid"></el-input>
            </el-form-item>

            <el-form-item label="接单者id" prop="ruid">
                <el-input type="textarea" v-model="ruleForm.ruid"></el-input>
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
                    rbeginTime: '',
                    rendTime: '',
                    rseate: '',
                    uid: '',
                    ruid: ''
                },
                rules: {
                    rbeginTime: [
                        { required: true, message: '请输开始时间', trigger: 'blur' },
                        { min: 1, max: 5, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    rendTime: [
                        { required: true, message: '请输入结束时间', trigger: 'blue' },
                        { min: 10, max: 16, message: '长度在 10 到 20 个字符', trigger: 'blur' }
                    ],
                    rseate: [
                        { required: true, message: '请选择跑腿状态', trigger: 'change' }
                    ],
                    uid: [
                        { required: true, message: '请输入用户id', trigger: 'blur' }
                    ],
                    ruid: [
                        { required: true, message: '请输入接单者id', trigger: 'blur' }
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
