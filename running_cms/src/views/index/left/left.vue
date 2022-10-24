<template>
    <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            :collapse="isCollapse"
            @open="handleOpen"
            style="height: 100vh">
        <el-row justify="center" style="align-items: center;height: 70px;line-height: 70px;">
            <img src="https://www.oyyf.top/images/appointment/logo.png" style="height: 40px;width: 40px;border-radius: 50%;float: left;margin-top: 13px;margin-left: 10px;margin-right: 5px" alt="logo">
            <h3 v-show="!isCollapse" style="margin-left: 10px;width: 160px">预约平台</h3>
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

        <el-submenu index="2">
            <template slot="title">
                <i class="el-icon-document-checked"></i>
                <span>轮播图管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="2-1" @click="updatePage('receiveList')">添加轮播图</el-menu-item>
                <el-menu-item index="2-2" @click="updatePage('receiveList')">轮播图列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="3">
            <template slot="title">
                <i class="el-icon-document-copy"></i>
                <span>公告管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="3-1" @click="updatePage('orderTypeSonAdd')">添加公告</el-menu-item>
                <el-menu-item index="3-2" @click="updatePage('orderList')">公告列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="4">
            <template slot="title">
                <i class="el-icon-paperclip"></i>
                <span>资讯管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="4-1" @click="updatePage('orderTypeAdd')">添加资讯</el-menu-item>
                <el-menu-item index="4-2" @click="updatePage('orderTypeList')">咨询列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="5">
            <template slot="title">
                <i class="el-icon-paperclip"></i>
                <span>场地类型管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="5-1" @click="updatePage('orderTypeSonAdd')">添加场地类型</el-menu-item>
                <el-menu-item index="5-2" @click="updatePage('orderTypeSonList')">产地类型列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="6">
            <template slot="title">
                <i class="el-icon-chat-line-round"></i>
                <span>场地管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="6-1" @click="updatePage('commentList')">添加场地</el-menu-item>
                <el-menu-item index="6-2" @click="updatePage('commentList')">场地列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="7" v-show="admin.aRole==='root'">
            <template slot="title">
                <i class="el-icon-aim"></i>
                <span>预约管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="7-1" @click="updatePage('applyList')">预约列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

<!--        <el-submenu index="8" v-show="admin.aRole==='root'">-->
<!--            <template slot="title">-->
<!--                <i class="el-icon-s-custom"></i>-->
<!--                <span>管理员管理</span>-->
<!--            </template>-->
<!--            <el-menu-item-group>-->
<!--                <el-menu-item index="8-1" @click="updatePage('adminAdd')">添加管理员</el-menu-item>-->
<!--                <el-menu-item index="8-2" @click="updatePage('adminList')">管理员列表</el-menu-item>-->
<!--            </el-menu-item-group>-->
<!--        </el-submenu>-->
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
            return;

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
