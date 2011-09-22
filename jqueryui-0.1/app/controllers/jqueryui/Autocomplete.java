package controllers.jqueryui;

import models.jqueryui.AutocompleteValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller that provides Ajax actions for a jQuery UI Autocomplete component.
 */
public class Autocomplete extends JQueryUI {

	/**
	 * Used to limit the size of auto-complete lists to a reasonable number of results.
	 */
	public static int AUTOCOMPLETE_MAX = 10;

	/**
	 * Returns a JSON representation of a list of Strings, to populate an autocomplete input field.
	 * The list is filtered by the given search term, and limited to a maximum length - so that there
	 * are not more results than you will reasonably select from.
	 *
	 * @param term The text entered in the input field, used to filter the results.
	 */
	public static void label(final String term) {
		final List<String> response = new ArrayList<String>();

		for (String label : TestData.locations()) {
			if (label.toLowerCase().startsWith(term.toLowerCase())) {
				response.add(label);
			}
			if (response.size() == AUTOCOMPLETE_MAX) {
				break;
			}
		}
		renderJSON(response);
	}

	/**
	 * Returns a JSON representation of a list of items, each of which has a label and a value.
	 * The value uses the {@link AutocompleteValue} type, which has the correct JSON representation
	 * for the jQuery UI autocomplete plug-in.
	 *
	 * @param term The text entered in the input field.
	 */
	public static void value(final String term) {
		final List<AutocompleteValue> response = new ArrayList<AutocompleteValue>();
		int index = 1;
		for (String label : TestData.locations()) {
			final String value = String.valueOf(index);
			if (label.toLowerCase().startsWith(term.toLowerCase())) {
				response.add(new AutocompleteValue(value, label));
			}
			if (response.size() == AUTOCOMPLETE_MAX) {
				break;
			}
			index++;
		}
		renderJSON(response);
	}

}
