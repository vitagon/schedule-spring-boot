import {serverBus} from '/vue/admin-panel/admin-panel.js';

Vue.component('edit-schedule-modal', {
	data: function () {
		return {
			subjects: [],
			teachers: [],
			modal: {
				lessonNum: 0,
				subjectId: 0,
				lessonTypeId: 0,
				teacherId: 0,
				classroom: ''
			},
			groupId: 0,
			week: '',
			dayName: '',
			schedule: {}
		}
	},
	created() {
		let _this = this;
		this.getTeachers();
		serverBus.$on('showEditScheduleModal', function (groupId, week, dayName, schedule) {
			_this.groupId = groupId;
			_this.week = week;
			_this.dayName = dayName;
			_this.schedule = schedule;
			
			if (schedule.teacherId != null) {
				_this.modal.teacherId = schedule.teacherId;
			}
			if (schedule.lessonTypeId != null) {
				_this.modal.lessonTypeId = schedule.lessonTypeId;
			}
			if (schedule.subjectId != null) {
				_this.modal.subjectId = schedule.subjectId;
			}
			if (schedule.lessonNum != null) {
				_this.modal.lessonNum = schedule.lessonNum;
			}
			if (schedule.classroom != null) {
				_this.modal.classroom = schedule.classroom;
			}
			$('.edit-schedule-modal').modal('show');
		});
		serverBus.$on('fetchedSubjects', function (subjects) {
			_this.subjects = subjects;
		});
		serverBus.$on('fetchedSubjects', function (subjects) {
			_this.subjects = subjects;
		});
	},
	methods: {
		saveScheduleChanges: function () {
			let _this = this;
			let method, url;
			let objToSend = {
				groupId: _this.groupId,
				week: _this.week,
				day: _this.dayName,
				lessonNum: _this.modal.lessonNum,
				
				subjectId: _this.modal.subjectId,
				lessonTypeId: _this.modal.lessonTypeId,
				userId: _this.modal.teacherId,
				classroom: _this.modal.classroom
			}
			
			if (_this.schedule.id == null) {
				this.createSchedule(objToSend);
			} else {
				this.updateSchedule(objToSend);
			}
		},
		createSchedule: function (objToSend) {
			let _this = this;
			$.ajax({
				type: 'POST',
				url: '/api/schedule',
				data: objToSend, 
				dataType: 'json'
			}).done(function (schedule) {
				serverBus.$emit('updatedLessonSchedule',
						_this.week,
						_this.dayName,
						_this.modal.lessonNum,
						schedule);
				$('.edit-schedule-modal').modal('hide');
			}).fail(function (jqXHR, exception) {
				alert(jqXHR.responseText);
			});
		},
		updateSchedule: function (objToSend) {
			let _this = this;
			$.ajax({
				type: 'PUT',
				url: `/api/schedule/${_this.schedule.id}`,
				data: objToSend, 
				dataType: 'json'
			}).done(function (schedule) {
				serverBus.$emit('updatedLessonSchedule',
						_this.week,
						_this.dayName,
						_this.modal.lessonNum,
						schedule);
				$('.edit-schedule-modal').modal('hide');
			}).fail(function (jqXHR, exception) {
				alert(jqXHR.responseText);
			});
		},
		getTeachers: function () {
			let _this = this;
			$.ajax({
				type: 'GET',
				url: '/api/translations/users/role/teacher',
				dataType: 'json'
			}).done(function (teachers) {
				_this.teachers = teachers;
			}).fail(function (jqXHR, exception) {
				alert(jqXHR.responseText);
			});
		}
	},
	template: `
		<div class="modal fade edit-schedule-modal"
			 id="edit-schedule-modal"
			 tabindex="-1"
			 role="dialog">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Modal title</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body edit-schedule-modal-body">
						<form>
							<input type="text" class="form-control" id="modal-group-id" style="display: none">
							<input type="text" class="form-control" id="modal-schedule-id" style="display: none">
							<input type="text" class="form-control" id="modal-week" style="display: none">
							<input type="text" class="form-control" id="modal-day-num" style="display: none">
							
							<div class="form-group">
								<label for="modal-lesson-num">Lesson number:</label>
								<select class="custom-select" id="modal-lesson-num" disabled v-model="modal.lessonNum">
									<option selected>Choose...</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
								</select>
							</div>
							<div class="form-group">
								<label for="modal-subject">Subject title:</label>
								<select class="custom-select" id="modal-subject" v-model="modal.subjectId">
									<option value="0" selected>Choose...</option>
									<option v-for="subject of subjects"
											v-bind:value="subject.id">
											{{subject.translation}}
									</option>
								</select>
							</div>
							<div class="form-group">
								<label for="modal-lesson-type">Lesson type:</label>
								<select class="custom-select" id="modal-lesson-type" v-model="modal.lessonTypeId">
									<option value="0" selected>Choose...</option>
									<option value="49">Lecture</option>
									<option value="50">Practice</option>
								</select>
							</div>
							<div class="form-group">
								<label for="modal-teacher">Teacher:</label>
								<select class="custom-select" id="modal-teacher" v-model="modal.teacherId">
									<option value="0" selected>Choose...</option>
									<option v-for="teacher of teachers" v-bind:value="teacher.id">{{teacher.fullName}}</option>
								</select>
							</div>
							<div class="form-group">
								<label for="message-text">Classroom:</label>
								<input type="text" class="form-control" id="modal-classroom" v-model="modal.classroom">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
						<button type="button"
								class="btn btn-primary schedule-save-changes-btn"
								@click="saveScheduleChanges">
							Save changes
						</button>
					</div>
				</div>
			</div>
		</div>
	`
});