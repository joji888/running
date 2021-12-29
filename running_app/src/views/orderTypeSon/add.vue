<template>
    <div v-loading="loading">
        <h3>添加订单子类</h3><br>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="子类名" prop="tsName">
                <el-input v-model="ruleForm.tsName"></el-input>
            </el-form-item>

            <el-form-item label="默认价格" prop="tsPrice">
                <el-input v-model.number="ruleForm.tsPrice"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                loading:false,
                ruleForm: {
                    tsName:'',
                    tsPrice:'',
                },
                rules: {
                    tsName: [
                        { required: true, message: '请输入子类名'},
                        { min: 2, max: 20, message: '长度在 2 到 20 个字符'}
                    ],
                    tsPrice: [
                        { required: true, message: '请输入子类默认价格'},
                        { type: 'number', message: '价格必须是数字'}
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
                            tsName:this.ruleForm.tsName,
                            tsPrice:this.ruleForm.tsPrice,
                        }
                        console.log(obj);

                        let _this=this;
                        this.$http.post("/orderTypeSon?"+this.$qs.stringify(obj)).then(function (res) {
                            _this.$myRequest(res);//判断请求是否合法
                            if (res.data.code===200){
                                _this.$message.success(res.data.message);
                                _this.resetForm('ruleForm')
                            }
                        });
                    } else {
                        this.loading=false;
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>
