import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';
import {clearValidationMessages, showValidationErrors} from '/vue/util/validation.js';

Vue.component('edit-subject', {
	data: function () {
		return {
			messages: {
				subject: "Subject",
				choose: "Choose",
				title: "Title",
				edit: "Edit"
			},
			subjects: [],
			editSubjectForm: {
				id: 0,
				name: '',
			}
		}
	},
	created() {
		let _this = this;
		serverBus.$on('fetchedSubjects', function (subjects) {
			_this.subjects = subjects;
		});
	},
	methods: {
		editSubject: function (e) {
			e.preventDefault();
			
			let _this = this;
			let $form = $(e.target.closest('form'));
			let objToSend = _this.editSubjectForm; 
			
			$.ajax({
				type: 'PUT',
				url: $form.attr('action'),
				data: JSON.stringify(objToSend),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json'
			}).done(function (response) {
				clearValidationMessages($form);
				serverBus.$emit('editedSubject', objToSend);
				
				_this.editSubjectForm = {
					id: 0,
					name: '',
				}
				
				$.snackbar({
					content: "Subject was edited!",
					timeout: 5000
				});
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
		<form action="/api/control/subjects" method="PUT">
			<div class="form-group">
				<label for="edit-subject_subject-select">{{messages.subject}}</label>
				<select class="form-control" id="edit-subject_subject-select" name="id"
						v-model="editSubjectForm.id">
					<option value="0" selected>{{messages.choose}}</option>
					<option v-for="subject of subjects" v-bind:value="subject.id">{{subject.name}}</option>
				</select>
			</div>
			
			<div class="form-group">
				<label for="edit-subject_subject-name-input">{{messages.title}}</label>
				<input class="form-control" id="edit-subject_subject-name-input" name="name"
					   v-model="editSubjectForm.name" />
			</div>
			
			<button type="submit" class="btn btn-success" v-on:click="editSubject">{{messages.edit}}</button>
		</form>
	` 
});