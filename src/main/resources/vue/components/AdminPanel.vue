// import {} from '/vue/admin-panel/schools-tab/schools-list.js';
// import {} from '/vue/admin-panel/schools-tab/add-school.js';
// import {} from '/vue/admin-panel/schools-tab/edit-school.js';
// import {} from '/vue/admin-panel/schools-tab/remove-school.js';

// import {} from '/vue/admin-panel/majors-tab/majors-list.js';
// import {} from '/vue/admin-panel/majors-tab/add-major.js';
// import {} from '/vue/admin-panel/majors-tab/edit-major.js';
// import {} from '/vue/admin-panel/majors-tab/remove-major.js';

// import {} from '/vue/admin-panel/groups-tab/groups-list.js';
// import {} from '/vue/admin-panel/groups-tab/add-group.js';
// import {} from '/vue/admin-panel/groups-tab/edit-group.js';
// import {} from '/vue/admin-panel/groups-tab/remove-group.js';

// import {} from '/vue/admin-panel/subjects-tab/subjects-list.js';
// import {} from '/vue/admin-panel/subjects-tab/add-subject.js';
// import {} from '/vue/admin-panel/subjects-tab/edit-subject.js';
// import {} from '/vue/admin-panel/subjects-tab/remove-subject.js';

// import {} from '/vue/admin-panel/subject-translations-tab/add-subject-translation.js';
// import {} from '/vue/admin-panel/subject-translations-tab/edit-subject-translation.js';
// import {} from '/vue/admin-panel/subject-translations-tab/remove-subject-translation.js';

// import {} from '/vue/admin-panel/user-translations-tab/user-translations-list.js';
// import {} from '/vue/admin-panel/user-translations-tab/add-user-translation.js';
// import {} from '/vue/admin-panel/user-translations-tab/edit-user-translation.js';
// import {} from '/vue/admin-panel/user-translations-tab/remove-user-translation.js';

// import {} from '/vue/admin-panel/edit-schedule-tab/edit-schedule.js';


// import {} from '/vue/admin-panel/main-tab.js'
// export const serverBus = new Vue();

<template>
	<v-navigation-drawer
		stateless
		value="true"
	>
		<v-list>
		<v-list-tile>
			<v-list-tile-action>
			<v-icon>home</v-icon>
			</v-list-tile-action>
			<v-list-tile-title>Home</v-list-tile-title>
		</v-list-tile>

		<v-list-group
			prepend-icon="account_circle"
			value="true"
		>
			<template v-slot:activator>
			<v-list-tile>
				<v-list-tile-title>Users</v-list-tile-title>
			</v-list-tile>
			</template>
			<v-list-group
			no-action
			sub-group
			value="true"
			>
			<template v-slot:activator>
				<v-list-tile>
				<v-list-tile-title>Admin</v-list-tile-title>
				</v-list-tile>
			</template>

			<v-list-tile
				v-for="(admin, i) in admins"
				:key="i"
				@click=""
			>
				<v-list-tile-title v-text="admin[0]"></v-list-tile-title>
				<v-list-tile-action>
				<v-icon v-text="admin[1]"></v-icon>
				</v-list-tile-action>
			</v-list-tile>
			</v-list-group>

			<v-list-group
			sub-group
			no-action
			>
			<template v-slot:activator>
				<v-list-tile>
				<v-list-tile-title>Actions</v-list-tile-title>
				</v-list-tile>
			</template>
			<v-list-tile
				v-for="(crud, i) in cruds"
				:key="i"
			>
				<v-list-tile-title v-text="crud[0]"></v-list-tile-title>
				<v-list-tile-action>
				<v-icon v-text="crud[1]"></v-icon>
				</v-list-tile-action>
			</v-list-tile>
			</v-list-group>
		</v-list-group>
		</v-list>
  </v-navigation-drawer>
</template>

<script>
export default {
	
}
</script>

<style lang="scss" scoped>

</style>


