import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage, showValidationErrors} from '/vue/util/error-msg-builder.js';

Vue.component('edit-school', {
	data: function () {
		return {
			messages: {
				chooseSchool: "Choose school",
				choose: "Choose",
				schoolName: "School name",
				edit: "Edit"
			},
			schools: [],
			schoolId: 0,
			schoolName: ''
		} 
	},
	created() {
		let _this = this;
		serverBus.$on('fetchedSchools', function (schools) {
			_this.schools = schools;
		});
	},
	methods: {
		editSchool: function (e) {
			e.preventDefault();
			
			let _this = this;
			let $form = $(e.target.closest('form'));
			let objToSend = {
				schoolId: _this.schoolId,
				newSchoolName: _this.schoolName
			}
			
			let editSchoolAjaxRequest = $.ajax({
				type: 'PUT',
				url: $form.attr('action'),
				data: JSON.stringify(objToSend),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json'
			});
			
			editSchoolAjaxRequest.done(function (response) {
				$form.find('.validation-message').remove();
				
				$.snackbar({
					content: response.message,
					timeout: 5000
				});
				
				_this.schoolName = '';
				serverBus.$emit('editedSchool', {id: _this.schoolId, name: _this.schoolName});
			});
			
			editSchoolAjaxRequest.fail(function (jqXHR, exception) {
				let msg = getErrorMessage(jqXHR, exception);
				if (jqXHR.status == 400) {
					let errors = jqXHR.responseJSON.details;
					showValidationErrors(errors, $form);
				} else {
					alert(msg);
				}
			});
		},
		fillSchoolNameInput: function () {
			if (this.schoolId == 0) {
				this.schoolName = '';
				return;
			}
			
			let school = this.schools.find(x => x.id == this.schoolId);
			this.schoolName = school.name; 
		}
	},
	template: `
		<form action="/api/control/school" method="PUT">
			<div class="form-group">
				<label for="edit-school_school-select">{{messages.chooseSchool}}</label>
				<select class="form-control" id="edit-school_school-select" name="schoolId"
					    v-model="schoolId" v-on:change="fillSchoolNameInput">
					<option value="0">{{messages.choose}}</option>
					<option v-for="school of schools" v-bind:value="school.id">{{school.name}}</option>
				</select>
			</div>
			
			<div class="form-group">
				<label for="edit-school_school-name-input">{{messages.schoolName}}</label>
				<input class="form-control" name="newSchoolName" v-model="schoolName" />
			</div>
			
			<button type="submit" class="btn btn-success" v-on:click="editSchool">{{messages.edit}}</button>
		 </form>
	`
});