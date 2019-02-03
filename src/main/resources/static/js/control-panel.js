const $schoolSelect = $("#choose-schedule-school__select");
const $majorSelect  = $("#choose-schedule-major__select");
const $courseSelect = $("#choose-schedule-course__select");
const $groupSelect  = $("#choose-schedule-group__select");

const $editScheduleContent = $("#edit-schedule-content");

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
				return rej(msg);
			}
		});
	});
}