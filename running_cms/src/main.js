import Vue from 'vue'
import router from "./router/router";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import axios from "axios";

Vue.config.productionTip = false

axios.defaults.headers.common['token'] = '';
// axios.defaults.baseURL='/api';

Vue.prototype.$http= axios

Vue.use(ElementUI);

new Vue({
  render: h => h(App),
  router:router,
}).$mount('#app')
