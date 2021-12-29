<template>
    <div class="loginMain">

        <div style="width:85%">
            <div style="font-size: 48px;font-weight: bold">登录</div>
            <div style="font-size: 12px;font-weight: bold">还没有账户？<span style="color: gold">立即注册</span></div>
        </div>

        <img
                style="margin-top: 40px ; margin-bottom: 40px"
                width="150"
                height="150"
                src="../assets/loginImg.png"
        />

        <div style="width: 78%">帐号</div>
        <van-field type="text" placeholder="请输入账号" style="width: 85%"/><br>

        <div style="width: 78%">密码</div>
        <van-field type="password" placeholder="请输入密码" style="width: 85%"/>
        <br>
        <div style="font-size:14px;width: 78%">
            <span style="color:orange">短信快捷登录</span>
            <span style="color: orange;float: right">忘记密码</span>
        </div>

        <van-button round type="info" style="width:85%;margin-top: 30px;" >登录</van-button>


    </div>
</template>

<script>
    export default {
        name: "login",
        data(){
            return {
            }
        },
        methods:{
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
