<template>
  <div v-if="isOpened" class="mt-5 mb-4">
    <h5>Add group</h5>
    <hr />
    <b-form  @submit.stop.prevent>
      <label for="major">Major</label>
      <b-form-select
        v-model="form.majorId.value"
        :options="majors"
        size="sm"
        id="major"
        >
          <template slot="first">
            <option :value="null" disabled>-- Choose --</option>
          </template>            
      </b-form-select>
      <b-form-invalid-feedback :state="form.majorId.isValid">
        <div v-for="validationMsg in form.majorId.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
      </b-form-invalid-feedback>

      <label for="name">Name</label>
      <b-input v-model="form.name.value" :state="form.name.isValid" id="name"></b-input>
      <b-form-invalid-feedback :state="form.name.isValid">
        <div v-for="validationMsg in form.name.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
      </b-form-invalid-feedback>

      <label for="courseNum">Course</label>
      <b-form-select
        v-model="form.courseNum.value"
        :options="Array.from(Array(7).keys()).map(num => { let course = num++;return {value: course, text: course}})"
        size="sm"
        id="courseNum">
          <template slot="first">
            <option :value="null" disabled>-- Choose --</option>
          </template>            
      </b-form-select>
      <b-form-invalid-feedback :state="form.courseNum.isValid">
        <div v-for="validationMsg in form.courseNum.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
      </b-form-invalid-feedback>

      <b-button class="mt-3" variant="outline-warning" @click="addGroup">Add</b-button>
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
import GroupService from '@/services/GroupService';
import { mapState } from 'vuex'
import Group from '@/models/Group'


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
export default class AddGroup extends Vue {
  public isOpened: boolean;
  public form: any;

  constructor() {
    super();
    this.isOpened = false;
    this.form = {
      majorId: new FormField(),
      name: new FormField(),
      courseNum: new FormField()
    };
  }

  created() {
    let _this = this;
    EventBus.$on('show-add-group-form', function () {
      _this.isOpened = true;
    })

    EventBus.$on('hide-add-group-form', function () {
      _this.isOpened = false;
    })
  }

  async addGroup() {
    let group = new Group({
      majorId: this.form.majorId.value,
      name: this.form.name.value,
      courseNum: this.form.courseNum.value
    })

    let updatedGroup;
    try {
      updatedGroup = await GroupService.create(group)
    } catch (error) {
      showValidationErrors(this.form, error.data.details);
      return;
    }

    EventBus.$emit('group-was-created', updatedGroup);
    clearForm(this.form);
    this.$bvToast.toast(`Group was added successfully!`, {
      title: 'Notification',
      autoHideDelay: 5000,
      appendToast: false
    });
  }
}
</script>