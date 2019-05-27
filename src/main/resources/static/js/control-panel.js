import {SchoolsTab} from '/js/control/schools-tab.js';


const $schoolSelect = $("#choose-schedule-school__select");
const $majorSelect  = $("#choose-schedule-major__select");
const $courseSelect = $("#choose-schedule-course__select");
const $groupSelect  = $("#choose-schedule-group__select");

const $editScheduleContent = $("#edit-schedule-content");

let schoolTab = new SchoolsTab();
document.querySelector("#schools-list_locale").addEventListener("change", function (event) {
	schoolTab.getSchoolsList(event);
});
document.querySelector("#add-school_btn").addEventListener("click", function (event) {
	schoolTab.addSchool(event);
});
document.querySelector("#remove-school_btn").addEventListener("click", function(event) {
	schoolTab.removeSchool(event);
});



$schoolSelect.on('change', function() {
	// if user choose default value (option[value=0])
	if (this.value == 0) return;
	
	let schoolId = this.value;
	let url = "/api/school/" + schoolId + "/majors";
	getRequest({url: url})
		.then(
			success => {
				$majorSelect.find("option:not([value='0'])").remove();
				$.each(success, function(majorId, majorTitle) {
					$majorSelect.append($("<option />").val(majorId).text(majorTitle));
				})
			},
			error => {
				alert(error);
			}
		);
});

$majorSelect.on('change', function() {
	// if user choose default value (option[value=0])
	if (this.value == 0) return;
	
	let majorId = this.value;
	let url = "/api/major/" + majorId + "/course-number";
	getRequest({url: url})
		.then(
			success => {
				let duration = success;
				$courseSelect.find("option:not([value='0'])").remove();
				let arrOfNumbers = Array.from({length: duration}, (v, k) => k+1);
				$.each(arrOfNumbers, function(index, courseNumber) {
					$courseSelect.append($("<option />").val(courseNumber).text(courseNumber));
				})
			},
			error => {
				alert(error);
			}
		)
});

$courseSelect.on('change', function() {
	// if user choose default value (option[value=0])
	if (this.value == 0) return;
	
	let courseNumber = this.value;
	let url = "/api/"+ $majorSelect.val() + "/" + courseNumber + "/groups";
	getRequest({url: url})
		.then(
			success => {
				$groupSelect.find("option:not([value='0'])").remove();
				$.each(success, function(key, value) {
					$groupSelect.append($("<option />").val(key).text(value));
				})
			},
			error => {
				alert(error);
			}
		)
});

$groupSelect.on('change', function() {
	// if user choose default value (option[value=0])
	if (this.value == 0) return;
	
	let groupId = this.value;
	let url = "/api/" + groupId + "/schedule/edit";
	getRequest({url: url, dataType: 'html'})
		.then(
			success => {
				$editScheduleContent.html(success);
			},
			error => {
				alert(error);
			}
		)
});

function getRequest(request) {
	let url = request.url;
	let dataType = request.dataType;
	if (dataType == null || dataType == '') {
		dataType = 'json'
	}
	
	return new Promise((res,rej) => {
		$.ajax({
			url: url,
			dataType: dataType,
			success: function(data) {
				return res(data);
			},
			error: function(jqXHR, exception) {
				return rej(getErrorMessage(jqXHR, exception));
			}
		});
	});
}


function addTeacherTranslation(e) {
	e.preventDefault();
	let $form = $(e.target.closest('form'));
	
	let $userId = $form.find('select[name=userId]');
	let $localeId = $form.find('select[name=localeId]');
	let $lastname = $form.find('input[name=lastname]');
	let $firstname = $form.find('input[name=firstname]');
	let $middlename = $form.find('input[name=middlename]');
	
	let obj = {
		userId: $userId.val(),
		localeId: $localeId.val(),
		lastname: $lastname.val(),
		firstname: $firstname.val(),
		middlename: $middlename.val()
	};
	
	console.log(obj);
	$.ajax({
		type: 'POST',
		url: $form.attr('action'),
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(obj),
		dataType: 'json',
		success: function (response) {
			$form.find('.validation-message').remove();
			$userId.val(0);
			$localeId.val(0);
			$lastname.val('');
			$firstname.val('');
			$middlename.val('');
			
			$.snackbar({
				content: response.message,
				timeout: 5000
			});
		},
		error: function (jqXHR, exception) {
			let msg = getErrorMessage(jqXHR, exception);
			if (jqXHR.status == 400) {
				let errors = jqXHR.responseJSON.details;
				showValidationErrors(errors, $form);
			} else {
				alert(msg);
			}
		}
	});
}


