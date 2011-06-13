$(function() {

	/**
	 * Tabs that load content from the Ajax source specified by the links in the tabs list.
	 */
	$('div.tabs').tabs({
		ajaxOptions: {
			error: function(xhr, status, index, anchor) {
				$(anchor.hash).html('Error loading tab');
			}
		}
	});

});
