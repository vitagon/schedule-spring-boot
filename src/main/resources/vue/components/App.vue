<template>
  <div>
    <navbar></navbar>

    <div>
        <toolbar></toolbar>
        <router-view class="router-content"></router-view>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import Toolbar from 'components/Toolbar.vue'
import Navbar from 'components/Navbar.vue'
import Component from 'vue-class-component'

@Component({
  components: {Toolbar, Navbar}
})
export default class App extends Vue {

    created() {
      this.$store.dispatch('getLocales');
    }
}
</script>

<style lang="scss" scoped>
    /deep/.v-toolbar__title {
        cursor: pointer;
    }
    .router-content {
        padding-top: 42px;
    }
</style>