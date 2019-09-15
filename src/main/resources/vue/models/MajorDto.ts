export default class MajorDto {
  id: Number;
  name: string;
  duration: Number;
  degree: Number;
  schoolId: Number;

  constructor(_obj?) {
    this.id = _obj.id || 0;
    this.name = _obj.name || '';
    this.degree = _obj.degree || 0;
    this.duration = _obj.duration || 0;
    this.schoolId = _obj.schoolId || 0;
  }
}