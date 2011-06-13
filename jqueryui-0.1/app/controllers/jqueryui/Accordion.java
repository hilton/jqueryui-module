package controllers.jqueryui;

import play.mvc.Controller;

import java.util.*;

/**
 * Controller that provides server-side data for a jQuery UI Accordion component.
 */
public class Accordion extends Controller {

	/**
	 * Renders an HTML representation of the contents of the accordion section specified by the parameter.
	 *
	 * @param header The header name, which specifies which content to load.
	 */
	public static void region(final String header) {
		final Set<String> locations = regionLocations(header);
		render(locations);
	}

	/**
	 * Autocomplete test data - a list of location names, mostly cities.
	 *
	 * @param region A {@link TimeZone} ID prefix.
	 */
	private static Set<String> regionLocations(final String region) {
		final SortedSet<String> result = new TreeSet<String>();
		final String[] timeZones = TimeZone.getAvailableIDs();
		for (int i = 0; i < timeZones.length; i++) {
			final String[] parts = timeZones[i].split("/");
			if (parts.length == 2 && parts[0].equals(region)) {
				final String location = parts[1].replaceAll("_", " ");
				result.add(location);
			}
		}
		return result;
	}

}
