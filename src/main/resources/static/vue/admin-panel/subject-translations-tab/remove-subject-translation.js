import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';
import {showValidationErrors, clearValidationMessages} from '/vue/util/validation.js';

Vue.component('remove-subject-translation', {
	data: function () {
		return {
			messages: {
				subject: "Subject",
				choose: "Choose",
				locale: "Locale",
				remove: "Remove"
			},
			subjects: [],
			locales: [],
			form: {
				subjectId: 0,
				localeId: 0
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
		removeSubjectTranslation: function (e) {
			e.preventDefault();
			let _this = this;
			let $form = $(e.target.closest('form'));
			let url = `/api/translations/subjects/${this.form.subjectId}/locales/${this.form.localeId}`;
			
			$.ajax({
				type: 'DELETE',
				url: url,
				data: _this.form,
				contentType: 'application/json; charset=utf-8',
			}).done(function (response) {
				clearValidationMessages($form);
				_this.form = {
					subjectId: 0,
					localeId: 0
				}
				
				$.snackbar({
					content: "Translation was removed!",
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
		<form action="/api/translations/subjects/{subjectId}/locales/{localeId}" method="DELETE">
			<div class="form-group">
				<label for="subject">{{messages.subject}}</label>
				<select class="form-control" name="subjectId" v-model="form.subjectId">
					<option value="0" selected>{{messages.choose}}</option>
					<option v-for="subject of subjects" v-bind:value="subject.id">{{subject.name}}</option>
				</select>
			</div>
	
			<div class="form-group">
				<label for="locale">{{messages.locale}}</label>
				<select class="form-control" name="localeId" v-model="form.localeId">
					<option value="0" selected>{{messages.choose}}</option>
					<option v-for="locale of locales" v-bind:value="locale.id">{{locale.code}}</option>
				</select>
			</div>
	
			<button type="submit" class="btn btn-success" v-on:click="removeSubjectTranslation">{{messages.remove}}</button>
		</form>
	`
});