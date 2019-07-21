<template>
    <div v-if="isOpened" class="mt-5">
        <h5>Edit school</h5>
        <hr />
        <b-form  @submit.stop.prevent>
            <label for="name">Name</label>
            <b-input v-model="newSchoolName.value" :state="validation" id="name"></b-input>
            <b-form-invalid-feedback :state="validation">
              <span v-for="validationMsg in newSchoolName.validationMsgs" :key="validationMsg">{{validationMsg}}</span>
            </b-form-invalid-feedback>

            <b-button class="mt-3" variant="outline-warning" @click="editSchool">Edit</b-button>
        </b-form>
  </div>
</template>

<script>
import _ from 'lodash'
import axios from 'axios'
import EventBus from 'EventBus.js'

export default {
    data() {
      return {
        form: {
          newSchoolName: {
            value: '',
            validationMsgs: []
          }
        },
        isOpened: false,
        curSchool: null,
        isValid: null
      }
    },
    created() {
      let _this = this;
      EventBus.$on('open-edit-school', function (item) {
        _this.isOpened = true;
        _this.curSchool = item;
      });
    },
    computed: {
      validation() {
        return this.isValid;
      }
    },
    methods: {
      editSchool() {
        let _this = this;
        let objToSend = {
          schoolId: this.curSchool.id,
          newSchoolName: this.newSchoolName.value
        }
        axios({
          method: 'PUT',
          url: '/api/control/school',
          data: objToSend,
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          let data = error.response.data;
          console.log(data);
          for (let detail of data.details) {
            _this.newSchoolName.validationMsgs = detail.messages
          }
          _this.isValid = false;
        })
      }
    }
}
</script>

