package controllers.jqueryui;

import play.i18n.Lang;

/**
 * Date picker example support.
 */
public class DatePicker extends JQueryUI {

	/**
	 * Sets the server-side language to the given language.
	 *
	 * @param lang Two-letter language code.
	 */
	public static void changeLanguage(final String lang) {
		Lang.change(lang);
		redirect(request.controller + ".index");
	}

}
