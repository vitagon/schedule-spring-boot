export default class LessonDto {
  /* key for searching schedule tree */
  groupId: Number;

  /* path in the schedule tree */
  week: string;
  dayName: string;
  lessonNum: Number;

  /* lesson info */
  subjectId: Number;
  lessonTypeId: Number;
  userId: Number;
  classroom: string;

  constructor() {
    this.groupId = 0;

    this.week = '';
    this.dayName = '';
    this.lessonNum = 0;

    this.subjectId = 0;
    this.lessonTypeId = 0;
    this.userId = 0;
    this.classroom = '';
  }
}