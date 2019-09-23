<template>
  <div>
    <b-modal
      v-model="isOpened"
      @ok="removeMajor"
    >
      Do you really want to remove "{{majorName}}"?
    </b-modal>
  </div>
</template>


<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component';
import EventBus from '@/EventBus';

@Component
export default class RemoveMajor extends Vue {

  private isOpened: boolean = false;
  private majorName: string = '';
  private majorId: number = 0;

  created() {
    let _this = this;
    EventBus.$on('show-remove-major-modal', function (item) {
      _this.isOpened = true;
      _this.majorName = item.name;
      _this.majorId = item.id;
    })
  }

  removeMajor() {
    this.$store.dispatch('removeMajor', this.majorId);
  }
}
</script>