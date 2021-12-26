<template>

    <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            :collapse="isCollapse"
            @open="handleOpen"
            style="height: 100vh">
        <el-row justify="center" style="align-items: center;height: 70px;line-height: 70px;">
            <img src="../../../assets/loginImg.png" style="height: 40px;width: 40px;border-radius: 50%;float: left;margin-top: 13px;margin-left: 10px;margin-right: 5px" alt="logo">
            <h3 v-show="!isCollapse" style="margin-left: 10px;width: 160px">学生管理系统</h3>
        </el-row>

        <el-submenu index="1" v-show="!adminIs">
            <template slot="title">
                <i class="el-icon-s-home"></i>
                <span>我的</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="1-1" @click="updatePage('home')">个人信息</el-menu-item>
                <el-menu-item index="1-2" @click="updatePage('courses')">我的课程</el-menu-item>
                <el-menu-item index="1-3" @click="updatePage('courses2')">在线选课</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="2" v-show="adminIs">
            <template slot="title">
                <i class="el-icon-user-solid"></i>
                <span>学生管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="2-1" @click="updatePage('studentAdd')">添加学生</el-menu-item>
                <el-menu-item index="2-2" @click="updatePage('studentList')">学生列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>

        <el-submenu index="3" v-show="adminIs">
            <template slot="title">
                <i class="el-icon-message-solid"></i>
                <span>课程管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="3-1" @click="updatePage('courseAdd')">添加课程</el-menu-item>
                <el-menu-item index="3-2" @click="updatePage('courseList')">课程列表</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
    </el-menu>

</template>

<script>
    export default {
        name: "left",
        props: ['isCollapse','adminIs'],
        methods:{
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            updatePage(page){
                this.$emit("updatePage",page);
            }
        }
    }
</script>

<style scoped>

</style>
