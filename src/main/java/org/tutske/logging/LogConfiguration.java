package org.tutske.logging;


import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.tutske.logging.impl.Configuration;

import java.util.Set;


public interface LogConfiguration {

	public static LogConfiguration configure () {
		return Configuration.INSTANCE;
	}

	public static LogConfiguration create () {
		return new Configuration ();
	}

	LogConfiguration register (LogFunction fn);
	LogConfiguration register (String prefix, LogFunction fn);
	LogConfiguration register (Marker marker, String prefix, LogFunction fn);
	LogConfiguration register (Set<Marker> marker, String prefix, LogFunction fn);
	LogConfiguration register (Level level, String prefix, LogFunction fn);

	LogConfiguration capture (LogFunction fn);
	LogConfiguration capture (String prefix, LogFunction fn);
	LogConfiguration capture (Marker marker, String prefix, LogFunction fn);
	LogConfiguration capture (Set<Marker> marker, String prefix, LogFunction fn);
	LogConfiguration capture (Level level, String prefix, LogFunction fn);

}
