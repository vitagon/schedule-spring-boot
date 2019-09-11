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
  removeLesson(state: any, lessonPath: {week: string, dayName: string, lesson: Lesson}) {
    let lessonList =  state.schedule[lessonPath.week][lessonPath.dayName];
    let lessonIndex = lessonList.findIndex(x => x.lessonNum == lessonPath.lesson.lessonNum);
    let lesson = new Lesson();
    lesson.lessonNum = lessonPath.lesson.lessonNum;
    Vue.set(state.schedule[lessonPath.week][lessonPath.dayName], lessonIndex, lesson);
  }
}

const actions = {
  async getSchedule({ commit }, groupId) {
    let schedule;
    try {
      schedule = await ScheduleService.getSchedule(groupId);
    } catch (e) { console.error(e) }
    
    commit('setSchedule', schedule);
  },
  async removeLesson({ commit }, lessonWithPath: {week: string, dayName: string, lesson: Lesson}) {
    try {
      await ScheduleService.removeLesson(lessonWithPath.lesson.id);
    } catch (e) { console.error(e) }

    commit('removeLesson', lessonWithPath);
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