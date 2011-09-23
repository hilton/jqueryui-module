package models.jqueryui;

import play.jobs.Job;
import play.libs.Codec;
import play.libs.F;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Example job that publishes progress as an event stream.
 */
public class Process extends Job implements Serializable {

   public static Map<String, Process> registry = new HashMap<String, Process>();

   public String id = Codec.UUID();

   /**
    * Event stream for events that report job completion percentage.
    */
   public F.EventStream<Integer> percentComplete = new F.EventStream<Integer>();

   public void doJob() {
      // Report completion percentage every 100 ms.
      for (int i = 1; i <= 50; i++) {
         try {
            Thread.sleep(100l);
            final int percent = i * 2;
            this.percentComplete.publish(percent);
         } catch (InterruptedException e) {
            // ignore
         }
      }
   }
}
