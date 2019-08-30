<template>
  <div v-if="isOpened" class="mt-5">
    <h5>Add school</h5>
    <hr />
    <b-form  @submit.stop.prevent>
      <label for="name">Name</label>
      <b-input v-model="form.name.value" :state="form.name.isValid" id="name"></b-input>
      <b-form-invalid-feedback :state="form.name.isValid">
        <div v-for="validationMsg in form.name.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
      </b-form-invalid-feedback>

      <b-button class="mt-3" variant="outline-warning" @click="addSchool">Add</b-button>
    </b-form>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import axios from 'axios'
import Component from 'vue-class-component'
import EventBus from '@/EventBus'
import FormField from '@/form/FormField'
import School from '@/models/School'
import {showValidationErrors, clearForm} from '@/util/FormUtil'


@Component
export default class AddSchool extends Vue {
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
    EventBus.$on('show-add-school-form', function () {
      _this.isOpened = true;
    })

    EventBus.$on('hide-add-school-form', function () {
      _this.isOpened = false;
    })
  }

  addSchool() {
    axios({
      method: 'POST',
      url: '/api/control/school',
      data: { name: this.form.name.value}
    })
    .then(response => {
      this.$store.commit('addSchool', response.data)
      clearForm(this.form)
      this.$bvToast.toast(`School was added successfully!`, {
        title: 'Notification',
        autoHideDelay: 5000,
        appendToast: false
      });
    })
    .catch(error => {
      let data = error.response.data;
      showValidationErrors(this.form, data.details);
    })
  }
}
</script>

