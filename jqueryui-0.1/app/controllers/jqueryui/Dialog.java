package controllers.jqueryui;

import org.codehaus.groovy.control.StaticImportVisitor;

import java.util.List;
import java.util.TimeZone;

/**
 * Dialog example support.
 */
public class Dialog extends JQueryUI {

	public static void timeZones() {
		final List<TimeZone> timeZones = TestData.timeZones();
		render(timeZones);
	}
}
