import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {clearValidationMessages, showValidationErrors} from '/vue/util/validation.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';

Vue.component('edit-group', {
	data: function () {
		return {
			messages: {
				group: "Group",
				course: "Course",
				edit: "Edit"
			},
			groups: [],
			editGroupForm: {
				id: 0,
				courseNum: 0
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
		editGroup: function (e) {
			e.preventDefault();
			
			let _this = this;
			let $form = $(e.target.closest('form'));
			let objToSend = this.editGroupForm;
			
			$.ajax({
				type: 'PUT',
				url: $form.attr('action'),
				data: JSON.stringify(objToSend),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json',
			}).done(function (response) {
				clearValidationMessages($form);
				_this.editGroupForm = {
					id: 0,
					courseNum: 0
				}
				
				$.snackbar({
					content: "Group was edited!",
					timeout: 5000
				});
				
				serverBus.$emit('editedGroup', response);
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
		<form action="/api/control/group" method="PUT">
			<div class="form-group">
				<label for="edit-group_group">{{messages.group}}</label>
				<select class="form-control" name="id" id="edit-group_major" v-model="editGroupForm.id">
					<option value="0" selected>{{messages.choose}}</option>
					<option v-for="group of groups" v-bind:value="group.id">{{group.name}}</option>
				</select>
			</div>
			
			<div class="form-group">
		 		<label for="edit-group_coursenum">{{messages.course}}</label>
		 		<select class="form-control" id="edit-group_coursenum" name="courseNum" v-model="editGroupForm.courseNum">
		 			<option value="0" selected>{{messages.choose}}</option>
		 			<option v-for="i of 7" v-bind:value="i">{{i}}</option>
		 		</select>
			</div>
			
			<button type="submit" class="btn btn-success" v-on:click="editGroup">{{messages.edit}}</button>
		 </form>
	`
});