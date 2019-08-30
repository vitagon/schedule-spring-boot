<template>
    <b-container class="pt-5">
        <schools-list></schools-list>
        <edit-school></edit-school>
        <edit-school-translation></edit-school-translation>

        <add-school></add-school>
    </b-container>
</template>

<script lang="ts">
import axios from 'axios'
import Vue from 'vue'
import Component from 'vue-class-component'
import SchoolsList from '@/components/schools/SchoolsList.vue'
import AddSchool from '@/components/schools/AddSchool.vue'
import EditSchool from '@/components/schools/EditSchool.vue'
import EditSchoolTranslation from '@/components/schools/EditSchoolTranslation.vue'
import EventBus from '@/EventBus'

@Component({
  components: {SchoolsList, AddSchool, EditSchool, EditSchoolTranslation}
})
export default class Schools extends Vue {
}
</script>

