<template>
  <div>
    <b-form-select
      v-model="selectedLocale"
      :options="locales"
      size="sm" class="mt-3"
      @change="getSchools">
        <template slot="first">
          <option :value="null" disabled>-- Choose locale --</option>
        </template>             
    </b-form-select>

    <b-table responsive striped hover small :items="schools" :fields="fields">
      <template slot="controls" slot-scope="row">
        <b-button squared variant="primary" @click="showEditSchoolForm(row)">Edit</b-button>
        <b-button squared variant="danger" @click="showRemoveSchoolModal(row)">Remove</b-button>
      </template>
    </b-table>
    <b-button block variant="success" @click="showAddSchoolForm()" class="mb-5">Add</b-button>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import EventBus from '@/EventBus';
import axios from 'axios';
import { mapState } from 'vuex';
import SchoolService from '@/services/SchoolService';

@Component({
  computed: {
    ...mapState({
      schools: (state:any) => state.schoolsStore.schools,
      locales: (state:any) => state.localesStore.localesForSelect
    })
  }
})
export default class SchoolsList extends Vue {
  public selectedLocale: any;
  public fields: any;

  constructor() {
    super();
    this.selectedLocale = null;
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
      controls: {
        key: 'controls',
        label: 'Controls',
        sortable: false,
        tdClass: 'school-controls'
      },
    }
  }

  created() {
    this.$store.dispatch('getSchools');
  }
  
  showEditSchoolForm(row) {
    EventBus.$emit('hide-add-school-form');
    EventBus.$emit('show-edit-school-form', row.item);
    EventBus.$emit('show-edit-school-translation-form', row.item);
  }

  showRemoveSchoolModal(row) {
    EventBus.$emit('show-remove-school-modal', row.item);
  }

  showAddSchoolForm() {
    EventBus.$emit('show-add-school-form');
    EventBus.$emit('hide-edit-school-form');
    EventBus.$emit('hide-edit-school-translation-form');
  }
  
  async getSchools() {
    let data = await SchoolService.getAllSchoolsByLocale(this.selectedLocale);
    this.$store.commit('setSchools', data);
  }
}
</script>

<style lang="scss" scoped>
/deep/.school-controls {
  width: 149px;
  min-width: 149px;
}
</style>

