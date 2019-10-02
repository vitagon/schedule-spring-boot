<template>
  <div>
    <b-modal
      v-model="isOpened"
      @ok="removeGroupTranslation"
    >
      Do you really want to remove translation "{{groupTranslation}}" of "{{groupName}}"?
    </b-modal>
  </div>
</template>


<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component';
import EventBus from '@/EventBus';
import GroupTranslationService from '@/services/GroupTranslationService';

@Component
export default class RemoveMajorTranslation extends Vue {

  private isOpened: boolean = false;
  private groupName: string = '';
  private groupTranslation: string = '';
  private groupId: number = 0;
  private localeId: number = 0;

  created() {
    let _this = this;
    EventBus.$on('show-remove-group-translation-modal', function (groupTranslation, localeId) {
      _this.isOpened = true;
      _this.groupName = groupTranslation.name;
      _this.groupId = groupTranslation.id;
      _this.groupTranslation = groupTranslation.translation;
      _this.localeId = localeId;
    })
  }

  async removeGroupTranslation() {
    let groupTranslation = {
      groupId: this.groupId,
      localeId: this.localeId,
      translation: null
    }

    try {
      await GroupTranslationService.removeTranslation(this.groupId, this.localeId);
    } catch (error) {
      console.error(error);
      return;
    }
    
    EventBus.$emit('group-translation-was-changed', groupTranslation);
    EventBus.$emit('group-translation-was-removed', groupTranslation);
    this.$bvToast.toast(`Group translation was removed successfully!`, {
      title: 'Notification',
      autoHideDelay: 5000,
      appendToast: false
    });
  }
}
</script>