import axios from 'axios'


class LocaleService {
  private static _instance = new LocaleService();
  private constructor() {}

  static get instance() {
    return this._instance;
  }

  getAllLocales() {
    return new Promise((res, rej) => {
      axios.get('/api/locales')
        .then(response => res(response.data));
    });
  }
}

export default LocaleService.instance;