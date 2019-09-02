<template>
  <div v-if="isOpened" class="mt-5">
    <h5>Edit school translation</h5>
    <hr />
    <b-form-select
      v-model="selectedLocale"
      :options="locales"
      size="sm" class="mb-2"
      @change="getTranslation">
        <template slot="first">
          <option :value="null" disabled>-- Choose locale --</option>
        </template>
    </b-form-select>

    <b-form  @submit.stop.prevent>
        <label for="translation">Translation</label>
        <b-input v-model="form.translation.value" :state="form.translation.isValid" id="translation"></b-input>
        <b-form-invalid-feedback :state="form.translation.isValid">
            <div v-for="validationMsg in form.translation.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
        </b-form-invalid-feedback>

        <b-button class="mt-3 mb-3" variant="outline-warning">Edit</b-button>
    </b-form>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import _ from 'lodash'
import axios from 'axios'
import EventBus from '@/EventBus'
import FormField from '@/form/FormField'
import Component from 'vue-class-component'
import { mapState } from 'vuex'

@Component({
  computed: {
    ...mapState({
      locales: (state: any) => state.localesStore.localesForSelect
    })
  }
})
export default class EditSchoolTranslation extends Vue {
  private selectedLocale;
  private isOpened: boolean;
  private form;

  constructor() {
    super();
    this.selectedLocale = null;
    this.isOpened = false;
    this.form = {
      translation: new FormField()
    }
  }

  created() {
    let _this = this;
    EventBus.$on('show-edit-school-translation-form', function (item) {
      _this.isOpened = true;
      _this.form.translation.value = item.translation;
    });

    EventBus.$on('hide-edit-school-translation-form', function () {
      _this.isOpened = false;
    });
  }
  
  getTranslation() {

  }
}
</script>

