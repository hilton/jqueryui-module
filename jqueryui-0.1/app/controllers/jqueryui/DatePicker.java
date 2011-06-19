package controllers.jqueryui;

import play.classloading.enhancers.ControllersEnhancer;
import play.i18n.Lang;
import play.mvc.Controller;
import play.mvc.Router;

import java.util.Set;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;

/**
 * Date picker example support.
 */
public class DatePicker extends JQueryUI {

	/**
	 * Set's the server-side language to the given language.
	 *
	 * @param lang Two-letter language code.
	 */
	public static void changeLanguage(final String lang) {
		Lang.change(lang);
		redirect(request.controller + ".index");
	}

}
