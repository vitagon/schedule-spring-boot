<template>
  <div class="edit-schedule-tab-controls">
    <form action="#" method="POST">
      <div class="form-group">
        <label for="choose-schedule-school__select">School</label>
        <select
            id="choose-schedule-school__select"
            class="form-control"
            v-model="chooseScheduleForm.schoolId"
            v-on:change="getMajors">
          <option value="0" selected>Choose...</option>
          <option
            v-for="school of schools"
            v-bind:value="school.id"
            :key="school.id">{{school.name}}</option>
        </select>
      </div>
      
      <div class="form-row">
        <div class="form-group col-md-6">
          <label for="choose-schedule-major__select">Major</label>
          <select id="choose-schedule-major__select" class="form-control"
              v-model="chooseScheduleForm.majorId"
              v-on:change="getMaxCourseNumber">
            <option value="0" selected>Choose...</option>
            <option
              v-for="major of majors"
              v-bind:value="major.id"
              :key="major.id">{{major.name}}</option>
          </select>
        </div>
      
        <div class="form-group col-md-2">
          <label for="choose-schedule-course__select">Course</label>
          <select id="choose-schedule-course__select" class="form-control"
              v-model="chooseScheduleForm.course"
              v-on:change="getGroups">
            <option value="0" selected>Choose...</option>
            <option
              v-for="num of maxCourseNum"
              v-bind:value="num"
              :key="num">{{num}}</option>
          </select>
        </div>
      
        <div class="form-group col-md-4">
          <label for="choose-schedule-group__select">Group</label>
          <select id="choose-schedule-group__select" class="form-control"
              v-model="chooseScheduleForm.groupId"
              v-on:change="getSchedule">
            <option value="0" selected>Choose...</option>
            <option
              v-for="group of groups"
              v-bind:value="group.id"
              :key="group.id">{{group.fullname}}</option>
          </select>
        </div>	
      </div>
    </form>
    
    <!-- <schedule></schedule> -->
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import MajorService from '@/services/MajorService'
import GroupService from '@/services/GroupService'
import EventBus from '../../EventBus'
import { mapState } from 'vuex'

@Component({
  computed: {
    ...mapState({
      schools: (state:any) => state.schoolsStore.schools
    })
  }
})
export default class ChooseSchedule extends Vue {
  private majors = [];
  private groups = [];
  private maxCourseNum = 0;
  private chooseScheduleForm = {
    schoolId: 0,
    majorId: 0,
    course: 0,
    groupId: 0
  };

  created() {
    this.$store.dispatch('getSchools');
  }
  
  getMajors() {
    MajorService.getMajors(this.chooseScheduleForm.schoolId)
      .then((response: any) => {
        this.majors = response.data;
      })
      .catch(error => console.error(error.data));
  }

  getMaxCourseNumber() {
    MajorService.getMaxCourseNumber(this.chooseScheduleForm.majorId)
      .then((response: any) => {
        this.maxCourseNum = response.data;
      })
      .catch(error => console.error(error));
  }

  getGroups() {
    let _this = this;
    let majorId = _this.chooseScheduleForm.majorId;
    let courseNum = _this.chooseScheduleForm.course;
    GroupService.getGroups(majorId, courseNum)
      .then((response: any) => {
        let groups = response.data;
        for (let group of groups) {
          group['fullname'] = group.degree.substr(0,1).toUpperCase() + group.number + group.suffix_translation;
        }
        this.groups = groups;
      })
      .catch(error => console.log(error));
  }

  getSchedule() {
    EventBus.$emit('fetchSchedule', this.chooseScheduleForm.groupId);
  }
}
</script>