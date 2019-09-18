<template>
  <div>
    <b-modal
      v-model="isOpened"
      @ok="removeMajor"
    >
      Do you really want to remove translation "{{majorTranslation}}" of "{{majorName}}"?
    </b-modal>
  </div>
</template>


<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component';
import EventBus from '@/EventBus';
import MajorTranslationService from '@/services/MajorTranslationService';

@Component
export default class RemoveMajorTranslation extends Vue {

  private isOpened: boolean = false;
  private majorName: string = '';
  private majorTranslation: string = '';
  private majorId: number = 0;
  private localeId: number = 0;

  created() {
    let _this = this;
    EventBus.$on('show-remove-major-translation-modal', function (majorTranslation, localeId) {
      _this.isOpened = true;
      _this.majorName = majorTranslation.name;
      _this.majorId = majorTranslation.id;
      _this.majorTranslation = majorTranslation.translation;
      _this.localeId = localeId;
    })
  }

  removeMajor() {
    let majorTranslation = {
      majorId: this.majorId,
      localeId: this.localeId,
      translation: null
    }
    MajorTranslationService.removeTranslation(
      this.majorId,
      this.localeId
    )
    .then(response => {
      EventBus.$emit('major-translation-was-changed', majorTranslation);
      EventBus.$emit('major-translation-was-removed', majorTranslation);
      this.$bvToast.toast(`Major translation was removed successfully!`, {
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
