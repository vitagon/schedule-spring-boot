export default class MajorDto {
  id: Number;
  name: string;
  duration: Number;
  degree: Number;
  schoolId: Number;

  constructor(_obj?) {
    this.id = _obj && _obj.id || 0;
    this.name = _obj && _obj.name || '';
    this.degree = _obj && _obj.degree || 0;
    this.duration = _obj && _obj.duration || 0;
    this.schoolId = _obj && _obj.schoolId || 0;
  }
}