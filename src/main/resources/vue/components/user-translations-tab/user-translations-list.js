import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';
import {showValidationErrors, clearValidationMessages} from '/vue/util/validation.js';

Vue.component('user-translations-list', {
	data: function () {
		return {
			messages: {
				choose: "Choose",
				locale: "Locale",
			},
			users: [],
			locales: [],
			localeId: 0
		}
	},
	created() {
		let _this = this;
		this.getUsers();
		serverBus.$on('fetchedLocales', function (locales) {
			_this.locales = locales;
		});
	},
	methods: {
		getUsers: function () {
			let _this = this;
			let url;
			
			if (_this.localeId != 0) {
				url = `/api/admin-panel/translations/users/role/teacher/locale-id/${_this.localeId}`;
			} else {
				url = `/api/admin-panel/translations/users/role/teacher`;
			}
			
			$.ajax({
				type: 'GET',
				url: url,
				contentType: 'application/json; charset=utf-8',
				data: {page: 0},
				dataType: 'json'
			}).done(function (users) {
				_this.users = users;
			}).fail(function (jqXHR, exception) {
				alert('Could not get users translations!');
			});
		}
	},
	template: `
		<div>
			<div class="form-group">
				<label>{{messages.locale}}</label>
				<select name="localeId" class="form-control" v-model="localeId" v-on:change="getUsers">
					<option value="0">{{messages.choose}}</option>
					<option v-for="locale of locales" v-bind:value="locale.id">{{locale.code}}</option>
				</select>
			</div>
			
			<table class="table table-condensed table-sm table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>name</th>
						<th>translation</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="user of users">
						<td>{{user.id}}</td>
						<td>{{user.fullName}}</td>
						<td>{{user.fullNameTranslation}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	`
});