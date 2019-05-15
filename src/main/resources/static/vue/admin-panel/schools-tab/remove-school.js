import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage, showValidationErrors} from '/vue/util/error-msg-builder.js';

Vue.component('remove-school', {
	data: function () {
		return {
			messages: {
				chooseSchool: "Choose school",
				choose: "Choose",
				delete: "Delete"
			},
			schoolId: 0,
			schools: []
		}
	},
	created() {
		let _this = this;
		serverBus.$on('fetchedSchools', function (schools) {
			_this.schools = schools;
		});
	},
	methods: {
		removeSchool: function (e) {
			e.preventDefault();
			
			let _this = this;
			let $form = $(e.target.closest('form'));
			let obj = {id: _this.schoolId};
			
			let removeSchoolAjaxRequest = $.ajax({
				type: 'DELETE',
				url: $form.attr('action'),
				data: obj,
				dataType: 'json'
			})
			
			removeSchoolAjaxRequest.done(function (response) {
				$form.find('.validation-message').remove();
				_this.schoolId = 0;
				
				$.snackbar({
					content: response.message,
					timeout: 5000
				});
				
				serverBus.$emit('removedSchool', _this.schoolId);
			})
			
			removeSchoolAjaxRequest.fail(function (jqXHR, exception) {
				let msg = getErrorMessage(jqXHR, exception);
				if (jqXHR.status == 400) {
					let errors = jqXHR.responseJSON.details;
					showValidationErrors(errors, $form);
				} else {
					alert(msg);
				}
			});
		}
	},
	template: `
		<form action="/api/control/school" method="DELETE">
			
				<div class="form-group">
					<label for="remove-school_school-select">{{messages.chooseSchool}}</label>
					<select class="form-control" name="schoolId" v-model="schoolId">
						<option value="0">{{messages.choose}}</option>
						<option v-for="school of schools" v-bind:value="school.id">{{school.name}}</option>
					</select>
				</div>
				
				<button type="submit" class="btn btn-success" v-on:click="removeSchool">{{messages.delete}}</button>
		 </form>
	`
});