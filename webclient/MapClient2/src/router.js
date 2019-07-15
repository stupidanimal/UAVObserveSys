import Vue from 'vue'
import Router from "vue-router"

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'default',
            conponent: () => import('@/components/DefaultPanel'),
        }
        ,
        {
            path: '/Map',
            name: 'map',
            component: () => import('@/components/subcomponents/MainMap')
        }
    ]
})