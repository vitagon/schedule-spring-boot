<template>
  <div>
    <navbar></navbar>

    <div>
        <toolbar></toolbar>
        <router-view class="router-content"></router-view>
    </div>
  </div>
</template>

<script>
import Toolbar from 'components/Toolbar.vue'
import Navbar from 'components/Navbar.vue'

export default {
    name: "App",
    components: {Toolbar, Navbar},
    props: {
      source: String
    },
    data() {
        return {
        }
    },
    methods: {
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