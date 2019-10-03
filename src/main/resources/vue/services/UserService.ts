import axios from 'axios'

class UserService {
  private static _instance = new UserService();
  
  private constructor() {}

  static get instance() {
    return this._instance;
  }

  getUsersByRole(role) {
    return new Promise((res, rej) => {
      axios.get(`/api/users/role/${role}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }
}

export default UserService.instance;