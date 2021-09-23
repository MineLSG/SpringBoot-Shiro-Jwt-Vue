import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Ant from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import axios from "axios";

Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.use(Ant)

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
