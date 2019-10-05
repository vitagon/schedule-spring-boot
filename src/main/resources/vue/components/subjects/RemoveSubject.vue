<template>
  <div>
    <b-modal
      v-model="isOpened"
      @ok="removeSubject"
    >
      Do you really want to remove "{{subjectName}}"?
    </b-modal>
  </div>
</template>


<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component';
import EventBus from '@/EventBus';

@Component
export default class RemoveSubject extends Vue {

  private isOpened: boolean = false;
  private subjectName: string = '';
  private subjectId: number = 0;

  created() {
    let _this = this;
    EventBus.$on('show-remove-subject-modal', function (item) {
      _this.isOpened = true;
      _this.subjectName = item.name;
      _this.subjectId = item.id;
    })
  }

  removeSubject() {
    this.$store.dispatch('removeSubject', this.subjectId);
  }
}
</script>
