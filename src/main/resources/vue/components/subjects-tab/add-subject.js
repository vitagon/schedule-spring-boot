import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';
import {clearValidationMessages, showValidationErrors} from '/vue/util/validation.js';

Vue.component('add-subject', {
	data: function () {
		return {
			messages: {
				englishTitle: "English title",
				add: "Add"
			},
			title: ""
		}
	},
	methods: {
		addSubject: function (e) {
			e.preventDefault();
			
			let _this = this;
			let $form = $(e.target.closest('form'));
			let obj = {subjectName: this.title};
			
			$.ajax({
				type: 'POST',
				url: $form.attr('action'),
				data: JSON.stringify(obj),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json'
			}).done(function (response) {
				clearValidationMessages($form);
				_this.title = "";
				
				$.snackbar({
					content: "Subject was added!",
					timeout: 5000
				});
				
				serverBus.$emit('addedSubject', response);
			}).fail(function (jqXHR, exception) {
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
		<form action="/api/control/subjects" method="POST">
				<!-- Add subject using english lang for name as default -->
		 		<div class="form-group">
			 		<label for="subject-name">{{messages.englishTitle}}</label>
			 		<input type="text" class="form-control" id="subject-name" name="subjectName" v-model="title" />
				</div>
				
				<button type="submit" class="btn btn-success" v-on:click="addSubject">{{messages.add}}</button>
		 </form>
	` 
});