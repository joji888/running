<template>
    <div v-loading="loading">
        <div style="line-height: 40px;">
            <h3 style="float: left;margin-right: 20px;">订单表</h3>
            <el-button type="primary" @click="initDate">刷新</el-button>
        </div>

        <div v-if="dialogVisible">
            <el-dialog
                    title="修改订单"
                    :visible.sync="dialogVisible"
                    width="700px"
                    :before-close="handleClose">
                <edit v-bind:oid="oid"></edit>
                <span slot="footer" class="dialog-footer">
            </span>
            </el-dialog>
        </div>

        <el-table
                :data="tableData"
                style="width: 100%">

            <el-table-column
                    prop="oid"
                    label="ID"
                    width="100">
            </el-table-column>

            <el-table-column
                    prop="oimage"
                    label="图片"
                    width="200">
                <template slot-scope="scope">
                    　　　　<img :src="scope.row.oimage" width="120" height="100%" class="head_pic"/>
                </template>
            </el-table-column>

            <el-table-column
                    label="标题"
                    width="120">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>标题: {{ scope.row.otile }}</p>
                        <p>描述: {{ scope.row.odescribe }}</p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{ scope.row.otile }}</el-tag>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>

            <el-table-column
                    label="订单状态"
                    width="100">
                <template slot-scope="scope">
                    <span v-if="scope.row.ostate===0" style="color: red">过期</span>
                    <span v-if="scope.row.ostate===1" style="color:green;">正常</span>
                    <span v-if="scope.row.ostate===2" style="color:blue;">已接单</span>
                    <span v-if="scope.row.ostate===3" style="color: orange">结束</span>
                </template>
            </el-table-column>

            <el-table-column
                    prop="orderType.otName"
                    label="订单类型"
                    width="100">
            </el-table-column>

            <el-table-column
                    prop="orderTypeSon.tsName"
                    label="订单子类"
                    width="100">
            </el-table-column>

            <el-table-column
                    prop="ocreateTime"
                    label="创建时间"
                    width="260">
                <template slot-scope="scope">
                    <el-date-picker
                            readonly="true"
                            v-model="scope.row.ocreateTime"
                            type="datetime"
                            placeholder="选择日期时间">
                    </el-date-picker>
                </template>
            </el-table-column>

            <el-table-column
                    prop="oendTime"
                    label="结束时间"
                    width="260">
                <template slot-scope="scope">
                    <el-date-picker
                            readonly="true"
                            v-model="scope.row.oendTime"
                            type="datetime"
                            placeholder="选择日期时间">
                    </el-date-picker>
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
                oid:'',
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
                this.oid=row.oid;
                this.dialogVisible=true;
            },
            handleDelete(index, row) {//删除函数
                console.log(index, row);
                let _this=this;
                this.$http.delete('/order',{
                    params:{
                        id:row.oid
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
                this.$http.get("/order",{
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
