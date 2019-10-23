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
      axios.get(`/api/majors`)
        .then(response => res(response.data))
        .catch(error => rej(error.response.data));
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
        .catch(error => rej(error.response.data));
    });
  }

  getAllByLocaleId(localeId) {
    return new Promise((res, rej) => {
      axios.get(`/api/majors/locale-id/${localeId}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response.data));
    });
  }

  getMaxCourseNumber(majorId) {
    return new Promise((res, rej) => {
      axios.get(`/api/majors/${majorId}/max-course-number`)
        .then(response => res(response.data))
        .catch(error => rej(error.response.data));
    });
  }

  add(majorDto: MajorDto) {
    return new Promise((res, rej) => {
      axios.post(`/api/majors`, majorDto)
        .then(response => res(response.data))
        .catch(error => rej(error.response.data));
    });
  }
  
  edit(majorDto: MajorDto) {
    return new Promise((res, rej) => {
      axios.put(`/api/majors/${majorDto.id}`, majorDto)
        .then(response => res(response.data))
        .catch(error => rej(error.response.data));
    });
  }

  remove(majorId) {
    return new Promise((res, rej) => {
      axios.delete(`/api/majors/${majorId}`)
        .then(response => res(response))
        .catch(error => rej(error.response.data));
    });
  }
}

export default MajorService.instance;