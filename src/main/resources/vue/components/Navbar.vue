<template>
  <nav v-if="isOpened" id="c-navbar">
    <div id="admin-panel-logo">
      <span>Admin panel</span>
    </div>

    <ul>
      <li v-for="item in items" :key="item.name" @click="hide"> 
        <router-link :to="item.link" class="c-nav-link">
          <i class="nav-link-icon fas" :class="item.icon"></i><span>{{item.title}}</span>
        </router-link>
      </li>
    </ul>
  </nav>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import EventBus from '@/EventBus'


@Component
export default class Navbar extends Vue {

  public isOpened: boolean;
  public items: Array<{title: string, icon: string, link: string}>;

  constructor() {
    super();
    this.isOpened = false;
    this.items = [
      {title: 'Home', icon: 'fa-home', link: '/control'},
      {title: 'Schools', icon: 'fa-university', link: '/control/schools'},
      {title: 'Majors', icon: 'fa-graduation-cap', link: '/control/majors'},
      {title: 'Groups', icon: 'fa-user-graduate', link: '/control/groups'},
      {title: 'Subjects', icon: 'fas fa-book', link: '/control/subjects'},
      {title: 'Schedule', icon: 'fa-calendar-alt', link: '/control/schedule'},
    ];
  }

  created() {
    let _this = this;
    EventBus.$on('toggle-navbar', function () {
      _this.isOpened = !_this.isOpened;
    });
    
    let body: HTMLBodyElement | null = document.querySelector('body');
    if (body) {
      body.addEventListener('click', this.hideOnClickOutsideNavbar)
    }
  }

  hideOnClickOutsideNavbar(e) {
    let clickedWithinNavbar = e.target.closest('#c-navbar') != null;
    let clickedOnNavToggle = e.target.closest('#navbar-toggle') != null;
    
    if (!clickedWithinNavbar && !clickedOnNavToggle && this.isOpened) {
      this.isOpened = false;
    }
  }

  hide() {
    EventBus.$emit('toggle-navbar');
  }
}
</script>

<style lang="scss" scoped>
  nav {
    background-color: #242635;
    z-index: 2;
    position: fixed;
    top: 0;
    left: 0;
    width: 230px;
    height: 100vh;
  }
  #admin-panel-logo {
		flex: 0 0 230px;
		display: flex;
		align-items: center;
		padding-left: 1em;
		background-color: #2c2f41;
		color: #fff;
		font-size: 1.4em;
		font-weight: bold;
    font-family: cursive;
    height: 42px;
  }
  ul {
    padding: 0;
  }
  li, li > a {
    display: block;
  }
  .c-nav-link {
    padding: .5em 1.1em;
    color: #a2a3b7;

    &:hover {
      background-color: #1a1c29;
      text-decoration: none;
    }
  }
  .nav-link-icon {
    margin-right: .4em;
  }
  .router-link-exact-active {
    background-color: #1a1c29;
    color: #fff;
    font-weight: 500;
  }
</style>

