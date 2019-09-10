import ScheduleService from '@/services/ScheduleService'
import Vue from 'vue'
import Lesson from '@/models/Lesson'

const state = {
  schedule: {}
}

const mutations = {
  setSchedule(state: any, schedule: Object) {
    state.schedule = schedule;
  },
  addLesson(state: any,  lessonWithPath: {week: string, dayName: string, lesson: Lesson}) {
    let lessonList =  state.schedule[lessonWithPath.week][lessonWithPath.dayName];
    let lessonIndex = lessonList.findIndex(x => x.lessonNum == lessonWithPath.lesson.lessonNum);
    Vue.set(state.schedule[lessonWithPath.week][lessonWithPath.dayName], lessonIndex, lessonWithPath.lesson);
  },
  // removeLesson(state: any, schoolId: number) {
  //   let removedIndex = state.schedule.findIndex((x: any) => x.id == schoolId);
  //   state.schools = [
  //     ...state.schools.slice(0, removedIndex),
  //     ...state.schools.slice(removedIndex + 1)
  //   ]
  // }
  // updateSchoolName(state: any, updateSchoolDto: any) {
  //   let updatedIndex = state.schools.findIndex((x: any) => x.id == updateSchoolDto.schoolId);
  //   let school = state.schools[updatedIndex];
  //   school.name = updateSchoolDto.newSchoolName;

  //   state.schools = [
  //     ...state.schools.slice(0, updatedIndex),
  //     school,
  //     ...state.schools.slice(updatedIndex + 1)
  //   ]
  // },
  // updateSchoolTranslation(state: any, schoolTranslation: any) {
  //   let updatedIndex = state.schools.findIndex((x: any) => x.id == schoolTranslation.schoolId);
  //   let school = state.schools[updatedIndex];
  //   school.translation = schoolTranslation.translation;

  //   state.schools = [
  //     ...state.schools.slice(0, updatedIndex),
  //     school,
  //     ...state.schools.slice(updatedIndex + 1)
  //   ]
  // }
}

const actions = {
  async getSchedule({ commit }, groupId) {
    let schedule;
    try {
      schedule = await ScheduleService.getSchedule(groupId);
    } catch (e) { console.error(e) }
    
    commit('setSchedule', schedule);
    return schedule;
  }
}

const getters = { 
}

export const scheduleStore = {
  state,
  mutations,
  actions,
  getters
}