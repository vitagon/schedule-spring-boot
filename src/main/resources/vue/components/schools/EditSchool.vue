<template>
  <div v-if="isOpened" class="mt-5">
    <h5>Edit school</h5>
    <hr />
    <b-form  @submit.stop.prevent>
        <label for="name">Name</label>
        <b-input v-model="form.name.value" :state="form.name.isValid" id="name"></b-input>
        <b-form-invalid-feedback :state="form.name.isValid">
          <div v-for="validationMsg in form.name.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
        </b-form-invalid-feedback>

        <b-button class="mt-3" variant="outline-warning" @click="editSchool">Edit</b-button>
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
import School from '@/models/School'
import SchoolService from '@/services/SchoolService'
import {showValidationErrors, clearForm} from '@/util/FormUtil'

@Component
export default class EditSchool extends Vue {
    public form: any;
    public isOpened: boolean = false;
    public curSchool: School;

    constructor() {
      super();
      this.form = {
        name: new FormField()
      };
      this.curSchool = new School();
    }

    created() {
      let _this = this;
      EventBus.$on('show-edit-school-form', function (item: School) {
        _this.isOpened = true;
        _this.curSchool = item;
        
        _this.clearForm();
        _this.form.name.value = item.name;
      });

      EventBus.$on('hide-edit-school-form', function () {
        _this.isOpened = false;
      });
    }

    clearForm() {
      clearForm(this.form);
    }
    
    async editSchool() {
      try {
        await SchoolService.editSchool(this.curSchool.id, this.form.name.value);
      } catch (error) {
        showValidationErrors(this.form, error.details);
        return;
      }
      
      this.$bvToast.toast(`School was edited successfully!`, {
        title: 'Notification',
        autoHideDelay: 5000,
        appendToast: false
      });
      this.$store.commit('updateSchoolName', {id: this.curSchool.id, name: this.form.name.value});
      this.clearForm();      
    }
}
</script>

