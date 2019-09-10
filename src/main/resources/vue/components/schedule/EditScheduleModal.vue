<template>
  <b-modal
    id="edit-schedule-modal"
    ref="edit-schedule-modal"
    title="Submit Your Name"
    @ok="saveScheduleChanges">
    <b-form @submit.stop.prevent>
      <div class="form-group">
        <label for="modal-lesson-num">Lesson number:</label>
        <select class="custom-select" id="modal-lesson-num" disabled v-model="form.lessonNum.value">
          <option selected>-- Please select an option --</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
        </select>
      </div>
      <div class="form-group">
        <label for="modal-subject">Subject title:</label>
        <b-form-select
          class="custom-select"
          id="modal-subject"
          v-model="form.subjectId.value"
          :state="form.subjectId.isValid"
          :options="subjects"
          >
          <template v-slot:first>
            <option :value="0" selected>-- Please select an option --</option>
          </template>
        </b-form-select>
        <b-form-invalid-feedback :state="form.subjectId.isValid">
          <div v-for="validationMsg in form.subjectId.validationMsgs" :key="validationMsg">{{validationMsg}}</div>
        </b-form-invalid-feedback>
      </div>
      <div class="form-group">
        <label for="modal-lesson-type">Lesson type:</label>
        <select class="custom-select" id="modal-lesson-type" v-model="form.lessonTypeId.value">
          <option value="0" selected>-- Please select an option --</option>
          <option value="49">Lecture</option>
          <option value="50">Practice</option>
        </select>
      </div>
      <div class="form-group">
        <label for="modal-teacher">Teacher:</label>
        <b-form-select
          class="custom-select"
          id="modal-teacher"
          v-model="form.teacherId.value"
          :state="form.teacherId.isValid"
          :options="teachers"
          >
          <template v-slot:first>
            <option :value="0" selected>-- Please select an option --</option>
          </template>
        </b-form-select>
      </div>
      <div class="form-group">
        <label for="message-text">Classroom:</label>
        <input type="text" class="form-control" id="modal-classroom" v-model="form.classroom.value">
      </div>
    </b-form>
  </b-modal>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component';
import EventBus from '@/EventBus';
import { mapState } from 'vuex';
import ScheduleService from '@/services/ScheduleService';
import UserService from '@/services/UserService';
import FormField from '@/form/FormField';
import { showValidationErrors, clearValidationMsgs } from '@/util/FormUtil';

@Component({
  computed: {
    ...mapState({
      subjects: (state: any) => {
        return state.subjectsStore.subjects.map(function (subject) {
          return {value: subject.id, text: subject.translation};
        });
      },
      teachers: (state: any) => {
        return state.teachersStore.teachers.map(function(teacher) {
          return {value: teacher.id, text: teacher.fullName};
        });
      }
    })
  }
})
export default class EditScheduleModal extends Vue {
  public form = {
    lessonNum: new FormField(),
    subjectId: new FormField(),
    lessonTypeId: new FormField(),
    teacherId: new FormField(),
    classroom: new FormField()
  };
  private groupId = 0;
  private week = '';
  private dayName = '';
  private schedule: any = {};

  mounted() {
    
    let _this: any = this;
    this.$store.dispatch('getSubjects');
    this.$store.dispatch('getTeachers');
		EventBus.$on('showEditScheduleModal', function (groupId, week, dayName, schedule) {
      clearValidationMsgs(_this.form);
			_this.groupId = groupId;
			_this.week = week;
			_this.dayName = dayName;
			_this.schedule = schedule;
			
			if (schedule.teacherId != null) {
				_this.form.teacherId.value = schedule.teacherId;
			} else {
        _this.form.teacherId.value = 0;
      }

			if (schedule.lessonTypeId != null) {
				_this.form.lessonTypeId.value = schedule.lessonTypeId;
			} else {
        _this.form.lessonTypeId.value = 0;
      }
      
			if (schedule.subjectId != null) {
				_this.form.subjectId.value = schedule.subjectId;
      } else {
        _this.form.subjectId.value = 0;
      }

			if (schedule.lessonNum != null) {
				_this.form.lessonNum.value = schedule.lessonNum;
			} else {
        _this.form.lessonNum.value = 0;
      }

			if (schedule.classroom != null) {
				_this.form.classroom.value = schedule.classroom;
      } else {
        _this.form.classroom.value = '';
      }
      
      _this.$refs['edit-schedule-modal'].show();
		});
  }
  
  saveScheduleChanges(modalOkEvent) {
    modalOkEvent.preventDefault();
    let _this = this;
    let method, url;
    let objToSend = {
      groupId: _this.groupId,
      week: _this.week,
      day: _this.dayName,
      lessonNum: _this.form.lessonNum.value,
      
      subjectId: _this.form.subjectId.value,
      lessonTypeId: _this.form.lessonTypeId.value,
      userId: _this.form.teacherId.value,
      classroom: _this.form.classroom.value
    }
    
    if (_this.schedule.id == null) {
      this.createSchedule(objToSend);
    } else {
      this.updateSchedule(objToSend);
    }
  }
  createSchedule(objToSend) {
    let _this: any = this;
    ScheduleService.addSchedule(objToSend)
      .then((response: any) => {
        let schedule = response.data;
        _this.$store.commit('addLesson', {
          week: _this.week,
          dayName: _this.dayName,
          lesson: schedule
        });
        _this.$refs['edit-schedule-modal'].hide();
      }).catch(error => {
        console.error(error.data);
        showValidationErrors(this.form, error.data.details);
      });
  }
  updateSchedule(objToSend) {
    let _this: any = this;
    ScheduleService.updateSchedule(this.schedule.id, objToSend)
      .then((response: any) => {
        let schedule = response.data;
        _this.$store.commit('addLesson', {
          week: _this.week,
          dayName: _this.dayName,
          lesson: schedule
        });
        _this.$refs['edit-schedule-modal'].hide();
      }).catch(error => {
        console.error(error.data);
        showValidationErrors(this.form, error.data.details);
      });
  }
}
</script>