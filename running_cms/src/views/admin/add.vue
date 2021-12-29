<template>
    <div v-loading="loading">
        <h3>添加管理员</h3><br>
        <el-form :model="admin" :rules="rules" ref="admin" label-width="100px" class="demo-user">

            <el-row style="display: flex">
                <el-form-item label="账号" prop="aAccount">
                    <el-input v-model="admin.aAccount"></el-input>
                </el-form-item>

                <el-form-item label="角色" prop="aRole">
                    <el-select v-model="admin.aRole" placeholder="请选择角色" value="">
                        <el-option label="root" value="root"></el-option>
                        <el-option label="admin" value="admin"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="密码" prop="aPassword">
                    <el-input type="password" v-model="admin.aPassword"></el-input>
                </el-form-item>
            </el-row>

            <el-form-item>
                <el-button type="primary" @click="submitForm('admin')">添加</el-button>
                <el-button @click="resetForm('admin')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {
        name:"edit",
        props:['aid'],
        data() {
            return {
                admin: {
                    aRole:'',
                    aAccount:'',
                    aPassword:'',
                },
                loading:false,
                rules: {
                    aRole:[
                        { required: true, message: '请选择角色'}
                    ],
                    aAccount:[
                        { required: true, message: '请输入账号'},
                        { min: 4, max: 16, message: '长度在 4 到 16 个字符'}
                    ],
                    aPassword:[
                        { required: true, message: '请输入密码'},
                        { min: 6, max: 32, message: '长度在 6 到 32 个字符'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.loading=true;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(this.admin)
                        let _this=this;
                        this.$http.post("/admin?"+this.$qs.stringify(this.admin)).then(function (res) {
                            _this.$myRequest(res);//判断请求是否合法
                            if (res.data.code===200){
                                _this.order=res.data.data;
                                _this.$refs[formName].resetFields();
                                _this.$message.success(res.data.message);
                            }
                        });
                    } else {
                        console.log('error submit!!');
                        this.loading=false;
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
