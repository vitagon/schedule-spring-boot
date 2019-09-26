export default class Group {
  id;
	name;
	nameTranslation;

	number;
	courseNum;
	suffix;
	suffixTranslation;

	degree;
  degreeTranslation;
  majorId;
  
  public constructor(_obj?) {
    this.id = _obj && _obj.id || null;
    this.name = _obj && _obj.name || null;
    this.nameTranslation = _obj && _obj.nameTranslation || null;
    this.number = _obj && _obj.number || null;
    this.courseNum = _obj && _obj.courseNum || null;
    this.suffix = _obj && _obj.suffix || null;
    this.suffixTranslation = _obj && _obj.suffixTranslation || null;
    this.degree = _obj && _obj.degree || null;
    this.degreeTranslation = _obj && _obj.degreeTranslation || null;
    this.majorId = _obj && _obj.majorId || null;
  }
}