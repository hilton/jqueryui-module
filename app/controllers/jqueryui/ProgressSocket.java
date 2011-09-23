package controllers.jqueryui;

import models.jqueryui.Process;
import play.libs.F;
import play.mvc.Http;
import play.mvc.WebSocketController;

import static play.libs.F.Matcher.ClassOf;

/**
 * Web socket controller for use by the progress bar.
 */
public class ProgressSocket extends WebSocketController {

   public static void progress(final String processId) {

      final Process process = Process.registry.get(processId);
      final F.EventStream<Integer> progress = process.percentComplete;

      // Loop while the socket is open
      while (inbound.isOpen()) {

         // Wait for either an inbound socket event or a process progress event.
         F.Either<Http.WebSocketEvent, Integer> e = await(F.Promise.waitEither(
            inbound.nextEvent(),
            progress.nextEvent()
         ));

         // Case: The socket has been closed
         for (Http.WebSocketClose closed : Http.WebSocketEvent.SocketClosed.match(e._1)) {
            disconnect();
         }

         // Case: percentComplete published - send the value to the client.
         for (Integer percentComplete : ClassOf(Integer.class).match(e._2)) {
            outbound.send(percentComplete.toString());
            if (percentComplete >= 100) {
               disconnect();
            }
         }
      }
   }

}
