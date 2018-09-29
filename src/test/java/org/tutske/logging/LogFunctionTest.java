package org.tutske.logging;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.event.Level;


public class LogFunctionTest extends AbstractLogTest {

	@Before
	public void link_fn_to_logging () {
		configuration.register (fn);
	}

	@Test
	public void it_should_notify_log_listeners () {
		getLogger ("test").error ("This is an error");
		verifyEvent ();
	}

	@Test
	public void it_should_have_the_right_message () {
		getLogger ("test").error ("This is an error");
		assertThat (verifyEvent ().msg, is ("This is an error"));
	}

	@Test
	public void it_should_have_a_log_level_of_error () {
		getLogger ("test").error ("This is an error");
		assertThat (verifyEvent ().level, is (Level.ERROR));
	}

	@Test
	public void it_should_not_have_an_exception_when_not_logged () {
		getLogger ("test").error ("This is an error");
		assertThat (verifyEvent ().throwable, nullValue ());
	}

	@Test
	public void it_should_have_an_exception () {
		getLogger ("test").error ("This is an error", new Exception ());
		assertThat (verifyEvent ().throwable, notNullValue ());
	}

	@Test
	public void it_should_have_a_provided_context () {
		getLogger ("test").error ("This is an error", new Context () {{
			add ("key", "value");
		}});
		assertThat (verifyEvent ().context, notNullValue ());
	}

	@Test
	public void it_should_not_have_a_context_if_not_provided_one () {
		getLogger ("test").error ("This is an error", new Exception ());
		assertThat (verifyEvent ().context, nullValue ());
	}

	@Test
	public void it_should_have_both_a_context_and_an_exception () {
		getLogger ("test").error ("This is an error", new Exception (), new Context () {{
			add ("key", "value");
		}});

		assertThat (verifyEvent ().throwable, notNullValue ());
		assertThat (verifyEvent ().context, notNullValue ());
	}

	@Test
	public void it_should_have_a_context_and_an_exception_when_they_are_in_reverse_order () {
		getLogger ("test").error ("This is an error", new Context () {{
			add ("key", "value");
		}}, new Exception ());

		assertThat (verifyEvent ().throwable, notNullValue ());
		assertThat (verifyEvent ().context, notNullValue ());
	}

	@Test
	public void it_should_format_the_message () {
		getLogger ("test").error ("{}: {}", "key", "value");
		assertThat (verifyEvent ().msg, is ("key: value"));
	}

	@Test
	public void it_should_format_a_single_argument () {
		getLogger ("test").error ("Failed for {}.", "service");
		assertThat (verifyEvent ().msg, is ("Failed for service."));
	}

	@Test
	public void it_should_format_and_have_a_context () {
		getLogger ("test").error ("{}: {}", "key", "value", new Context () {{
			add ("key", "value");
		}});
		assertThat (verifyEvent ().msg, is ("key: value"));
		assertThat (verifyEvent ().context, notNullValue ());
	}

	@Test
	public void it_should_have_the_name_of_the_logger () {
		getLogger ("CUSTOM").error ("This is an error");
		assertThat (verifyEvent ().name, is ("CUSTOM"));
	}

}
