<template>
    <div v-loading="loading">
        <div style="line-height: 40px;">
            <h3 style="float: left;margin-right: 20px;">管理员列表</h3>
            <el-input
                    placeholder="请输入管理员关键字"
                    prefix-icon="el-icon-search"
                    v-model="keyword" style="width: 300px;">
            </el-input>
            <el-button style="margin-left: 20px" type="primary" @click="initDate">查询</el-button>
            <el-button type="primary" @click="initDate">刷新</el-button>
        </div>

        <div v-if="dialogVisible">
            <el-dialog
                    title="提示"
                    :visible.sync="dialogVisible"
                    width="30%"
                    :before-close="handleClose">
                <edit v-bind:aid="aid"></edit>
                <span slot="footer" class="dialog-footer">
            </span>
            </el-dialog>
        </div>

        <el-table
                :data="tableData"
                style="width: 100%">

            <el-table-column
                    prop="aid"
                    label="ID"
                    width="180">
            </el-table-column>

            <el-table-column
                    prop="aRole"
                    label="角色"
                    width="120">
            </el-table-column>

            <el-table-column
                    prop="aaccount"
                    label="账号"
                    width="180">
            </el-table-column>

            <el-table-column label="操作">
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
                aid:'',
                dialogVisible:false,
                keyword:"",
                loading:true,
                tableData: []
            }
        },
        methods: {
            handleClose(done) {
                let _this=this;
                console.log(_this)
                done()
                _this.initDate()
            },
            handleEdit(index, row) {
                console.log(index, row);
                if (row.aaccount==="root"){
                    this.$message.success("root账号不能被修改");
                    return;
                }
                this.aid=row.aid;
                this.dialogVisible=true;
            },
            handleDelete(index, row) {
                console.log(index, row);
                let _this=this;
                this.$http.get('student/delete',{
                    params:{
                        sid:row.sid
                    }
                }).then(function (res) {

                });
            },
            initDate(){
                let _this=this;
                this.$http.get("/admin",{
                    params:{
                        keyword:_this.keyword,
                        page:0,
                        sort:"desc",
                        size:10000
                    }
                }).then(function (res) {
                    _this.$myRequest(res);
                    _this.tableData=res.data.data.body.content;
                });
            }
        },
        mounted() {
            console.log(this)
            this.initDate();
        }
    }
</script>
