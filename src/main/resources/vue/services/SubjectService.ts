import axios from 'axios'

class SubjectService {
  private static _instance = new SubjectService();
  
  private constructor() {}

  static get instance() {
    return this._instance;
  }

  getAllSubjects() {
    return new Promise((res, rej) => {
      axios.get('/api/control/subjects')
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }
}

export default SubjectService.instance;