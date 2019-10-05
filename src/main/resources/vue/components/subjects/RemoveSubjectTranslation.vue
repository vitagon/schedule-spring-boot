<template>
  <div>
    <b-modal
      v-model="isOpened"
      @ok="removeSubjectTranslation"
    >
      Do you really want to remove translation "{{subjectTranslation}}" of "{{subjectName}}"?
    </b-modal>
  </div>
</template>


<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component';
import EventBus from '@/EventBus';
import SubjectTranslationService from '@/services/SubjectTranslationService';

@Component
export default class RemoveSubjectTranslation extends Vue {

  private isOpened: boolean = false;
  private subjectName: string = '';
  private subjectTranslation: string = '';
  private subjectId: number = 0;
  private localeId: number = 0;

  created() {
    let _this = this;
    EventBus.$on('show-remove-subject-translation-modal', function (subjectTranslation, localeId) {
      _this.isOpened = true;
      _this.subjectName = subjectTranslation.name;
      _this.subjectId = subjectTranslation.id;
      _this.subjectTranslation = subjectTranslation.translation;
      _this.localeId = localeId;
    })
  }

  removeSubjectTranslation() {
    let subjectTranslation = {
      subjectId: this.subjectId,
      localeId: this.localeId,
      translation: null
    }
    SubjectTranslationService.removeTranslation(
      this.subjectId,
      this.localeId
    )
    .then(response => {
      EventBus.$emit('subject-translation-was-changed', subjectTranslation);
      EventBus.$emit('subject-translation-was-removed', subjectTranslation);
      this.$bvToast.toast(`Subject translation was removed successfully!`, {
        title: 'Notification',
        autoHideDelay: 5000,
        appendToast: false
      });
    }).catch(error => {
        console.error(error);
    })
  }
}
</script>