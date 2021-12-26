<template>
    <div v-loading="loading">
        <div style="line-height: 40px;">
            <h3 style="float: left;margin-right: 20px;">学生列表</h3>
            <el-input
                    placeholder="请输入学生关键字"
                    prefix-icon="el-icon-search"
                    v-model="keyword" style="width: 300px;">
            </el-input>
            <el-button style="margin-left: 20px" type="primary" @click="initDate">查询</el-button>
        </div>

        <div v-if="dialogVisible">
            <el-dialog
                    title="提示"
                    :visible.sync="dialogVisible"
                    width="30%"
                    :before-close="handleClose">
                <edit v-bind:sId="sId"></edit>
                <span slot="footer" class="dialog-footer">
            </span>
            </el-dialog>
        </div>

        <el-table
                :data="tableData"
                style="width: 100%">

            <el-table-column
                    prop="sid"
                    label="ID"
                    width="180">
            </el-table-column>

            <el-table-column
                    prop="sNumber"
                    label="学号"
                    width="180">
            </el-table-column>

            <el-table-column
                    label="姓名"
                    width="180">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>姓名: {{ scope.row.sname }}</p>
                        <p>住址: {{ scope.row.address }}</p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{ scope.row.sname }}</el-tag>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>

            <el-table-column
                    prop="gender"
                    label="性别"
                    width="180">
            </el-table-column>


            <el-table-column
                    prop="age"
                    label="年龄"
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
                sId:'',
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
                this.sId=row.sid;
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
                    console.log(res)
                    if (res.data.massage=="请先登陆"){
                        _this.$message.error("请先登陆");
                        _this.$router.push({path:'/login',query:{}});
                        return;
                    }
                    if (res.data.massage=="token 无效"){
                        _this.$message.error("token 无效");
                        _this.$router.push({path:'/login',query:{}});
                        return;
                    }
                    if(res.data.code===200){
                        console.log(res);
                        _this.loading=false;
                        _this.$message.success("删除成功");
                    } else if (res.data.code===500){
                        _this.loading=false;
                        _this.$message.error(res.data.massage);
                    }else {
                        _this.loading=false;
                        _this.$message.error(res.data);
                    }
                });
            },
            initDate(){
                let user=localStorage.getItem("user");
                user=JSON.parse(user);
                if (user==null){
                    return
                }
                let time=user['time'];
                let token=user['token']
                user=user['user'];
                if (user==null||time<new Date().getTime()){
                    this.$router.push({path:'/login',query:{}});
                }
                this.$http.defaults.headers.common['token'] = token

                let _this=this;
                this.$http.get('student/searchByLimit',{
                    params:{
                        keyword:_this.keyword,
                        offset:1,
                        limit:10000
                    }
                }).then(function (res) {
                    console.log(res.data);
                    if (res.data.massage=="请先登陆"){
                        _this.$message.error("请先登陆");
                        _this.$router.push({path:'/login',query:{}});
                    }
                    if (res.data.massage=="token 无效"){
                        _this.$message.error("token 无效");
                        _this.$router.push({path:'/login',query:{}});
                    }
                    if(res.data.code===200){
                        console.log(res);
                        _this.loading=false;
                        _this.tableData=res.data.data;
                    }
                    if (res.data.code===500){
                        _this.loading=false;
                        _this.$message.error(res.data.massage);
                    }
                });
            }
        },
        mounted() {
            console.log(this)
            this.initDate();
        }
    }
</script>
