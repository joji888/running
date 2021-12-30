<template>

    <div>
<!--        顶部选项卡-->
        <top @onShowType="onShowType"></top>

<!--        左边类型菜单-->
        <van-popup v-model="show" position="left" :style="{ height: '100%',width: '80%'}">
            <typelist></typelist>
        </van-popup>

<!--        详情弹出框-->
        <van-popup v-model="detailsShow" style="width: 100%;height: 100%;">
            <div style="display: flex;align-items: center;flex-direction: column;padding-top: 10px;">
                <div style="width: 85%" @click="closeDetailsShow"><van-icon name="revoke" color="red"/></div>
                <div class="oTitle2">{{order.otile}}</div>
                <div class="oUser2">
                    <van-image
                            width="18"
                            height="18"
                            radius="18"
                            src="https://img01.yzcdn.cn/vant/cat.jpeg"
                    />
                    <span class="oUserText">{{order.uid}}+++++++++++++++++++++++++++++++++++++++++++++++++++++++</span>
                </div>
                <div style="width: 85%;">
                    <div style="float: left;font-size: 10px;margin-top: 8px;color: #cccccc;margin-bottom: 15px;height: 2vh">发布时间:{{order.oCreateTime}}</div>
                </div>
                <div style="width: 85%;margin-bottom: 15px">
                    <van-tag plain type="primary">{{order.orderType.otName}}</van-tag>
                    <van-tag plain type="primary" style="margin-left: 5px">{{order.orderTypeSon.tsName}}</van-tag>
                </div>


                <div style="height:63vh;overflow:auto;align-items: center;display: flex;flex-direction: column;padding-bottom: 80px">
                    <div class="oDescribe2">{{order.odescribe}}</div>
                    <van-image
                            width="85%"
                            fit="fill"
                            style="margin-top: 15px"
                            :src="order.oimage"
                    />
                    <div style="width: 85%;margin-top: 15px;color: red;font-size: 14px;line-height: 28px">
                        <div style="float: left;line-height: 48px">价格:￥{{order.orderTypeSon.tsPrice}}</div>
                        <van-button type="info" style="float: right">接单</van-button>
                    </div>
                </div>
            </div>
        </van-popup>

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

        <!--        订单列表-->
        <van-list
                style="margin-top: 46px"
                v-model="loading"
                :finished="finished"
                finished-text="没有更多了"
                @load="onLoad"
        >
            <van-cell v-for="item in list" :key="item" @click="onDetails(item)">
                <div style="height: 120px;">
                    <div class="oTitle">{{item.otile}}</div>
                    <div style="float: left;width: 65%">
                        <div class="oUser">
                            <van-image
                                    width="18"
                                    height="18"
                                    radius="18"
                                    src="https://img01.yzcdn.cn/vant/cat.jpeg"
                            />
                            <span class="oUserText">{{item.uid}}+++++++++++++++++++++++++++++++++++++++++++++++++++++++</span>
                        </div>
                        <div class="oDescribe">{{item.odescribe}}</div>
                    </div>

                    <van-image
                            width="30%"
                            fit="fill"
                            :src="item.oimage"
                    />
                </div>
            </van-cell>
        </van-list>

    </div>

</template>

<script>
    import top from "./top";
    import typelist from "./typeList";
    export default {
        components:{typelist, top},
        name: "order",
        props:['issueShow'],
        data(){
            return{
                list:[],
                order:{},
                show:false,
                addOrder:{
                    uId:"",
                    tsId:"",
                    otId:"",
                    oTile:"",
                    oState:"",
                    oEndTime:"",
                    oDescribe:"",
                    oCreateTime:new Date()
                },
                finished:true,
                detailsShow:false,
            }
        },
        methods:{
            onShowType(){
                this.show=true;
            },
            onLoad(){//初始化函数
                let _this=this;
                this.$http.get("/order",{
                    params:{
                        page:0,
                        size:10000
                    }
                }).then(function (res) {
                    _this.$myRequest(res);//判断请求是否合法
                    _this.order=res.data.data.body.content[0];
                    _this.list=res.data.data.body.content;
                });
            },
            onDetails(obj){
                console.log(obj)
                this.order=obj;
                this.detailsShow=true
            },
            closeDetailsShow(){
                this.detailsShow=false;
            },
            addOrderF(){
                this.issueShow=true;
            },
            closeAddOrder(){
                this.issueShow=false;
            }
        },
        mounted() {
            this.onLoad();
        }
    }
</script>

<style scoped>
    .oTitle{
        width: 95%;
        overflow: hidden;
        font-weight: bold;
        padding-left: 5px;
        white-space: nowrap;
        text-overflow: ellipsis;
    }
    .oTitle2{
        width: 85%;
        height: 2vh;
        margin-top: 10px;
        padding-left: 5px;
    }
    .oUser{
        width: 80%;
        display: flex;
        color: #696969;
        margin-top: 5px;
        overflow: hidden;
        padding-left: 5px;
        white-space: nowrap;
        align-items: center;
        text-overflow: ellipsis;
    }
    .oUser2{
        width: 85%;
        height: 2vh;
        display: flex;
        color: #696969;
        margin-top: 25px;
    }
    .oUserText{
        width: 95%;
        overflow: hidden;
        padding-left: 5px;
        white-space: nowrap;
        text-overflow: ellipsis;
    }
    .oDescribe{
        float: left;
        font-size: 13px;
        overflow: hidden;
        padding-top: 5px;
        padding-left: 5px;
        line-height: 14px;
        letter-spacing:1px;
        /*text-indent: 2em;*/
        display: -webkit-box;
        -webkit-line-clamp: 4;
        text-overflow: ellipsis;
        -webkit-box-orient: vertical;
    }
    .oDescribe2{
        width: 85%;
        float: left;
        font-size: 13px;
        padding-left: 5px;
        line-height: 14px;
        letter-spacing:1px;
    }
</style>
