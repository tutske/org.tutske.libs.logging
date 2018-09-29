package org.tutske.logging;

import org.junit.Test;
import org.slf4j.Logger;


public class ConfigurationOrderTest extends AbstractLogTest {

	@Test
	public void it_should_receive_events_when_attaching_after_logger_creation () {
		Logger logger = getLogger ("test");

		configuration.register (fn);

		logger.error ("This is an error");

		verifyEvent ();
	}

}
