import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';

Vue.component('schools-list', {
	data: function () {
		return {
			messages: {
				localization: "Localization",
				choose: "Choose"
			},
			schools: [],
			localeId: 0,
			locales: []
		}
	},
	created() {
		this.getSchools();
		this.getLocales();
		
		let _this = this;
		serverBus.$on('addedSchool', function (school) {
			_this.schools.push({
				id: school.id,
				name: school.name,
				translation: null
			});
		});
		
		serverBus.$on('editedSchool', function(school) {
			let targetSchool = _this.schools.find(x => x.id == school.id);
			targetSchool.name = school.name;
		});
		
		serverBus.$on('removedSchool', function(schoolId) {
			let removedSchoolIndex = _this.schools.findIndex(x => x.id == schoolId);
			_this.schools.splice(removedSchoolIndex, 1);
		});
	},
	methods: {
		getLocales: function () {
			let _this = this;
			
			let getLocalesRequest = $.ajax({
				type: 'GET',
				url: '/api/locales',
				dataType: 'json'
			});
			
			getLocalesRequest.done(function (response) {
				_this.locales = response;
			});
			
			getLocalesRequest.fail(function (jqXHR, exception) {
				console.err("Couldn't get locales");
			})
		},
		getSchools: function () {
			let _this = this;
			let getSchoolsRequest = $.ajax({
				type: 'GET',
				url: '/api/control/schools',
				data: {localeId: _this.localeId},
				contentType: 'application/json; charset=utf-8',
				dataType: 'json'
			});
			
			getSchoolsRequest.done(function (response) {
				_this.schools = response;
				serverBus.$emit('fetchedSchools', _this.schools);
			});
			
			getSchoolsRequest.fail(function (jqXHR, exception) {
				let msg = getErrorMessage(jqXHR, exception);
				alert(msg);
			});
		}
	},
	template: `
		<div>
			<div class="form-group">
				<label for="schools-list_locale">{{messages.localization}}</label>
				<select name="localeId" class="form-control" v-model="localeId" v-on:change="getSchools">
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
					<tr v-for="school in schools">
						<td>{{ school.id }}</td>
						<td>{{ school.name }}</td>
						<td>{{ school.translation }}</td>
					</tr>
				</tbody>
			</table>
		</div>
	`
});