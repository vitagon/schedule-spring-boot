import axios from 'axios'

class MajorService {
  private static _instance = new MajorService();

  private constructor() {}

  static get instance() {
    return this._instance;
  }

  getMajors(schoolId) {
    return new Promise((res, rej) => {
      axios.get(`/api/majors/school-id/${schoolId}`)
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }

  getMaxCourseNumber(majorId) {
    return new Promise((res, rej) => {
      axios.get(`/api/major/${majorId}/max-course-number`)
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }
}

export default MajorService.instance;