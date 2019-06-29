import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';
import {showValidationErrors, clearValidationMessages} from '/vue/util/validation.js';
import {} from '/vue/admin-panel/user-translations-tab/user-search.js';

Vue.component('edit-user-translation', {
	data: function () {
		return {
			messages: {
				choose: "Choose",
				locale: "Locale",
				edit: "Edit"
			},
			locales: [],
			editUserTranslForm: {
				localeId: 0,
				lastname: '',
				firstname: '',
				middlename: '',
			},
			currentUser: {}
		}
	},
	created() {
		let _this = this;
		serverBus.$on('fetchedLocales', function (locales) {
			_this.locales = locales;
		});
	},
	methods: {
		updateCurUser: function (user) {
			this.currentUser = user;
			this.editUserTranslForm = {
				localeId: 0,
				lastname: '',
				firstname: '',
				middlename: '',
			};
		},
		getTranslation: function (e) {
			this.checkIfExists(e)
				.then(exists => {
					if (exists) {
						this.fetchTranslation();
					}
				});
		},
		fetchTranslation: function () {
			let _this = this;
			let url = `/api/translations/users/${_this.currentUser.id}`; 
			$.ajax({
				type: 'GET',
				url: url,
				data: {
					userId: _this.currentUser.id,
					localeId: _this.editUserTranslForm.localeId
				}
			}).done(function (userTranslation) {
				_this.editUserTranslForm.lastname = userTranslation.lastname;
				_this.editUserTranslForm.firstname = userTranslation.firstname;
				_this.editUserTranslForm.middlename = userTranslation.middlename;
			}).fail(function (jqXHR, exception) {
				let msg = getErrorMessage(jqXHR, exception);
				console.error(msg);
			});
		},
		checkIfExists: function (e) {
			return new Promise((res, rej) => {
				let _this = this;
				let $form = $(e.target.closest('form')); 
				clearValidationMessages($form);
				if (this.editUserTranslForm.localeId != 0) {
					$.ajax({
						type: 'GET',
						url: '/api/translations/users/check',
						data: {
							localeId: _this.editUserTranslForm.localeId,
							userId: _this.currentUser.id,
							exists: true
						}
					}).done(function (response) {
						res(true);
					}).fail(function (jqXHR, exception) {
						let msg = getErrorMessage(jqXHR, exception);
						if (jqXHR.status == 400) {
							let errors = jqXHR.responseJSON.details;
							showValidationErrors(errors, $form);
						} else {
							alert(msg);
						}
						res(false);
					});
				}
			});
		},
		editUserTranslation: function (e) {
			e.preventDefault();
			let _this = this;
			let $form = $(e.target.closest('form'));
			let objToSend = {
				userId: _this.currentUser.id,
				localeId: _this.editUserTranslForm.localeId,
				lastname: _this.editUserTranslForm.lastname,
				firstname: _this.editUserTranslForm.firstname,
				middlename: _this.editUserTranslForm.middlename,
			};
			
			$.ajax({
				type: 'PUT',
				url: '/api/translations/users',
				data: JSON.stringify(objToSend),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json'
			}).done(function (response) {
				clearValidationMessages($form);
				_this.editUserTranslForm = {
					localeId: 0,
					lastname: '',
					firstname: '',
					middlename: '',
				};
				
				$.snackbar({
					content: 'User translation was edited!',
					timeout: 5000
				});
			}).fail(function (jqXHR, exception) {
				let msg = getErrorMessage(jqXHR, exception);
				if (jqXHR.status == 400) {
					let errors = jqXHR.responseJSON.details;
					showValidationErrors(errors, $wrap);
				} else {
					alert(msg);
				}
			});
		},
		clearValidationMsg: function () {
			let $compWrap = $('#edit-user-translation-comp');
			clearValidationMessages($compWrap);
		}
	},
	template: `
		<div id="edit-user-translation-comp">
			<user-search v-on:choose_user="updateCurUser"
						 v-on:clear_vmsg="clearValidationMsg"></user-search>
			
			<hr class="bg-success" style="border-width: 2px"/>
			
			<form action="#">
				<div class="form-group">
					<label>{{messages.locale}}</label>
					<select name="localeId" class="form-control" v-model="editUserTranslForm.localeId" v-on:change="getTranslation">
						<option value="0">{{messages.choose}}</option>
						<option v-for="locale of locales" v-bind:value="locale.id">{{locale.code}}</option>
					</select>
				</div>
				
				<div class="input-group mb-3">
					<div class="input-group-prepend pr-3">
						<span class="input-group-text">{{currentUser.lastname}} -></span>
					</div>
					<input type="text" class="form-control" v-model="editUserTranslForm.lastname">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend pr-3">
						<span class="input-group-text">{{currentUser.firstname}} -></span>
					</div>
					<input type="text" class="form-control" v-model="editUserTranslForm.firstname">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend pr-3">
						<span class="input-group-text">{{currentUser.middlename}} -></span>
					</div>
					<input type="text" class="form-control" v-model="editUserTranslForm.middlename">
				</div>
				
				<button class="btn btn-primary" type="submit" v-on:click="editUserTranslation">{{messages.edit}}</button>
			</form>
		</div>
	`
});