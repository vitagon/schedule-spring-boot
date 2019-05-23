import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage, showValidationErrors} from '/vue/util/error-msg-builder.js';
import {clearValidationMessages} from '/vue/util/validation.js';

Vue.component('edit-major', {
	data: function () {
		return {
			messages: {
				major: "Major",
				englishName: "English name",
				duration: "Duration",
				choose: "Choose",
				degree: "Degree",
				edit: "Edit"
			},
			editMajorForm: {
				id: 0,
				name: '',
				duration: 0,
				degree: 0
			},
			majors: [],
			degrees: []
		}
	},
	created() {
		let _this = this;
		serverBus.$on('fetchedMajors', function (majors) {
			_this.majors = majors;
		});
		
		serverBus.$on('fetchedDegrees', function (degrees) {
			_this.degrees = degrees;
		});
	},
	methods: {
		editMajor: function (e) {
			let _this = this;
			e.preventDefault();
			
			let $form = $(e.target.closest('form'));
			
			$.ajax({
				type: 'PUT',
				url: $form.attr('action'),
				data: JSON.stringify(_this.editMajorForm),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json',
				success: function (response) {
					serverBus.$emit('editedMajor', _this.editMajorForm);
					clearValidationMessages($form);
					_this.editMajorForm = {
						id: 0,
						name: '',
						duration: 0,
						degree: 0
					};
					
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
	},
	template: `
		<form action="/api/control/major" method="PUT">
	 		<div class="form-group">
		 		<label for="edit-major_major-id">{{messages.major}}</label>
		 		<select class="form-control" id="edit-major_major-id" name="id" v-model="editMajorForm.id">
		 			<option value="0">{{messages.choose}}</option>
		 			<option v-for="major of majors" v-bind:value="major.id">{{major.name}}</option>
		 		</select>
			</div>
		
			<!-- Add subject using english lang for name as default -->
	 		<div class="form-group">
		 		<label for="edit-major_major-name">{{messages.englishName}}</label>
		 		<input type="text" class="form-control" id="edit-major_major-name" name="name" v-model="editMajorForm.name" />
			</div>
			
			<div class="form-group">
		 		<label for="edit-major_duration">{{messages.duration}}</label>
		 		<select class="form-control" id="edit-major_duration" name="duration" v-model="editMajorForm.duration">
		 			<option value="0">{{messages.choose}}</option>
		 			<option v-for="n in 7" v-bind:value="n">{{n}}</option>
		 		</select>
			</div>
			
			<div class="form-group">
		 		<label for="edit-major_degree">{{messages.degree}}</label>
		 		<select class="form-control" id="edit-major_degree" name="degree" v-model="editMajorForm.degree">
		 			<option value="0">{{messages.choose}}</option>
		 			<option v-for="degree of degrees" v-bind:value="degree">{{degree}}</option>
		 		</select>
			</div>
			
			<button type="submit" class="btn btn-success" v-on:click="editMajor">{{messages.edit}}</button>
		 </form>
	`
});