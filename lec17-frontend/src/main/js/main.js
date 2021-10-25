import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueResource from 'vue-resource'
import router from 'router/router.js'
import App from '../pages/App.vue'
import locale from 'element-ui/lib/locale/lang/ru-RU'

Vue.use(ElementUI, { locale })
Vue.use(VueResource)
Vue.prototype.$eventBus = new Vue({})

new Vue({
    el: '#app',
    router,
    render: a => a(App)
})