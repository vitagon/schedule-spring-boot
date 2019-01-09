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
		
	let lessonNum = $(scheduleRow).find('.lessonNum').html();
	let subjectTitle = $(scheduleRow).find('.subjectTitle').html();
	let lessonType = $(scheduleRow).find('.lessonType').attr('data-lesson-type');
	let teacherName = $(scheduleRow).find('.teacher').html();
	let classroom = $(scheduleRow).find('.classroom').html();
	
	let obj = {
		week: week,
		day: day,
		lessonNum: lessonNum,
		subjectTitle: subjectTitle,
		lessonType: lessonType,
		teacher: teacherName,
		classroom: classroom
	}
	
	console.log(obj);
	
	// get elements from modal
	let lessonNumSelect = $('#lesson-num-select');
	let subjectTitleInput = $('#subject-title');
	let lessonTypeSelect = $('#lesson-type-select');
	let teacherInput = $('#teacher');
	let classroomInput = $('#classroom');
	
	lessonNumSelect.val(lessonNum);
	subjectTitleInput.val(subjectTitle);
	teacherInput.val(teacher);
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

$('#teacher').on('keyup', debounce(function () {
	let keyword = $('#teacher').val();
	$.get('/api/teachers/all', null, function(data) {
		console.log(data);
	});
}, 3000))