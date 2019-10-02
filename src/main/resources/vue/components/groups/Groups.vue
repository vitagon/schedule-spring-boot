<template>
  <b-container>
    <groups-list></groups-list>

    <add-group></add-group>
    <edit-group></edit-group>
    <remove-group></remove-group>
    <edit-group-translation></edit-group-translation>
    <remove-group-translation></remove-group-translation>
  </b-container>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import { mapState } from 'vuex'
import GroupsList from '@/components/groups/GroupsList.vue'
import AddGroup from '@/components/groups/AddGroup.vue'
import EditGroup from '@/components/groups/EditGroup.vue'
import RemoveGroup from '@/components/groups/RemoveGroup.vue'
import EditGroupTranslation from '@/components/groups/EditGroupTranslation.vue'
import RemoveGroupTranslation from '@/components/groups/RemoveGroupTranslation.vue'

@Component({
  components: {GroupsList, AddGroup, EditGroup, RemoveGroup, EditGroupTranslation, RemoveGroupTranslation}
})
export default class Groups extends Vue {
}
</script>