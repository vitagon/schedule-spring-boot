import School from '@/models/School';
import SchoolService from '@/services/SchoolService'

const state = {
  schools: []
}

const mutations = {
  setSchools(state: any, schools: Array<School>) {
    state.schools = schools;
  },
  addSchool(state: any, school: School) {
    state.schools = [
      ...state.schools,
      school
    ]
  },
  removeSchool(state: any, schoolId: number) {
    let removedIndex = state.schools.findIndex((x: any) => x.id == schoolId);
    state.schools = [
      ...state.schools.slice(0, removedIndex),
      ...state.schools.slice(removedIndex + 1)
    ]
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
  },
  updateSchoolTranslation(state: any, schoolTranslation: any) {
    let updatedIndex = state.schools.findIndex((x: any) => x.id == schoolTranslation.schoolId);
    let school = state.schools[updatedIndex];
    school.translation = schoolTranslation.translation;

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
    },
    async removeSchool({ commit }, id) {
      await SchoolService.removeSchool(id);
      commit('removeSchool', id);
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