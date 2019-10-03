import UserService from '@/services/UserService'

const state = {
  teachers: []
}

const mutations = {
  setTeachers(state: any, teachers) {
    state.teachers = teachers;
  }
}

const actions = {
    async getTeachers({ commit }) {
      let response: any = await UserService.getUsersByRole('teacher');
      commit('setTeachers', response);
      return response.data;
    }
}

const getters = { 
}

export const teachersStore = {
  state,
  mutations,
  actions,
  getters
}