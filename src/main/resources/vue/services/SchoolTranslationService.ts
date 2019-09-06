import axios from 'axios'

class SchoolTranslationService {

  private static _instance = new SchoolTranslationService();

  private constructor() {
  }

  static get instance() {
    return this._instance;
  }

  getTranslation(schoolId, localeId) {
    return new Promise((res, rej) => {
      axios.get(`/api/translations/schools/${schoolId}/locales/${localeId}`)
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }

  addTranslation(schoolId, localeId, translation) {
    return new Promise((res, rej) => {
      axios.post(`/api/translations/schools/${schoolId}/locales/${localeId}`, {
          schoolId: schoolId,
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

  editTranslation(schoolId, localeId, translation) {
    return new Promise((res, rej) => {
      axios.put(`/api/translations/schools/${schoolId}/locales/${localeId}`, {
          schoolId: schoolId,
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

  removeTranslation(schoolId, localeId) {
    return new Promise((res, rej) => {
      axios.delete(`/api/translations/schools/${schoolId}/locales/${localeId}`)
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }
}

export default SchoolTranslationService.instance;