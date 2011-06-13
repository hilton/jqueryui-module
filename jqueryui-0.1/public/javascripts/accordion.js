$(function() {

	/*
	 * Accordion that loads content from the Ajax source specified by the data-url attribute and heading names.
	 */
	$('div.accordion h3').each( function() {
		// Add a DIV for each section's content.
		$(this).after('<div></div>');
	});
	$('div.accordion').each( function() {
		var $accordion = $(this);
		var serverUrl = $accordion.data('url');
		$(this).accordion({
			autoHeight: false,
			active: false, // Initially collapse all sections.
			changestart: function(event, ui) {
				// Load content if the div is still empty.
				if (ui.newContent.is(':empty')) {
					$(ui.newContent).load(serverUrl + escape(ui.newHeader.text()));
				}
			},
			change: function() {
				// Resize the content DIV after populating it.
				$accordion.accordion('resize');
			}
		});
	});

});
