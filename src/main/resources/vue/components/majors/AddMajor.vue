<template>
  <div v-if="isOpened" class="mt-5 mb-4">
    <h5>Add major</h5>
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
        v-model="form.schoolId.value"
        :options="schools"
        size="sm"
        id="schoolId">
          <template slot="first">
            <option :value="null" disabled>-- Choose --</option>
          </template>            
      </b-form-select>
      <b-form-invalid-feedback :state="form.schoolId.isValid">
        <div v-for="validationMsg in form.schoolId.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
      </b-form-invalid-feedback>

      <b-button class="mt-3" variant="outline-warning" @click="addMajor">Add</b-button>
    </b-form>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import axios from 'axios'
import Component from 'vue-class-component'
import EventBus from '@/EventBus'
import FormField from '@/form/FormField'
import {showValidationErrors, clearForm} from '@/util/FormUtil'
import MajorService from '@/services/MajorService';
import MajorDto from '@/models/MajorDto'
import { mapState } from 'vuex'


@Component({
  computed: {
    ...mapState({
      schools: (state:any) => {
        return state.schoolsStore.schools.map(function (school) {
          return {value: school.id, text: school.name};
        });
      },
      degrees: (state:any) => {
        return state.majorsStore.degrees.map(function (degree) {
          return {value: degree, text: degree};
        });
      }
    })
  }
})
export default class AddMajor extends Vue {
  public isOpened: boolean;
  public form: any;

  constructor() {
    super();
    this.isOpened = false;
    this.form = {
      name: new FormField(),
      duration: new FormField(),
      degree: new FormField(),
      schoolId: new FormField()
    };
  }

  created() {
    let _this = this;
    EventBus.$on('show-add-major-form', function () {
      _this.isOpened = true;
    })

    EventBus.$on('hide-add-major-form', function () {
      _this.isOpened = false;
    })
  }

  addMajor() {
    let majorDto = new MajorDto({
      schoolId: this.form.schoolId.value,
      name: this.form.name.value,
      duration: this.form.duration.value,
      degree: this.form.degree.value
    })
    MajorService.add(majorDto)
      .then((response: any) => {
        this.$store.commit('addMajor', response)
        clearForm(this.form)
        this.$bvToast.toast(`Major was added successfully!`, {
          title: 'Notification',
          autoHideDelay: 5000,
          appendToast: false
        });
      })
      .catch(error => {
        showValidationErrors(this.form, error.details);
      })
  }
}
</script>

