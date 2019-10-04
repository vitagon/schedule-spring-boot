import axios from 'axios'

class SubjectTranslationService {

  private static _instance = new SubjectTranslationService();

  private constructor() {
  }

  static get instance() {
    return this._instance;
  }

  getTranslation(subjectId, localeId) {
    return new Promise((res, rej) => {
      axios.get(`/api/translations/subjects/${subjectId}/locales/${localeId}`)
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }

  addTranslation(subjectId, localeId, translation) {
    return new Promise((res, rej) => {
      axios.post(`/api/translations/subjects/${subjectId}/locales/${localeId}`, {
          subjectId: subjectId,
          localeId: localeId,
          translation: translation
        },
        {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }

  editTranslation(subjectId, localeId, translation) {
    return new Promise((res, rej) => {
      axios.put(`/api/translations/subjects/${subjectId}/locales/${localeId}`, {
          subjectId: subjectId,
          localeId: localeId,
          translation: translation
        },
        {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }

  removeTranslation(subjectId, localeId) {
    return new Promise((res, rej) => {
      axios.delete(`/api/translations/subjects/${subjectId}/locales/${localeId}`)
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }
}

export default SubjectTranslationService.instance;