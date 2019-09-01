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

  addSchool(name: string) {
    return new Promise((res, rej) => {
      axios({
        method: 'POST',
        url: '/api/control/school',
        data: { name: name}
      })
      .then(response => res(response.data))
      .catch(error => rej(error.response.data));
    });
  }

  removeSchool(id: number) {
    return new Promise((res, rej) => {
      axios.delete('/api/control/school', {
        params: {
          id: id
        }
      }).then(response => res());
    });
  }
}

export default SchoolService.instance;