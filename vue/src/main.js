import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/css/global.css'
import '@/assets/fonts/font-awesome.css'
import '@/assets/css/theme/index.css'
import request from "@/utils/request";

Vue.config.productionTip = false

// request工具类
Vue.prototype.$request = request
//定义URL，打包上线时使用
Vue.prototype.$baseUrl = process.env.VUE_APP_BASEURL

Vue.use(ElementUI, {size: "small"})

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
