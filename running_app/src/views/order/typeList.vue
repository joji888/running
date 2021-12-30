<template>
    <van-tree-select
            height="100%"
            :items="items"
            :active-id.sync="activeId"
            :main-active-index.sync="activeIndex"
    />
</template>

<script>
    export default {
        name: "typeList",
        data() {
            return {
                items:[
                    { text: '分组 1' },
                    { text: '分组 2' }
                ],
                activeId: 1,
                activeIndex: 0,
            };
        },
    }
</script>

<style scoped>

</style>
