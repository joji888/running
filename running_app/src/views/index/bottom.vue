<template>
    <van-tabbar v-model="active" placeholder="true">
        <van-tabbar-item @click="setPage('order')" ref="order">
            <span>首页</span>
            <template #icon="props">
                <img :src="props.active ? index.active : index.inactive" />
            </template>
        </van-tabbar-item>

        <van-tabbar-item @click="addOrder">
            <template #icon="props">
                <img :src="add.active" style="width: 50px;height: 50px;margin-top: 3px" />
            </template>
        </van-tabbar-item>

        <van-tabbar-item badge="" @click="setPage('home')">
            <span>我的</span>
            <template #icon="props">
                <img :src="props.active ? home.active : home.inactive" />
            </template>
        </van-tabbar-item>
    </van-tabbar>
</template>

<script>
    export default {
        name: "index",
        data() {
            return {
                active: 0,
                user:{},
                add: {
                    active: '/file/add02.png',
                    inactive: '/file/add01.png',
                },
                home: {
                    active: '/file/user03.png',
                    inactive: '/file/user01.png',
                },
                index: {
                    active: '/file/index03.png',
                    inactive: '/file/index01.png',
                },
            };
        },
        methods:{
            setPage(page){
                console.log(page)
                let _this=this;
                if (page==="home"&&this.user==null){
                    this.$dialog.alert({
                        message: '你还未登录是否跳转到登陆页面?',
                        showCancelButton:true
                    }).then(function (res) {
                        _this.onOder();
                        _this.$router.push({path:'/login',query:{}});
                    }).catch(function(res){
                        _this.$refs.order.$el.click();
                    });
                }else {
                    this.$emit("setPage",page);
                }
            },
            onOder(){
                this.$refs.order.$el.click();
            },
            addOrder(){
                let _this=this;
                if (this.user==null){
                    this.$dialog.alert({
                        message: '你还未登录是否跳转到登陆页面?',
                        showCancelButton:true
                    }).then(function (res) {
                        _this.onOder();
                        _this.$router.push({path:'/login',query:{}});
                    }).catch(function(res){
                        _this.$refs.order.$el.click();
                    });
                }else {
                    this.$emit("addOrder");
                }
            }
        },
        mounted() {
            this.user=JSON.parse(localStorage.getItem("user"));
            if (this.user!=null&&this.user.uheadImg!=null&&this.user.uheadImg!==""){
                this.headImg=true;
            }
        }
    }
</script>

<style scoped>

</style>
