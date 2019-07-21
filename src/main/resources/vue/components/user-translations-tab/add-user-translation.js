import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';
import {showValidationErrors, clearValidationMessages} from '/vue/util/validation.js';
import {} from '/vue/admin-panel/user-translations-tab/user-search.js';

Vue.component('add-user-translation', {
	data: function () {
		return {
			messages: {
				choose: "Choose",
				locale: "Locale",
				add: "Add"
			},
			locales: [],
			addUserTranslForm: {
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
			this.addUserTranslForm.localeId = 0;
		},
		checkIfNotExists: function (e) {
			let _this = this;
			let $form = $(e.target.closest('form')); 
			clearValidationMessages($form);
			if (this.addUserTranslForm.localeId != 0) {
				$.ajax({
					type: 'GET',
					url: '/api/translations/users/check',
					data: {
						localeId: _this.addUserTranslForm.localeId,
						userId: _this.currentUser.id,
						exists: false
					}
				}).done(function (response) {
					/* Just do nothing */
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
		addUserTranslation: function (e) {
			e.preventDefault();
			let _this = this;
			let $form = $(e.target.closest('form'));
			let objToSend = {
				userId: _this.currentUser.id,
				localeId: _this.addUserTranslForm.localeId,
				lastname: _this.addUserTranslForm.lastname,
				firstname: _this.addUserTranslForm.firstname,
				middlename: _this.addUserTranslForm.middlename,
			};
			
			$.ajax({
				type: 'POST',
				url: '/api/translations/users',
				data: JSON.stringify(objToSend),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json'
			}).done(function (response) {
				clearValidationMessages($form);
				_this.addUserTranslForm = {
					localeId: 0,
					lastname: '',
					firstname: '',
					middlename: '',
				};
				
				$.snackbar({
					content: 'User translation was added!',
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
			let $compWrap = $('#add-user-translation-comp');
			clearValidationMessages($compWrap);
		}
	},
	template: `
		<div id="add-user-translation-comp">
			<user-search v-on:choose_user="updateCurUser"
						 v-on:clear_vmsg="clearValidationMsg"></user-search>
			
			<hr class="bg-success" style="border-width: 2px"/>
			
			<form action="#">
				<div class="form-group">
					<label>{{messages.locale}}</label>
					<select name="localeId" class="form-control" v-model="addUserTranslForm.localeId" v-on:change="checkIfNotExists">
						<option value="0">{{messages.choose}}</option>
						<option v-for="locale of locales" v-bind:value="locale.id">{{locale.code}}</option>
					</select>
				</div>
				
				<div class="input-group mb-3">
					<div class="input-group-prepend pr-3">
						<span class="input-group-text">{{currentUser.lastname}} -></span>
					</div>
					<input type="text" class="form-control" v-model="addUserTranslForm.lastname">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend pr-3">
						<span class="input-group-text">{{currentUser.firstname}} -></span>
					</div>
					<input type="text" class="form-control" v-model="addUserTranslForm.firstname">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend pr-3">
						<span class="input-group-text">{{currentUser.middlename}} -></span>
					</div>
					<input type="text" class="form-control" v-model="addUserTranslForm.middlename">
				</div>
				
				<button class="btn btn-primary" type="submit" v-on:click="addUserTranslation">{{messages.add}}</button>
			</form>
		</div>
	`
});