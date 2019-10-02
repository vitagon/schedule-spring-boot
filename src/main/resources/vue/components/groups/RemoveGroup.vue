<template>
  <div>
    <b-modal
      v-model="isOpened"
      @ok="removeGroup"
    >
      Do you really want to remove "{{groupName}}"?
    </b-modal>
  </div>
</template>


<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component';
import EventBus from '@/EventBus';

@Component
export default class RemoveGroup extends Vue {

  private isOpened: boolean = false;
  private groupName: string = '';
  private groupId: number = 0;

  created() {
    let _this = this;
    EventBus.$on('show-remove-group-modal', function (item) {
      _this.isOpened = true;
      _this.groupName = item.name;
      _this.groupId = item.id;
    })
  }

  removeGroup() {
    this.$store.dispatch('removeGroup', this.groupId);
  }
}
</script>