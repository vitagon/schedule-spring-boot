<template>
    <b-container class="pt-5">
        <b-button @click="makeToast()">Show Toast</b-button>
        <schools-list></schools-list>
        <edit-school></edit-school>
        <edit-school-translation></edit-school-translation>
    </b-container>
</template>

<script>
import axios from 'axios'
import SchoolsList from 'components/schools/SchoolsList.vue'
import EditSchool from 'components/schools/EditSchool.vue'
import EditSchoolTranslation from 'components/schools/EditSchoolTranslation.vue'
import EventBus from '../../EventBus'

export default {
    components: {SchoolsList, EditSchool, EditSchoolTranslation},
    created() {
    },
    methods: {
        makeToast(append = false) {
        this.toastCount++
        this.$bvToast.toast(`This is toast number ${this.toastCount}`, {
          title: 'BootstrapVue Toast',
          autoHideDelay: 5000,
          appendToast: append
        })
      }
    }
}
</script>

