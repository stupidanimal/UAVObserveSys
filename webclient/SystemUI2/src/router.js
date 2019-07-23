import Router from 'vue-router'
import Vue from 'vue'

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: '/',
            component: {}
        },
        {
            path: '/map',
            name: 'map',
            component: () => import('@/pages/shipLineMap/ShipLineMapPanel')
        },
        {
            path: '/page1',
            name: 'page1',
            component: () => import('@/pages/Page')
        },
        {
            path: '/page2',
            name: 'page2',
            component: () => import('@/pages/Page2')
        }
    ]
});
