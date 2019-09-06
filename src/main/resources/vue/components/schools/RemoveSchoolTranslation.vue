<template>
  <div>
    <b-modal
      v-model="isOpened"
      @ok="removeSchool"
    >
      Do you really want to remove translation "{{schoolTranslation}}" of "{{schoolName}}"?
    </b-modal>
  </div>
</template>


<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component';
import EventBus from '@/EventBus';
import SchoolTranslationService from '@/services/SchoolTranslationService';

@Component
export default class RemoveSchoolTranslation extends Vue {

  private isOpened: boolean = false;
  private schoolName: string = '';
  private schoolTranslation: string = '';
  private schoolId: number = 0;
  private localeId: number = 0;

  created() {
    let _this = this;
    EventBus.$on('show-remove-school-translation-modal', function (schoolTranslation, localeId) {
      _this.isOpened = true;
      _this.schoolName = schoolTranslation.name;
      _this.schoolId = schoolTranslation.id;
      _this.schoolTranslation = schoolTranslation.translation;
      _this.localeId = localeId;
    })
  }

  removeSchool() {
    let schoolTranslation = {
      schoolId: this.schoolId,
      localeId: this.localeId,
      translation: null
    }
    SchoolTranslationService.removeTranslation(
      this.schoolId,
      this.localeId
    )
    .then(response => {
      EventBus.$emit('school-translation-was-changed', schoolTranslation);
      EventBus.$emit('school-translation-was-removed', schoolTranslation);
      this.$bvToast.toast(`School translation was removed successfully!`, {
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
