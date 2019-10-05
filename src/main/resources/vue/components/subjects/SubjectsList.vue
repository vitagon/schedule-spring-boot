<template>
  <div>
    <b-form-select
      v-model="selectedLocale"
      :options="locales"
      size="sm" class="mt-3"
      @change="getSubjects">
        <template slot="first">
          <option :value="null" disabled>-- Choose locale --</option>
        </template>             
    </b-form-select>

    <b-table responsive striped hover small :items="subjects" :fields="fields">
      <template slot="controls" slot-scope="row">
        <b-button squared variant="primary" @click="showEditSubjectForm(row)">Edit</b-button>
        <b-button squared variant="danger" @click="showRemoveSubjectModal(row)">Remove</b-button>
      </template>
    </b-table>
    <b-button block variant="success" @click="showAddSubjectForm()" class="mb-5">Add</b-button>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import EventBus from '@/EventBus';
import axios from 'axios';
import { mapState } from 'vuex';
import SubjectService from '@/services/SubjectService';

@Component({
  computed: {
    ...mapState({
      subjects: (state:any) => state.subjectsStore.subjects,
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
export default class SubjectsList extends Vue {
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
        tdClass: 'subject-controls'
      },
    }
  }

  created() {
    this.$store.dispatch('getSubjects');
    let _this = this;

    EventBus.$on('subject-translation-was-changed', function (subjectTranslation) {
      if (subjectTranslation.localeId == _this.selectedLocale) {
        _this.$store.commit('updateSubjectTranslation', subjectTranslation);
      }
    });
  }
  
  showEditSubjectForm(row) {
    EventBus.$emit('hide-add-subject-form');
    EventBus.$emit('show-edit-subject-form', row.item);
    EventBus.$emit('show-edit-subject-translation-form', row.item);
  }

  showRemoveSubjectModal(row) {
    EventBus.$emit('show-remove-subject-modal', row.item);
  }

  showAddSubjectForm() {
    EventBus.$emit('show-add-subject-form');
    EventBus.$emit('hide-edit-subject-form');
    EventBus.$emit('hide-edit-subject-translation-form');
  }
  
  async getSubjects() {
    let data = await SubjectService.getAllByLocale(this.selectedLocale);
    this.$store.commit('setSubjects', data);
  }
}
</script>

<style lang="scss" scoped>
/deep/.subject-controls {
  width: 149px;
  min-width: 149px;
}
</style>