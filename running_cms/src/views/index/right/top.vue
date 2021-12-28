<template>
    <el-row type="flex" class="row-bg" justify="space-between">
        <el-col class="top-item">
            <i class="el-icon-s-fold" style="font-size: 24px" v-show="!isCollapse" @click="foldOrUnfold_func"></i>
            <i class="el-icon-s-unfold" style="font-size: 24px" v-show="isCollapse"  @click="foldOrUnfold_func"></i>
        </el-col>
        <el-col class="top-item" style="text-align: right">
            <el-dropdown>
              <span class="el-dropdown-link">
                {{adminName}}<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
                <el-dropdown-menu slot="dropdown">
<!--                    <el-dropdown-item>个人信息</el-dropdown-item>-->
                    <el-dropdown-item @click.native="logOut">退出登陆</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: "top",
        data(){
            return {
                adminName:""
            }
        },
        props: ['isCollapse'],
        methods:{
            foldOrUnfold_func(){
                this.$emit("foldOrUnfold_func","");
            },
            logOut(){
                console.log('退出登陆');
                localStorage.removeItem("user");
                this.$router.push({path:'/login',query:{}});
            }
        },
        mounted() {
            let admin=this.$mySetToken();
            // console.log(admin)
            this.adminName=admin.aaccount;
        }

    }
</script>

<style scoped>
    .top-item{
        height: 60px;
        line-height: 70px;
    }
</style>
