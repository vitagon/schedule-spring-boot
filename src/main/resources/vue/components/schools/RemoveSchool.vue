<template>
  <div>
    <b-modal
      v-model="isOpened"
      @ok="removeSchool"
    >
      Do you really want to remove "{{schoolName}}"?
    </b-modal>
  </div>
</template>


<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component';
import EventBus from '@/EventBus';

@Component
export default class RemoveSchool extends Vue {

  private isOpened: boolean = false;
  private schoolName: string = '';
  private schoolId: number = 0;

  created() {
    let _this = this;
    EventBus.$on('show-remove-school-modal', function (item) {
      _this.isOpened = true;
      _this.schoolName = item.name;
      _this.schoolId = item.id;
    })
  }

  removeSchool() {
    this.$store.dispatch('removeSchool', this.schoolId);
  }
}
</script>
