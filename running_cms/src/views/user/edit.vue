<template>
    <div v-loading="loading">
        <el-form :model="user" :rules="rules" ref="user" label-width="100px" class="demo-user">
            <el-form-item label="订单图片" prop="oid">
                <el-upload
                        class="avatar-uploader"
                        :action="'/user/editImg?uHeadImg='+user.uheadImg+'&uId='+user.uid"
                        name="uploadFIle"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload">
                    <img :src="user.uheadImg" width="300" height="300" class="head_pic"/><br>
                    点击图片修改
                </el-upload>
            </el-form-item>

            <el-row style="display: flex">
                <el-form-item label="角色" prop="uRole">
                    <el-input v-model="user.uRole" disabled="disabled"></el-input>
                </el-form-item>
                <el-form-item label="账号" prop="uaccount">
                    <el-input v-model="user.uaccount" disabled="disabled"></el-input>
                </el-form-item>
            </el-row>

            <el-row style="display: flex">
                <el-form-item label="邮箱" prop="uemail">
                    <el-input v-model="user.uemail" disabled="disabled"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="uphone">
                    <el-input v-model="user.uphone" disabled="disabled"></el-input>
                </el-form-item>
            </el-row>

            <el-row style="display: flex">
                <el-form-item label="昵称" prop="unick">
                    <el-input v-model="user.unick"></el-input>
                </el-form-item>
                <el-form-item label="姓别" prop="ugender">
                    <el-select v-model="user.ugender" placeholder="请选择姓别" value="">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                    </el-select>
                </el-form-item>
            </el-row>

            <el-form-item>
                <el-button type="primary" @click="submitForm('user')">修改</el-button>
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
                user: {},
                loading:true,
                rules: {
                    unick: [
                        { required: true, message: '请输昵称'},
                        { min: 1, max: 20, message: '长度在 1 到 20 个字符'}
                    ],
                    ugender: [
                        { required: true, message: '选择性别'}
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
                            uId:this.user.uid,
                            uNick:this.user.unick,
                            uRole:this.user.uRole,
                            uAccount:this.user.uaccount,
                            uPassword:this.user.upassword,
                            uEmail:this.user.uemail,
                            uPhone:this.user.uphone,
                            uGender:this.user.ugender,
                        }
                        console.log(obj)
                        let _this=this;
                        this.$http.put("/user?"+this.$qs.stringify(obj)).then(function (res) {
                            _this.$myRequest(res);//判断请求是否合法
                            if (res.data.code===200){
                                _this.$parent.handleClose();
                                _this.order=res.data.data;
                                _this.$message.success(res.data.message);
                            }
                        });
                    } else {
                        console.log('error submit!!');
                        this.loading=false;
                        return false;
                    }
                });
            },
            handleAvatarSuccess(res, file) {
                this.user.uheadImg = URL.createObjectURL(file.raw);
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
            this.$http.get("/user/"+this.uid).then(function (res) {
                _this.$myRequest(res);//判断请求是否合法
                _this.user=res.data.data;
            });
        }
    }
</script>
