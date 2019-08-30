<template>
  <div v-if="isOpened" class="mt-5">
    <h5>Edit school translation</h5>
    <hr />
    <b-form-select v-model="selectedLocale"
                   :options="locales"
                   size="sm" class="mb-2"
                   @change="getTranslation"></b-form-select>

    <b-form  @submit.stop.prevent>
        <label for="translation">Translation</label>
        <b-input v-model="translation" :state="validation" id="translation"></b-input>
        <b-form-invalid-feedback :state="validation">
            Name must be 5-12 characters long.
        </b-form-invalid-feedback>

        <b-button class="mt-3 mb-3" variant="outline-warning">Edit</b-button>
    </b-form>
  </div>
</template>

<script>
import _ from 'lodash'
import axios from 'axios'
import EventBus from '@/EventBus'

export default {
  data() {
      return {
        translation: '',
        isOpened: false,
        selectedLocale: null,
        locales: []
      }
    },
    created() {
      let _this = this;
      EventBus.$on('show-edit-school-translation-form', function (item) {
        _this.isOpened = true;
        _this.name = item.name;
      });

      EventBus.$on('hide-edit-school-translation-form', function () {
        _this.isOpened = false;
      });

      EventBus.$on('gotLocales', function (locales) {
        _this.locales = locales.map(function (locale) {
            return {value: locale.id, text: locale.code};
        });
        _this.locales.unshift({value: null, text: 'Choose locale'})
    });
    },
    computed: {
      validation() {
        return this.name.length > 4 && this.name.length < 40;
      }
    },
    methods: {
      getTranslation() {

      }
    }
}
</script>

