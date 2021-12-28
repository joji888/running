<template>
    <div v-loading="loading">
        <h3>接单信息</h3><br>
        <el-form :model="receive" :rules="rules" ref="receive" label-width="100px" class="demo-user">

            <el-form-item label="开始时间" prop="rbeginTime">
                <el-input v-model="user.rbeginTime" disabled="disabled"></el-input>
            </el-form-item>

            <el-form-item label="结束时间" prop="rendTime">
                <el-input v-model="user.rendTime" disabled="disabled"></el-input>
            </el-form-item>

            <el-form-item label="跑腿状态" prop="rseate">
                <el-input v-model="user.rseate" disabled="disabled"></el-input>
            </el-form-item>

            <el-form-item label="用户id" prop="uid">
                <el-input v-model="user.uid" disabled="disabled"></el-input>
            </el-form-item>

            <el-form-item label="接单者id" prop="ruid">
                <el-input v-model="user.ruid" disabled="disabled"></el-input>
            </el-form-item>


            <el-form-item>
                <el-button type="primary" @click="submitForm('user')">修改</el-button>
<!--                <el-button @click="resetForm('user')">重置</el-button>-->
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {
        name:"edit",
        props:['uid'],
        data() {
            return {
                user: {
                    rbeginTime: '',
                    rendTime: '',
                    rseate: '',
                    uid: '',
                    ruid: ''
                },
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
            this.$http.get("/user/"+this.uid).then(function (res) {
                _this.$myRequest(res);//判断请求是否合法
                _this.user=res.data.data;
            });
            this.loading=false;
        }
    }
</script>
