package controllers.jqueryui;

import java.util.*;

/**
 * Test data for the module examples, based on data from {@link java.util.TimeZone}.
 */
public class TestData {

	/**
	 * Autocomplete test data - a list of location names, mostly cities.
	 */
	static Set<String> locations() {
		return regionLocations(null);
	}

	/**
	* Accordion test data - a list of location names for the given region.
	*
	* @param region A {@link java.util.TimeZone} ID prefix.
	* @return A sorted collection of location names.
	*/
	static Set<String> regionLocations(final String region) {
		final SortedSet<String> result = new TreeSet<String>();
		final String[] timeZones = TimeZone.getAvailableIDs();
		for (int i = 0; i < timeZones.length; i++) {
			final String[] parts = timeZones[i].split("/");
			boolean regionMatches = parts[0].equals(region);
			if (parts.length == 2 && (region == null || regionMatches)) {
				final String location = parts[1].replaceAll("_", " ");
				result.add(location);
			}
		}
		return result;
	}

	static List<TimeZone> timeZones() {
		final List<TimeZone> timeZones = new ArrayList<TimeZone>();
		for(String id : TimeZone.getAvailableIDs()) {
			if (id.contains("/") && !id.startsWith("Etc/")) {
				timeZones.add(TimeZone.getTimeZone(id));
			}
		}
		return timeZones;
	}
}
