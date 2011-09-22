package controllers.jqueryui;

import play.jobs.Job;
import play.libs.F;
import play.mvc.Http;
import play.mvc.WebSocketController;

import static play.libs.F.Matcher.ClassOf;

/**
 * Progressbar example.
 */
public class Progressbar extends JQueryUI {

	/**
	 * Web socket controller for use by the progress bar.
	 */
	public static class ProgressSocket extends WebSocketController {

		public static void progress() {

			final Process process = new Process();
			final F.EventStream<Integer> progress = process.percentComplete;
			process.now();

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


	/**
	 * Sample job that publishes progress as an event stream.
	 */
	public static class Process extends Job {

		/** Event stream for events that report job completion percentage. */
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

}
