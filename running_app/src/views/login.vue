<template>
    <div class="loginMain">

        <div style="width: 85%"><van-icon name="revoke" color="red" @click="onOrder"/></div><br>
        <div style="width:85%">
            <div style="font-size: 48px;font-weight: bold">登录</div>
            <div style="font-size: 12px;font-weight: bold">还没有账户？<span style="color: gold" @click="onRegister">立即注册</span></div>
        </div>

        <img
                style="margin-top: 30px ; margin-bottom: 20px"
                width="150"
                height="150"
                src="../assets/loginImg.png"
        />

        <div style="width: 78%">帐号</div>
        <van-field type="text" placeholder="请输入账号" v-model="acc" style="width: 85%"/><br>

        <div style="width: 78%">密码</div>
        <van-field type="password" placeholder="请输入密码" v-model="pwd" style="width: 85%"/>
        <br>
        <div style="font-size:14px;width: 78%">
<!--            <span style="color:orange">短信快捷登录</span>-->
            <span style="color: orange;float: right">忘记密码</span>
        </div>

        <van-button round type="info" style="width:85%;margin-top: 30px;" @click="login">登录</van-button>

    </div>
</template>

<script>
    export default {
        name: "login",
        data(){
            return {
                acc:"",
                pwd:"",
            }
        },
        methods:{
            login(){
                console.log(this.acc);

                if (this.acc==""){
                    this.$notify('账号不能为空！');
                    return
                }
                if (this.pwd==""){
                    this.$notify('密码不能为空！');
                    return
                }

                let obj={
                    acc:this.acc,
                    pwd:this.pwd
                }
                let _this=this;
                this.$http.post("/user/login?"+this.$qs.stringify(obj)).then(function (res) {
                    console.log(res)
                    if (res.data.code===200){
                        _this.$notify({
                            type: 'success',
                            message: res.data.message
                        });
                        localStorage.setItem("user",JSON.stringify(res.data.data));
                        _this.$router.push({path:'/',query:{}});
                    }else {
                        _this.$notify('登录失败，'+res.data.message);
                    }
                });
            },
            onRegister(){
                this.$router.push({path:'/register',query:{}});
            },
            onOrder(){
                this.$router.push({path:'/',query:{}});
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
        /*background-color: rgba(25, 186, 250, 0.99)*/
        background-color: white;
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
