<template>
  <div>
    <label for="schoolSelect">Get majors by school</label>
    <b-form-select
      v-model="selectedSchool"
      :options="schools"
      size="sm"
      id="schoolSelect"
      @change="getMajorsBySchoolId">
        <template slot="first">
          <option :value="null" disabled>-- Choose school --</option>
        </template>             
    </b-form-select>

    <b-form-select
      v-model="selectedLocale"
      :options="locales"
      size="sm" class="mt-3"
      @change="getMajorsByLocaleId">
        <template slot="first">
          <option :value="null" disabled>-- Choose locale --</option>
        </template>             
    </b-form-select>

    <b-table responsive striped hover small :items="majors" :fields="fields">
      <template slot="controls" slot-scope="row">
        <b-button squared variant="primary" @click="showEditMajorForm(row)">Edit</b-button>
        <b-button squared variant="danger" @click="showRemoveMajorModal(row)">Remove</b-button>
      </template>
    </b-table>
    <b-button block variant="success" @click="showAddMajorForm()" class="mb-5">Add</b-button>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import EventBus from '@/EventBus';
import axios from 'axios';
import { mapState } from 'vuex';
import MajorService from '@/services/MajorService';

@Component({
  computed: {
    ...mapState({
      schools: (state: any) => {
        return state.schoolsStore.schools.map(function (school) {
          return {value: school.id, text: school.name};
        });
      },
      majors: (state:any) => state.majorsStore.majors,
      locales: function(state:any) {
        let localesForSelect = state.localesStore.localesForSelect;
        for (let locale of localesForSelect) {
          if (locale.active) {
            this.selectedLocale = locale.value;
          }
        }
        return localesForSelect;
      }
    })
  }
})
export default class MajorList extends Vue {
  public selectedLocale: any;
  public selectedSchool: any;
  public fields: any;

  constructor() {
    super();
    this.selectedLocale = null;
    this.selectedSchool = null;
    this.fields = {
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
      duration: {
        key: 'duration',
        label: 'Duration',
        sortable: true
      },
      degree: {
        key: 'degree',
        label: 'Degree',
        sortable: true
      },
      controls: {
        key: 'controls',
        label: 'Controls',
        sortable: false,
        tdClass: 'major-controls'
      },
    }
  }

  created() {
    this.$store.dispatch('getMajors');
    let _this = this;

    EventBus.$on('major-translation-was-changed', function (majorTranslation) {
      if (majorTranslation.localeId == _this.selectedLocale) {
        _this.$store.commit('updateMajorTranslation', majorTranslation);
      }
    });
  }
  
  showEditMajorForm(row) {
    EventBus.$emit('hide-add-major-form');
    EventBus.$emit('show-edit-major-form', row.item);
    EventBus.$emit('show-edit-major-translation-form', row.item);
  }

  showRemoveMajorModal(row) {
    EventBus.$emit('show-remove-major-modal', row.item);
  }

  showAddMajorForm() {
    EventBus.$emit('show-add-major-form');
    EventBus.$emit('hide-edit-major-form');
    EventBus.$emit('hide-edit-major-translation-form');
  }

  async getMajorsBySchoolId() {
    let data = await MajorService.getAllBySchoolId(this.selectedSchool);
    this.$store.commit('setMajors', data);
  }
  
  async getMajorsByLocaleId() {
    let data = await MajorService.getAllByLocaleId(this.selectedLocale);
    this.$store.commit('setMajors', data);
  }
}
</script>

<style lang="scss" scoped>
/deep/.major-controls {
  width: 149px;
  min-width: 149px;
}
</style>