import {} from '/vue/admin-panel/edit-schedule-tab/day-schedule-table.js';
import {} from '/vue/admin-panel/edit-schedule-tab/edit-schedule-modal.js';
import {} from '/vue/admin-panel/edit-schedule-tab/remove-schedule-modal.js';
import {serverBus} from '/vue/admin-panel/admin-panel.js';

Vue.component('schedule', {
	data: function () {
		return {
			schedule: {}
		}
	},
	created() {
		let _this = this;
		serverBus.$on('fetchSchedule', function(groupId) {
			_this.getSchedule(groupId);
		});
		
		serverBus.$on('updatedLessonSchedule', function(week, dayName, lessonNum, schedule) {
			let lessonList =  _this.schedule[week][dayName];
			let lessonScheduleIndex = lessonList.findIndex(x => x.lessonNum == lessonNum);
			Vue.set(lessonList, lessonScheduleIndex, schedule);
		});
		
		serverBus.$on('removedLessonSchedule', function(week, dayName, lessonNum, schedule) {
			let lessonList =  _this.schedule[week][dayName];
			let lessonScheduleIndex = lessonList.findIndex(x => x.lessonNum == lessonNum);
			Vue.set(lessonList, lessonScheduleIndex, schedule);
		});
	},
	methods: {
		getSchedule: function (groupId) {
			let _this = this;
			let url = `/api/schedule/group-id/${groupId}`;
			$.ajax({
				type: 'GET',
				url: url,
				dataType: 'json'
			}).done(function (schedule) {
				_this.schedule = schedule;
			}).fail(function (jqXHR, exception) {
				alert(jqXHR.responseText);
			});
		}
	},
	template: `
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
			
			<edit-schedule-modal></edit-schedule-modal>
			<remove-schedule-modal></remove-schedule-modal>
		</div>
	`
});