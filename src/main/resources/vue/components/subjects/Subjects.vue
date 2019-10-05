<template>
  <b-container>
    <subjects-list></subjects-list>

    <add-subject></add-subject>
    <edit-subject></edit-subject>
    <remove-subject></remove-subject>
    <edit-subject-translation></edit-subject-translation>
    <remove-subject-translation></remove-subject-translation>
  </b-container>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import { mapState } from 'vuex'
import SubjectsList from '@/components/subjects/SubjectsList.vue'
import AddSubject from '@/components/subjects/AddSubject.vue'
import EditSubject from '@/components/subjects/EditSubject.vue'
import RemoveSubject from '@/components/subjects/RemoveSubject.vue'
import EditSubjectTranslation from '@/components/subjects/EditSubjectTranslation.vue'
import RemoveSubjectTranslation from '@/components/subjects/RemoveSubjectTranslation.vue'

@Component({
  components: {SubjectsList, AddSubject, EditSubject, RemoveSubject, EditSubjectTranslation, RemoveSubjectTranslation}
})
export default class Subjects extends Vue {
}
</script>