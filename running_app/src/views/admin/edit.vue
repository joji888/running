<template>
    <div v-loading="loading">
        <h3>个人信息</h3><br>
        <el-form :model="admin" :rules="rules" ref="admin" label-width="100px" class="demo-user">

            <el-form-item label="账号" prop="sname">
                <el-input v-model="admin.aaccount" disabled></el-input>
            </el-form-item>

            <el-form-item label="角色" prop="aRole">
                <el-select v-model="admin.aRole" placeholder="请选择角色" value="">
                    <el-option label="root" value="root"></el-option>
                    <el-option label="admin" value="admin"></el-option>
                </el-select>
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
        props:['aid'],
        data() {
            return {
                admin: {},
                loading:true,
                rules: {
                    aRole:[
                        { required: true, message: '请选择角色'}
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
                        let obj={
                            aId:this.admin.aid,
                            aRole:this.admin.aRole,
                        }
                        console.log(obj)

                        let _this=this;
                        this.$http.put("/admin?"+this.$qs.stringify(obj)).then(function (res) {
                            _this.$myRequest(res);//判断请求是否合法
                            if (res.data.code===200){
                                _this.$parent.handleClose();
                                _this.order=res.data.data;
                                _this.$message.success(res.data.message);
                            }
                        });
                    } else {
                        console.log('error submit!!');
                        this.loading=false;
                        return false;
                    }
                });
            }
        },
        mounted() {
            let admin=this.$mySetToken();//判断用户是否登陆
            let _this=this;
            this.$http.get("/admin/"+this.aid).then(function (res) {
                _this.$myRequest(res);//判断请求是否合法
                _this.admin=res.data.data;
            });
        }
    }
</script>
