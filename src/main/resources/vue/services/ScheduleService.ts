import axios from 'axios'

class ScheduleService {
  private static _instance = new ScheduleService();

  private constructor() {}

  static get instance() {
    return this._instance;
  }

  getSchedule(groupId) {
    return new Promise((res, rej) => {
      axios.get(`/api/schedule/group-id/${groupId}`)
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }

  addSchedule(schedule) {
    return new Promise((res, rej) => {
      axios.post('/api/schedule', schedule)
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }

  updateSchedule(scheduleId, schedule) {
    return new Promise((res, rej) => {
      axios.put(`/api/schedule/${scheduleId}`, schedule)
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }
}

export default ScheduleService.instance;