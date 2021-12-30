<template>
    <div v-loading="loading">
        <div style="line-height: 40px;">
            <h3 style="float: left;margin-right: 20px;">申请表</h3>
            <el-button type="primary" @click="initDate">刷新</el-button>
        </div>

        <div v-if="dialogVisible">
            <el-dialog
                    title="审核申请"
                    :visible.sync="dialogVisible"
                    width="700px"
                    :before-close="handleClose">
                <edit v-bind:applyId="applyId"></edit>
                <span slot="footer" class="dialog-footer">
            </span>
            </el-dialog>
        </div>

        <el-table
                :data="tableData"
                style="width: 100%">
            <el-table-column
                    prop="applyId"
                    label="ID"
                    width="100">
            </el-table-column>

            <el-table-column
                    label="用户"
                    width="120">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>昵称: {{scope.row.user.unick}}</p>
                        <p>账号: {{scope.row.user.uaccount}}</p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{scope.row.user.unick}}</el-tag>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>

            <el-table-column
                    label="订单状态"
                    width="100">
                <template slot-scope="scope">
                    <span v-if="scope.row.applyState==='0'" style="color: blue">未审核</span>
                    <span v-if="scope.row.applyState==='1'" style="color: orange">驳回</span>
                    <span v-if="scope.row.applyState==='2'" style="color: green;">同意</span>
                </template>
            </el-table-column>

            <el-table-column
                    prop="applyTime"
                    label="创建时间"
                    width="260">
                <template slot-scope="scope">
                    <el-date-picker
                            readonly="true"
                            v-model="scope.row.applyTime"
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
                            style="color: red"
                            @click="handleEdit(scope.$index, scope.row)">
                        <i class="el-icon-edit"></i>
                        审核</el-button>
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
                applyId:'',
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
                console.log(index, row)
                if (row.applyState!=="0"){
                    this.$message.success("该申请已经被审核过了");
                    return;
                }
                this.applyId=row.applyId;
                this.dialogVisible=true;
            },
            handleDelete(index, row) {//删除函数
                console.log(index, row);
                let _this=this;
                this.$http.delete('/apply',{
                    params:{
                        id:row.applyId
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
                this.$http.get("/apply",{
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
