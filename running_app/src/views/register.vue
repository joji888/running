<template>
    <div class="loginMain">

        <div style="width: 85%"><van-icon name="revoke" color="red" @click="onOrder"/></div><br>
        <div style="width:85%;margin-bottom: 33px">
            <div style="font-size: 48px;font-weight: bold">注册</div>
            <div style="font-size: 12px;font-weight: bold">已有帐户？<span style="color: gold" @click="onLogin">立即登录</span></div>
        </div>

        <div style="width: 78%;margin-top: 40px">手机号</div>
        <van-field type="text" placeholder="请输入手机号" style="width: 85%"/><br>

        <div style="width: 78%">邮箱号</div>
        <van-field type="text" placeholder="请输入邮箱号" style="width: 85%"/><br>

<!--        <div style="width: 78%">邮箱号验证码</div>-->
<!--        <van-field type="text" placeholder="请输入邮箱号验证码" style="width: 85%"><br>-->
<!--            <template #button>-->
<!--                <van-button size="small" type="primary" style="background-color:#1989fa;border: 0px" >发送验证码</van-button>-->
<!--            </template>-->
<!--        </van-field><br>-->

        <div style="width: 78%">密码</div>
        <van-field type="password" placeholder="请输入密码" style="width: 85%"/><br>

        <div style="width: 78%">确认密码</div>
        <van-field type="password" placeholder="请再次输入密码" style="width: 85%"/><br>

        <van-button round type="info" style="width:85%;margin-top: 10px;" >注册</van-button>


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
            onLogin(){
                this.$router.push({path:'/login',query:{}});
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
