package org.tutske.logging;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.event.Level;

import java.util.HashSet;
import java.util.Set;


public class LogFilterTest extends AbstractLogTest {

	@Test
	public void it_should_be_notified_of_messages_with_the_right_prefix () {
		configuration.register ("sub.pack.age", fn);
		getLogger ("sub.pack.age.ClassName").error ("This is the message");
		assertThat (verifyEvent ().name, is ("sub.pack.age.ClassName"));
	}

	@Test
	public void it_should_not_be_notified_when_the_prefix_does_not_match () {
		configuration.register ("sub.pack.age", fn);
		getLogger ("sub.pack.name.ClassName").error ("This is the message");
		verifyEvents (0);
	}

	@Test
	public void it_should_be_notified_when_the_marker_matches () {
		Marker marker = MarkerFactory.getMarker ("ROOT");

		configuration.register (marker, "", fn);
		getLogger ("sub.pack.name.ClassName").error (marker, "This is the message");

		assertThat (verifyEvent ().marker, is (marker));
	}

	@Test
	public void it_should_not_notify_when_the_marker_does_not_match () {
		Marker root = MarkerFactory.getMarker ("ROOT");
		Marker other = MarkerFactory.getMarker ("OTHER");

		configuration.register (root, "", fn);
		getLogger ("sub.pack.name.ClassName").error (other, "This is the message");

		verifyEvents (0);
	}

	@Test
	public void it_should_notify_when_the_marker_contains_the_log_marker () {
		Marker root = MarkerFactory.getMarker ("ROOT");
		Marker sub = MarkerFactory.getMarker ("OTHER");
		sub.add (root);

		configuration.register (root, "", fn);
		getLogger ("sub.pack.name.ClassName").error (sub, "This is the message");

		assertThat (verifyEvent ().marker, is (sub));
	}

	@Test
	public void it_should_notify_multiple_markers () {
		Marker one = MarkerFactory.getMarker ("ONE");
		Marker two = MarkerFactory.getMarker ("TWO");
		Marker three = MarkerFactory.getMarker ("THREE");

		configuration.register (set (one, two), "", fn);

		getLogger ("sub.pack.name.ClassName").error (one, "This is the message");
		getLogger ("sub.pack.name.ClassName").error (two, "This is the message");
		getLogger ("sub.pack.name.ClassName").error (three, "This is the message");

		verifyEvents (2);
	}

	@Test
	public void it_should_notify_of_log_level_with_higher_value () {
		configuration.register (Level.INFO, "", fn);
		getLogger ("sub.pack.name.ClassName").error ("This is the message");
		assertThat (verifyEvent ().level, is (Level.ERROR));
	}

	@Test
	public void it_should_not_notify_of_log_level_with_lower_value () {
		configuration.register (Level.INFO, "", fn);
		getLogger ("sub.pack.name.ClassName").debug ("This is the message");
		verifyEvents (0);
	}

	@Test
	public void it_should_notify_of_log_level_exact_matching_value () {
		configuration.register (Level.WARN, "", fn);
		getLogger ("sub.pack.name.ClassName").warn("This is the message");
		assertThat (verifyEvent ().level, is (Level.WARN));
	}

	private <T> Set<T> set (T ... ts) {
		Set<T> result = new HashSet<> ();
		for ( T t : ts ) { result.add (t); }
		return result;
	}

}
