<template>
    <el-container
            v-loading="loading"
            element-loading-text="拼命加载中"
            element-loading-background="rgba(0, 0, 0, 0.8)">
        <el-aside :style="leftClass">
            <left v-bind:isCollapse="isCollapse" v-bind:adminIs="adminIs" @updatePage="updatePage"></left>
        </el-aside>
        <el-container>
            <el-header>
                <top @foldOrUnfold_func="foldOrUnfold_func" v-bind:isCollapse="isCollapse"></top>
            </el-header>
            <el-main>
                <component v-bind:is="mainPag"></component>
            </el-main>
            <el-footer></el-footer>
        </el-container>
    </el-container>
</template>

<script>
    import left from "./left/left";
    import top from "./right/top";
    import studentAdd from "../student/add";
    import studentList from "../student/list";
    import home from "../home/home";
    import courses from "../home/courses";
    import courses2 from "../home/courses2";
    import courseAdd from "../course/add";
    import courseList from "../course/list";
    import adminList from "../admin/list"

    export default {
        name: "index",
        components: {top, left,studentAdd,studentList,home,courses,courses2,courseAdd,courseList,adminList},
        data(){
            return {
                adminIs:false,
                mainPag:"adminList",
                leftClass:"width: 210px;",
                isCollapse:false,
                loading:true
            }
        },
        methods:{
            foldOrUnfold_func(){
                this.isCollapse=!this.isCollapse;
                if (this.isCollapse){
                    for (let i=210;i>74;i--){
                        // console.log(11);
                        let _this=this
                        setTimeout(function () {
                            _this.leftClass="width: "+i+"px;";
                        },150)
                    }
                }else {
                    for (let i=74;i<210;i++){
                        // console.log(11);
                        let _this=this
                        setTimeout(function () {
                            _this.leftClass="width: "+i+"px;";
                        },150)
                    }
                }
            },
            updatePage(page){
                // console.log(page)
                this.mainPag=page;
            }
        },
        mounted() {
            let admin = this.$mySetToken();
            let _this=this;
            setTimeout(function () {_this.loading=false;},500)
        }
    }
</script>

<style scoped>
    .leftMaxWidth{
        width: 110px;
    }
    .leftMinWidth{
        width: 74px;
    }

</style>
