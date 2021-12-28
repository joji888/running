<template>
    <div v-loading="loading">
        <h3>个人信息</h3><br>
        <el-form :model="order" :rules="rules" ref="order" label-width="100px" class="demo-user">
            <el-form-item label="学号" prop="oid">
                <el-input v-model="order.oid" disabled="disabled"></el-input>
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
        props:['oid'],
        data() {
            return {
                order: {
                    ocreateTime: "",
                    odescribe: "",
                    oendTime: "",
                    oid: 0,
                    oimage: "",
                    ostate: 0,
                    otId: 0,
                    otile: "",
                    tsId: 0,
                    uid: 0,
                },
                loading:true,
                rules: {
                    ocreateTime: [
                        { required: true, message: '创建时间为空!!'}
                    ],
                    odescribe: [
                        { required: true, message: '请输入订单描述'}
                    ],
                    oendTime: [
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
            this.$http.get("/order/"+this.oid).then(function (res) {
                _this.$myRequest(res);//判断请求是否合法
                _this.order=res.data.data;
            });
        }
    }
</script>
