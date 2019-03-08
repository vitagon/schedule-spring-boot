const $schoolSelect = $("#choose-schedule-school__select");
const $majorSelect  = $("#choose-schedule-major__select");
const $courseSelect = $("#choose-schedule-course__select");
const $groupSelect  = $("#choose-schedule-group__select");

const $editScheduleContent = $("#edit-schedule-content");


$('#sidebar-toggle').click(function(e) {
	$('#admin-panel_tabs-wrap').toggleClass('side-menu-toggled');
	$('#admin-panel_tabs-content').toggleClass('side-menu-toggled');
	e.stopPropagation();
});

$('#admin-panel_tabs a').on('click', function(e) {
  e.preventDefault();
  let $target = $(e.target);
  
  // if link is a parent link, we should just show submenu and hide others tab's submenus
  if ($target.hasClass('submenu_parent-link')) {
	  let submenuIsOpened = $target.hasClass('active');
	  
	  $('.nav-item_lvl-0').not(".opened").find('.submenu_parent-link').removeClass(['active','show']);
	  $('.nav-item_lvl-0').not(".opened").find('.side-menu-tab_submenu').collapse('hide');
	  
	  if (!submenuIsOpened) {
		  $target.toggleClass(['show', 'active']);
	  }
	  
	  $target.siblings('ul').collapse('toggle');
	  return;
  }
  
  // hide all tab's content
  $('#admin-panel_tabs-content .tab-pane').removeClass(['show','active']);
  
  // if target is a link of sublist we should not remove .active.show from parent link
  if ($target.closest('.side-menu-tab_submenu').length) {
	  
	  let rootLinks = $('.nav-item_lvl-0');
	  for (rootLink of rootLinks) {
		  let rootLink_submenu = $(rootLink).find('.side-menu-tab_submenu');
		  let target_submenu = $target.closest('.side-menu-tab_submenu'); 
		  
		  if (rootLink_submenu != null && rootLink_submenu[0] != target_submenu[0]) {
			  $(rootLink_submenu).collapse('hide');
			  $(rootLink).removeClass('opened');
			  $(rootLink).find('a').removeClass(['show','active']);
		  }
		  
		  if (rootLink_submenu != null && rootLink_submenu[0] == target_submenu[0]) {
			  $(target_submenu).find('a').removeClass(['show','active']);
		  }
		  
		  if (rootLink_submenu == null) {
			  $(rootLink).removeClass('opened');
		  }
	  }
	  
	  $target.closest('.nav-item_lvl-0').addClass('opened');
  } else {
	  // collapse all sidemenu submenu-s 
	  $('#admin-panel_tabs .side-menu-tab_submenu').collapse('hide');
	  // deactivate all active tabs
	  $('#admin-panel_tabs a').removeClass('show');
	  $('#admin-panel_tabs a').removeClass('active');
  }
  
  // mark tab and show tab content
  let href = $target[0].hash;
  $(href).addClass('show');
  $(href).addClass('active');
  $target.addClass('show');
  $target.addClass('active');
  
  // if it is mobile device, then hide side menu (after user had chosen tab)
  if ($(window).width() < 768) {
	  $('#admin-panel_tabs-wrap').toggleClass('side-menu-toggled');
	  $('#admin-panel_tabs-content').toggleClass('side-menu-toggled');
  }
});

$(document).click(function(event) {
	
	// when user clicks on 'content' on mobile devices, then hide sidemenu if it is opened
	if (!$(event.target).closest("#admin-panel_tabs-wrap").length &&
			$(window).width() < 768 &&
			$('#admin-panel_tabs-wrap').hasClass('side-menu-toggled') &&
			$('#admin-panel_tabs-content').hasClass('side-menu-toggled')) {
		$('#admin-panel_tabs-wrap').toggleClass('side-menu-toggled');
		$('#admin-panel_tabs-content').toggleClass('side-menu-toggled');
	}
})

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

function showValidationErrors(errors, $form) {
	$form.find('.validation-message').remove();
	$.each(errors, function (fieldName, fieldErrors) {
		let $field = $form.find('[name=' + fieldName + ']');
		
		if ($field != null) {
			let $fieldWrap = $field.closest('div');
			$.each(fieldErrors, function (index, fieldError) {
				$fieldWrap.append('<div class="validation-message" style="color: red">' +
								  fieldError +
								  '</div>');
			});
		}
	});
}

