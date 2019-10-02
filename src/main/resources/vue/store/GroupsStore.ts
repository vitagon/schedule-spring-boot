import Group from '@/models/Group';
import GroupService from '@/services/GroupService'

const state = {
  groups: []
}

const mutations = {
  setGroups(state: any, groups: Array<Group>) {
    state.groups = groups;
  },
  addGroup(state: any, group: Group) {
    state.groups = [
      ...state.groups,
      group
    ]
  },
  removeGroup(state: any, groupId: Number) {
    let removedIndex = state.groups.findIndex((x: any) => x.id == groupId);
    state.groups = [
      ...state.groups.slice(0, removedIndex),
      ...state.groups.slice(removedIndex + 1)
    ]
  },
  updateGroup(state: any, updatedGroup: any) {
    let updatedIndex = state.groups.findIndex((x: any) => x.id == updatedGroup.id);
    let group = state.groups[updatedIndex];
    group = updatedGroup;

    state.groups = [
      ...state.groups.slice(0, updatedIndex),
      group,
      ...state.groups.slice(updatedIndex + 1)
    ]
  },
  updateGroupTranslation(state: any, groupTranslation: any) {
    let updatedIndex = state.groups.findIndex((x: any) => x.id == groupTranslation.groupId);
    let group = state.groups[updatedIndex];
    group.translation = groupTranslation.translation;

    state.groups = [
      ...state.groups.slice(0, updatedIndex),
      group,
      ...state.groups.slice(updatedIndex + 1)
    ]
  }
}

const actions = {
  async removeGroup({commit}, groupId) {
    try {
      await GroupService.delete(groupId);
    } catch (error) {
      console.error(error);
      return;
    }
    
    commit('removeGroup', groupId);
  }
}

const getters = { 
}

export const groupsStore = {
  state,
  mutations,
  actions,
  getters
}