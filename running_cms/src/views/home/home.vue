<template>
    <div v-loading="loading">
        <h3>个人信息</h3><br>
        <el-form :model="user" :rules="rules" ref="user" label-width="100px" class="demo-user">
            <el-form-item label="学号" prop="sNumber">
                <el-input v-model="user.sNumber" disabled="disabled"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="sname">
                <el-input v-model="user.sname"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
                <el-input v-model="user.age"></el-input>
            </el-form-item>
            <el-form-item label="姓别" prop="gender">
                <el-select v-model="user.gender" placeholder="请选择姓别" value="">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="电话号码" prop="phone">
                <el-input v-model="user.phone"></el-input>
            </el-form-item>
            <el-form-item label="家庭住址" prop="address">
                <el-input type="textarea" v-model="user.address"></el-input>
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
        data() {
            return {
                user: {
                    sid:'',
                    sNumber: '1234',
                    sname: '张三',
                    age: 18,
                    gender: '男',
                    phone: '12323239423',
                    address: '湖南省张家界市'
                },
                loading:true,
                rules: {
                    name: [
                        { required: true, message: '请输姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    age: [
                        { required: true, message: '请输入年龄', trigger: 'change' }
                    ],
                    sex: [
                        { required: true, message: '请选择姓别', trigger: 'change' }
                    ],
                    phone: [
                        { required: true, message: '请输入手机号码', trigger: 'blur' }
                    ],
                    site: [
                        { required: true, message: '请输入家庭住址', trigger: 'blur' }
                    ],
                    sNumber: [
                        { required: true, message: '请输入学号', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.loading=true;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(this.user);
                        let _this=this;
                        this.$http.post('student/update',this.user).then(function (res) {
                            console.log(res)
                            if (res.data.message=="请先登陆"){
                                _this.$message.error("请先登陆");
                                _this.$router.push({path:'/login',query:{}});
                                return;
                            }
                            if (res.data.message=="token 无效"){
                                _this.$message.error("token 无效");
                                _this.$router.push({path:'/login',query:{}});
                                return;
                            }
                            if(res.data.code===200){
                                console.log(res);
                                _this.loading=false;
                                _this.$message.success("修改成功");
                            } else if (res.data.code===500){
                                _this.loading=false;
                                _this.$message.error(res.data.massage);
                            }else {
                                _this.loading=false;
                                _this.$message.error(res.data);
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
            let user=localStorage.getItem("user");
            user=JSON.parse(user);
            if (user==null){
                return
            }
            let time=user['time'];
            let token=user['token']
            user=user['user'];
            if (user==null||time<new Date().getTime()){
                this.$router.push({path:'/login',query:{}});
            }
            this.$http.defaults.headers.common['token'] = token

            let _this=this;
            this.$http.get('student/searchById',{
                params:{
                    sId:user.sId
                }
            }).then(function (res) {
                console.log(res.data);
                if (res.data.massage=="请先登陆"){
                    _this.$message.error("请先登陆");
                    _this.$router.push({path:'/login',query:{}});
                    return
                }
                if (res.data.massage=="token 无效"){
                    _this.$message.error("token 无效");
                    _this.$router.push({path:'/login',query:{}});
                    return
                }
                if(res.data.code===200){
                    console.log(res);
                    _this.loading=false;
                    _this.user=res.data.data;
                } else if (res.data.code===500){
                    _this.loading=false;
                    _this.$message.error(res.data.massage);
                }else {
                    _this.loading=false;
                    _this.$message.error(res.data);
                }
            });
        }
    }
</script>
