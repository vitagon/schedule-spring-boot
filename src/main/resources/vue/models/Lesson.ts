export default class Lesson {
  id: Number;

  lessonNum: Number;
  time: string;
  subjectId: Number;
  subjectName: string;
  lessonTypeId: Number;
  lessonTypeName: string;
  teacherId: Number;
  teacherName: string;
  classroom: string;

  constructor() {
    this.id = 0;
    this.lessonNum = 0;
    this.time = '';
    this.subjectId = 0;
    this.subjectName = '';
    this.lessonTypeId = 0;
    this.lessonTypeName = '';
    this.teacherId = 0;
    this.teacherName = '';
    this.classroom = '';
  }
}