function addSubjectTranslation(e) {
	e.preventDefault();
	let $form = $(e.target.closest('form'));
	
	let $subjectId = $form.find('select[name=subjectId]');
	let $localeId = $form.find('select[name=localeId]');
	let $title = $form.find('input[name=title]');
	
	let obj = {
		subjectId: $subjectId.val(),
		localeId: $localeId.val(),
		title: $title.val()
	};
	
	console.log(obj);
	$.ajax({
		type: 'POST',
		url: $form.attr('action'),
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(obj),
		dataType: 'json',
		success: function (response) {
			$form.find('.validation-message').remove();
			$subjectId.val(0);
			$localeId.val(0);
			$title.val('');
			
			$.snackbar({
				content: response.message,
				timeout: 5000
			});
		},
		error: function (jqXHR, exception) {
			let msg = getErrorMessage(jqXHR, exception);
			if (jqXHR.status == 400) {
				let errors = jqXHR.responseJSON.details;
				showValidationErrors(errors, $form);
			} else {
				alert(msg);
			}
		}
	});
}

function addSubject(e) {
	e.preventDefault();
	
	let $form = $(e.target.closest('form'));
	let subjectName = $form.find('input[name=subjectName]').val();
	
	let obj = {subjectName: subjectName};
	
	$.ajax({
		type: 'POST',
		url: $form.attr('action'),
		data: JSON.stringify(obj),
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		success: function (response) {
			$form.find('.validation-message').remove();
			$form.find('input[name=subjectName]').val('');
			
			$.snackbar({
				content: response.message,
				timeout: 5000
			});
		},
		error: function (jqXHR, exception) {
			let msg = getErrorMessage(jqXHR, exception);
			if (jqXHR.status == 400) {
				let errors = jqXHR.responseJSON.details;
				showValidationErrors(errors, $form);
			} else {
				alert(msg);
			}
		}
		
	});
}

function getSubjectsList(e) {
	let localeId = e.target.value;
	let $subjectsListContainer = $('.subjects-list_content'); 
	
	$.ajax({
		type: 'GET',
		url: '/api/control/subjects/view',
		data: {localeId: localeId},
		contentType: 'application/json; charset=utf-8',
		dataType: 'html',
		success: function(response) {
			$subjectsListContainer.html(response);
		},
		error: function(jqXHR, exception) {
			let msg = getErrorMessage(jqXHR, exception);
			alert(msg);
		}
	});
}

function editSubject(e) {
	e.preventDefault();
	
	let $form = $(e.target.closest('form'));
	let obj = {
		oldSubjectId: $form.find('select[name=oldSubjectId]').val(),
		newSubjectName: $form.find('input[name=newSubjectName]').val() 
	}
	
	$.ajax({
		type: 'PUT',
		url: $form.attr('action'),
		data: JSON.stringify(obj),
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		success: function(response) {
			$form.find('.validation-message').remove();
			$form.find('select[name=oldSubjectId]').val(0);
			$form.find('input[name=newSubjectName]').val('');
			
			$.snackbar({
				content: response.message,
				timeout: 5000
			});
		},
		error: function(jqXHR, exception) {
			let msg = getErrorMessage(jqXHR, exception);
			if (jqXHR.status == 400) {
				let errors = jqXHR.responseJSON.details;
				showValidationErrors(errors, $form);
			} else {
				alert(msg);
			}
		}
	});
}

function removeSubject(e) {
	e.preventDefault();
	let $form = $(e.target.closest('form'));
	
	$.ajax({
		type: 'DELETE',
		url: $form.attr('action'),
		data: {id: $form.find('select[name=subjectId]').val()},
		dataType: 'json',
		success: function(response) {
			$form.find('.validation-message').remove();
			$form.find('select[name=subjectId]').val(0);
			
			$.snackbar({
				content: response.message,
				timeout: 5000
			});
		},
		error: function(jqXHR, exception) {
			let msg = getErrorMessage(jqXHR, exception);
			if (jqXHR.status == 400) {
				let errors = jqXHR.responseJSON.details;
				showValidationErrors(errors, $form);
			} else {
				alert(msg);
			}
		}
	});
}

function getFormData($form){
    let unindexed_array = $form.serializeArray();
    let indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function clearValidationMessages($form) {
	$form.find('.validation-message').remove();
}

function clearForm($form) {
	$form.find('select').val(0);
	$form.find('input').val('');
}

function getErrorMessage(jqXHR, exception) {
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
	return msg;
}