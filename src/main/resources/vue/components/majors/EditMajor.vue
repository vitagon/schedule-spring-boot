<template>
  <div v-if="isOpened" class="mt-5">
    <h5>Edit major</h5>
    <hr />
    <b-form  @submit.stop.prevent>
        <label for="name">Name</label>
        <b-input v-model="form.name.value" :state="form.name.isValid" id="name"></b-input>
        <b-form-invalid-feedback :state="form.name.isValid">
          <div v-for="validationMsg in form.name.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
        </b-form-invalid-feedback>

        <label for="duration">Duration</label>
        <b-form-select
          v-model="form.duration.value"
          :options="Array.from(Array(7).keys()).map(num => { let course = num++;return {value: course, text: course}})"
          :state="form.duration.isValid"
          size="sm"
          id="duration"
          >
            <template slot="first">
              <option :value="null" disabled>-- Choose --</option>
            </template>            
        </b-form-select>
        <b-form-invalid-feedback :state="form.duration.isValid">
          <div v-for="validationMsg in form.duration.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
        </b-form-invalid-feedback>

        <label for="degree">Degree</label>
        <b-form-select
          v-model="form.degree.value"
          :options="degrees"
          :state="form.degree.isValid"
          size="sm"
          id="degree">
            <template slot="first">
              <option :value="null" disabled>-- Choose --</option>
            </template>            
        </b-form-select>
        <b-form-invalid-feedback :state="form.degree.isValid">
          <div v-for="validationMsg in form.degree.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
        </b-form-invalid-feedback>

        <label for="school">School</label>
        <b-form-select
          v-model="form.school.value"
          :options="schools"
          size="sm"
          id="school">
            <template slot="first">
              <option :value="null" disabled>-- Choose --</option>
            </template>            
        </b-form-select>
        <b-form-invalid-feedback :state="form.school.isValid">
          <div v-for="validationMsg in form.school.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
        </b-form-invalid-feedback>

        <b-button class="mt-3" variant="outline-warning" @click="editMajor">Edit</b-button>
    </b-form>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import _ from 'lodash'
import axios from 'axios'
import EventBus from '@/EventBus'
import FormField from '@/form/FormField'
import Component from 'vue-class-component'
import Major from '@/models/Major'
import {showValidationErrors, clearForm} from '@/util/FormUtil'
import MajorService from '@/services/MajorService'
import MajorDto from '@/models/MajorDto'
import { mapState } from 'vuex';

@Component({
  computed: {
    ...mapState({
      schools: (state:any) => {
        return state.schoolsStore.schools.map(function (school) {
          return {value: school.id, text: school.name};
        });
      },
      locales: function(state:any) {
        let localesForSelect = state.localesStore.localesForSelect;
        for (let locale of localesForSelect) {
          if (locale.active) {
            this.selectedLocale = locale.value;
          }
        }
        return localesForSelect;
      },
      degrees: function(state:any) {
        return state.majorsStore.degrees.map(function (degree) {
          return {value: degree, text: degree};
        });
      }
    })
  }
})
export default class EditMajor extends Vue {
    public form: any;
    public isOpened: boolean = false;
    public curMajor: Major;

    constructor() {
      super();
      this.form = {
        name: new FormField(),
        duration: new FormField(),
        degree: new FormField(),
        school: new FormField()
      };
      this.curMajor = new Major();
    }

    created() {
      let _this = this;
      _this.$store.dispatch('getSchools');
      _this.$store.dispatch('getDegrees');
      EventBus.$on('show-edit-major-form', function (item: Major) {
        _this.isOpened = true;
        _this.curMajor = item;
        
        _this.clearForm();
        _this.form.name.value = item.name;
        _this.form.duration.value = item.duration;
        _this.form.degree.value = item.degree;
        _this.form.school.value = item.schoolId;
      });

      EventBus.$on('hide-edit-major-form', function () {
        _this.isOpened = false;
      });
    }

    clearForm() {
      clearForm(this.form);
    }
    
    async editMajor() {
      let majorDto = new MajorDto({
        id: this.curMajor.id,
        name: this.form.name.value,
        duration: this.form.duration.value,
        degree: this.form.degree.value,
        schoolId: this.form.school.value
      });

      try {
        await MajorService.edit(majorDto);
      } catch (error) {
        showValidationErrors(this.form, error.details);
        return;
      }
      
      this.$bvToast.toast(`Major was edited successfully!`, {
        title: 'Notification',
        autoHideDelay: 5000,
        appendToast: false
      });
      this.clearForm();

      this.$store.commit('updateMajor', majorDto);
    }
}
</script>