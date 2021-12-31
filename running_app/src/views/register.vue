<template>
    <div class="loginMain">

        <div style="width: 85%"><van-icon name="revoke" color="red" @click="onOrder"/></div><br>
        <div style="width:85%;margin-bottom: 33px">
            <div style="font-size: 48px;font-weight: bold">注册</div>
            <div style="font-size: 12px;font-weight: bold">已有帐户？<span style="color: gold" @click="onLogin">立即登录</span></div>
        </div>

        <div style="width: 78%;margin-top: 40px">手机号</div>
        <van-field type="text" placeholder="请输入手机号" v-model="registerData.uPhone" style="width: 85%" /><br>

        <div style="width: 78%">邮箱号</div>
        <van-field type="text" placeholder="请输入邮箱号" v-model="registerData.uEmail" style="width: 85%"/><br>

        <van-popup v-model="issueShow" style="width: 85%;">
            <div style="display: flex;justify-content: center;flex-direction: column;align-items: center">
                <div style="width: 85%;margin-top: 15px"><van-icon name="cross" color="red" @click="myCross"/></div><br>
                <div>邮箱号验证码</div><br>
                <van-field type="text" placeholder="请输入验证码" v-model="registerData.verifyCodeData" style="width: 85%">
                    <template #button>
                        <van-button size="small" type="primary" style="background-color:#1989fa;border: 0px;" @click="verifyCode" :disabled="verifyColor" >{{verifyCodeTest}}</van-button>
                    </template>
                </van-field>
                <van-button round type="info" style="width:85%;margin-top: 10px;border:0px" @click="registerFonc">验证</van-button><br>
            </div>
        </van-popup>


        <div style="width: 78%">密码</div>
        <van-field type="password" placeholder="请输入密码" v-model="registerData.uPassword" style="width: 85%"/><br>

        <div style="width: 78%">确认密码</div>
        <van-field type="password" placeholder="请再次输入密码" v-model="registerData.uPassword2" style="width: 85%"/><br>

        <van-button round type="info" style="width:85%;margin-top: 10px;" @click="verifyCode">注册</van-button>
    </div>
</template>

<script>
    export default {
        name: "login",
        data(){
            return {
                myTime:0,
                registerData:{
                    uPhone:"",
                    uEmail:"",
                    uPassword:"",
                    uPassword2:"",
                    Token:"",
                    verifyCodeData:"",
                },
                verifyColor:false,
                verifyCodeTest:'发送验证码',
                issueShow:false
            }
        },
        methods:{
            myCross(){
                console.log(1)
                this.issueShow=false
            },
            verifyCode(){
                if (this.myTime!==0){
                    this.issueShow=true;
                    return;
                }

                this.registerFonc();

                this.myTime=60;
                this.verifyColor=true;

                this.verifyCodeTest=this.myTime+"后再试";

                let _this=this;
                let interval=setInterval(function () {
                    _this.myTime-=1;
                    if (_this.myTime===0){
                        _this.verifyColor=false;
                        _this.registerData.Token="";
                        _this.verifyCodeTest="发送验证码";
                        clearInterval(interval);
                    }else {
                        _this.verifyCodeTest=_this.myTime+"后再试";
                    }
                },1000);
            },
            registerFonc(){
                if (this.registerData.uPhone===null||this.registerData.uPhone===""){
                    this.$notify('手机号不能为空！');
                    return
                }

                let reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式

                if (this.registerData.uEmail===null||this.registerData.uEmail===""){
                    this.$notify('邮箱不能为空！');
                    return
                }
                if (!reg.test(this.registerData.uEmail)){
                    this.$notify('邮箱格式有误！');
                    return
                }
                if (this.registerData.uPassword===null||this.registerData.uPassword===""){
                    this.$notify('请输入密码！');
                    return
                }
                if (this.registerData.uPassword2===null||this.registerData.uPassword2===""){
                    this.$notify('请确认密码！');
                    return
                }

                let _this=this;
                this.$http.post("/user/verifyUser?"+this.$qs.stringify(this.registerData)).then(function (res) {
                    console.log(res)
                    if (res.data.code===200){
                        if (res.data.message==='没有验证码'){
                            // _this.verifyCode();
                            _this.registerData.Token=res.data.data;
                            _this.issueShow=true;
                        }

                        if (res.data.message==='请输入验证码'){
                            _this.$notify('注册失败，'+res.data.message);
                        }

                        if (res.data.message==="成功"){
                            _this.$notify({
                                type: 'success',
                                message: res.data.message
                            });
                            _this.$router.push({path:'/login',query:{}});
                        }



                    }else {
                        _this.$notify('登录失败，'+res.data.message);
                    }
                });

            },
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
