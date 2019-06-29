import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {} from '/vue/admin-panel/edit-schedule-tab/schedule.js';

Vue.component('edit-schedule', {
	data: function () {
		return {
			schools: [],
			majors: [],
			groups: [],
			maxCourseNum: 0,
			chooseScheduleForm: {
				schoolId: 0,
				majorId: 0,
				course: 0,
				groupId: 0
			}
		}
	},
	created() {
		let _this = this;
		serverBus.$on('fetchedSchools', function(schools) {
			_this.schools = schools;
		});
	},
	methods: {
		getMajors: function () {
			let _this = this;
			$.ajax({
				type: 'GET',
				url: `/api/majors/school-id/${_this.chooseScheduleForm.schoolId}`,
				dataType: 'json'
			}).done(function (majors) {
				_this.majors = majors;
			}).fail(function (jqXHR, exception) {
				alert(jqXHR.responseText);
			});
		},
		getMaxCourseNumber: function () {
			let _this = this;
			$.ajax({
				type: 'GET',
				url: `/api/major/${_this.chooseScheduleForm.majorId}/max-course-number`,
				dataType: 'json'
			}).done(function (maxCourseNum) {
				_this.maxCourseNum = maxCourseNum;
			}).fail(function (jqXHR, exception) {
				alert(jqXHR.responseText);
			});
		},
		getGroups: function () {
			let _this = this;
			let majorId = _this.chooseScheduleForm.majorId;
			let courseNum = _this.chooseScheduleForm.course;
			let url = `/api/groups/major-id/${majorId}/course-num/${courseNum}`;
			$.ajax({
				type: 'GET',
				url: url,
				dataType: 'json'
			}).done(function (groups) {
				for (let group of groups) {
					group['fullname'] = group.degree.substr(0,1).toUpperCase() + group.number + group.suffix_translation;
				}
				_this.groups = groups;
			}).fail(function (jqXHR, exception) {
				alert(jqXHR.responseText);
			});
		},
		getSchedule: function () {
			serverBus.$emit('fetchSchedule', this.chooseScheduleForm.groupId);
		}
	},
	template: `
		<div class="edit-schedule-tab-controls">
			<form action="#" method="POST">
				<div class="form-group">
					<label for="choose-schedule-school__select">School</label>
					<select id="choose-schedule-school__select" class="form-control"
							v-model="chooseScheduleForm.schoolId"
							v-on:change="getMajors">
						<option value="0" selected>Choose...</option>
						<option v-for="school of schools" v-bind:value="school.id">{{school.name}}</option>
					</select>
				</div>
				
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="choose-schedule-major__select">Major</label>
						<select id="choose-schedule-major__select" class="form-control"
								v-model="chooseScheduleForm.majorId"
								v-on:change="getMaxCourseNumber">
							<option value="0" selected>Choose...</option>
							<option value="0" v-for="major of majors" v-bind:value="major.id">{{major.name}}</option>
						</select>
					</div>
				
					<div class="form-group col-md-2">
						<label for="choose-schedule-course__select">Course</label>
						<select id="choose-schedule-course__select" class="form-control"
								v-model="chooseScheduleForm.course"
								v-on:change="getGroups">
							<option value="0" selected>Choose...</option>
							<option v-for="num of maxCourseNum" v-bind:value="num">{{num}}</option>
						</select>
					</div>
				
					<div class="form-group col-md-4">
						<label for="choose-schedule-group__select">Group</label>
						<select id="choose-schedule-group__select" class="form-control"
								v-model="chooseScheduleForm.groupId"
								v-on:change="getSchedule">
							<option value="0" selected>Choose...</option>
							<option v-for="group of groups" v-bind:value="group.id">{{group.fullname}}</option>
						</select>
					</div>	
				</div>
			</form>
			
			<schedule></schedule>
		</div>
	`
});