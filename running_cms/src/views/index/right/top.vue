<template>
    <el-row type="flex" class="row-bg" justify="space-between">

        <div v-if="dialogVisible">
            <el-dialog
                    title="修改密码"
                    :visible.sync="dialogVisible"
                    width="700px"
                    :before-close="handleClose">
                <edit v-bind:aId="aId"></edit>
                <span slot="footer" class="dialog-footer">
            </span>
            </el-dialog>
        </div>

        <el-col class="top-item">
            <i class="el-icon-s-fold" style="font-size: 24px" v-show="!isCollapse" @click="foldOrUnfold_func"></i>
            <i class="el-icon-s-unfold" style="font-size: 24px" v-show="isCollapse"  @click="foldOrUnfold_func"></i>
        </el-col>
        <el-col class="top-item" style="text-align: right">
            <el-dropdown trigger="click">
              <span class="el-dropdown-link">
                {{adminName}}<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
                <el-dropdown-menu slot="dropdown">
<!--                    <el-dropdown-item>个人信息</el-dropdown-item>-->
                    <el-dropdown-item @click.native="handleEdit">修改密码</el-dropdown-item>
                    <el-dropdown-item @click.native="logOut">退出登陆</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </el-col>
    </el-row>
</template>

<script>

    import edit from "./edit"

    export default {
        name: "top",
        components:{edit},
        data(){
            return {
                aId:"",
                adminName:"",
                dialogVisible:false
            }
        },
        props: ['isCollapse'],
        methods:{
            foldOrUnfold_func(){
                this.$emit("foldOrUnfold_func","");
            },
            logOut(){
                console.log('退出登陆');
                localStorage.removeItem("token");
                this.$router.push({path:'/login',query:{}});
            },
            handleClose(done) {//关闭编辑弹窗
                done()
            },
            handleEdit() {//开启编辑弹窗
                this.dialogVisible=true;
            }
        },
        mounted() {
            console.log("top");
            let admin=this.$mySetToken();
            if (admin){
                console.log(admin)
                this.aId=admin.aid;
                this.adminName=admin.aaccount;
            }
        }

    }
</script>

<style scoped>
    .top-item{
        height: 60px;
        line-height: 70px;
    }
</style>
