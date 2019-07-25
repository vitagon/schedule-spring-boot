import Locale from '@/models/Locale';
import LocaleService from '@/services/LocaleService';

const state = {
  locales: [],
  localesForSelect: []
}

const mutations = {
  setLocales(state: any, locales: Array<Locale>) {
    state.locales = locales;
  },
  setLocalesForSelect(state: any, locales: Array<Locale>) {
    state.localesForSelect = locales.map(function (locale) {
      return {value: locale.id, text: locale.code};
    });
  }
}

const actions = {
    async getLocales({ commit }) {
      let data = await LocaleService.getAllLocales();
      commit('setLocales', data);
      commit('setLocalesForSelect', data);
      return data;
    }
}

const getters = { 
}

export const localesStore = {
  state,
  mutations,
  actions,
  getters
}