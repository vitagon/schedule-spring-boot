import Vue from 'vue'
import Vuex from 'vuex'
import {schoolsStore} from '@/store/SchoolsStore'
import {localesStore} from '@/store/LocalesStore'
import {subjectsStore} from '@/store/SubjectsStore'
import {scheduleStore} from '@/store/ScheduleStore'
import {teachersStore} from '@/store/TeachersStore'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    schoolsStore,
    localesStore,
    subjectsStore,
    scheduleStore,
    teachersStore
  }
})