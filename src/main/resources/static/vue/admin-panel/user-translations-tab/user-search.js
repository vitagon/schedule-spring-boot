import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';
import {showValidationErrors, clearValidationMessages} from '/vue/util/validation.js';

Vue.component('user-search', {
	data: function () {
		return {
			searchForm: {
				lastname: '',
				firstname: '',
				middlename: '',
			},
			users: [],
			chosenUserId: 0
		}
	},
	methods: {
		findUsers: function (e) {
			e.preventDefault();
			this.clearValidationMessages();
			let _this = this;
			let $form = $(e.target.closest('form'));
			let url = `/api/users/search`;
			
			let params = {};
			if (this.searchForm.lastname.trim() !== '') {
				params['lastname'] = this.searchForm.lastname.trim();
			}
			
			if (this.searchForm.firstname.trim() !== '') {
				params['firstname'] = this.searchForm.firstname.trim();
			}
			
			if (this.searchForm.middlename.trim() !== '') {
				params['middlename'] = this.searchForm.middlename.trim();
			}
			
			$.ajax({
				type: 'GET',
				url: url,
				contentType: 'application/json; charset=utf-8',
				data: params,
				dataType: 'json'
			}).done(function (users) {
				_this.users = users;
			}).fail(function (jqXHR, exception) {
				alert('Could not get users translations!');
			});
		},
		showHints: function (e) {
			if (this.chosenUserId != 0) {
				let userIndex = this.users.findIndex(x => x.id == this.chosenUserId);
				this.$emit('choose_user', this.users[userIndex]);
				this.clearValidationMessages();
			}
		},
		clearValidationMessages: function () {
			this.$emit('clear_vmsg');
		}
	},
	template: `
		<div class="user-search-comp">
			<form>
				<div class="form-group">
					<label for="add-user-translation_lastname">Lastname</label>
					<input type="text" class="form-control" id="add-user-translation_lastname" placeholder="Gosling"
						   v-model="searchForm.lastname">
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="add-user-translation_firstname">Firstname</label>
						<input type="text" class="form-control" id="add-user-translation_firstname" placeholder="James"
							   v-model="searchForm.firstname">
					</div>
					<div class="form-group col-md-6">
						<label for="add-user-translation_middlename">Middlename</label>
						<input type="text" class="form-control" id="add-user-translation_middlename" placeholder="..."
							   v-model="searchForm.middlename">
					</div>
				</div>
				
				<button type="button" class="btn btn-outline-warning w-100" v-on:click="findUsers">Find</button>
			</form>
			
			
			<table class="table table-condensed table-sm table-striped">
				<thead>
					<tr>
						<th>Choose</th>
						<th>#</th>
						<th>name</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="user of users">
						<td style="position: relative; padding: 0">
							<label v-bind:for="'t_' + user.id"
								   style="position: absolute; width: 100%; height: 100%; margin: 0;
								   		  display: flex; align-items: center; justify-content: center; cursor: pointer">
								<input type="radio" name="options" v-bind:id="'t_' + user.id" autocomplete="off" v-bind:value="user.id"
									   v-model="chosenUserId" v-on:change="showHints">
							</label>
						</td>
						<td>{{user.id}}</td>
						<td>{{user.lastname + ' ' + user.firstname + ' ' + user.middlename}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	`
});