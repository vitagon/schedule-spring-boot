<template>
	<div class="bg-light fixed-top toolbar">
		<a class="nav-toggle" id="navbar-toggle" href="#" @click="toggleNavbar">
			<i class="fas fa-bars"></i>
		</a>

		<div class="collapsed-content">
			<ul class="ml-auto">
				<li class="nav-item dropdown">
						<b-dropdown size="md" variant="link" toggle-class="text-decoration-none">
							<template slot="button-content"><i class="fas fa-user"></i><span class="sr-only">Search</span></template>
							<h6 class="dropdown-header">
					  		<span>Harington</span>
					  		<span>Bob</span>
					  	</h6>
							<b-dropdown-item href="/logout">Logout</b-dropdown-item>
						</b-dropdown>
				</li>
			</ul>
		</div>
	</div>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import EventBus from '@/EventBus'

@Component
export default class Toolbar extends Vue {
	
	toggleNavbar() {
		EventBus.$emit('toggle-navbar')
	}
}
</script>

<style lang="scss" scoped>
	.toolbar {
		display: flex;
		z-index: 1;
		height: 42px;
	}
	@media screen and (max-width: 768px) {
		#admin-panel-logo {
			display: none!important;
		}
	}
	.collapsed-content {
		flex: 1;
		display: flex;
		align-items: center;

	}
	ul {
		margin-bottom: 0;
		padding: 0;
	}
	li {
		display: inline-block;
	}
	.nav-toggle {
		display: flex;
		align-items: center;
		padding: 0 .3em;
		color: black;
		font-size: 1.8em;
		font-weight: bold;
		text-decoration: none;
	}
	#dropdownMenuButton {
		display: block;
		padding: .3em .6em;
	}
	.lang-list {
		margin-left: auto;
	}
	/deep/.dropdown-menu li {
		display: block;
	}
</style>

