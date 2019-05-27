import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';

Vue.component('subjects-list', {
	data: function () {
		return {
			messages: {
				localization: "Localization",
				choose: "Choose"
			},
			localeId: 0,
			locales: [],
			subjects: []
		}
	},
	created() {
		let _this = this;
		this.getSubjects();
		
		serverBus.$on('fetchedLocales', function (locales) {
			_this.locales = locales;
		});
		
		serverBus.$on('addedSubject', function (subject) {
			_this.subjects.push(subject);
		});
		
		serverBus.$on('editedSubject', function (subject) {
			let editedSubjectIndex = _this.subjects.findIndex(x => x.id == subject.id);
			Vue.set(_this.subjects, editedSubjectIndex, subject);
		});
		
		serverBus.$on('removedSubject', function (id) {
			let removedSubjectIndex = _this.subjects.findIndex(x => x.id == id);
			_this.subjects.splice(removedSubjectIndex, 1);
		});
	},
	methods: {
		getSubjects: function (e) {
			let _this = this;
			let localeId = _this.localeId;
			
			$.ajax({
				type: 'GET',
				url: '/api/control/subjects',
				data: {localeId: localeId},
				contentType: 'application/json; charset=utf-8',
				dataType: 'json'
			}).done(function (response) {
				_this.subjects = response;
				serverBus.$emit('fetchedSubjects', response);
			}).fail(function (jqXHR, exception) {
				let msg = getErrorMessage(jqXHR, exception);
				alert(msg);
			});
		}
	},
	template: `
		<div>
			<div class="form-group">
				<label for="subjects-list_locale">{{messages.localization}}</label>
				<select name="" id="subjects-list_locale" class="form-control" v-on:change="getSubjects" v-model="localeId">
					<option value="0" selected>{{messages.choose}}</option>
					<option v-for="locale of locales" v-bind:value="locale.id">{{locale.code}}</option>
				</select>
			</div>
			
			<div class="subjects-list_content">
				<table class="table table-condensed table-sm table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>name</th>
							<th>translation</th>
						</tr>
					</thead>
					
					<tbody>
						<tr v-for="subject of subjects">
							<td>{{subject.id}}</td>
							<td>{{subject.name}}</td>
							<td>{{subject.translation}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	` 
});