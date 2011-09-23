package controllers.jqueryui;

import models.jqueryui.Process;

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
      Process.registry.put(processId, process);
      renderTemplate("jqueryui/Progressbar/index.html", processId);
   }
}
