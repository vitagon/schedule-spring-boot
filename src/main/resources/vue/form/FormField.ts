export default class FormField {
  public value: string;
  public isValid: boolean;
  public validationMsgs: Array<any>;

  constructor(_obj?: any) {
    this.value = _obj && _obj.value || '';
    this.isValid = _obj && _obj.isValid || null;
    this.validationMsgs = _obj && _obj.validationMsgs || [];
  }
}