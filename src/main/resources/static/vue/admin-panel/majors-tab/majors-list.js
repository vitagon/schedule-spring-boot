import {serverBus} from '/vue/admin-panel/admin-panel.js';
import {getErrorMessage} from '/vue/util/error-msg-builder.js';

Vue.component('majors-list', {
	data: function () {
		return {
			messages: {
				localization: "Localization",
				choose: "Choose"
			},
			locales: [],
			localeId: 0,
			majors: []
		}
	},
	created() {
		let _this = this;
		serverBus.$on('fetchedLocales', function (locales) {
			_this.locales = locales;
		});
		
		serverBus.$on('addedMajor', function (major) {
			_this.majors.push(major);
		});
		
		serverBus.$on('editedMajor', function (major) {
			let editedMajorIndex = _this.majors.findIndex(x => x.id == major.id);
			Vue.set(_this.majors, editedMajorIndex, major);
		});
		
		serverBus.$on('removedMajor', function (id) {
			let removedMajorIndex = _this.majors.findIndex(x => x.id == id);
			_this.majors.splice(removedMajorIndex, 1);
		});
		
		_this.getMajors();
	},
	methods: {
		getMajors: function () {
			let _this = this;
			let url, parameters;
			if (_this.localeId != 0) {
				url = `/api/control/majors/locale-id/${_this.localeId}`;
			} else {
				url = '/api/control/majors';
			}
			
			$.ajax({
				type: 'GET',
				url: url,
				contentType: 'application/json; charset=utf-8',
				dataType: 'json',
			}).done(function (response) {
				_this.majors = response;
				serverBus.$emit('fetchedMajors', response);
			}).fail(function (jqXHR, exception) {
				let msg = getErrorMessage(jqXHR, exception);
				alert(msg);
			});
		}
	},
	template: `
		<div>
			<div class="form-group">
				<label>{{messages.localization}}</label>
				<select name="localeId" class="form-control" v-model="localeId" v-on:change="getMajors">
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
					<tr v-for="major of majors">
						<td>{{major.id}}</td>
						<td>{{major.name}}</td>
						<td>{{major.translation}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	`
});