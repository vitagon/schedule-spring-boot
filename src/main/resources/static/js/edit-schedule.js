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
	
	let groupId = $(rootTable).attr('data-group-id');
	let week = $(rootTable).attr('data-week');
	let dayNum = $(rootTable).attr('data-day-num');
	
	let scheduleId = $(scheduleRow).attr('data-schedule-id');
	let lessonNum  = $(scheduleRow).find('.lesson-num').html();
	let subjectId  = $(scheduleRow).find('.subject').attr('data-id');
	let lessonType = $(scheduleRow).find('.lesson-type').attr('data-lesson-type');
	let teacherId  = $(scheduleRow).find('.teacher').attr('data-id');
	let classroom  = $(scheduleRow).find('.classroom').html();
	
	
	$(MODAL_GROUP_ID_INPUT).val(groupId);
	$(MODAL_WEEK_TYPE_INPUT).val(week);
	$(MODAL_DAY_NUM_INPUT).val(dayNum);
	$(MODAL_SCHEDULE_ID_INPUT).val(scheduleId);
	$(MODAL_LESSON_NUM_SELECT).val(lessonNum);
	$(MODAL_SUBJECT_SELECT).val(subjectId);
	$(MODAL_TEACHER_SELECT).val(teacherId);
	$(MODAL_LESSON_TYPE_SELECT).val(lessonType);
	
	if (classroom != '-') {
		$(MODAL_CLASSROOM_INPUT).val(classroom);
	}
	
	$(scheduleRow).addClass("is-being-edited");
	$('.edit-schedule-modal').modal('show');
}

function saveScheduleChanges(e) {
	let scheduleId = $(MODAL_SCHEDULE_ID_INPUT).val();
	
	let scheduleDTO = {
		groupId: $(MODAL_GROUP_ID_INPUT).val(),
		week: $(MODAL_WEEK_TYPE_INPUT).val(),
		dayNum: $(MODAL_DAY_NUM_INPUT).val(),
		lessonNum: $(MODAL_LESSON_NUM_SELECT).val(),
		
		subjectId: $(MODAL_SUBJECT_SELECT).val(),
		lessonType: $(MODAL_LESSON_TYPE_SELECT).val(),
		userId: $(MODAL_TEACHER_SELECT).val(),
		classroom: $(MODAL_CLASSROOM_INPUT).val()
	}
	
	let type = '';
	if (scheduleId == 0) {
		type = 'POST';
	} else {
		type = 'PUT';
	}
	
	$.ajax({
		type: type,
		url: '/api/schedule',
		data: JSON.stringify(scheduleDTO),
		contentType: "application/json; charset=utf-8",
		dataType: 'JSON',
		success: function (response) {
			updateRow(response);
		},
		error: function (jqXHR, exception) {
			let msg = '';
	        if (jqXHR.status === 0) {
	            msg = 'Not connect.\n Verify Network.';
	        } else if (jqXHR.status == 404) {
	            msg = 'Requested page not found. [404]';
	        } else if (jqXHR.status == 400) {
	        	let $formGroup = $(MODAL_SUBJECT_SELECT).closest(".form-group");
	        	
	        	// clear old errorMessage div
	        	$formGroup.find('.validation-message').remove();
	        	
	        	let errorMsgs = jqXHR.responseJSON.details.subjectId;
	        	$.each(errorMsgs, function (index, msg) {
	        		$formGroup.append('<div class="validation-message" style="color: red">' +
	        				msg +
	    	        		'</div>');
	        	});
	        	return;
	        } else if (jqXHR.status == 500) {
	            msg = jqXHR.responseJSON.message;
	        } else if (exception === 'parsererror') {
	            msg = 'Requested JSON parse failed.';
	        } else if (exception === 'timeout') {
	            msg = 'Time out error.';
	        } else if (exception === 'abort') {
	            msg = 'Ajax request aborted.';
	        } else {
	            msg = 'Uncaught Error.\n' + jqXHR.responseJSON.message;
	        }
			alert(msg);
		}
	});
}

