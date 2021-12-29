<template>
    <van-tabbar v-model="active">
        <van-tabbar-item @click="setPage('order')">
            <span>首页</span>
            <template #icon="props">
                <img :src="props.active ? index.active : index.inactive" />
            </template>
        </van-tabbar-item>

        <van-tabbar-item>
            <template #icon="props">
                <img :src="add.active" style="width: 50px;height: 50px;margin-top: 3px" />
            </template>
        </van-tabbar-item>

        <van-tabbar-item badge="3" @click="setPage('home')">
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
                this.$emit("setPage",page);
            }
        }
    }
</script>

<style scoped>

</style>
