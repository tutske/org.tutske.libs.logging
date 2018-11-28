package org.tutske.logging;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.event.Level;


public class MultiLogFnFilterTest extends AbstractLogTest {

	LogFunction misses = mock (LogFunction.class);
	LogFunction capture = mock (LogFunction.class);

	@Test
	public void it_should_only_call_one_registered_log_function () {
		configuration.register ("sub.pack.age", fn);
		configuration.register ("sub.pack", fn);

		getLogger ("sub.pack.age.ClassName").error ("This is the message");

		verifyEvents (1);
	}

	@Test
	public void it_should_call_all_the_caputuring_log_functions () {
		configuration.capture ("sub.pack.age", fn);
		configuration.capture ("sub.pack", fn);

		getLogger ("sub.pack.age.ClassName").error ("This is the message");

		verifyEvents (2);
	}

	@Test
	public void it_should_call_the_most_specific_prefix () {
		configuration.register ("sub.pack", misses);
		configuration.register ("sub.pack.age", fn);

		getLogger ("sub.pack.age.ClassName").error ("This is the message");

		verifyEvents (misses, 0);
		verifyEvents (1);
	}

	@Test
	public void it_should_call_the_highest_log_level () {
		configuration.register (Level.DEBUG, "sub.pack.age", misses);
		configuration.register (Level.WARN, "sub.pack.age", fn);

		getLogger ("sub.pack.age.ClassName").error ("This is the message");

		verifyEvents (misses, 0);
		verifyEvents (1);
	}

	@Test
	public void it_should_prefer_specific_markers () {
		configuration.register (misses);
		configuration.register (MarkerFactory.getMarker ("mark"), "", fn);

		getLogger ("sub.pack.age.ClassName").error (MarkerFactory.getMarker ("mark"), "This is the message");

		verifyEvents (misses, 0);
		verifyEvents (1);
	}

	@Test
	public void it_should_still_call_the_capture_handlers () {
		configuration.capture (capture);
		configuration.register (fn);
		configuration.capture (capture);

		getLogger ("sub.pack.age.ClassName").error ("This is the message");

		verifyEvents (fn, 1);
		verifyEvents (capture, 2);
	}

	@Test
	public void it_should_still_call_the_capture_handler_with_a_prefix () {
		configuration.capture ("sub.pack", capture);
		configuration.register (misses);
		configuration.register ("sub.pack", fn);
		configuration.capture ("sub.pack.age", capture);

		getLogger ("sub.pack.age.ClassName").error ("This is the message");

		verifyEvents (misses, 0);
		verifyEvents (fn, 1);
		verifyEvents (capture, 2);
	}

	@Test
	public void it_should_still_call_the_capture_handler_with_a_marker () {
		Marker mark = MarkerFactory.getMarker ("mark");

		configuration.capture (mark, "", capture);
		configuration.register ("", misses);
		configuration.register (mark, "", fn);
		configuration.capture (mark, "", capture);

		getLogger ("sub.pack.age.ClassName").error (MarkerFactory.getMarker ("mark"), "This is the message");

		verifyEvents (misses, 0);
		verifyEvents (fn, 1);
		verifyEvents (capture, 2);
	}

	@Test
	public void it_should_still_call_the_capture_handler_with_a_levels () {

		configuration.capture (Level.WARN, "", capture);
		configuration.register ("", misses);
		configuration.register (Level.WARN, "", fn);
		configuration.capture (Level.WARN, "", capture);

		getLogger ("sub.pack.age.ClassName").error ("This is the message");

		verifyEvents (misses, 0);
		verifyEvents (fn, 1);
		verifyEvents (capture, 2);
	}

}
