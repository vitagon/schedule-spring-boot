<template>
  <b-container>
    <groups-list></groups-list>
  </b-container>
</template>

<script lang="ts">
import Vue from 'vue';
import Component from 'vue-class-component';
import { mapState } from 'vuex';
import GroupsList from '@/components/groups/GroupsList.vue'

@Component({
  components: {GroupsList}
})
export default class Groups extends Vue {
}
</script>