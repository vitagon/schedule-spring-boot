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
}

export default SchoolTranslationService.instance;