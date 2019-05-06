package org.tutske.logging;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.event.Level;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiConsumer;


@RunWith (Parameterized.class)
public class LogSanityTest extends AbstractLogTest {

	private static Object log (BiConsumer<Logger, Marker> consumer) {
		return consumer;
	}

	@Parameterized.Parameters (name = "m: {0}, l: {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {

			/* -- errors -- */

			{
				MarkerFactory.getMarker ("Test"), Level.ERROR, "This is an message",
				log ((logger, marker) -> logger.error (marker, "This is an message"))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.ERROR, "This is an message",
				log ((logger, marker) -> logger.error (
					marker, "This is an message", new Exception ()
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.ERROR, "This is an message",
				log ((logger, marker) -> logger.error (
					marker, "This is an message", new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.ERROR, "This is an message",
				log ((logger, marker) -> logger.error (
					marker, "This is an message", new Exception (), new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.ERROR, "This is an message",
				log ((logger, marker) -> logger.error (
					marker, "This is an message", new Exception (), new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				null, Level.ERROR, "One, Two, Three",
				log ((logger, marker) -> logger.error (
					"{}, {}, {}", "One", "Two", "Three"
				))
			},

			{
				null, Level.ERROR, "One, Two",
				log ((logger, marker) -> logger.error (
					"{}, {}", "One", "Two"
				))
			},

			{
				null, Level.ERROR, "One",
				log ((logger, marker) -> logger.error (
					"{}", "One"
				))
			},

			{
				null, Level.ERROR, "One",
				log ((logger, marker) -> logger.error (
					"{}", "One", "Two"
				))
			},

			{
				null, Level.ERROR, "One, Two, {}",
				log ((logger, marker) -> logger.error (
					"{}, {}, {}", "One", "Two"
				))
			},

			/* -- warnings -- */

			{
				MarkerFactory.getMarker ("Test"), Level.WARN, "This is an message",
				log ((logger, marker) -> logger.warn (marker, "This is an message"))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.WARN, "This is an message",
				log ((logger, marker) -> logger.warn (
					marker, "This is an message", new Exception ()
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.WARN, "This is an message",
				log ((logger, marker) -> logger.warn (
					marker, "This is an message", new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.WARN, "This is an message",
				log ((logger, marker) -> logger.warn (
					marker, "This is an message", new Exception (), new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.WARN, "This is an message",
				log ((logger, marker) -> logger.warn (
					marker, "This is an message", new Exception (), new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				null, Level.WARN, "One, Two, Three",
				log ((logger, marker) -> logger.warn (
					"{}, {}, {}", "One", "Two", "Three"
				))
			},

			{
				null, Level.WARN, "One, Two",
				log ((logger, marker) -> logger.warn (
					"{}, {}", "One", "Two"
				))
			},

			{
				null, Level.WARN, "One",
				log ((logger, marker) -> logger.warn (
					"{}", "One"
				))
			},

			{
				null, Level.WARN, "One",
				log ((logger, marker) -> logger.warn (
					"{}", "One", "Two"
				))
			},

			{
				null, Level.WARN, "One, Two, {}",
				log ((logger, marker) -> logger.warn (
					"{}, {}, {}", "One", "Two"
				))
			},

			/* -- info -- */

			{
				MarkerFactory.getMarker ("Test"), Level.INFO, "This is an message",
				log ((logger, marker) -> logger.info (marker, "This is an message"))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.INFO, "This is an message",
				log ((logger, marker) -> logger.info (
					marker, "This is an message", new Exception ()
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.INFO, "This is an message",
				log ((logger, marker) -> logger.info (
					marker, "This is an message", new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.INFO, "This is an message",
				log ((logger, marker) -> logger.info (
					marker, "This is an message", new Exception (), new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.INFO, "This is an message",
				log ((logger, marker) -> logger.info (
					marker, "This is an message", new Exception (), new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				null, Level.INFO, "One, Two, Three",
				log ((logger, marker) -> logger.info (
					"{}, {}, {}", "One", "Two", "Three"
				))
			},

			{
				null, Level.INFO, "One, Two",
				log ((logger, marker) -> logger.info (
					"{}, {}", "One", "Two"
				))
			},

			{
				null, Level.INFO, "One",
				log ((logger, marker) -> logger.info (
					"{}", "One"
				))
			},

			{
				null, Level.INFO, "One",
				log ((logger, marker) -> logger.info (
					"{}", "One", "Two"
				))
			},

			{
				null, Level.INFO, "One, Two, {}",
				log ((logger, marker) -> logger.info (
					"{}, {}, {}", "One", "Two"
				))
			},

			/* -- debug -- */

			{
				MarkerFactory.getMarker ("Test"), Level.DEBUG, "This is an message",
				log ((logger, marker) -> logger.debug (marker, "This is an message"))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.DEBUG, "This is an message",
				log ((logger, marker) -> logger.debug (
					marker, "This is an message", new Exception ()
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.DEBUG, "This is an message",
				log ((logger, marker) -> logger.debug (
					marker, "This is an message", new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.DEBUG, "This is an message",
				log ((logger, marker) -> logger.debug (
					marker, "This is an message", new Exception (), new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.DEBUG, "This is an message",
				log ((logger, marker) -> logger.debug (
					marker, "This is an message", new Exception (), new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				null, Level.DEBUG, "One, Two, Three",
				log ((logger, marker) -> logger.debug (
					"{}, {}, {}", "One", "Two", "Three"
				))
			},

			{
				null, Level.DEBUG, "One, Two",
				log ((logger, marker) -> logger.debug (
					"{}, {}", "One", "Two"
				))
			},

			{
				null, Level.DEBUG, "One",
				log ((logger, marker) -> logger.debug (
					"{}", "One"
				))
			},

			{
				null, Level.DEBUG, "One",
				log ((logger, marker) -> logger.debug (
					"{}", "One", "Two"
				))
			},

			{
				null, Level.DEBUG, "One, Two, {}",
				log ((logger, marker) -> logger.debug (
					"{}, {}, {}", "One", "Two"
				))
			},

			/* -- trace -- */

			{
				MarkerFactory.getMarker ("Test"), Level.TRACE, "This is an message",
				log ((logger, marker) -> logger.trace (marker, "This is an message"))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.TRACE, "This is an message",
				log ((logger, marker) -> logger.trace (
					marker, "This is an message", new Exception ()
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.TRACE, "This is an message",
				log ((logger, marker) -> logger.trace (
					marker, "This is an message", new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.TRACE, "This is an message",
				log ((logger, marker) -> logger.trace (
					marker, "This is an message", new Exception (), new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				MarkerFactory.getMarker ("Test"), Level.TRACE, "This is an message",
				log ((logger, marker) -> logger.trace (
					marker, "This is an message", new Exception (), new Context () {{
						add ("key", "value");
					}}
				))
			},

			{
				null, Level.TRACE, "One, Two, Three",
				log ((logger, marker) -> logger.trace (
					"{}, {}, {}", "One", "Two", "Three"
				))
			},

			{
				null, Level.TRACE, "One, Two",
				log ((logger, marker) -> logger.trace (
					"{}, {}", "One", "Two"
				))
			},

			{
				null, Level.TRACE, "One",
				log ((logger, marker) -> logger.trace (
					"{}", "One"
				))
			},

			{
				null, Level.TRACE, "One",
				log ((logger, marker) -> logger.trace (
					"{}", "One", "Two"
				))
			},

			{
				null, Level.TRACE, "One, Two, {}",
				log ((logger, marker) -> logger.trace (
					"{}, {}, {}", "One", "Two"
				))
			},

		});
	}


	@Parameterized.Parameter (0) public Marker marker;
	@Parameterized.Parameter (1) public Level level;
	@Parameterized.Parameter (2) public String msg;
	@Parameterized.Parameter (3) public BiConsumer<Logger, Marker> consumer;

	@Before public void link_fn_to_logging () {
		configuration.register (fn);
	}

	@Test public void it_should_contain_the_marker () {
		assertThat (log ().marker, is (marker));
	}

	@Test public void it_should_have_the_right_log_level () {
		assertThat (log ().level, is (level));
	}

	@Test public void it_should_have_the_right_message () {
		assertThat (log ().msg, is (msg));
	}

	private LogEvent log () {
		consumer.accept (getLogger ("TEST"), marker);
		return verifyEvent ();
	}

}
