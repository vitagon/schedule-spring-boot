<template>
  <div>
    <b-form-select v-model="selectedLocale"
                   :options="locales"
                   size="sm" class="mt-3"
                   @change="getSchools">
        <template slot="first">
          <option :value="null" disabled>-- Choose locale --</option>
        </template>             
      </b-form-select>

    <b-table responsive striped hover small :items="schools" :fields="fields">
      <template slot="controls" slot-scope="row">
        <b-button squared variant="primary" @click="emit('open-edit-school', row)">Edit</b-button>
        <b-button squared variant="danger">Remove</b-button>
      </template>
    </b-table>
    <b-button block variant="success">Add</b-button>
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
  
  emit(event, row) {
    EventBus.$emit(event, row.item);
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

