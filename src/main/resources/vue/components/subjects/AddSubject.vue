<template>
  <div v-if="isOpened" class="mt-5 mb-4">
    <h5>Add subject</h5>
    <hr />
    <b-form  @submit.stop.prevent>
      <label for="name">Name</label>
      <b-input v-model="form.name.value" :state="form.name.isValid" id="name"></b-input>
      <b-form-invalid-feedback :state="form.name.isValid">
        <div v-for="validationMsg in form.name.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
      </b-form-invalid-feedback>

      <b-button class="mt-3" variant="outline-warning" @click="addSubject">Add</b-button>
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
import SubjectService from '@/services/SubjectService';


@Component
export default class AddSubject extends Vue {
  public isOpened: boolean;
  public form: any;

  constructor() {
    super();
    this.isOpened = false;
    this.form = {
      name: new FormField()
    }
  }

  created() {
    let _this = this;
    EventBus.$on('show-add-subject-form', function () {
      _this.isOpened = true;
    })

    EventBus.$on('hide-add-subject-form', function () {
      _this.isOpened = false;
    })
  }

  addSubject() {
    SubjectService.create(this.form.name.value)
      .then((response: any) => {
        this.$store.commit('addSubject', response)
        clearForm(this.form)
        this.$bvToast.toast(`Subject was added successfully!`, {
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