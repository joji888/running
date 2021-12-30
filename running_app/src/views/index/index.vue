<template>
    <div>
        <component :is="page"></component>
        <bottom @setPage="setPage"></bottom>
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
            }
        }
    }
</script>

<style scoped>

</style>
