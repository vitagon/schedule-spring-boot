import axios from 'axios'

class SchoolService {
  private static _instance = new SchoolService();
  private constructor() {
  }

  static get instance() {
    return this._instance;
  }

  getAllSchools() {
    return new Promise((res, rej) => {
      axios.get('/api/control/schools')
        .then(response => res(response.data));
    });
  }
  
  getAllSchoolsByLocale(localeId: number) {
    return new Promise((res, rej) => {
      axios.get('/api/control/schools', {
          params: {
            localeId: localeId
          }
        }).then(response => res(response.data));
    });
  }
}

export default SchoolService.instance;