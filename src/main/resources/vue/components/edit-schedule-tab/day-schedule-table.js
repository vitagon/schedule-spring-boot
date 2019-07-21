import {serverBus} from '/vue/admin-panel/admin-panel.js';

Vue.component('day-schedule-table', {
	props: {
		daySchedules: Array,
		dayName: String,
		week: String,
		groupId: Number
	},
	data: function () {
		return {
			schedules: this.daySchedules
		}
	},
	methods: {
		showEditScheduleModal: function (schedule) {
			let _this = this;
			serverBus.$emit('showEditScheduleModal',
				_this._props.groupId,
				_this._props.week,
				_this._props.dayName,
				schedule
			);
		},
		showRemoveScheduleModal: function (schedule) {
			let _this = this;
			serverBus.$emit('showRemoveScheduleModal',
				_this._props.groupId,
				_this._props.week,
				_this._props.dayName,
				schedule);
		}
	},
	template: `
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
					<th scope="col" colspan="2" style="min-width: 100px">Controls</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="schedule of schedules">
					<td scope="col" class="lesson-num" style="width: 10%">{{schedule.lessonNum}}</td>
					<td scope="col">{{schedule.time}}</td>
					
					<!-- Subject title -->
					<td scope="col" class="subject">{{schedule.subjectName}}</td>
					
					<!-- Lesson type -->
					<td scope="col" class="lesson-type">{{schedule.lessonTypeName}}</td>
					
					<!-- Teacher -->
					<td scope="col" class="teacher">{{schedule.teacherName}}</td>
					
					<!-- Classroom -->
					<td scope="col"class="classroom">{{schedule.classroom}}</td>
					
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
	`
});