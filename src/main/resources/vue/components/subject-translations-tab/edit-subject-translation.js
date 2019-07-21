import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';
import {showValidationErrors, clearValidationMessages} from '/vue/util/validation.js';

Vue.component('edit-subject-translation', {
	data: function () {
		return {
			messages: {
				subject: "Subject",
				choose: "Choose",
				locale: "Locale",
				translation: "Translation",
				edit: "Edit"
			},
			subjects: [],
			locales: [],
			form: {
				subjectId: 0,
				localeId: 0,
				translation: ''
			}
		}
	},
	created() {
		let _this = this;
		serverBus.$on('fetchedSubjects', function (subjects) {
			_this.subjects = subjects;
		});
		
		serverBus.$on('fetchedLocales', function (locales) {
			_this.locales = locales;
		});
	},
	methods: {
		getSubjectTranslation: function (e) {
			let _this = this;
			let $form = $(e.target.closest('form'));
			clearValidationMessages($form);
			
			if (this.form.subjectId != 0 && this.form.localeId != 0) {
				let url = `api/translations/subjects/${this.form.subjectId}/locales/${this.form.localeId}`;
				$.ajax({
					type: 'GET',
					url: url,
					dataType: 'json'
				}).done(function (subjectTranslation) {
					_this.form.translation = subjectTranslation.translation;
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
		editSubjectTranslation: function (e) {
			e.preventDefault();
			let _this = this;
			let $form = $(e.target.closest('form'));
			let url = `/api/translations/subjects/${this.form.subjectId}/locales/${this.form.localeId}`;
			let objToSend = this.form;
			
			$.ajax({
				type: 'PUT',
				url: url,
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(objToSend)
			}).done(function (response) {
				clearValidationMessages($form);
				_this.form = {
					subjectId: 0,
					localeId: 0,
					translation: ''
				}
				
				$.snackbar({
					content: "Translation was edited!",
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
		<form action="/api/translations/subjects/{subjectId}/locales/{localeId}" method="POST">
			<div class="form-group">
				<label for="subject">{{messages.subject}}</label>
				<select class="form-control" name="subjectId" v-model="form.subjectId" v-on:change="getSubjectTranslation">
					<option value="0" selected>{{messages.choose}}</option>
					<option v-for="subject of subjects" v-bind:value="subject.id">{{subject.name}}</option>
				</select>
			</div>
	
			<div class="form-group">
				<label for="locale">{{messages.locale}}</label>
				<select class="form-control" name="localeId" v-model="form.localeId" v-on:change="getSubjectTranslation">
					<option value="0" selected>{{messages.choose}}</option>
					<option v-for="locale of locales" v-bind:value="locale.id">{{locale.code}}</option>
				</select>
			</div>
	
			<div class="form-group">
				<label for="subject-translation">{{messages.translation}}</label>
				<input type="text" class="form-control" name="translation" v-model="form.translation" />
			</div>
	
			<button type="submit" class="btn btn-success" v-on:click="editSubjectTranslation">{{messages.edit}}</button>
		</form>
	`
});