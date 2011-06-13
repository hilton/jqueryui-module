$(function() {

	/**
	 * Converts a java.text.SimpleDateFormat pattern to a http://docs.jquery.com/UI/Datepicker/formatDate pattern.
	 */
	var convertDateFormat = function(format) {
	
		var replacements = [
			['yyyy', 'yy'], // 2011
			['yy', 'y'], // 11
			['MMMM', 'MM'], // January
			['MMM', 'M'], // Jan
			['MM', 'mm'], // 01
			['M', 'm'], // 1
		];
	
		var result ='';
		while (format.length > 0) {
			for (var i=0; i < replacements.length; i++) {
				if (format.substr(0, replacements[i][0].length) == replacements[i][0]) {
					// The initial substring is a match, so copy the replacement to the result.
					result = result + replacements[i][1]
					
					// Removed the matched initial substring and continue.
					format = format.substr(replacements[i][0].length);
					break;
				}
			}
			
			// Move one character to the result and look for more matches.
			result = result + format.substr(0, 1);
			format = format.substr(1);
		}
		return result;
	};

	
	/**
	 * Set date picker default options using the Java date format defined on the body tag.
	 */
	$('body').each(function() {
		$.datepicker.setDefaults({
			dateFormat: convertDateFormat($(this).data('dateformat'))
		});
	});

	/**
	 * Date picker, configured with server-side configuration options.
	 */
	$('input.date').datepicker();

});
