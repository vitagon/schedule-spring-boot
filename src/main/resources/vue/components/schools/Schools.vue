<template>
    <b-container class="pt-5">
        <schools-list></schools-list>
        <edit-school></edit-school>
        <edit-school-translation></edit-school-translation>
        <remove-school-translation></remove-school-translation>

        <add-school></add-school>
        <remove-school></remove-school>
    </b-container>
</template>

<script lang="ts">
import axios from 'axios'
import Vue from 'vue'
import Component from 'vue-class-component'
import SchoolsList from '@/components/schools/SchoolsList.vue'
import AddSchool from '@/components/schools/AddSchool.vue'
import EditSchool from '@/components/schools/EditSchool.vue'
import RemoveSchool from '@/components/schools/RemoveSchool.vue'
import EditSchoolTranslation from '@/components/schools/EditSchoolTranslation.vue'
import RemoveSchoolTranslation from '@/components/schools/RemoveSchoolTranslation.vue'
import EventBus from '@/EventBus'

@Component({
  components: {SchoolsList, AddSchool, EditSchool, RemoveSchool, EditSchoolTranslation, RemoveSchoolTranslation}
})
export default class Schools extends Vue {
}
</script>

