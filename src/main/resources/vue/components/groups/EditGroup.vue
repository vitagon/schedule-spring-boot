<template>
  <div v-if="isOpened" class="mt-5 mb-4">
    <h5>Edit group</h5>
    <hr />
    <b-form  @submit.stop.prevent>
        <label for="major">Major</label>
        <b-form-select
          v-model="form.majorId.value"
          :options="majors"
          size="sm"
          id="major">
            <template slot="first">
              <option :value="null" disabled>-- Choose --</option>
            </template>            
        </b-form-select>
        <b-form-invalid-feedback :state="form.majorId.isValid">
          <div v-for="validationMsg in form.majorId.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
        </b-form-invalid-feedback>

        <label for="name">Name</label>
        <b-input v-model="form.name.value" id="name">
            <template slot="first">
              <option :value="null" disabled>-- Choose --</option>
            </template>            
        </b-input>
        <b-form-invalid-feedback :state="form.name.isValid">
          <div v-for="validationMsg in form.name.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
        </b-form-invalid-feedback>

        <label for="course">Course</label>
        <b-form-select
          v-model="form.courseNum.value"
          :options="Array.from(Array(7).keys()).map(num => { let course = num++;return {value: course, text: course}})"
          size="sm"
          id="course">
            <template slot="first">
              <option :value="null" disabled>-- Choose --</option>
            </template>            
        </b-form-select>
        <b-form-invalid-feedback :state="form.courseNum.isValid">
          <div v-for="validationMsg in form.courseNum.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
        </b-form-invalid-feedback>

        <b-button class="mt-3" variant="outline-warning" @click="editGroup">Edit</b-button>
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
import {showValidationErrors, clearForm, clearValidationMsgs} from '@/util/FormUtil'
import GroupService from '@/services/GroupService'
import MajorDto from '@/models/MajorDto'
import Group from '@/models/Group'
import { mapState } from 'vuex';

@Component({
  computed: {
    ...mapState({
      majors: (state:any) => {
        return state.majorsStore.majors.map(function (major) {
          return {value: major.id, text: major.name};
        })
      }
    })
  }
})
export default class EditGroup extends Vue {
    public form: any;
    public isOpened: boolean = false;
    public curGroup: Group;

    constructor() {
      super();
      this.form = {
        majorId: new FormField(),
        name: new FormField(),
        courseNum: new FormField()
      };
      this.curGroup = new Group();
    }

    created() {
      let _this = this;
      EventBus.$on('show-edit-group-form', function (item: Group) {
        _this.isOpened = true;
        _this.curGroup = item;
        
        _this.clearForm();
        _this.form.majorId.value = item.majorId;
        _this.form.name.value = item.name;
        _this.form.courseNum.value = item.courseNum;
      });

      EventBus.$on('hide-edit-group-form', function () {
        _this.isOpened = false;
      });
    }

    clearForm() {
      clearForm(this.form);
    }
    
    async editGroup() {
      clearValidationMsgs(this.form);
      let group = new Group({
        // id: this.curGroup.id,
        name: this.form.name.value,
        courseNum: this.form.courseNum.value,
        majorId: this.form.majorId.value
      });

      try {
        await GroupService.edit(group);
      } catch (error) {
        let data = error.data;
        showValidationErrors(this.form, data.details);
        return;
      }
      
      this.$bvToast.toast(`Group was edited successfully!`, {
        title: 'Notification',
        autoHideDelay: 5000,
        appendToast: false
      });
      this.clearForm();

      EventBus.$emit('group-was-updated', this.curGroup.id);
    }
}
</script>