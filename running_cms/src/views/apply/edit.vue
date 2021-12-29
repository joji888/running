<template>
    <div v-loading="loading">
        <el-form :model="apply" :rules="rules" ref="apply" label-width="100px" class="demo-user">

            <el-form-item label="身份证正面" prop="apply">
                <img :src="apply.applyIdentityCodeFront" width="300" height="100%" class="head_pic"/><br>
            </el-form-item>
            <el-form-item label="身份证背面" prop="apply">
                <img :src="apply.applyIdentityCodeBack" width="300" height="100%" class="head_pic"/><br>
            </el-form-item>

            <el-form-item label="申请时间" prop="applyTime">
                <el-date-picker
                        v-model="apply.applyTime"
                        type="datetime"
                        disabled="disabled"
                        placeholder="选择日期时间">
                </el-date-picker>
            </el-form-item>

            <el-form-item label="身份证号" prop="applyTime">
                <el-input v-model="apply.applyIdentityCode" disabled="disabled"></el-input>
            </el-form-item>

            <el-form-item label="驳回描述" prop="applyTime">
                <el-input  type="textarea" v-model="apply.applyRejectInfo" placeholder="描述一下驳回理由（同意申请请忽略）"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('apply',true)" style="color: blue;background-color: white;border: 1px solid rgba(0,0,0,0.29)">通过</el-button>
                <el-button type="primary" @click="submitForm('apply',false)" style="color: orange;background-color: white;border: 1px solid rgba(0,0,0,0.29)">驳回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name:"edit",
        props:['applyId'],
        data() {
            return {
                apply: {},
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
                    ]
                }
            };
        },
        methods: {
            submitForm(formName,state) {
                this.loading=true;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(this.apply)
                        let obj={
                            uId:this.apply.uId,
                            applyId:this.apply.applyId,
                            applyIdentityCode:this.apply.applyIdentityCode,
                            applyIdentityCodeBack:this.apply.applyIdentityCodeBack,
                            applyIdentityCodeFront:this.apply.applyIdentityCodeFront,
                            applyState:state?"2":"1",
                            applyTime:this.apply.applyTime,
                        }
                        if(!state){
                            console.log(this.apply.applyRejectInfo)
                            if (this.apply.applyRejectInfo==null||this.apply.applyRejectInfo.length===""){
                                this.$message.error("驳回描述不能为空！");
                                this.loading=false;
                                return;
                            }
                            obj.applyRejectInfo=this.apply.applyRejectInfo
                        }
                        console.log(obj)

                        let _this=this;
                        this.$http.put("/apply?"+this.$qs.stringify(obj)).then(function (res) {
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
        },
        mounted() {
            let admin=this.$mySetToken();//判断用户是否登陆
            let _this=this;
            this.$http.get("/apply/"+this.applyId).then(function (res) {
                _this.$myRequest(res);//判断请求是否合法
                _this.apply=res.data.data;
            });
        }
    }
</script>
