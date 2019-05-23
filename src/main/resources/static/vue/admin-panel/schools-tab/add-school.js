import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';
import {showValidationErrors} from '/vue/util/validation.js';

Vue.component('add-school', {
	data: function () {
		return {
			messages: {
				englishTitle: "English title",
				add: "Add"
			},
			schoolName: ''
		}
	},
	methods: {
		addSchool: function (e) {
			let _this = this;
			e.preventDefault();
			
			let $form = $(e.target.closest('form'));
			let obj = {schoolName: _this.schoolName};
			
			let addSchoolAjaxRequest = $.ajax({
				type: 'POST',
				url: $form.attr('action'),
				data: JSON.stringify(obj),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json'
			});
			
			addSchoolAjaxRequest.done(function (school) {
				$form.find('.validation-message').remove();
				
				$.snackbar({
					content: "School was added!",
					timeout: 5000
				});
				
				_this.schoolName = '';
				serverBus.$emit('addedSchool', school);
			});
			
			addSchoolAjaxRequest.fail(function (jqXHR, exception) {
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
		<form action="/api/control/school" method="POST">
			
			<!-- Add school in English language -->
			<div class="form-group">
		 		<label for="school-name">{{messages.englishTitle}}</label>
		 		<input type="text" class="form-control" id="school-name" v-model="schoolName">
			</div>
			
			<button type="submit" v-on:click="addSchool" class="btn btn-success" id="add-school_btn">{{messages.add}}</button>
		</form>
	`
});