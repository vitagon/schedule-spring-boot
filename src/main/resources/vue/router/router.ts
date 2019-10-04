import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/components/main/Main.vue'
import Schools from '@/components/schools/Schools.vue'
import MainRouter from  '@/components/MainRouter.vue'
import Schedule from '@/components/schedule/Schedule.vue'
import Majors from '@/components/majors/Majors.vue'
import Groups from '@/components/groups/Groups.vue'
import Subjects from '@/components/subjects/Subjects.vue'

Vue.use(VueRouter)

const routes = [
    { path: '', component: Main },
    { path: 'schools', component: Schools },
    { path: 'schedule', component: Schedule },
    { path: 'majors', component: Majors },
    { path: 'groups', component: Groups },
    { path: 'subjects', component: Subjects }
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