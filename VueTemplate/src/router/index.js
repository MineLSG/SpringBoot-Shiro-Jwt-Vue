import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/Login";
import Manager from "@/views/Manager";
import UserInfo from "@/components/UserComponent/UserInfo";
import UserUpdate from "@/components/UserComponent/UserUpdate";
import DataModify from "@/components/DataComponent/DataModify";
import DataList from "@/components/DataComponent/DataList";

Vue.use(VueRouter)

const routes = [

    {
        path: '/',
        name: 'Login',
        component: Login
    },
    {
        path: '/manager',
        name: 'manager',
        component: Manager,
        children: [
            {
                path: '/userinfo',
                component: UserInfo
            },
            {
                path: '/userupdate',
                component: UserUpdate
            },
            {
                path: '/datamodify',
                component: DataModify
            },
            {
                path: '/datalist',
                component: DataList
            }
        ]
    }
]

const router = new VueRouter({
    routes
})

export default router
