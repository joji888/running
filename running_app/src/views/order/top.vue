<template>
    <van-nav-bar left-arrow>
        <template #left>
            <van-image
                    width="40"
                    height="40"
                    radius="40"
                    src="https://img01.yzcdn.cn/vant/cat.jpeg"
            />
        </template>

        <template #title>
            <van-row gutter="24" style="margin-left: 2px">
                <van-col span="8"><van-icon name="bell" size="14" color="blud"/>最新</van-col>
                <van-col span="7">推荐</van-col>
                <van-col span="8">加急<van-icon name="fire" size="14" color="red"/></van-col>
            </van-row>
        </template>

        <template #right>
            <van-icon name="search" size="18" />
        </template>
    </van-nav-bar>
</template>

<script>
    export default {
        name: "top"
    }
</script>

<style scoped>

</style>
