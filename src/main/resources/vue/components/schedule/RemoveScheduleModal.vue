<template>
  <b-modal @ok="removeLesson" ref="remove-schedule-modal">
    Do you really want to remove lesson {{lessonNum}} on {{dayName}}?
  </b-modal>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component';
import EventBus from '@/EventBus';

@Component
export default class RemoveScheduleModal extends Vue {
  public groupId: Number = 0;
  public week: string = '';
  public dayName: string = '';
  public schedule: any;
  public lessonNum: Number = 0;

  created() {
    let _this: any = this;
    EventBus.$on('showRemoveScheduleModal', function (groupId, week, dayName, schedule) {
      _this.groupId = groupId;
      _this.week = week;
      _this.dayName = dayName;
      _this.schedule = schedule;
      _this.lessonNum = schedule.lessonNum;
      _this.$refs['remove-schedule-modal'].show();
    })
  }

  removeLesson() {
    this.$store.dispatch('removeLesson', {
      week: this.week,
      dayName: this.dayName,
      lesson: this.schedule
    });
  }
}
</script>