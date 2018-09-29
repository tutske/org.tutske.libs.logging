package org.tutske.logging.impl;

import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.tutske.logging.LogFunction;


public class LogFnEntry {

	final Level level;
	final String prefix;
	final Marker marker;
	final LogFunction fn;

	public LogFnEntry (Level level, String prefix, Marker marker, LogFunction fn) {
		this.level = level;
		this.prefix = prefix != null && prefix.isEmpty () ? null : prefix;
		this.marker = marker;
		this.fn = fn;
	}

}
