import axios from 'axios'
import Group from '@/models/Group';

class GroupService {
  private static _instance = new GroupService();

  private constructor() {}

  static get instance() {
    return this._instance;
  }

  getAll() {
    return new Promise((res, rej) => {
      axios.get(`/api/groups`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  getGroups(majorId, courseNum) {
    return new Promise((res, rej) => {
      axios.get(`/api/groups/major-id/${majorId}/course-num/${courseNum}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  getGroupsByMajorId(majorId) {
    return new Promise((res, rej) => {
      axios.get(`/api/groups/major-id/${majorId}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  getGroupsByMajorIdAndLocaleId(majorId, localeId) {
    return new Promise((res, rej) => {
      axios.get(`/api/groups/major-id/${majorId}/locale-id/${localeId}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  getByGroupIdAndLocaleId(groupId, localeId) {
    return new Promise((res, rej) => {
      axios.get(`/api/groups/${groupId}/locale-id/${localeId}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  getByGroupId(groupId) {
    return new Promise((res, rej) => {
      axios.get(`/api/groups/${groupId}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  create(group: Group) {
    return new Promise((res, rej) => {
      axios.post(`/api/groups`, group)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  edit(group: Group) {
    return new Promise((res, rej) => {
      axios.put(`/api/groups/${group.id}`, group)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  delete(groupId: Number) {
    return new Promise((res, rej) => {
      axios.delete(`/api/groups/${groupId}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }
}

export default GroupService.instance;