<template>
    <van-nav-bar left-arrow fixed="true">
        <template #left>
            <van-popover
                    style="margin-top: 5px;"
                    v-model="showPopover"
                    trigger="click"
                    :actions="actions"
                    @select="onSelect"
            >
                <template #reference>
                    <van-image
                            v-show="headImg"
                            width="40"
                            height="40"
                            radius="40"
                            :src="'../'+user.uheadImg"
                    />
                </template>
            </van-popover>

        </template>

        <template #title style="text-align: center">
            <van-row gutter="24" style="margin-left: 2px;">
                <van-col span="8" :style="zxStyle" @click="onZx"><van-icon name="bell" size="14" color="golden"/>最新</van-col>
                <van-col span="7" :style="tjStyle" @click="onTj">推荐</van-col>
                <van-col span="8" :style="jjStyle" @click="onJj">加急<van-icon name="fire" size="14" color="red"/></van-col>
            </van-row>
        </template>

        <template #right>
            <van-icon name="wap-nav" size="18" @click="onShowType"/>
        </template>

    </van-nav-bar>
</template>

<script>
    export default {
        name: "top",
        data(){
            return{
                user:{
                    uheadImg:""
                },
                zxStyle:'color: black;font-size:14px',
                tjStyle:'color: #1989fa;font-weight: bold',
                jjStyle:'color: black;font-size:14px',
                headImg:false,
                showPopover: false,
                // 通过 actions 属性来定义菜单选项
                actions: [{ text: '退出登录' }]
            }
        },
        methods:{
            onSelect(action) {
                console.log(action)
                if (action.text==="退出登录"){
                    localStorage.removeItem("user")
                    location.reload();
                }
            },
            onZx(){//点击最新
                this.zxStyle='color: #1989fa;font-weight: bold';
                this.tjStyle='color: black;font-size:14px';
                this.jjStyle='color: black;font-size:14px';

            },
            onTj(){//点击推荐
                this.zxStyle='color: black;font-size:14px';
                this.tjStyle='color: #1989fa;font-weight: bold';
                this.jjStyle='color: black;font-size:14px';
            },
            onJj(){//点击加急
                this.zxStyle='color: black;font-size:14px';
                this.tjStyle='color: black;font-size:14px';
                this.jjStyle='color: #1989fa;font-weight: bold';
            },
            onShowType(){//显示右边列表
                this.$emit("onShowType");
            },
        },
        mounted() {
            console.log("top")
            // localStorage.removeItem("user")
            let user=JSON.parse(localStorage.getItem("user"));
            if (user==null){
                this.headImg=false;
                return;
            }
            if (this.user!=null&&this.user.uheadImg!=null&&this.user.uheadImg!==""){
                this.headImg=true;
            }
        }
    }
</script>

<style scoped>

</style>
