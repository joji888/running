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

                    <van-uploader v-model="fileList" multiple :max-count="1" preview-size="320px"/>

                    <van-field
                            v-model="addOrder.oTile"
                            name="标题"
                            label="标题"
                            placeholder="标题"
                    />

                    <van-field
                            readonly
                            clickable
                            name="calendar"
                            :value="myDate"
                            label="结束日期"
                            placeholder="点击选择日期"
                            @click="showCalendar = true"
                    />
                    <van-calendar v-model="showCalendar" @confirm="onConfirm" />

                    <van-field
                            readonly
                            clickable
                            name="datetimePicker"
                            :value="myTime"
                            label="精确时间"
                            placeholder="点击选择精确时间"
                            @click="showPicker = true"
                    />
                    <van-popup v-model="showPicker" position="bottom">
                        <van-datetime-picker
                                type="time"
                                @confirm="onConfirm2"
                                @cancel="showPicker = false"
                        />
                    </van-popup>

                    <van-field
                            v-model="otIdNAndTsIdN"
                            is-link
                            readonly
                            label="类型"
                            placeholder="请选择订单类型"
                            @click="showType = true"
                    />
                    <van-popup v-model="showType" round position="bottom">
                        <van-cascader
                                v-model="addOrder.tsId"
                                title="请选择订单类型"
                                :options="options2"
                                @close="showType = false"
                                @finish="onTypeOk"
                        />
                    </van-popup>

                    <van-field
                            v-model="addOrder.oDescribe"
                            rows="4"
                            autosize
                            label="描述"
                            type="textarea"
                            placeholder="请输入描述"
                    />

                    <!-- 允许输入正整数，调起纯数字键盘 -->
                    <van-field v-model="addOrder.oPrice" type="digit" label="出价金额" />

                    <div style="margin: 16px;">
                        <van-button round type="info" native-type="submit" style="width: 160px" @click="addOrderFonc">提交</van-button>
                        <van-button round type="info" style="width: 90px;float: right;background-color: red;border: 0px;" @click="closeAddOrder">取消</van-button>
                    </div>
                </van-form>
            </div>
        </van-popup>

        <!--        订单列表-->
        <van-list
                v-model="loading"
                offset="10"
                style="margin-top: 46px"
                :finished="finished"
                finished-text="没有更多了"
                @load="listAdd"
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
                                    :src="item.user.uheadImg"
                            />
                            <span class="oUserText">{{item.user.unick}}</span>
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
                page:0,
                list:[],
                order:{},
                totalPages:'',
                otIdNAndTsIdN:"",
                show:false,
                showType:false,
                myDate:"",
                myTime:"",
                loading:false,
                addOrder:{
                    uId:"",
                    tsId:"",
                    otId:"",
                    oTile:"",
                    oPrice:"",
                    oState:"",
                    oEndTime:"",
                    oDescribe:"",
                    oCreateTime:""
                },
                option1: [
                    { text: '全部商品', value: 0 },
                    { text: '新款商品', value: 1 },
                    { text: '活动商品', value: 2 },
                ],
                options2: [
                    {
                        text: '浙江省',
                        value: '330000',
                        children: [{ text: '杭州市', value: '330100' }],
                    },
                    {
                        text: '江苏省',
                        value: '320000',
                        children: [{ text: '南京市', value: '320100' }],
                    },
                ],
                fileList:[],
                finished:false,
                detailsShow:false,
                showCalendar: false,
                showPicker: false,
            }
        },
        methods:{
            onShowType(){
                this.show=true;
            },
            listAdd(){
                this.page+=1;
                if (this.page>=this.totalPages){
                    this.finished=true;
                    return;
                }
                console.log(this.page)
                let _this=this;
                this.$http.get("/order",{
                    params:{
                        oState:1,
                        page:this.page,
                        size:6
                    }
                }).then(function (res) {
                    _this.loading = false;
                    _this.$myRequest(res);//判断请求是否合法
                    console.log(_this.list)
                    for (let i=0;i<res.data.data.body.content.length;i++){
                        _this.list.push(res.data.data.body.content[i]);
                    }
                    console.log(_this.list)
                });
            },
            onLoad(){//初始化函数
                let _this=this;
                this.$http.get("/order",{
                    params:{
                        page:0,
                        size:6
                    }
                }).then(function (res) {
                    _this.$myRequest(res);//判断请求是否合法
                    _this.totalPages=res.data.data.body.totalPages;
                    _this.order=res.data.data.body.content[0];
                    _this.list=res.data.data.body.content;
                });

                this.$http.get("/orderType",{
                    params:{
                        page:this.page,
                        size:10000
                    }
                }).then(function (res) {
                    _this.$myRequest(res);//判断请求是否合法
                    let typeList=res.data.data.body.content;
                    let options2=[];
                    for (let i=0;i<typeList.length;i++){
                        let children=[];
                        for (let j=0;j<typeList[i].orderTypeSons.length;j++){
                            children.push({
                                text:typeList[i].orderTypeSons[j].tsName,
                                value: typeList[i].orderTypeSons[j].tsId
                            })
                        }
                        options2.push({
                            text:typeList[i].otName,
                            value: typeList[i].otId,
                            children:children
                        })
                    }
                    console.log(options2)
                    _this.options2=options2;
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
            },
            onConfirm(date) {
                console.log(date.getTime())
                this.addOrder.oEndTime = date;
                this.myDate = `${date.getMonth() + 1}/${date.getDate()}`;
                this.showCalendar = false;
            },
            onConfirm2(time) {
                this.myTime = time;
                this.showPicker = false;
            },
            onTypeOk(res){
                console.log(res)
                this.addOrder.otId=res.selectedOptions[0].value
                this.addOrder.tsId=res.selectedOptions[1].value

                this.otIdNAndTsIdN=res.selectedOptions[0].text+'/'+res.selectedOptions[1].text
            },
            addOrderFonc(){
                console.log(this.fileList[0].file)

                this.addOrder.oCreateTime=this.$dateFormat('yyyy-MM-dd HH:mm',new Date());
                let xs=this.myTime.split(":")[0];
                let fz=this.myTime.split(":")[1];
                let test=100*60*60*Number(xs)+100*60*Number(fz)
                this.addOrder.oEndTime=this.$dateFormat('yyyy-MM-dd HH:mm',new Date(this.addOrder.oEndTime.getTime()+test));
                console.log(this.addOrder)

                let param = new FormData(); // 创建form对象
                param.append("file",this.fileList[0].file); // 通过append向form对象添加数据

                let config = {
                    headers: { "Content-Type": "multipart/form-data" }
                };

                let _this=this;
                this.$http.post("/order?"+this.$qs.stringify(this.addOrder), param, config).then(function (res) {
                    console.log(res)
                    if (res.data.code===200){
                        _this.$notify({
                            type: 'success',
                            message: res.data.message
                        });
                        location.reload();
                        // _this.onLoad();
                        // _this.otIdNAndTsIdN="";
                        // _this.addOrder="";
                        // _this.fileList="";
                        // _this.myDate="";
                        // _this.myTime="";
                    }else {
                        _this.$notify('添加失败，'+res.data.message);
                    }
                })

            }
        },
        mounted() {
            console.log("order")
            let user=JSON.parse(localStorage.getItem("user"));
            if (user!=null){
                this.addOrder.uId=user.uid;
            }
            this.addOrder.oState=1;
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
