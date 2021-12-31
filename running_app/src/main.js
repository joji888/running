import Vue from 'vue'
import Vant from 'vant';
import 'vant/lib/index.css';
import router from "./router/router";
import App from './App.vue'
import axios from "axios";
import qs from "qs";

Vue.config.productionTip = false

axios.defaults.headers.common['token'] = '';
axios.defaults.baseURL='/running';

Vue.prototype.$http= axios
Vue.prototype.$qs= qs

//设置token全局函数
Vue.prototype.$mySetToken=function(){
  let tokenO=localStorage.getItem("token");
  tokenO=JSON.parse(tokenO);
  if (tokenO==null){
    return
  }
  let time=tokenO['time'];
  let token=tokenO['token'];
  let admin=tokenO['admin'];

  if (admin==null||time<new Date().getTime()){
    this.$router.push({path:'/login',query:{}});
  }
  this.$http.defaults.headers.common['token'] = token
  return admin;
}
//请求后台结束判断全局函数
Vue.prototype.$myRequest= function(res){
  console.log(res);
  if (res.data.message==="请先登陆"){
    this.$message.error("请先登陆");
    this.$router.push({path:'/login',query:{}});
  }
  if (res.data.message==="token 无效"){
    this.$message.error("token 无效");
    this.$router.push({path:'/login',query:{}});
  }
  if(res.data.code===200){
    this.loading=false;
  }
  if (res.data.code===500){
    this.loading=false;
    this.$message.error(res.data.message);
  }
}
//修改日期格式全局函数
Vue.prototype.$dateFormat= function(fmt, date){
  let ret;
  console.log(fmt)
  const opt = {
    "y+": date.getFullYear().toString(),        // 年
    "M+": (date.getMonth() + 1).toString(),     // 月
    "d+": date.getDate().toString(),            // 日
    "H+": date.getHours().toString(),           // 时
    "m+": date.getMinutes().toString(),         // 分
    "S+": date.getSeconds().toString()          // 秒
    // 有其他格式化字符需求可以继续添加，必须转化成字符串
  };
  for (let k in opt) {
    ret = new RegExp("(" + k + ")").exec(fmt);
    if (ret) {
      fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
    };
  };
  return fmt;
}

Vue.use(Vant);

new Vue({
  render: h => h(App),
  router:router,
}).$mount('#app')
