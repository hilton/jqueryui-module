package controllers.jqueryui;

import models.jqueryui.Process;
import play.cache.Cache;

/**
 * Progressbar example.
 */
public class Progressbar extends JQueryUI {

   /**
    * Start a job, cache it and re-render the page
    */
   public static void startJob() {
      final Process process = new Process();
      process.now();
      final String processId = process.id;
      Cache.set(processId, process);
      renderTemplate("jqueryui/Progressbar/index.html", processId);
   }
}
