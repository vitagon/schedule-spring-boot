export default class Group {
  id;
	name;
	translation;

	courseNum;
  majorId;
  
  public constructor(_obj?) {
    this.id = _obj && _obj.id || null;
    this.name = _obj && _obj.name || null;
    this.translation = _obj && _obj.nameTranslation || null;
    this.courseNum = _obj && _obj.courseNum || null;
    this.majorId = _obj && _obj.majorId || null;
  }
}