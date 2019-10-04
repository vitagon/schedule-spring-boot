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
      axios.get('/api/schools')
        .then(response => res(response.data));
    });
  }
  
  getAllSchoolsByLocale(localeId: number) {
    return new Promise((res, rej) => {
      axios.get(`/api/schools/locale-id/${localeId}`, {
        }).then(response => res(response.data));
    });
  }

  addSchool(name: string) {
    return new Promise((res, rej) => {
      axios.post(`/api/schools`, {
        name: name
      })
      .then(response => res(response.data))
      .catch(error => rej(error.response.data));
    });
  }

  editSchool(id: Number, name: string) {
    return new Promise((res, rej) => {
      axios.put(`/api/schools/${id}`, {
        name: name
      })
      .then(response => res(response.data))
      .catch(error => rej(error.response.data));
    });
  }

  removeSchool(id: number) {
    return new Promise((res, rej) => {
      axios.delete(`/api/schools/${id}`)
      .then(response => res())
      .catch(error => rej(error.response.data));
    });
  }
}

export default SchoolService.instance;