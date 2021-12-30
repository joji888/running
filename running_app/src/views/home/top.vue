<template>
    <van-nav-bar left-arrow  title="我的"   @click-left="onIndex"></van-nav-bar>
</template>

<script>
    export default {
        name: "top",
        data(){
            return{
            }
        },
        methods:{
            onIndex(){
                this.$emit("onIndex");
            }
        },
        mounted() {
            // localStorage.removeItem("user")
            this.user=JSON.parse(localStorage.getItem("user"));
            if (this.user!=null&&this.user.uheadImg!=null&&this.user.uheadImg!==""){
                this.headImg=true;
            }
        }
    }
</script>

<style scoped>

</style>
