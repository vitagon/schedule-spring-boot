import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {clearValidationMessages, showValidationErrors} from '/vue/util/validation.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js'

Vue.component('remove-major', {
	data: function () {
		return {
			messages: {
				major: "Major",
				delete: "Delete",
				choose: "Choose"
			},
			removeMajorForm: {
				id: 0
			},
			majors: []
		}
	},
	created() {
		let _this = this;
		serverBus.$on('fetchedMajors', function (majors) {
			_this.majors = majors;
		});
	},
	methods: {
		removeMajor: function (e) {
			let _this = this;
			e.preventDefault();
			let $form = $(e.target.closest('form'));
			
			$.ajax({
				type: 'DELETE',
				url: '/api/control/major',
				data: _this.removeMajorForm,
				dataType: 'json',
				success: function(response) {
					clearValidationMessages($form);
					serverBus.$emit('removedMajor', _this.removeMajorForm.id);
					_this.removeMajorForm.id = 0;
					
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
	},
	template: `
		<form action="/api/control/major" method="DELETE">
	 		<div class="form-group">
		 		<label for="remove-major_major">{{messages.major}}</label>
		 		<select class="form-control" id="remove-major_major" name="id" v-model="removeMajorForm.id">
		 			<option value="0">{{messages.choose}}</option>
		 			<option v-for="major of majors" v-bind:value="major.id">{{major.name}}</option>
		 		</select>
			</div>
			
			<button type="submit" class="btn btn-success" v-on:click="removeMajor">{{messages.delete}}</button>
		</form>
	`
});