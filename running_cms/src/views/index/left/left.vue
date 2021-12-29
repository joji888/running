<template>

    <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            :collapse="isCollapse"
            @open="handleOpen"
            style="height: 100vh">
        <el-row justify="center" style="align-items: center;height: 70px;line-height: 70px;">
            <img src="../../../assets/loginImg.png" style="height: 40px;width: 40px;border-radius: 50%;float: left;margin-top: 13px;margin-left: 10px;margin-right: 5px" alt="logo">
            <h3 v-show="!isCollapse" style="margin-left: 10px;width: 160px">跑腿平台</h3>
        </el-row>

        <el-submenu index="1" v-show="!adminIs">
            <template slot="title">
                <i class="el-icon-user"></i>
                <span>用户管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="1-2" @click="updatePage('userList')">用户列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="3">
            <template slot="title">
                <i class="el-icon-paperclip"></i>
                <span>订单类型管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="3-1" @click="updatePage('orderTypeAdd')">添加类型</el-menu-item>
                <el-menu-item index="3-2" @click="updatePage('orderTypeList')">类型列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="4">
            <template slot="title">
                <i class="el-icon-paperclip"></i>
                <span>订单子类管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="4-1" @click="updatePage('orderTypeSonAdd')">添加子类</el-menu-item>
                <el-menu-item index="4-2" @click="updatePage('orderTypeSonList')">子类列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="2">
            <template slot="title">
                <i class="el-icon-document-copy"></i>
                <span>订单管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="2-1" @click="updatePage('orderList')">订单列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="5">
            <template slot="title">
                <i class="el-icon-document-checked"></i>
                <span>接单管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="5-1" @click="updatePage('receiveList')">接单列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="6">
            <template slot="title">
                <i class="el-icon-chat-line-round"></i>
                <span>评论管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="6-1" @click="updatePage('commentList')">评论列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="7" v-show="admin.aRole==='root'">
            <template slot="title">
                <i class="el-icon-aim"></i>
                <span>审核管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="7-1" @click="updatePage('applyList')">审核列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="8" v-show="admin.aRole==='root'">
            <template slot="title">
                <i class="el-icon-s-custom"></i>
                <span>管理员管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="8-1" @click="updatePage('adminAdd')">添加管理员</el-menu-item>
                <el-menu-item index="8-2" @click="updatePage('adminList')">管理员列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
    </el-menu>

</template>

<script>
    export default {
        name: "left",
        props: ['isCollapse','adminIs'],
        data() {
            return {
                admin:{}
            }
        },
        methods:{
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            updatePage(page){
                this.$emit("updatePage",page);
            }
        },
        mounted() {
            console.log("left");
            let admin=this.$mySetToken();
            if (admin==null){
                this.$message.error("请先登陆");
                this.$router.push({path:'/login',query:{}});
                return
            }
            console.log(admin)
            this.admin=admin;
        }
    }
</script>

<style scoped>

</style>
