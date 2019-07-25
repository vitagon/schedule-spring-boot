import School from '@/models/School';
import SchoolService from '@/services/SchoolService'

const state = {
  schools: []
}

const mutations = {
  setSchools(state: any, schools: Array<School>) {
    state.schools = schools;
  },
  updateSchoolName(state: any, updateSchoolDto: any) {
    let updatedIndex = state.schools.findIndex((x: any) => x.id == updateSchoolDto.schoolId);
    let school = state.schools[updatedIndex];
    school.name = updateSchoolDto.newSchoolName;

    state.schools = [
      ...state.schools.slice(0, updatedIndex),
      school,
      ...state.schools.slice(updatedIndex + 1)
    ]
  }
}

const actions = {
    async getSchools({ commit }) {
      let data = await SchoolService.getAllSchools();
      commit('setSchools', data);
      return data;
    }
}

const getters = { 
}

export const schoolsStore = {
  state,
  mutations,
  actions,
  getters
}