$(document).on('hidden.bs.modal', '#edit-schedule-modal', function (event) {
	let $editedRow = $("#edit-schedule-content .is-being-edited");
	$editedRow.removeClass('is-being-edited');
	
	// clear old errorMessage div
	let $formGroup = $(MODAL_SUBJECT_SELECT).closest(".form-group");
	$formGroup.find('.validation-message').remove();
});

/**
 * This function will be called after successful schedule creation/edit
 * It updated row in the table with new data,
 * for ex. with new subject or classroom
 * 
 * @param response
 * @returns
 */
function updateRow(response, lio) {
	let $editedRow = $("#edit-schedule-content .is-being-edited");
	
	$editedRow.attr('data-schedule-id', response.id);
	$editedRow.find('.subject').attr('data-id', response.subjectId);
	$editedRow.find('.subject').html(response.subjectTitle);
	
	if (response.lessonType != null) {
		$editedRow.find('.lesson-type').attr('data-lesson-type', response.lessonType);
		$editedRow.find('.lesson-type').html(response.lessonTypeName);
	} else {
		$editedRow.find('.lesson-type').attr('data-lesson-type', 0);
		$editedRow.find('.lesson-type').html('-');
	}
	
	$editedRow.find('.teacher').attr('data-id', response.teacherId);
	if (response.teacherId != 0) {
		$editedRow.find('.teacher').html(response.teacherName);
	} else {
		$editedRow.find('.teacher').html('-');
	}
	
	if (response.classroom != null) {
		$editedRow.find('.classroom').html(response.classroom);
	} else {
		$editedRow.find('.classroom').html('-');
	}
	
	$editedRow.removeClass('is-being-edited');
	$('.edit-schedule-modal').modal('hide');
}

function showRemoveScheduleModal(e) {
	let scheduleRow = e.target.closest('tr');
	
	let scheduleId = $(scheduleRow).attr('data-schedule-id');
	
	if (scheduleId == 0) {
		alert('Schedule does not exist!');
	} else {
		$('#remove-schedule-modal .scheduleId').val(scheduleId);
		$('#remove-schedule-modal').modal('show');
	}
}

function removeSchedule(e) {
	let scheduleId = $('#remove-schedule-modal .scheduleId').val();
	
	$.ajax({
		type: 'DELETE',
		data: {"id": scheduleId},
		url: '/api/schedule',
		dataType: 'json',
		success: function (response) {
			alert('Record was deleted!');
			let scheduleRow = $('#edit-schedule-content tr[data-schedule-id='+ scheduleId + ']');
			$(scheduleRow).attr('data-schedule-id', 0);
			$(scheduleRow).find('.subject').attr('data-id', 0);
			$(scheduleRow).find('.subject').html('-');
			$(scheduleRow).find('.lesson-type').attr('data-lesson-type', 0);
			$(scheduleRow).find('.lesson-type').html('-');
			$(scheduleRow).find('.teacher').attr('data-id', 0);
			$(scheduleRow).find('.teacher').html('-');
			$(scheduleRow).find('.classroom').html('-');
			$('#remove-schedule-modal').modal('hide');
		},
		error: function (jqXHR, exception) {
			let msg = '';
	        if (jqXHR.status === 0) {
	            msg = 'Not connect.\n Verify Network.';
	        } else if (jqXHR.status == 404) {
	            msg = 'Requested page not found. [404]';
	        } else if (jqXHR.status == 500) {
	            msg = 'Internal Server Error [500].';
	        } else if (exception === 'parsererror') {
	            msg = 'Requested JSON parse failed.';
	        } else if (exception === 'timeout') {
	            msg = 'Time out error.';
	        } else if (exception === 'abort') {
	            msg = 'Ajax request aborted.';
	        } else {
	            msg = 'Uncaught Error.\n' + jqXHR.responseText;
	        }
			alert(msg);
		}
	});
}

/** Example, how to make query no more than N time per ...
 
$('#teacher').on('keyup', debounce(function () {
	let keyword = $('#teacher').val();
	$.get('/api/teachers/all', null, function(data) {
		console.log(data);
	});
}, 3000))
*/