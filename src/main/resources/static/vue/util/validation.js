export function clearValidationMessages($form) {
	$form.find('.validation-message').remove();
}

export function showValidationErrors(errors, $form) {
	$form.find('.validation-message').remove();
	$.each(errors, function (fieldName, fieldErrors) {
		let $field = $form.find('[name=' + fieldName + ']');
		
		if ($field != null) {
			let $fieldWrap = $field.closest('div');
			$.each(fieldErrors, function (index, fieldError) {
				$fieldWrap.append('<div class="validation-message" style="color: red">' +
								  fieldError +
								  '</div>');
			});
		}
	});
}