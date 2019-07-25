import Vue from 'vue'
import Vuex from 'vuex'
import {schoolsStore} from '@/store/SchoolsStore'
import {localesStore} from '@/store/LocalesStore'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    schoolsStore,
    localesStore
  }
})