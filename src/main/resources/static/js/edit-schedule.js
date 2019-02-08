const PRACTICE = 'practice';
const LECTURE  = 'lecture';

const MODAL_GROUP_ID_INPUT     = '#modal-group-id';
const MODAL_SCHEDULE_ID_INPUT  = '#modal-schedule-id';
const MODAL_WEEK_TYPE_INPUT    = '#modal-week';
const MODAL_DAY_NUM_INPUT      = '#modal-day-num';
const MODAL_LESSON_NUM_SELECT  = '#modal-lesson-num';
const MODAL_SUBJECT_SELECT     = '#modal-subject';
const MODAL_LESSON_TYPE_SELECT = '#modal-lesson-type';
const MODAL_TEACHER_SELECT     = '#modal-teacher';
const MODAL_CLASSROOM_INPUT    = '#modal-classroom';


const debounce = (func, delay) => {
    let debounceTimer 
    return function() { 
        const context = this;
        const args = arguments; 
        clearTimeout(debounceTimer);
        debounceTimer = setTimeout(() => func.apply(context, args), delay); 
    } 
}

function showEditScheduleModal(e) {
	let scheduleRow = e.target.closest('tr');
	let rootTable = scheduleRow.closest('table');
	let scheduleId = $(scheduleRow).attr('data-schedule-id');
	
	let groupId = $(rootTable).attr('data-group-id');
	let week = $(rootTable).attr('data-week');
	let dayNum = $(rootTable).attr('data-day-num');
	
	let lessonNum  = $(scheduleRow).find('.lesson-num').html();
	let subjectId  = $(scheduleRow).find('.subject').attr('data-id');
	let lessonType = $(scheduleRow).find('.lesson-type').attr('data-lesson-type');
	let teacherId  = $(scheduleRow).find('.teacher').attr('data-id');
	let classroom  = $(scheduleRow).find('.classroom').html();
	
	console.log("Lesson type: " + lessonType);
	
	$(MODAL_GROUP_ID_INPUT).val(groupId);
	$(MODAL_WEEK_TYPE_INPUT).val(week);
	$(MODAL_DAY_NUM_INPUT).val(dayNum);
	$(MODAL_SCHEDULE_ID_INPUT).val(scheduleId);
	$(MODAL_LESSON_NUM_SELECT).val(lessonNum);
	$(MODAL_SUBJECT_SELECT).val(subjectId);
	$(MODAL_TEACHER_SELECT).val(teacherId);
	$(MODAL_CLASSROOM_INPUT).val(classroom);
	$(MODAL_LESSON_TYPE_SELECT).val(lessonType);
	
	$(scheduleRow).addClass("is-being-edited");
	$('.edit-schedule-modal').modal('show');
}

function saveScheduleChanges(e) {
	let obj = {
		groupId: $(MODAL_GROUP_ID_INPUT).val(),
		week: $(MODAL_WEEK_TYPE_INPUT).val(),
		dayNum: $(MODAL_DAY_NUM_INPUT).val(),
		scheduleId: $(MODAL_SCHEDULE_ID_INPUT).val(),
		lessonNum: $(MODAL_LESSON_NUM_SELECT).val(),
		subjectId: $(MODAL_SUBJECT_SELECT).val(),
		lessonType: $(MODAL_LESSON_TYPE_SELECT).val(),
		userId: $(MODAL_TEACHER_SELECT).val(),
		classroom: $(MODAL_CLASSROOM_INPUT).val()
	}
	
	$.ajax({
		type: 'POST',
		url: '/api/schedule/edit',
		data: JSON.stringify(obj),
		contentType: "application/json; charset=utf-8",
		dataType: 'JSON',
		success: function (response) {
			let $editedRow = $("#edit-schedule-content .is-being-edited");
			
			$editedRow.find('.subject').attr('data-id', response.subjectId);
			$editedRow.find('.subject').html(response.subjectTitle);
			$editedRow.find('.lesson-type').attr('data-lesson-type', response.lessonType);
			$editedRow.find('.lesson-type').html(response.lessonTypeName);
			$editedRow.find('.teacher').attr('data-id', response.teacherId);
			$editedRow.find('.teacher').html(response.teacherName);
			$editedRow.find('.classroom').html(response.classroom);
			
			$editedRow.removeClass('is-being-edited');
			
			console.log(response);
			$('.edit-schedule-modal').modal('hide');
		}
	});
}

$(document).on('hidden.bs.modal', '#edit-schedule-modal', function (event) {
	console.log("Remove class");
	let $editedRow = $("#edit-schedule-content .is-being-edited");
	$editedRow.removeClass('is-being-edited');
});

/** Example, how to make query no more than N time per ...
 
$('#teacher').on('keyup', debounce(function () {
	let keyword = $('#teacher').val();
	$.get('/api/teachers/all', null, function(data) {
		console.log(data);
	});
}, 3000))
*/