import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

import login from '../views/login';
import index from '../views/index/index';

const routes=[
    {path:"/",component:index},
    {path:"/login",component:login}
]

const router=new Router({
    routes
});

export default router
