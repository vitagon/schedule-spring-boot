<template>
  <b-modal
    id="edit-schedule-modal"
    ref="edit-schedule-modal"
    title="Submit Your Name"
    @ok="saveScheduleChanges">
    <form>
      <input type="text" class="form-control" id="modal-group-id" style="display: none">
      <input type="text" class="form-control" id="modal-schedule-id" style="display: none">
      <input type="text" class="form-control" id="modal-week" style="display: none">
      <input type="text" class="form-control" id="modal-day-num" style="display: none">
      
      <div class="form-group">
        <label for="modal-lesson-num">Lesson number:</label>
        <select class="custom-select" id="modal-lesson-num" disabled v-model="modal.lessonNum">
          <option selected>Choose...</option>
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
        <select class="custom-select" id="modal-subject" v-model="modal.subjectId">
          <option value="0" selected>Choose...</option>
          <option
            v-for="subject of subjects"
            v-bind:value="subject.id"
            v-bind:key="subject.id">
            {{subject.translation}}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label for="modal-lesson-type">Lesson type:</label>
        <select class="custom-select" id="modal-lesson-type" v-model="modal.lessonTypeId">
          <option value="0" selected>Choose...</option>
          <option value="49">Lecture</option>
          <option value="50">Practice</option>
        </select>
      </div>
      <div class="form-group">
        <label for="modal-teacher">Teacher:</label>
        <select class="custom-select" id="modal-teacher" v-model="modal.teacherId">
          <option value="0" selected>Choose...</option>
          <option
            v-for="teacher of teachers"
            v-bind:value="teacher.id"
            v-bind:key="teacher.id">
            {{teacher.fullName}}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label for="message-text">Classroom:</label>
        <input type="text" class="form-control" id="modal-classroom" v-model="modal.classroom">
      </div>
    </form>
  </b-modal>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component';
import EventBus from '@/EventBus';
import { mapState } from 'vuex';
import ScheduleService from '../../services/ScheduleService';
import UserService from '../../services/UserService';

@Component({
  computed: {
    ...mapState({
      subjects: (state:any) => state.subjectsStore.subjects
    })
  }
})
export default class EditScheduleModal extends Vue {
  private teachers = [];
  private modal = {
    lessonNum: 0,
    subjectId: 0,
    lessonTypeId: 0,
    teacherId: 0,
    classroom: ''
  };
  private groupId = 0;
  private week = '';
  private dayName = '';
  private schedule: any = {};

  created() {
    let _this: any = this;
    this.$store.dispatch('getSubjects');
		this.getTeachers();
		EventBus.$on('showEditScheduleModal', function (groupId, week, dayName, schedule) {
			_this.groupId = groupId;
			_this.week = week;
			_this.dayName = dayName;
			_this.schedule = schedule;
			
			if (schedule.teacherId != null) {
				_this.modal.teacherId = schedule.teacherId;
			} else {
        _this.modal.teacherId = 0;
      }

			if (schedule.lessonTypeId != null) {
				_this.modal.lessonTypeId = schedule.lessonTypeId;
			} else {
        _this.modal.lessonTypeId = 0;
      }
      
			if (schedule.subjectId != null) {
				_this.modal.subjectId = schedule.subjectId;
      } else {
        _this.modal.subjectId = 0;
      }

			if (schedule.lessonNum != null) {
				_this.modal.lessonNum = schedule.lessonNum;
			} else {
        _this.modal.lessonNum = 0;
      }

			if (schedule.classroom != null) {
				_this.modal.classroom = schedule.classroom;
      } else {
        _this.modal.classroom = '';
      }
      
      _this.$refs['edit-schedule-modal'].show();
		});
  }
  
  saveScheduleChanges() {
    let _this = this;
    let method, url;
    let objToSend = {
      groupId: _this.groupId,
      week: _this.week,
      day: _this.dayName,
      lessonNum: _this.modal.lessonNum,
      
      subjectId: _this.modal.subjectId,
      lessonTypeId: _this.modal.lessonTypeId,
      userId: _this.modal.teacherId,
      classroom: _this.modal.classroom
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
        EventBus.$emit('updatedLessonSchedule',
            _this.week,
            _this.dayName,
            _this.modal.lessonNum,
            schedule);
        _this.$refs['edit-schedule-modal'].hide();
      }).catch(error => console.error(error.data));
  }
  updateSchedule(objToSend) {
    let _this: any = this;
    ScheduleService.updateSchedule(this.schedule.id, objToSend)
      .then((response: any) => {
        let schedule = response.data;
        EventBus.$emit('updatedLessonSchedule',
            _this.week,
            _this.dayName,
            _this.modal.lessonNum,
            schedule);
        _this.$refs['edit-schedule-modal'].hide();
      }).catch(error => console.error(error.data));
  }
  getTeachers() {
    let _this = this;
    UserService.getUsersByRole('teacher')
      .then((response: any) => {
        let teachers = response.data;
        _this.teachers = teachers;
      }).catch(error => console.error(error.data));
  }
}
</script>