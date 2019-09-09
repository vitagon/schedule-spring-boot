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
  /* 
  updateSubjectName(state: any, updateSubjectDto: any) {
    let updatedIndex = state.subjects.findIndex((x: any) => x.id == updateSubjectDto.subjectId);
    let subject = state.subjects[updatedIndex];
    subject.name = updateSubjectDto.newSubjectName;

    state.subjects = [
      ...state.subjects.slice(0, updatedIndex),
      subject,
      ...state.subjects.slice(updatedIndex + 1)
    ]
  },
  */
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
      let data = await SubjectService.getAllSubjects();
      commit('setSubjects', data);
      return data;
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