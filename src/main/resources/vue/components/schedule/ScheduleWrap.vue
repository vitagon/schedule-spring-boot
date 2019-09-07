<template>
  <div id="edit-schedule-content">
    <ul class="nav nav-tabs" role="tablist">
      <li class="nav-item">
        <a class="nav-link active" id="up-week-tab" data-toggle="tab" href="#up-week" role="tab" aria-controls="up-week" aria-selected="true">Up</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" id="down-week-tab" data-toggle="tab" href="#down-week" role="tab" aria-controls="down-week" aria-selected="false">Down</a>
      </li>
    </ul>
			
    <div class="tab-content">
      <div class="tab-pane fade show active" id="up-week" role="tabpanel" aria-labelledby="up-week-tab">
        <div class="table-responsive">
          <day-schedule-table v-for="(weekSchedule, propertyName) of schedule.up"
                    :key="propertyName" :daySchedules="weekSchedule"
                    :dayName="propertyName"
                    :week="'up'"
                    :groupId="schedule.groupId">
          </day-schedule-table>
        </div>
      </div>
				
      <div class="tab-pane fade" id="down-week" role="tabpanel" aria-labelledby="down-week-tab">
        <div class="table-responsive">
          <day-schedule-table v-for="(weekSchedule, propertyName) of schedule.down"
                    :key="propertyName" :daySchedules="weekSchedule"
                    :dayName="propertyName"
                    :week="'down'"
                    :groupId="schedule.groupId">
          </day-schedule-table>
        </div>
      </div>
    </div>
			
    <!-- <edit-schedule-modal></edit-schedule-modal> -->
    <!-- <remove-schedule-modal></remove-schedule-modal> -->
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import ScheduleService from '@/services/ScheduleService'
import EventBus from '../../EventBus'
import DayScheduleTable from '@/components/schedule/DayScheduleTable.vue'

@Component({
  components: {DayScheduleTable}
})
export default class ScheduleWrap extends Vue {
  private schedule = {};

  created() {
    let _this = this;
		EventBus.$on('fetchSchedule', function(groupId) {
			_this.getSchedule(groupId);
		});
		
		EventBus.$on('updatedLessonSchedule', function(week, dayName, lessonNum, schedule) {
			let lessonList =  _this.schedule[week][dayName];
			let lessonScheduleIndex = lessonList.findIndex(x => x.lessonNum == lessonNum);
			Vue.set(lessonList, lessonScheduleIndex, schedule);
		});
		
		EventBus.$on('removedLessonSchedule', function(week, dayName, lessonNum, schedule) {
			let lessonList =  _this.schedule[week][dayName];
			let lessonScheduleIndex = lessonList.findIndex(x => x.lessonNum == lessonNum);
			Vue.set(lessonList, lessonScheduleIndex, schedule);
		});
  }

  getSchedule(groupId) {
    ScheduleService.getSchedule(groupId)
      .then((response: any) => {
        this.schedule = response.data;
      })
      .catch(error => alert(error.data))
  }
}
</script>