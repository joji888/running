<template>
    <div v-loading="loading">
        <el-form :model="order" :rules="rules" ref="order" label-width="100px" class="demo-user">

            <el-form-item label="订单图片" prop="oid">
                <el-upload
                        class="avatar-uploader"
                        :action="'/order/editImg?image='+order.oimage+'&oId='+order.oid"
                        name="uploadFIle"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload">
                    <img :src="order.oimage" width="300" height="300" class="head_pic"/><br>
                    点击图片修改
                </el-upload>
            </el-form-item>

            <el-row style="display: flex">
                <el-form-item label="订单ID" prop="oid">
                    <el-input v-model="order.oid" disabled="disabled"></el-input>
                </el-form-item>
                <el-form-item label="用户ID" prop="uid">
                    <el-input v-model="order.uid" disabled="disabled"></el-input>
                </el-form-item>
            </el-row>

            <el-row style="display: flex">
                <el-form-item label="创建时间" prop="oCreateTime">
                    <el-date-picker
                            v-model="order.oCreateTime"
                            type="datetime"
                            disabled="disabled"
                            placeholder="选择日期时间">
                    </el-date-picker>
                </el-form-item>

                <el-form-item label="结束时间" prop="oEndTime">
                    <el-date-picker
                            v-model="order.oEndTime"
                            type="datetime"
                            placeholder="选择日期时间">
                    </el-date-picker>
                </el-form-item>
            </el-row>

            <el-row style="display: flex">
                <el-form-item label="订单标题" prop="otile">
                    <el-input v-model="order.otile"></el-input>
                </el-form-item>
                <el-form-item label="订单状态" prop="uid">
                    <template>
                        <el-select v-model="order.ostate" placeholder="请选择">
                            <el-option
                                    v-for="item in oStateS"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </template>
                </el-form-item>
            </el-row>

            <el-row style="display: flex">
                <el-form-item label="订单类型" prop="uid">
                    <template>
                        <el-select v-model="order.otId" filterable placeholder="请选择">
                            <el-option
                                    v-for="item in oTypes"
                                    :key="item.otId"
                                    :label="item.otName"
                                    :value="item.otId">
                            </el-option>
                        </el-select>
                    </template>
                </el-form-item>
                <el-form-item label="订单子类" prop="uid">
                    <template>
                        <el-select v-model="order.tsId" filterable placeholder="请选择">
                            <el-option
                                    v-for="item in oTypeSons"
                                    :key="item.tsId"
                                    :label="item.tsName"
                                    :value="item.tsId">
                            </el-option>
                        </el-select>
                    </template>
                </el-form-item>
            </el-row>

            <el-form-item label="订单描述" prop="odescribe">
                <el-input  type="textarea" v-model="order.odescribe"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('order')">修改</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name:"edit",
        props:['oid'],
        data() {
            return {
                order: {},
                loading:true,
                oStateS: [{
                    value: 0,
                    label: '过期'
                },{
                    value: 1,
                    label: '正常'
                },{
                    value: 2,
                    label: '已接单'
                },{
                    value: 3,
                    label: '结束'
                }],
                oTypes: [],
                oTypeSons: [],
                rules: {
                    oCreateTime: [
                        { required: true, message: '创建时间为空!!'}
                    ],
                    odescribe: [
                        { required: true, message: '请输入订单描述'}
                    ],
                    oEndTime: [
                        { required: true, message: '请选择订单结束时间'}
                    ],
                    oid: [
                        { required: true, message: '订单ID不能为空' }
                    ],
                    oimage: [
                        { required: true, message: '选择订单图片'}
                    ],
                    ostate: [
                        { required: true, message: '设置订单状态'}
                    ],
                    otId: [
                        { required: true, message: '请选择类型'}
                    ],
                    otile: [
                        { required: true, message: '请输入订单标题'},
                        { min: 2, max: 100, message: '长度在 2 到 100 个字符'}
                    ],
                    tsId: [
                        { required: true, message: '请选择子类型'}
                    ],
                    uid: [
                        { required: true, message: '用户ID不能为空'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.loading=true;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(this.order.oEndTime)
                        let obj={
                            oId:this.order.oid,
                            uId:this.order.uid,
                            tsId:this.order.tsId,
                            otId:this.order.otId,
                            oTile:this.order.otile,
                            oState:this.order.ostate,
                            oDescribe:this.order.odescribe,
                            oCreateTime:this.order.oCreateTime,
                            oEndTime:this.$dateFormat('yyyy-MM-dd HH:mm',new Date(this.order.oEndTime))
                        }
                        console.log(obj)

                        let _this=this;
                        this.$http.put("/order?"+this.$qs.stringify(obj)).then(function (res) {
                            _this.$myRequest(res);//判断请求是否合法
                            if (res.data.code===200){
                                _this.$parent.handleClose();
                                _this.order=res.data.data;
                                _this.$message.success(res.data.message);
                            }
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            handleAvatarSuccess(res, file) {
                this.order.oimage = URL.createObjectURL(file.raw);
            },
            beforeAvatarUpload(file) {
                console.log(file.type)
                const isJPG = file.type === 'image/jpeg'||file.type === 'image/png';
                const isLt2M = file.size / 1024 / 1024 < 2;
                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 或者 PNG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            }
        },
        mounted() {
            let admin=this.$mySetToken();//判断用户是否登陆
            let _this=this;
            this.$http.get("/order/"+this.oid).then(function (res) {
                _this.$myRequest(res);//判断请求是否合法
                _this.order=res.data.data;
            });
            this.$http.get("/orderType",{
                params:{
                    page:0,
                    size:10000
                }
            }).then(function (res) {
                _this.$myRequest(res);//判断请求是否合法
                _this.oTypes=res.data.data.body.content;
            })
            this.$http.get("/orderTypeSon",{
                params:{
                    page:0,
                    size:10000
                }
            }).then(function (res) {
                _this.$myRequest(res);//判断请求是否合法
                _this.oTypeSons=res.data.data.body.content;
            });
        }
    }
</script>
