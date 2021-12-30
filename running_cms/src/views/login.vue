<template>
    <div class="loginMain">
        <img class="loginImg" src="../assets/loginImg.png">
        <el-form :model="users" :rules="rules" ref="users" class="loginForm">
            <el-form-item prop="acc">
                <el-input v-model="users.acc" type="text" autocomplete="off" placeholder="账号" style="background-color: transparent"><i slot="prefix" class="el-input__icon el-icon-user"></i></el-input>
            </el-form-item>
            <el-form-item prop="pwd">
                <el-input v-model="users.pwd" type="password" autocomplete="off" placeholder="密码" @keyup.enter="login('users')"><i slot="prefix" class="el-input__icon el-icon-lock"></i></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" class="loginBut" @click="login('users')" >登录</el-button>

            </el-form-item>
            <span style="color: white;font-size: 14px">跑腿平台--吉首大学张家界学院</span>
            <br><span style="color: white;font-size: 14px">2021-现在 版权所有</span>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "login",
        data(){
            return {
                users:{
                    acc:"admin",
                    pwd:""
                },
                rules: {
                    acc: [
                        { required: true, message: '请输人账号', trigger: 'blur' }
                    ],
                    pwd: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                }
            }
        },
        methods:{
            login(users){
                let _this=this;
                this.$refs[users].validate((valid) => {
                    if (valid) {
                        console.log(this.users);
                        let obj=this.users;
                        this.$http.get('/admin/login',{
                            params:obj
                        }).then(function (res) {
                            console.log(res);
                            if (res.data.code===200){
                                if (res.data.data!=null){
                                    console.log("登陆成功 token:\n"+res.data.data['token'])
                                    _this.$message.success("登陆成功");
                                    let time=new Date();
                                    console.log(time.getTime())
                                    localStorage.setItem("token",JSON.stringify({
                                        'admin':res.data.data['admin'],
                                        'token':res.data.data['token'],
                                        'time':time.getTime()+1000*60*60*3
                                    }));
                                    _this.$http.defaults.headers.common['token'] = res.data.data['token'];
                                    _this.$router.push({path:'/',query:{}});
                                }
                            }else {
                                _this.$message.error(res.data.message);
                            }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .loginMain{
        width: 100%;
        height: 100vh;
        display: flex;
        align-items: center;
        flex-direction: column;
        justify-content: center;
        background-color: rgb(20, 26, 72)
    }
    .loginImg{
        width: 200px;
        height: 200px;
        margin-top: -200px;
        border-radius: 50%;
        margin-bottom: 30px;
    }
    .loginForm{
        width: 300px;
        text-align: center;
    }
    .loginBut{
        width: 100%;
    }

</style>
