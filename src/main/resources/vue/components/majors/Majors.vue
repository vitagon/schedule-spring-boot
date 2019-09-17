<template>
    <b-container class="pt-5">
        <majors-list></majors-list>
        <edit-major></edit-major>
        <edit-major-translation></edit-major-translation>
        <!-- <remove-major-translation></remove-major-translation>

        <add-major></add-major>
        <remove-major></remove-major> -->
    </b-container>
</template>

<script lang="ts">
import axios from 'axios'
import Vue from 'vue'
import Component from 'vue-class-component'
import MajorsList from '@/components/majors/MajorsList.vue'
// import AddMajor from '@/components/majors/AddMajor.vue'
import EditMajor from '@/components/majors/EditMajor.vue'
// import RemoveMajor from '@/components/majors/RemoveMajor.vue'
import EditMajorTranslation from '@/components/majors/EditMajorTranslation.vue'
// import RemoveMajorTranslation from '@/components/majors/RemoveMajorTranslation.vue'
import EventBus from '@/EventBus'

@Component({
  components: {MajorsList, /*AddMajor,*/ EditMajor/*, RemoveMajor*/, EditMajorTranslation, /*RemoveMajorTranslation*/}
})
export default class Majors extends Vue {
}
</script>

