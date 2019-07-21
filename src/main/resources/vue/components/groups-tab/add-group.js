import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {clearValidationMessages, showValidationErrors} from '/vue/util/validation.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';

Vue.component('add-group', {
	data: function () {
		return {
			messages: {
				major: "Major",
				choose: "Choose",
				number: "Number",
				suffix: "Suffix",
				course: "Course",
				add: "Add"
			},
			majors: [],
			addGroupForm: {
				majorId: 0,
				number: "",
				suffix: "",
				courseNum: 0
			}
		}
	},
	created() {
		let _this = this;
		serverBus.$on('fetchedMajors', function (majors) {
			_this.majors = majors;
		});
	},
	methods: {
		addGroup: function (e) {
			e.preventDefault();
			
			let _this = this;
			let $form = $(e.target.closest('form'));
			let objToSend = this.addGroupForm;
			
			$.ajax({
				type: 'POST',
				url: $form.attr('action'),
				data: JSON.stringify(objToSend),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json'
			}).done(function (response) {
				clearValidationMessages($form);
				_this.addGroupForm = {
					majorId: 0,
					number: "",
					suffix: "",
					courseNum: 0
				}
				
				$.snackbar({
					content: "Group was added!",
					timeout: 5000
				});
				
				serverBus.$emit('addedGroup', response);
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
		<form action="/api/control/group" method="POST">
			<div class="form-group">
				<label for="add-group_major">{{messages.major}}</label>
				<select class="form-control" id="add-group_major" name="majorId" v-model="addGroupForm.majorId">
					<option value="0" selected>{{messages.choose}}</option>
					<option v-for="major of majors" v-bind:value="major.id">{{major.name}}</option>
				</select>
			</div>
		
	 		<div class="form-group">
		 		<label for="add-group_number">{{messages.number}}</label>
		 		<input type="text" class="form-control" id="add-group_number" name="number"
		 		 	   placeholder="1101" v-model="addGroupForm.number">
			</div>
			
			<div class="form-group">
		 		<label for="add-group_suffix">{{messages.suffix}}</label>
		 		<input type="text" class="form-control" id="add-group_suffix" name="suffix"
		 			   placeholder="pd" v-model="addGroupForm.suffix">
			</div>
			
			<div class="form-group">
		 		<label for="add-group_coursenum">{{messages.course}}</label>
		 		<select class="form-control" id="add-group_coursenum" name="courseNum" v-model="addGroupForm.courseNum">
		 			<option value="0" selected>{{messages.choose}}</option>
		 			<option v-for="i of 7" v-bind:value="i">{{i}}</option>
		 		</select>
			</div>
			
			<button type="submit" class="btn btn-success" v-on:click="addGroup">{{messages.add}}</button>
		</form>
	`
});