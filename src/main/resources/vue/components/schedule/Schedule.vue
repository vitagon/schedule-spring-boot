<template>
  <b-container class="pt-5">
    <choose-schedule></choose-schedule>
    <schedule-wrap></schedule-wrap>
  </b-container>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import ScheduleWrap from '@/components/schedule/ScheduleWrap.vue'
import ChooseSchedule from '@/components/schedule/ChooseSchedule.vue'

@Component({
  components: {ScheduleWrap, ChooseSchedule}
})
export default class Schedule extends Vue {
  
}
</script>