function getSchoolsList(e) {
	let localeId = e.target.value;
	
	$.ajax({
		type: 'GET',
		url: '/api/control/schools/view',
		data: {localeId: localeId},
		contentType: 'application/json; charset=utf-8',
		dataType: 'html',
		success: function(response) {
			$('.schools-list_content').html(response);
		},
		error: function(jqXHR, exception) {
			let msg = getErrorMessage(jqXHR, exception);
			alert(msg);
		}
	});
}

function addSchool(e) {
	e.preventDefault();
	
	let $form = $(e.target.closest('form'));
	let schoolName = $form.find('input[name=schoolName]').val();
	
	let obj = {schoolName: schoolName};
	
	$.ajax({
		type: 'POST',
		url: $form.attr('action'),
		data: JSON.stringify(obj),
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		success: function (response) {
			$form.find('.validation-message').remove();
			$form.find('input[name=schoolName]').val('');
			
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

function editSchool(e) {
	e.preventDefault();
	
	let $form = $(e.target.closest('form'));
	let obj = {
		schoolId: $form.find('select[name=schoolId]').val(),
		newSchoolName: $form.find('input[name=newSchoolName]').val()
	}
	
	$.ajax({
		type: 'PUT',
		url: $form.attr('action'),
		data: JSON.stringify(obj),
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		success: function(response) {
			$form.find('.validation-message').remove();
			$form.find('select[name=schoolId]').val(0);
			$form.find('input[name=newSchoolName]').val('');
			
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

function removeSchool(e) {
	e.preventDefault();
	
	let $form = $(e.target.closest('form'));
	let obj = {id: $form.find('select[name=schoolId]').val()};
	
	$.ajax({
		type: 'DELETE',
		url: $form.attr('action'),
		data: obj,
		dataType: 'json',
		success: function(response) {
			$form.find('.validation-message').remove();
			$form.find('select[name=schoolId]').val(0);
			
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

function getMajorsList(e) {
	let localeId = e.target.value;
	
	$.ajax({
		type: 'GET',
		url: '/api/control/majors/view',
		data: {localeId: localeId},
		contentType: 'application/json; charset=utf-8',
		dataType: 'html',
		success: function(response) {
			$('#majors-list_content').html(response);
		},
		error: function(jqXHR, exception) {
			let msg = getErrorMessage(jqXHR, exception);
			alert(msg);
		}
	});
}

function addMajor(e) {
	e.preventDefault();
	
	let $form = $(e.target.closest('form'));
	let obj = getFormData($form);
	
	$.ajax({
		type: 'POST',
		url: $form.attr('action'),
		data: JSON.stringify(obj),
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		success: function (response) {
			clearValidationMessages($form);
			clearForm($form);
			
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

function editMajor(e) {
	e.preventDefault();
	
	let $form = $(e.target.closest('form'));
	let obj = getFormData($form);
	
	$.ajax({
		type: 'PUT',
		url: $form.attr('action'),
		data: JSON.stringify(obj),
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		success: function (response) {
			clearValidationMessages($form);
			clearForm($form);
			
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

function removeMajor(e) {
	e.preventDefault();
	
	let $form = $(e.target.closest('form'));
	let obj = {id: $form.find('select[name=id]').val()};
	
	$.ajax({
		type: 'DELETE',
		url: $form.attr('action'),
		data: obj,
		dataType: 'json',
		success: function(response) {
			clearValidationMessages($form);
			clearForm($form);
			
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

function addGroup(e) {
	e.preventDefault();
	
	let $form = $(e.target.closest('form'));
	let obj = getFormData($form);
	
	$.ajax({
		type: 'POST',
		url: $form.attr('action'),
		data: JSON.stringify(obj),
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		success: function (response) {
			clearValidationMessages($form);
			clearForm($form);
			
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

function editGroup(e) {
	e.preventDefault();
	
	let $form = $(e.target.closest('form'));
	let obj = getFormData($form);
	
	$.ajax({
		type: 'PUT',
		url: $form.attr('action'),
		data: JSON.stringify(obj),
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		success: function (response) {
			clearValidationMessages($form);
			clearForm($form);
			
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

function removeGroup(e) {
	e.preventDefault();
	
	let $form = $(e.target.closest('form'));
	let obj = {id: $form.find('select[name=id]').val()};
	
	$.ajax({
		type: 'DELETE',
		url: $form.attr('action'),
		data: obj,
		dataType: 'json',
		success: function(response) {
			clearValidationMessages($form);
			clearForm($form);
			
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