package controllers.jqueryui;

import models.jqueryui.AutocompleteValue;
import play.Logger;
import play.mvc.Controller;

import java.util.*;

/**
 * Controller that provides server-side data for a jQuery UI Autocomplete component.
 */
public class Autocomplete extends Controller {

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

		for (String label : locations()) {
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
		for (String label : locations()) {
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

	/**
	 * Autocomplete test data - a list of location names, mostly cities.
	 */
	private static List<String> locations() {
		final List<String> result = new ArrayList<String>();
		final String[] timeZones = TimeZone.getAvailableIDs();
		for (int i = 0; i < timeZones.length; i++) {
			final String[] parts = timeZones[i].split("/");
			if (parts.length == 2 && parts[1].matches("[A-Za-z_]+")) {
				final String location = parts[1].replaceAll("_", " ");
				result.add(location);
			}
		}
		Collections.sort(result);
		return result;
	}
}
