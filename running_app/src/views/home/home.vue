<template>
    <div>
        <top @onIndex="onIndex"></top>

        <!--        发布订单弹出框-->
        <van-popup v-model="issueShow" style="width: 100%;height: 100%;">
            <div style="display: flex;align-items: center;flex-direction: column;padding-top: 10px;">
                <div style="width: 85%" @click="closeAddOrder"><van-icon name="revoke" color="red"/></div>

                <van-form @submit="onSubmit">
                    <van-field
                            v-model="username"
                            name="标题"
                            label="标题"
                            placeholder="标题"
                            :rules="[{ required: true, message: '请填写标题' }]"
                    />
                    <van-field
                            v-model="password"
                            type="password"
                            name="密码"
                            label="密码"
                            placeholder="密码"
                            :rules="[{ required: true, message: '请填写密码' }]"
                    />
                    <div style="margin: 16px;">
                        <van-button round block type="info" native-type="submit">提交</van-button>
                    </div>
                </van-form>

            </div>
        </van-popup>

        <div class="userInfo">
            <van-image
                    style="border: 1px solid white;margin-top: 30px;margin-left: 30px;float: left"
                    v-show="true"
                    width="80"
                    height="80"
                    radius="80"
                    :src="'../'+user.uheadImg"
            />
            <div style="border: 1px solid white;margin-top: 40px;margin-left: 30px;float: left;padding-top: 5px">
                <div style="font-size: 12px">{{user.unick}}</div>
                <van-tag plain type="primary" style="margin-top: 10px" color="#ff9094">{{user.uRole}}</van-tag>
            </div>

            <van-button round type="info" style="float: right;margin-top: 55px;font-size: 10px;height: 28px;margin-right: 30px">编辑资料</van-button>
        </div>
        <div style="padding-right: 30px;padding-left: 30px;font-size: 10px;color: #cccccc;margin-bottom: 20px;margin-top: 10px;">
            <span>发布跑腿：{{0}}</span>
            <span style="float: right">接受跑腿：{{0}}</span>
        </div>


        <van-cell title="接单者认证" is-link style="color: #989898;padding-left: 30px;padding-right: 30px"/>
        <van-cell title="我的订单" is-link style="color: #989898;padding-left: 30px;padding-right: 30px"/>
        <van-cell title="我的接单" is-link style="color: #989898;padding-left: 30px;padding-right: 30px"/>


        <van-empty description="每天跑跑腿，天天好心情" />


    </div>

</template>

<script>
    import top from "./top";
    export default {
        components:{top},
        name: "home",
        data(){
            return{
                user:{},
                issueShow:false
            }
        },
        methods:{
            onIndex(){
                this.$emit("onIndex");
            },
            addOrderF(){
                this.issueShow=true;
            },
            closeAddOrder(){
                this.issueShow=false;
            }
        },
        mounted() {
            this.user=JSON.parse(localStorage.getItem("user"));
            if (this.user!=null&&this.user.uheadImg!=null&&this.user.uheadImg!==""){
                this.headImg=true;
            }
        }
    }
</script>

<style scoped>
    .myBackground{
        width: 100%;
        height: 10vh;
        background-color: cadetblue;
    }
    .userInfo{
        height: 120px;
    }
</style>
