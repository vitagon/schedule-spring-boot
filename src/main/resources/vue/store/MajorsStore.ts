import MajorService from '@/services/MajorService'
import Major from '@/models/Major';

const state = {
  majors: [],
  degrees: []
}

const mutations = {
  setMajors(state: any, majors: Array<Major>) {
    state.majors = majors;
  },
  setDegrees(state: any, degrees) {
    state.degrees = degrees;
  },
  addMajor(state: any, major: Major) {
    state.majors = [
      ...state.majors,
      major
    ]
  },
  removeMajor(state: any, majorId: Number) {
    let removedIndex = state.majors.findIndex((x: any) => x.id == majorId);
    state.majors = [
      ...state.majors.slice(0, removedIndex),
      ...state.majors.slice(removedIndex + 1)
    ]
  },
  updateMajor(state: any, updateMajorDto: any) {
    let updatedIndex = state.majors.findIndex((x: any) => x.id == updateMajorDto.id);
    let major = state.majors[updatedIndex];
    major.name = updateMajorDto.name;
    major.schoolId = updateMajorDto.school;
    major.degree = updateMajorDto.degree;
    major.duration = updateMajorDto.duration;

    state.majors = [
      ...state.majors.slice(0, updatedIndex),
      major,
      ...state.majors.slice(updatedIndex + 1)
    ]
  },
  updateMajorTranslation(state: any, majorTranslation: any) {
    let updatedIndex = state.majors.findIndex((x: any) => x.id == majorTranslation.majorId);
    let major = state.majors[updatedIndex];
    major.translation = majorTranslation.translation;

    state.majors = [
      ...state.majors.slice(0, updatedIndex),
      major,
      ...state.majors.slice(updatedIndex + 1)
    ]
  }
}

const actions = {
    async getMajors({ commit }) {
      let data = await MajorService.getAll();
      commit('setMajors', data);
      return data;
    },
    async getDegrees({ commit }) {
      let data = await MajorService.getDegrees();
      commit('setDegrees', data);
    },
    async removeMajor({ commit }, id) {
      await MajorService.remove(id);
      commit('removeMajor', id);
    }
}

const getters = { 
}

export const majorsStore = {
  state,
  mutations,
  actions,
  getters
}