import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';
import {showValidationErrors, clearValidationMessages} from '/vue/util/validation.js';
import {} from '/vue/admin-panel/user-translations-tab/user-search.js';

Vue.component('remove-user-translation', {
	data: function () {
		return {
			messages: {
				choose: "Choose",
				locale: "Locale",
				remove: "Remove"
			},
			locales: [],
			localeId: 0,
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
			this.localeId = 0;
		},
		checkIfExists: function (e) {
			return new Promise((res, rej) => {
				let _this = this;
				let $form = $(e.target.closest('form')); 
				clearValidationMessages($form);
				if (this.localeId != 0) {
					$.ajax({
						type: 'GET',
						url: '/api/translations/users/check',
						data: {
							localeId: _this.localeId,
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
		removeUserTranslation: function (e) {
			e.preventDefault();
			let _this = this;
			let $form = $(e.target.closest('form'));
			let objToSend = {
				userId: _this.currentUser.id,
				localeId: _this.localeId
			};
			
			$.ajax({
				type: 'DELETE',
				url: `/api/translations/users/${_this.currentUser.id}`,
				data: JSON.stringify(objToSend),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json'
			}).done(function (response) {
				clearValidationMessages($form);
				_this.localeId = 0;
				
				$.snackbar({
					content: 'User translation was removed!',
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
			let $compWrap = $('#remove-user-translation-comp');
			clearValidationMessages($compWrap);
		}
	},
	template: `
		<div id="remove-user-translation-comp">
			<user-search v-on:choose_user="updateCurUser"
						 v-on:clear_vmsg="clearValidationMsg"></user-search>
			
			<hr class="bg-success" style="border-width: 2px"/>
			
			<form action="#">
				<div class="form-group">
					<label>{{messages.locale}}</label>
					<select name="localeId" class="form-control" v-model="localeId" v-on:change="checkIfExists">
						<option value="0">{{messages.choose}}</option>
						<option v-for="locale of locales" v-bind:value="locale.id">{{locale.code}}</option>
					</select>
				</div>
				
				<button class="btn btn-primary" type="submit" v-on:click="removeUserTranslation">{{messages.remove}}</button>
			</form>
		</div>
	`
});