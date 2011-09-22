package controllers.jqueryui;

import java.util.Set;

/**
 * Controller that provides server-side data for a jQuery UI Accordion component.
 */
public class Accordion extends JQueryUI {

   /**
    * Renders an HTML representation of the contents of the accordion section
    * specified by the parameter.
    *
    * @param header The header name, which specifies which content to load.
    */
   public static void region(final String header) {
      final Set<String> locations = TestData.regionLocations(header);
      render(locations);
   }
}
