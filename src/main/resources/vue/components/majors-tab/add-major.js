import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';
import {clearValidationMessages, showValidationErrors} from '/vue/util/validation.js';

Vue.component('add-major', {
	data: function () {
		return {
			messages: {
				school: "School",
				choose: "Choose",
				englishTitle: "English title",
				duration: "Duration",
				degree: "Degree",
				add: "Add"
			},
			schools: [],
			degrees: [],
			addMajorForm: {
				schoolId: 0,
				title: '',
				duration: 0,
				degree: 0
			}
		}
	},
	created() {
		let _this = this;
		serverBus.$on('fetchedSchools', function (schools) {
			_this.schools = schools;
		});
		
		this.getDegrees();
	},
	methods: {
		getDegrees: function () {
			let _this = this;
			let getDegreesRequest = $.ajax({
				type: 'GET',
				url: '/api/degrees'
			});
			
			getDegreesRequest.done(function (degrees) {
				_this.degrees = degrees;
				serverBus.$emit('fetchedDegrees', degrees);
			});
			
			getDegreesRequest.fail(function (degrees) {
				console.error("Unable to fetch degrees!");
			});
		},
		addMajor: function (e) {
			let _this = this;
			e.preventDefault();
			let $form = $(e.target.closest('form'));
			
			let addMajorRequest = $.ajax({
				type: 'POST',
				url: '/api/control/major',
				data: JSON.stringify(_this.addMajorForm),
				contentType: 'application/json; charset=utf-8',
				dataType: 'json',
			});
			
			addMajorRequest.done(function (response) {
				clearValidationMessages($form);
				_this.addMajorForm = {
					schoolId: 0,
					title: '',
					duration: 0,
					degree: 0
				}
				
				serverBus.$emit('addedMajor', {
					id: response.id,
					name: response.name,
					translation: response.translation
				});
				
				$.snackbar({
					content: "Major was added!",
					timeout: 5000
				});
			});
			
			addMajorRequest.fail(function (jqXHR, exception) {
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
		<form action="/api/control/major" method="POST">
	 		<div class="form-group">
		 		<label for="add-major_school">{{messages.school}}</label>
		 		<select class="form-control" id="add-major_school" name="schoolId" v-model="addMajorForm.schoolId">
		 			<option value="0">{{messages.choose}}</option>
		 			<option v-for="school of schools" v-bind:value="school.id">{{school.name}}</option>
		 		</select>
			</div>
		
			<!-- Add subject using english lang for name as default -->
	 		<div class="form-group">
		 		<label for="add-major_major-name">{{messages.englishTitle}}</label>
		 		<input type="text" class="form-control" id="add-major_major-name" name="title" v-model="addMajorForm.title" />
			</div>
			
			<div class="form-group">
		 		<label for="add-major_duration">{{messages.duration}}</label>
		 		<select class="form-control" id="add-major_duration" name="duration" v-model="addMajorForm.duration">
		 			<option value="0">{{messages.choose}}</option>
		 			<option v-for="n in 7" v-bind:value="n">{{n}}</option>
		 		</select>
			</div>
			
			<div class="form-group">
		 		<label for="add-major_degree">{{messages.degree}}</label>
		 		<select class="form-control" id="add-major_degree" name="degree" v-model="addMajorForm.degree">
		 			<option value="0">{{messages.choose}}</option>
		 			<option v-for="degree of degrees" v-bind:value="degree">{{degree}}</option>
		 		</select>
			</div>
			
			<button type="submit" class="btn btn-success" v-on:click="addMajor">{{messages.add}}</button>
		 </form>
	`
});