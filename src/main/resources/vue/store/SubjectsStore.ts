import SubjectService from '@/services/SubjectService'
import Subject from '@/models/Subject';

const state = {
  subjects: []
}

const mutations = {
  setSubjects(state: any, subjects: Array<Subject>) {
    state.subjects = subjects;
  },
  addSubject(state: any, subject: Subject) {
    state.subjects = [
      ...state.subjects,
      subject
    ]
  },
  removeSubject(state: any, subjectId: number) {
    let removedIndex = state.subjects.findIndex((x: any) => x.id == subjectId);
    state.subjects = [
      ...state.subjects.slice(0, removedIndex),
      ...state.subjects.slice(removedIndex + 1)
    ]
  },
  updateSubjectName(state: any, updateSubjectDto: any) {
    let updatedIndex = state.subjects.findIndex((x: any) => x.id == updateSubjectDto.id);
    let subject = state.subjects[updatedIndex];
    subject.name = updateSubjectDto.name;

    state.subjects = [
      ...state.subjects.slice(0, updatedIndex),
      subject,
      ...state.subjects.slice(updatedIndex + 1)
    ]
  },
  updateSubjectTranslation(state: any, subjectTranslation: any) {
    let updatedIndex = state.subjects.findIndex((x: any) => x.id == subjectTranslation.subjectId);
    let subject = state.subjects[updatedIndex];
    subject.translation = subjectTranslation.translation;

    state.subjects = [
      ...state.subjects.slice(0, updatedIndex),
      subject,
      ...state.subjects.slice(updatedIndex + 1)
    ]
  }
}

const actions = {
    async getSubjects({ commit }) {
      let data;
      try {
        data = await SubjectService.getAll();
      } catch (error) {
        console.error(error);
        return;
      }
      
      commit('setSubjects', data);
      return data;
    },
    async removeSubject({commit}, id) {
      try {
        await SubjectService.delete(id);
      } catch (error) {
        console.error(error);
        return;
      }
      commit('removeSubject', id);
    }
}

const getters = { 
}

export const subjectsStore = {
  state,
  mutations,
  actions,
  getters
}