import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';
import {clearValidationMessages, showValidationErrors} from '/vue/util/validation.js';

Vue.component('remove-subject', {
	data: function () {
		return {
			messages: {
				subject: "Subject",
				choose: "Choose",
				delete: "Delete"
			},
			subjects: [],
			id: 0
		}
	},
	created() {
		let _this = this;
		serverBus.$on('fetchedSubjects', function (subjects) {
			_this.subjects = subjects;
		});
	},
	methods: {
		removeSubject: function (e) {
			e.preventDefault();
			
			let _this = this;
			let $form = $(e.target.closest('form'));
			let objToSend = {id: _this.id};
			
			$.ajax({
				type: 'DELETE',
				url: $form.attr('action'),
				data: objToSend,
				dataType: 'json'
			}).done(function(response) {
				clearValidationMessages($form);
				serverBus.$emit('removedSubject', _this.id);
				_this.id = 0;
				
				$.snackbar({
					content: response.message,
					timeout: 5000
				});
			}).fail(function(jqXHR, exception) {
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
		<form action="/api/control/subjects" method="DELETE">
			<div class="form-group">
				<label for="remove-subject_subject-select">{{messages.subject}}</label>
				<select class="form-control" id="remove-subject_subject-select" name="id" v-model="id">
					<option value="0" selected>{{messages.choose}}</option>
					<option v-for="subject of subjects" v-bind:value="subject.id">{{subject.name}}</option>
				</select>
			</div>
			
			<button type="submit" class="btn btn-success" v-on:click="removeSubject">{{messages.delete}}</button>
		 </form>
	` 
});