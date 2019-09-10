<template>
  <table class="table table-condensed table-sm table-striped" style="margin-top: 2em">
    <thead>
      <tr>
        <th scope="col" colspan="7">{{dayName}}</th>
      </tr>
      <tr>
        <th scope="col" style="width: 5%">#</th>
        <th scope="col" style="width: 15%">Time</th>
        <th scope="col" style="width: 20%">Title</th>
        <th scope="col" style="width: 15%">Type</th>
        <th scope="col" style="width: 20%">Teacher</th>
        <th scope="col" style="width: 10%">Classroom</th>
        <th scope="col" colspan="2" style="min-width: 102px">Controls</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="schedule of daySchedules" :key="schedule.id">
        <td scope="col" class="lesson-num" style="width: 10%">{{schedule.lessonNum}}</td>
        <td scope="col">{{schedule.time}}</td>
        
        <!-- Subject title -->
        <td scope="col" class="subject">{{schedule.subjectName}}</td>
        
        <!-- Lesson type -->
        <td scope="col" class="lesson-type">{{schedule.lessonTypeName}}</td>
        
        <!-- Teacher -->
        <td scope="col" class="teacher">{{schedule.teacherName}}</td>
        
        <!-- Classroom -->
        <td scope="col" class="classroom">{{schedule.classroom}}</td>
        
        <td scope="col" colspan="2">
          <button type="button" class="btn btn-primary edit-schedule-btn"
              v-on:click="showEditScheduleModal(schedule)">
            <i class="fas fa-edit"></i>
          </button>
          <button type="button" class="btn btn-danger remove-schedule-btn"
              v-on:click="showRemoveScheduleModal(schedule)">
            <i class="far fa-trash-alt"></i>
          </button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script lang="ts">
import {Vue, Component, Prop} from 'vue-property-decorator';
import EventBus from '@/EventBus';

@Component
export default class DayScheduleTable extends Vue {
  @Prop(Array) daySchedules: Array<any> | undefined;
  @Prop(String) dayName: String | undefined;
  @Prop(String) week: String | undefined;
  @Prop(Number) groupId: Number | undefined;

  showEditScheduleModal (schedule) {
    let _this = this;
    EventBus.$emit('showEditScheduleModal',
      this.groupId,
      this.week,
      this.dayName,
      schedule
    );
  }

  showRemoveScheduleModal (schedule) {
    let _this = this;
    EventBus.$emit('showRemoveScheduleModal',
      this.groupId,
      this.week,
      this.dayName,
      schedule);
  }
}
</script>