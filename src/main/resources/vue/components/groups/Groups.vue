<template>
  <b-container>
    <groups-list></groups-list>

    <edit-group></edit-group>
  </b-container>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import { mapState } from 'vuex'
import GroupsList from '@/components/groups/GroupsList.vue'
import EditGroup from '@/components/groups/EditGroup.vue'

@Component({
  components: {GroupsList, EditGroup}
})
export default class Groups extends Vue {
}
</script>