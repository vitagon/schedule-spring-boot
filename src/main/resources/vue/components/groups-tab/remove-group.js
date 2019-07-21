import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {clearValidationMessages, showValidationErrors} from '/vue/util/validation.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';

Vue.component('remove-group', {
	data: function () {
		return {
			messages: {
				group: "Group",
				choose: "Choose",
				delete: "Delete"
			},
			groups: [],
			removeGroupForm: {
				id: 0
			}
		}
	},
	created() {
		let _this = this;
		serverBus.$on('fetchedGroups', function (groups) {
			_this.groups = groups;
		});
	},
	methods: {
		removeGroup: function (e) {
			e.preventDefault();
			
			let _this = this;
			let $form = $(e.target.closest('form'));
			let objToSend = this.removeGroupForm;
			
			$.ajax({
				type: 'DELETE',
				url: $form.attr('action'),
				data: objToSend,
				dataType: 'json',
				success: function(response) {
					clearValidationMessages($form);
					serverBus.$emit('removedGroup', _this.removeGroupForm.id);
					
					_this.removeGroupForm = {
						id: 0
					};
					
					$.snackbar({
						content: response.message,
						timeout: 5000
					});
				},
				error: function(jqXHR, exception) {
					let msg = getErrorMessage(jqXHR, exception);
					if (jqXHR.status == 400) {
						let errors = jqXHR.responseJSON.details;
						showValidationErrors(errors, $form);
					} else {
						alert(msg);
					}
				}
			});
		}
	},
	template: `
		<form action="/api/control/group" method="DELETE">
	 		<div class="form-group">
		 		<label for="remove-group_group">{{messages.group}}</label>
		 		<select class="form-control" id="remove-group_group" name="id" v-model="removeGroupForm.id">
		 			<option value="0">{{messages.choose}}</option>
		 			<option v-for="group of groups" v-bind:value="group.id">{{group.name}}</option>
		 		</select>
			</div>
			
			<button type="submit" class="btn btn-success" v-on:click="removeGroup">{{messages.delete}}</button>
		</form>
	`
});