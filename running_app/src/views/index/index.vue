<template>
    <div>
        <component :is="page" @onIndex="onIndex" ref="myComponent"></component>
        <bottom @setPage="setPage" @addOrder="addOrder" ref="setPage"></bottom>
    </div>
</template>

<script>
    import order from "../order/list";

    import home from "../home/home";

    import bottom from "./bottom";

    export default {
        components:{
            bottom,
            order,
            home
        },
        name: "index",
        data() {
            return {
                page:'order',
                active: 0,
            };
        },
        methods:{
            setPage(res){
                this.page=res;
            },
            onIndex(){
                this.$refs.setPage.onOder();
            },
            addOrder(){
                this.$refs.myComponent.addOrderF();
            }
        },
        mounted() {
            console.log("index")
        }
    }
</script>

<style scoped>

</style>
