import FormField from '@/form/FormField'

export function clearForm(form: any) {
  for (let prop in form) {
    if (form.hasOwnProperty(prop)) {
      form[prop] = new FormField();
    }
  }
}

export function showValidationErrors(form: any, details: any) {
  for (let detail of details) {
    form[detail.fieldName].validationMsgs = detail.messages;
    form[detail.fieldName].isValid = false;
  }
}