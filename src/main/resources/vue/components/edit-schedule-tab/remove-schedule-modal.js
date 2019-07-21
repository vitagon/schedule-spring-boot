import {serverBus} from '/vue/admin-panel/admin-panel.js';

Vue.component('remove-schedule-modal', {	
	data: function () {
		return {
			warning: '',
			removeBtnIsVisible: false,
			groupId: 0,
			week: '',
			dayName: '',
			schedule: {}
		}
	},
	created() {
		let _this = this;
		serverBus.$on('showRemoveScheduleModal', function (groupId, week, dayName, schedule) {
			if (schedule.id == null) {
				_this.warning = 'Schedule does not exist!';
				_this.removeBtnIsVisible = false;
			} else {
				_this.warning = 'Do you really want to remove this schedule?';
				_this.removeBtnIsVisible = true;
				
				_this.groupId = groupId;
				_this.week = week;
				_this.dayName = dayName;
				_this.schedule = schedule;
			}
			$('#remove-schedule-modal').modal('show');
		});
	},
	methods: {
		removeSchedule: function () {
			let _this = this;
			$.ajax({
				type: 'DELETE',
				url: `/api/schedule/${_this.schedule.id}`,
			}).done(function (apiSuccess) {
				let emptySchedule = {
					id: null,
					lessonNum: _this.schedule.lessonNum,
					time: null,
					subjectId: null,
					subjectName: null,
					lessonTypeId: null,
					lessonTypeName: null,
					teacherId: null,
					teacherName: null
				};
				serverBus.$emit('removedLessonSchedule',
						_this.week,
						_this.dayName,
						_this.schedule.lessonNum,
						emptySchedule);
				$('#remove-schedule-modal').modal('hide');
			}).fail(function (jqXHR, exception) {
				alert(jqXHR.responseText);
			});
		}
	},
	template: `
		<div class="modal fade" id="remove-schedule-modal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">Confirmation</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p>{{warning}}</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"data-dismiss="modal">Close</button>
						<button v-if="removeBtnIsVisible" type="button" class="btn btn-primary"
								@click="removeSchedule">Remove</button>
					</div>
				</div>
			</div>
		</div>
	`
});