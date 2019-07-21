<template>
  <div>
    <b-form-select v-model="selectedLocale"
                   :options="locales"
                   size="sm" class="mt-3"
                   @change="getSchools"></b-form-select>

    <b-table responsive striped hover small :items="schools" :fields="fields">
      <template slot="controls" slot-scope="row">
        <b-button squared variant="primary" @click="emit('open-edit-school', row)">Edit</b-button>
        <b-button squared variant="danger">Remove</b-button>
      </template>
    </b-table>
    <b-button block variant="success">Add</b-button>
  </div>
</template>

<script>
import EventBus from 'EventBus.js'
import axios from 'axios'

export default {
  props: {},
  created() {
    let _this = this;

    EventBus.$on('gotLocales', function (locales) {
        _this.localesResp = locales;
        _this.locales = _this.localesResp.map(function (locale) {
            return {value: locale.id, text: locale.code};
        });
        _this.locales.unshift({value: null, text: 'Choose locale'})
    });

    axios.get('https://localhost:8081/api/control/schools')
      .then(response => {
          this.schools = response.data
      })
  },
  data() {
    return {
      schools: [],
      locales: [],
      localesResp: [],
      selectedLocale: null,
      fields: {
        id: {
          label: '#',
          sortable: true
        },
        name: {
          label: 'Name',
          sortable: true
        },
        translation: {
          key: 'translation',
          label: 'Translation',
          sortable: true
        },
        controls: {
          key: 'controls',
          label: 'Controls',
          sortable: false,
          tdClass: 'school-controls'
        },
      }
    }
  },
  methods: {
    emit(event, row) {
      EventBus.$emit(event, row.item)
    },
    getSchools() {
      let _this = this;
      axios.get('https://localhost:8081/api/control/schools', {
        params: {
          localeId: _this.selectedLocale
        }
      }).then(response => {
        this.schools = response.data
      });
    }
  }
}
</script>

<style lang="scss" scoped>
/deep/.school-controls {
  width: 149px;
  min-width: 149px;
}
</style>

