import {serverBus} from '/vue/admin-panel/admin-panel.js';

Vue.component('groups-list', {
	data: function () {
		return {
			groups: []
		}
	},
	created() {
		let _this = this;
		this.getGroups();
		
		serverBus.$on('addedGroup', function (group) {
			_this.groups.push(group);
		});
		
		serverBus.$on('editedGroup', function (group) {
			let editedGroupIndex = _this.groups.findIndex(x => x.id == group.id);
			Vue.set(_this.groups, editedGroupIndex, group);
		});
		
		serverBus.$on('removedGroup', function (id) {
			let editedGroupIndex = _this.groups.findIndex(x => x.id == id);
			_this.groups.splice(editedGroupIndex, 1);
		});
	},
	methods: {
		getGroups: function () {
			let _this = this;
			$.ajax({
				type: 'GET',
				url: '/api/groups',
				dataType: 'json'
			}).done(function (response) {
				_this.groups = response;
				serverBus.$emit('fetchedGroups', response);
			}).fail(function (jqXHR, exception) {
				console.err("Error while getting groups!");
			});
		}
	},
	template: `
		<form>
			<table class="table table-condensed table-sm table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>name</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="group of groups">
						<td>{{group.id}}</td>
						<td>{{group.name}}</td>
					</tr>
				</tbody>
			</table>
		</form>
	`
});