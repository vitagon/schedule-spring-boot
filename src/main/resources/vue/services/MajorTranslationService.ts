import axios from 'axios'

class MajorTranslationService {

  private static _instance = new MajorTranslationService();

  private constructor() {
  }

  static get instance() {
    return this._instance;
  }

  getTranslation(majorId, localeId) {
    return new Promise((res, rej) => {
      axios.get(`/api/translations/majors/${majorId}/locales/${localeId}`)
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }

  addTranslation(majorId, localeId, translation) {
    return new Promise((res, rej) => {
      axios.post(`/api/translations/majors/${majorId}/locales/${localeId}`, {
          majorId: majorId,
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

  editTranslation(majorId, localeId, translation) {
    return new Promise((res, rej) => {
      axios.put(`/api/translations/majors/${majorId}/locales/${localeId}`, {
          majorId: majorId,
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

  removeTranslation(majorId, localeId) {
    return new Promise((res, rej) => {
      axios.delete(`/api/translations/majors/${majorId}/locales/${localeId}`)
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }
}

export default MajorTranslationService.instance;