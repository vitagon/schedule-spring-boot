const PRACTICE = 'practice';
const LECTURE  = 'lecture';

const debounce = (func, delay) => { 
    let debounceTimer 
    return function() { 
        const context = this;
        const args = arguments; 
        clearTimeout(debounceTimer);
        debounceTimer = setTimeout(() => func.apply(context, args), delay); 
    } 
}

$('.edit-schedule-btn').on('click', function (e) {
	let scheduleRow = e.target.closest('tr');
	let rootTable = scheduleRow.closest('table');
	
	let week = $(rootTable).attr('data-week');
	let day = $(rootTable).attr('data-day');
	let scheduleId = $(scheduleRow).attr('data-schedule-id');
		
	let lessonNum = $(scheduleRow).find('.lessonNum').html();
	let subjectTitle = $(scheduleRow).find('.subjectTitle').html();
	let lessonType = $(scheduleRow).find('.lessonType').attr('data-lesson-type');
	let teacherId = $(scheduleRow).find('.teacher').attr('data-teacher-id');
	let classroom = $(scheduleRow).find('.classroom').html();
	
	let obj = {
		week: week,
		day: day,
		lessonNum: lessonNum,
		subjectTitle: subjectTitle,
		lessonType: lessonType,
		teacher: teacherId,
		classroom: classroom
	}
	
	// get elements from modal
	let scheduleIdInput = $('#schedule-id');
	let lessonNumSelect = $('#lesson-num-select');
	let subjectTitleInput = $('#subject-title');
	let lessonTypeSelect = $('#lesson-type-select');
	let teacherSelect = $('#teacher-select');
	let classroomInput = $('#classroom');
	
	scheduleIdInput.val(scheduleId);
	lessonNumSelect.val(lessonNum);
	subjectTitleInput.val(subjectTitle);
	teacherSelect.val(teacherId);
	classroomInput.val(classroom);
	
	let lessonTypeNumeric;
	switch (lessonType) {
		case LECTURE: {
			lessonTypeNumeric = 1;
			break;
		}
		case PRACTICE: {
			lessonTypeNumeric = 2;
			break;
		}
		default: {
			lessonTypeNumeric = 0;
			break;
		}
	}
	lessonTypeSelect.val(lessonTypeNumeric);
	
	$('.edit-schedule-modal').modal('show');
});

$('.schedule-save-changes-btn').on('click', function (e) {
	let obj = {
		scheduleId: $('#schedule-id').val(),
		lessonNum: $('#lesson-num-select').val(),
		subjectTitle: $('#subject-title').val(),
		lessonType: $('#lesson-type-select').val(),
		teacherId: $('#teacher-select').val(),
		classroom: $('#classroom').val()
	}
	
	console.log(obj);
	
	$.ajax({
		type: 'POST',
		url: '/api/schedule/edit',
		data: obj,
		dataType: 'JSON',
		success: function (response) {
			console.log(response);
		}
	});
});

$('#teacher').on('keyup', debounce(function () {
	let keyword = $('#teacher').val();
	$.get('/api/teachers/all', null, function(data) {
		console.log(data);
	});
}, 3000))