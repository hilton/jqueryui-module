$(function() {

	/*
	 * Autocomplete for populating an input field with a value from the server.
	 */
	$('input.autocomplete').each( function() {
		var $input = $(this);
		var serverUrl = $input.data('url');
		$(this).autocomplete({ source:serverUrl });
	});

	/*
	 * Autocomplete for selecting an entity instance from the server, which provides a label (name) and value (id).
	 * This approach stores the selection's value in a hidden input field that is submitted with the form, and
	 * puts the selection's label in the original input field, which is not submitted with the form.
	 */
	$('input.autocomplete-relation').each( function() {
		var $input = $(this);
	
		// Create a hidden input with the same form control name to submit the value.
		var controlName = $input.attr('name');
		var $hidden = $('<input type="hidden"/>').attr('name', controlName);
		$input.after($hidden).attr('name', controlName + '_label');
	
		// Set-up the autocomplete widget.
		var serverUrl = $input.data('url');
		$(this).autocomplete({
			source: serverUrl,
			focus: function(event, ui) {
				// Set the text input value to the focused item's label, instead of the value.
				$input.val(ui.item.label);
				return false;
			},
			select: function(event, ui) {
				// Save the selection item and value in the two inputs.
				$input.val(ui.item.label);
				$hidden.val(ui.item.value);
				return false;
			}
		});
	});
	
	// On form submit, suppress autocomplete fields that only contain a label.
	$('form').submit(function() {
		$(this).find('input.autocomplete-relation').attr('disabled', 'disabled');
	});

});
