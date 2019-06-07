export function clearValidationMessages($form) {
	$form.find('.validation-message').remove();
}

export function showValidationErrors(errors, $form) {
	$form.find('.validation-message').remove();
	$.each(errors, function (index, validationError) {
		let $field = $form.find('[name=' + validationError.fieldName + ']');
		
		if ($field != null) {
			let $fieldWrap = $field.closest('div');
			$.each(validationError.messages, function (index, errorMessage) {
				$fieldWrap.append('<div class="validation-message" style="color: red">' +
									errorMessage +
								  '</div>');
			});
		}
	});
}