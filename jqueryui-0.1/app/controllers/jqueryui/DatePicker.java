package controllers.jqueryui;

import play.i18n.Lang;
import play.mvc.Controller;
import play.mvc.Router;

import java.util.Set;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;

/**
 *
 */
public class DatePicker extends Controller {

	public static void changeLanguage(final String lang) {
		Lang.change(lang);
		redirect(Router.reverse("jqueryui.Demo.index") + "#datepicker");
	}

}
