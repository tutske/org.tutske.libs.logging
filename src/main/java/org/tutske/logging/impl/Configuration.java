package org.tutske.logging.impl;

import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.tutske.logging.LogConfiguration;
import org.tutske.logging.LogEvent;
import org.tutske.logging.LogFunction;

import java.util.Set;


public class Configuration implements LogConfiguration{

	public static final LogConfiguration INSTANCE = new Configuration ();

	private ConfigurationData data = new ConfigurationData ();
	private LogFunction fn = new RootLogger (this);

	public Configuration register (LogFunction fn) {
		return register (new LogFnEntry (null, null, null, fn));
	}

	public Configuration register (String prefix, LogFunction fn) {
		return register (new LogFnEntry (null, prefix, null, fn));
	}

	public Configuration register (Marker marker, String prefix, LogFunction fn) {
		return register (new LogFnEntry (null, prefix, marker, fn));
	}

	public Configuration register (Set<Marker> markers, String prefix, LogFunction fn) {
		for ( Marker marker : markers ) { register (new LogFnEntry (null, prefix, marker, fn)); }
		return this;
	}

	public Configuration register (Level level, String prefix, LogFunction fn) {
		return register (new LogFnEntry (level, prefix, null, fn));
	}

	protected LogFunction getLogFn () {
		return fn;
	}

	private Configuration register (LogFnEntry entry) {
		synchronized ( data ) {
			this.data.fns.add (entry);
		}
		return this;
	}

	private static class RootLogger implements LogFunction {
		private final Configuration configuration;

		private RootLogger (Configuration configuration) {
			this.configuration = configuration;
		}

		@Override public void riskyAccept (LogEvent event) {
			for ( LogFnEntry entry : configuration.data.fns ) {
				if ( entry.level != null && event.level.toInt () < entry.level.toInt () ) { continue; }
				if ( entry.marker != null && event.marker == null ) { continue; }
				if ( entry.marker != null && ! event.marker.contains (entry.marker) ) { continue; }
				if ( entry.prefix == null || event.name.startsWith (entry.prefix) ) {
					entry.fn.accept (event);
				}
			}
		}
	}

}
