// function test() {
//     let user=localStorage.getItem("user");
//     user=JSON.parse(user);
//     if (user==null){
//         return
//     }
//     let time=user['time'];
//     let token=user['token']
//     user=user['user'];
//     if (user==null||time<new Date().getTime()){
//         this.$router.push({path:'/login',query:{}});
//     }
//     this.$http.defaults.headers.common['token'] = token
//
//     let _this=this;
//     this.$http.get('student/searchByLimit',{
//         params:{
//             keyword:_this.keyword,
//             offset:1,
//             limit:10000
//         }
//     }).then(function (res) {
//         console.log(res.data);
//         if (res.data.massage=="请先登陆"){
//             _this.$message.error("请先登陆");
//             _this.$router.push({path:'/login',query:{}});
//         }
//         if (res.data.massage=="token 无效"){
//             _this.$message.error("token 无效");
//             _this.$router.push({path:'/login',query:{}});
//         }
//         if(res.data.code===200){
//             console.log(res);
//             _this.loading=false;
//             _this.tableData=res.data.data;
//         }
//         if (res.data.code===500){
//             _this.loading=false;
//             _this.$message.error(res.data.massage);
//         }
//     });
// }
