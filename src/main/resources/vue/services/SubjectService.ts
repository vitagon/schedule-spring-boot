import axios from 'axios'

class SubjectService {
  private static _instance = new SubjectService();
  
  private constructor() {}

  static get instance() {
    return this._instance;
  }

  getAll() {
    return new Promise((res, rej) => {
      axios.get('/api/subjects')
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  getAllByLocale(localeId: Number) {
    return new Promise((res, rej) => {
      axios.get(`/api/subjects/locale-id/${localeId}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response.data));
    });
  }

  create(name: string) {
    return new Promise((res, rej) => {
      axios.post(`/api/subjects`, {name: name})
        .then(response => res(response.data))
        .catch(error => rej(error.response.data));
    });
  }

  update(id: Number, name: string) {
    return new Promise((res, rej) => {
      axios.put(`/api/subjects/${id}`, {name: name})
        .then(response => res(response.data))
        .catch(error => rej(error.response.data));
    });
  }

  delete(id: Number) {
    return new Promise((res, rej) => {
      axios.delete(`/api/subjects/${id}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response.data));
    });
  }
}

export default SubjectService.instance;