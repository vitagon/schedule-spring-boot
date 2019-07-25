import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from 'components/main/Main.vue'
import Schools from 'components/schools/Schools.vue'
import MainRouter from  'components/MainRouter.vue'

Vue.use(VueRouter)

const routes = [
    { path: '', component: Main },
    { path: 'schools', component: Schools }
]

export default new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/control',
            component: MainRouter,
            children: routes
        }
    ]
})