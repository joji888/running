<template>
    <div v-loading="loading">
        <h3>个人信息</h3><br>
        <el-form :model="user" :rules="rules" ref="user" label-width="100px" class="demo-user">

            <el-form-item label="昵称" prop="unick">
                <el-input v-model="user.unick" disabled="disabled"></el-input>
            </el-form-item>

            <el-form-item label="帐号" prop="uaccount">
                <el-input v-model="user.uaccount" disabled="disabled"></el-input>
            </el-form-item>

            <el-form-item label="性别" prop="ugender">
                <el-input v-model="user.ugender" disabled="disabled"></el-input>
            </el-form-item>

            <el-form-item label="电话" prop="uphone">
                <el-input v-model="user.uphone" disabled="disabled"></el-input>
            </el-form-item>

            <el-form-item label="头像" prop="uheadImg">
                <el-input v-model="user.uheadImg" disabled="disabled"></el-input>
            </el-form-item>

            <el-form-item label="邮箱" prop="uemail">
                <el-input v-model="user.uemail" disabled="disabled"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('user')">修改</el-button>
<!--                <el-button @click="resetForm('user')">重置</el-button>-->
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {
        name:"edit",
        props:['uid'],
        data() {
            return {
                user: {
                    unick: '',
                    uaccount: '',
                    ugender: '',
                    uphone: '',
                    uheadImg: '',
                    uemail: ''
                },
                loading:true,
                rules: {
                    unick: [
                        { required: true, message: '昵称为空!!'}
                    ],
                    uaccount: [
                        { required: true, message: '请输入账户'}
                    ],
                    ugender: [
                        { required: true, message: '请选择性别'}
                    ],
                    uphone: [
                        { required: true, message: '电话号码不能为空' }
                    ],
                    uheadImg: [
                        { required: true, message: '请选择头像图片'}
                    ],
                    uemail: [
                        { required: true, message: '请填写邮箱号'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.loading=true;
                this.$refs[formName].validate((valid) => {
                    if (valid) {

                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        },
        mounted() {
            let admin=this.$mySetToken();//判断用户是否登陆
            let _this=this;
            this.$http.get("/user/"+this.uid).then(function (res) {
                _this.$myRequest(res);//判断请求是否合法
                _this.user=res.data.data;
            });
            this.loading=false;
        }
    }
</script>
