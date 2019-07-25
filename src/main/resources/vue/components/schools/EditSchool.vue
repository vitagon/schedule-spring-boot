<template>
    <div v-if="isOpened" class="mt-5">
        <h5>Edit school</h5>
        <hr />
        <b-form  @submit.stop.prevent>
            <label for="name">Name</label>
            <b-input v-model="form.newSchoolName.value" :state="form.newSchoolName.isValid" id="name"></b-input>
            <b-form-invalid-feedback :state="form.newSchoolName.isValid">
              <div v-for="validationMsg in form.newSchoolName.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
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
import { clearForm } from '@/util/FormUtil'

@Component
export default class EditSchool extends Vue {
    public form: any;
    public isOpened: boolean = false;
    public curSchool: School;

    constructor() {
      super();
      this.form = {
        newSchoolName: new FormField()
      };
      this.curSchool = new School();
    }

    created() {
      let _this = this;
      EventBus.$on('open-edit-school', function (item: School) {
        _this.isOpened = true;
        _this.curSchool = item;
        
        _this.clearForm();
        _this.form.newSchoolName.value = item.name;
      });
    }

    clearForm() {
      clearForm(this.form);
    }
    
    editSchool() {
      let objToSend = {
        schoolId: this.curSchool.id,
        newSchoolName: this.form.newSchoolName.value
      }
      axios({
        method: 'PUT',
        url: '/api/control/school',
        data: objToSend,
        headers: {
          'Content-Type': 'application/json;charset=UTF-8'
        }
      })
      .then((response) => {
        this.$bvToast.toast(`School was edited successfully!`, {
          title: 'Notification',
          autoHideDelay: 5000,
          appendToast: false
        });
        this.clearForm();

        this.$store.commit('updateSchoolName', objToSend);
      })
      .catch((error) => {
        let data = error.response.data;
        for (let detail of data.details) {
          this.form[detail.fieldName].validationMsgs = detail.messages;
          this.form[detail.fieldName].isValid = false;
        }
        
      })
    }
}
</script>

