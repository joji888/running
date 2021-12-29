<template>
    <div v-loading="loading">
        <div style="line-height: 40px;">
            <h3 style="float: left;margin-right: 20px;">订单子类表</h3>
            <el-button type="primary" @click="initDate">刷新</el-button>
        </div>

        <div v-if="dialogVisible">
            <el-dialog
                    title="修改订单子类"
                    :visible.sync="dialogVisible"
                    width="700px"
                    :before-close="handleClose">
                <edit v-bind:tsId="tsId"></edit>
                <span slot="footer" class="dialog-footer">
            </span>
            </el-dialog>
        </div>

        <el-table
                :data="tableData"
                style="width: 100%">

            <el-table-column
                    prop="tsId"
                    label="ID"
                    width="100">
            </el-table-column>

            <el-table-column
                    prop="tsName"
                    label="类型名"
                    width="180">
            </el-table-column>

            <el-table-column
                    prop="tsPrice"
                    label="默认价格"
                    width="180">
                <template slot-scope="scope">
                    <span style="color: red;font-weight: bold">￥{{scope.row.tsPrice}}</span>
                </template>
            </el-table-column>

            <el-table-column
                    label="操作"
                    width="180">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">
                        <i class="el-icon-edit"></i>
                        编辑</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">
                        <i class="el-icon-delete"></i>
                        删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import edit from "./edit";
    export default {
        components:{edit},
        data() {
            return {
                tsId:'',
                dialogVisible:false,
                keyword:"",
                loading:true,
                tableData: []
            }
        },
        methods: {
            handleClose(done) {//关闭编辑弹窗
                let _this=this;
                console.log(_this)
                done()
                _this.initDate()
            },
            handleEdit(index, row) {//开启编辑弹窗
                console.log(index, row);
                this.tsId=row.tsId;
                this.dialogVisible=true;
            },
            handleDelete(index, row) {//删除函数
                console.log(index, row);
                let _this=this;
                this.$http.delete('/orderTypeSon',{
                    params:{
                        id:row.tsId
                    }
                }).then(function (res) {
                    _this.$myRequest(res);//判断请求是否合法
                    if (res.data.code===200){
                        _this.initDate();//重新渲染页面
                        _this.$message.success(res.data.message);
                    }
                });
            },
            initDate(){//初始化函数
                let _this=this;
                this.$http.get("/orderTypeSon",{
                    params:{
                        page:0,
                        size:10000
                    }
                }).then(function (res) {
                    _this.$myRequest(res);//判断请求是否合法
                    _this.tableData=res.data.data.body.content;
                });
            }
        },
        mounted() {
            let admin=this.$mySetToken();//判断用户是否登陆
            this.initDate();//执行初始化函数
        }
    }
</script>
