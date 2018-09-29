package org.tutske.logging;

import static org.mockito.Mockito.*;

import org.mockito.ArgumentCaptor;
import org.slf4j.Logger;
import org.tutske.logging.impl.CustomLoggerFactory;

import java.util.List;


public abstract class AbstractLogTest {

	LogConfiguration configuration = LogConfiguration.create ();
	LogFunction fn = mock (LogFunction.class);

	protected Logger getLogger (String name) {
		return new CustomLoggerFactory ().getLogger (configuration, name);
	}

	protected LogEvent verifyEvent () {
		ArgumentCaptor<LogEvent> captor = ArgumentCaptor.forClass (LogEvent.class);
		verify (fn).accept (captor.capture ());
		return captor.getValue ();
	}

	protected List<LogEvent> verifyEvents (int amount) {
		ArgumentCaptor<LogEvent> captor = ArgumentCaptor.forClass (LogEvent.class);
		verify (fn, times (amount)).accept (captor.capture ());
		return captor.getAllValues ();
	}

}
