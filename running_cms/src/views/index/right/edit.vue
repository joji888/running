<template>
    <div v-loading="loading">
        <el-form :model="admin" :rules="rules" ref="admin" label-width="100px" class="demo-user">

            <el-form-item label="旧密码" prop="oldPwd">
                <el-input type="password" v-model="admin.oldPwd"></el-input>
            </el-form-item>

            <el-form-item label="新密码" prop="newPwd">
                <el-input type="password" v-model="admin.newPwd"></el-input>
            </el-form-item>

            <el-form-item label="确认密码" prop="newPwd2">
                <el-input type="password" v-model="admin.newPwd2"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('admin')">修改</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name:"edit",
        props:['aId'],
        data() {
            return {
                admin: {
                    aId:"",
                    oldPwd:"",
                    newPwd:"",
                    newPwd2:""
                },
                loading:false,
                rules: {
                    oldPwd: [
                        { required: true, message: '请输入旧密码'},
                    ],
                    newPwd: [
                        { required: true, message: '请输入新密码'},
                        { min: 6, max: 32, message: '长度在 6 到 32 个字符'}
                    ],
                    newPwd2: [
                        { required: true, message: '确认新密码'},
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
                        this.admin.aId=this.aId;
                        console.log(this.admin);
                        if (this.admin.newPwd!==this.admin.newPwd2){
                            this.$message.error("两次密码不一致！");
                            this.loading=false;
                            return
                        }

                        let _this=this;
                        this.$http.put("/admin/updatePwd?"+this.$qs.stringify(this.admin)).then(function (res) {
                            _this.$myRequest(res);//判断请求是否合法
                            if (res.data.code===200){
                                _this.$parent.handleClose();
                                _this.$message.success(res.data.message+",请重新登陆");
                                _this.$router.push({path:'/login',query:{}});
                            }
                        });

                    } else {
                        console.log('error submit!!');
                        this.loading=false;
                        return false;
                    }
                });
            },
        }
    }
</script>
