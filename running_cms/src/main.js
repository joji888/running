import Vue from 'vue'
import router from "./router/router";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import axios from "axios";

Vue.config.productionTip = false

axios.defaults.headers.common['token'] = '';
axios.defaults.baseURL='/api';

Vue.prototype.$http= axios

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
  if (res.data.massage==="请先登陆"){
    this.$message.error("请先登陆");
    this.$router.push({path:'/login',query:{}});
  }
  if (res.data.massage==="token 无效"){
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

Vue.use(ElementUI);

new Vue({
  render: h => h(App),
  router:router,
}).$mount('#app')
