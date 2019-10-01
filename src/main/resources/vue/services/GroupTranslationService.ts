import axios from 'axios'

class GroupTranslationService {

  private static _instance = new GroupTranslationService();

  private constructor() {
  }

  static get instance() {
    return this._instance;
  }

  getTranslation(groupId, localeId) {
    return new Promise((res, rej) => {
      axios.get(`/api/translations/groups/${groupId}/locales/${localeId}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  addTranslation(groupId, localeId, translation) {
    return new Promise((res, rej) => {
      axios.post(`/api/translations/groups/${groupId}/locales/${localeId}`, {
          groupId: groupId,
          localeId: localeId,
          translation: translation
        },
        {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  editTranslation(groupId, localeId, translation) {
    return new Promise((res, rej) => {
      axios.put(`/api/translations/groups/${groupId}/locales/${localeId}`, {
          groupId: groupId,
          localeId: localeId,
          translation: translation
        },
        {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  removeTranslation(groupId, localeId) {
    return new Promise((res, rej) => {
      axios.delete(`/api/translations/groups/${groupId}/locales/${localeId}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }
}

export default GroupTranslationService.instance;