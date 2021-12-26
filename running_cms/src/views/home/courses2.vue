<template>
    <div v-loading="loading">
        <div style="line-height: 40px;">
            <h3 style="float: left;margin-right: 20px;">课程列表</h3>
        </div>

        <div v-if="dialogVisible">
            <el-dialog
                    title="提示"
                    :visible.sync="dialogVisible"
                    width="30%"
                    :before-close="handleClose">
                <edit v-bind:cId="cId"></edit>
                <span slot="footer" class="dialog-footer">
            </span>
            </el-dialog>
        </div>

        <el-table
                :data="tableData"
                style="width: 100%">

            <el-table-column
                    prop="cName"
                    label="课名"
                    width="180">
            </el-table-column>


            <el-table-column
                    prop="tName"
                    label="授课老师"
                    width="180">
            </el-table-column>

            <el-table-column
                    label="上课时间"
                    width="180">
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ minuteFormate(scope.row.cTime) }}</span>
                </template>
            </el-table-column>

            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="success"
                            @click="handleEdit(scope.$index, scope.row)">
                        <i class="el-icon-circle-plus-outline"></i>
                        加入课程</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                sId:'',
                dialogVisible:false,
                loading:true,
                keyword:'',
                tableData: []
            }
        },
        computed:{
            cTime:function () {
                console.log(111)
                return 11;
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
                console.log(this.ruleForm)
                let _this=this;
                this.$http.get('class_table/add',{
                    params:{
                        cId:row.cId,
                        sId:_this.sId
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
                        _this.$message.success("选课成功");
                        _this.initDate();
                    } else if (res.data.code===500){
                        _this.loading=false;
                        _this.$message.error(res.data.massage);
                    }else {
                        _this.loading=false;
                        _this.$message.error(res.data);
                    }
                });
            },
            handleDelete(index, row) {
                console.log(index, row);
            },
            minuteFormate(minute){
                let timeStr = '';
                let time = parseInt(minute);
                time/=1000;
                let hourS=Number((time/60/60).toFixed(0))+Number(8);
                if (hourS>24){
                    hourS-=24;
                }
                timeStr+=hourS;
                console.log(timeStr)
                let minuteS=time - (time / 60 / 60).toFixed(0)*60*60;
                minuteS=(minuteS/=60).toFixed(0);
                console.log(minuteS)
                if (minuteS<10){
                    timeStr+=':0'+minuteS;
                }else {
                    timeStr+=":"+minuteS;

                }
                return timeStr
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
                console.log(user)
                this.sId=user.sId;
                if (user==null||time<new Date().getTime()){
                    this.$router.push({path:'/login',query:{}});
                }
                this.$http.defaults.headers.common['token'] = token

                let _this=this;
                this.$http.get('course/searchBysIdAndLimitNo',{
                    params:{
                        sId:user.sId,
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
            this.initDate()
        }
    }
</script>
