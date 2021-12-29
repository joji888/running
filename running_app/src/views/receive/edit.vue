<template>
    <div v-loading="loading">
        <h3>接单信息</h3><br>
        <el-form :model="receive" :rules="rules" ref="receive" label-width="100px" class="demo-user">

            <el-form-item label="开始时间" prop="rbeginTime">
                <el-date-picker
                        v-model="receive.rbeginTime"
                        type="datetime"
                        disabled="disabled"
                        placeholder="选择日期时间">
                </el-date-picker>
            </el-form-item>

            <el-form-item label="完成时间" prop="rendTime">
                <el-date-picker
                        v-model="receive.rendTime"
                        type="datetime"
                        placeholder="选择日期时间">
                </el-date-picker>
            </el-form-item>

            <el-form-item label="跑腿状态" prop="rseate">
                <el-select v-model="receive.rseate" placeholder="请选跑腿状态" value="">
                    <el-option label="结束" value="0"></el-option>
                    <el-option label="放弃" value="1"></el-option>
                    <el-option label="完成" value="2"></el-option>
                    <el-option label="超时" value="3"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('receive')">修改</el-button>
<!--                <el-button @click="resetForm('user')">重置</el-button>-->
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {
        name:"edit",
        props:['rid'],
        data() {
            return {
                receive: {},
                loading:true,
                rules: {
                    rbeginTime: [
                        { required: true, message: '开始时间为空!!'}
                    ],
                    rendTime: [
                        { required: true, message: '结束时间为空!!'}
                    ],
                    rseate: [
                        { required: true, message: '请选择配送状态'}
                    ],
                    uid: [
                        { required: true, message: '用户id不能为空' }
                    ],
                    ruid: [
                        { required: true, message: '接单者id不能为空'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.loading=true;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let obj={
                            rBeginTime: this.$dateFormat('yyyy-MM-dd HH:mm',new Date(this.receive.rbeginTime)),
                            rEndTime: this.$dateFormat('yyyy-MM-dd HH:mm',new Date(this.receive.rendTime)),
                            rId: this.receive.rid,
                            rSeate: this.receive.rseate,
                            ruId: this.receive.ruId,
                            uId: this.receive.uId,
                        }
                        console.log(obj)
                        let _this=this;
                        this.$http.put("/receive?"+this.$qs.stringify(obj)).then(function (res) {
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
            }
        },
        mounted() {
            let admin=this.$mySetToken();//判断用户是否登陆
            let _this=this;
            this.$http.get("/receive/"+this.rid).then(function (res) {
                _this.$myRequest(res);//判断请求是否合法
                _this.receive=res.data.data;
            });
            this.loading=false;
        }
    }
</script>
