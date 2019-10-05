<template>
  <div v-if="isOpened" class="mt-5">
    <h5>Edit subject</h5>
    <hr />
    <b-form  @submit.stop.prevent>
        <label for="name">Name</label>
        <b-input v-model="form.name.value" :state="form.name.isValid" id="name"></b-input>
        <b-form-invalid-feedback :state="form.name.isValid">
          <div v-for="validationMsg in form.name.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
        </b-form-invalid-feedback>

        <b-button class="mt-3" variant="outline-warning" @click="editSubject">Edit</b-button>
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
import SubjectService from '@/services/SubjectService'
import {showValidationErrors, clearForm} from '@/util/FormUtil'
import Subject from '@/models/Subject'

@Component
export default class EditSubject extends Vue {
    public form: any;
    public isOpened: boolean = false;
    public curSubject: Subject;

    constructor() {
      super();
      this.form = {
        name: new FormField()
      };
      this.curSubject = new Subject();
    }

    created() {
      let _this = this;
      EventBus.$on('show-edit-subject-form', function (item: Subject) {
        _this.isOpened = true;
        _this.curSubject = item;
        
        _this.clearForm();
        _this.form.name.value = item.name;
      });

      EventBus.$on('hide-edit-subject-form', function () {
        _this.isOpened = false;
      });
    }

    clearForm() {
      clearForm(this.form);
    }
    
    async editSubject() {
      try {
        await SubjectService.update(this.curSubject.id, this.form.name.value);
      } catch (error) {
        showValidationErrors(this.form, error.details);
        return;
      }
      
      this.$bvToast.toast(`Subject was edited successfully!`, {
        title: 'Notification',
        autoHideDelay: 5000,
        appendToast: false
      });
      this.$store.commit('updateSubjectName', {id: this.curSubject.id, name: this.form.name.value});
      this.clearForm();      
    }
}
</script>