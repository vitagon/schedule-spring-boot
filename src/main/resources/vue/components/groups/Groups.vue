<template>
  <b-container>
    <groups-list></groups-list>

    <add-group></add-group>
    <edit-group></edit-group>
    <edit-group-translation></edit-group-translation>
  </b-container>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import { mapState } from 'vuex'
import GroupsList from '@/components/groups/GroupsList.vue'
import AddGroup from '@/components/groups/AddGroup.vue'
import EditGroup from '@/components/groups/EditGroup.vue'
import EditGroupTranslation from '@/components/groups/EditGroupTranslation.vue'

@Component({
  components: {GroupsList, AddGroup, EditGroup, EditGroupTranslation}
})
export default class Groups extends Vue {
}
</script>