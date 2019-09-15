export default class Major {
  id: Number;
  name: string;
  url: string;
  translation: string;
  degree: string;
  duration: Number;
  schoolId: Number;
  schoolName: string;

  constructor() {
    this.id = 0;
    this.name = '';
    this.url = '';
    this.translation = '';
    this.degree = '';
    this.duration = 0;
    this.schoolId = 0;
    this.schoolName = '';
  }
}