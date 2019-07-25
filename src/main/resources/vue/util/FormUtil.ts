import FormField from '@/form/FormField'

export function clearForm(form: any) {
  for (let prop in form) {
    if (form.hasOwnProperty(prop)) {
      form[prop] = new FormField();
    }
  }
}