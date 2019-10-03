import axios from 'axios'
import MajorDto from '@/models/MajorDto';

class MajorService {
  private static _instance = new MajorService();

  private constructor() {}

  static get instance() {
    return this._instance;
  }

  getAll() {
    return new Promise((res, rej) => {
      axios.get(`/api/control/majors`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  getDegrees() {
    return new Promise((res, rej) => {
      axios.get('/api/degrees')
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  getAllBySchoolId(schoolId) {
    return new Promise((res, rej) => {
      axios.get(`/api/majors/school-id/${schoolId}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  getAllByLocaleId(localeId) {
    return new Promise((res, rej) => {
      axios.get(`/api/control/majors/locale-id/${localeId}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  getMaxCourseNumber(majorId) {
    return new Promise((res, rej) => {
      axios.get(`/api/major/${majorId}/max-course-number`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  add(majorDto: MajorDto) {
    return new Promise((res, rej) => {
      axios.post(`/api/control/majors`, majorDto)
        .then(response => res(response.data))
        .catch(error => rej(error.response.data));
    });
  }
  
  edit(majorDto: MajorDto) {
    return new Promise((res, rej) => {
      axios.put(`/api/control/majors`, majorDto)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  remove(majorId) {
    return new Promise((res, rej) => {
      axios.delete(`/api/control/majors`, {
          params: {
            id: majorId
          }
        })
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }
}

export default MajorService.instance;