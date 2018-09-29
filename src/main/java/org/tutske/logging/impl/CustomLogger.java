package org.tutske.logging.impl;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.tutske.logging.LogEvent;
import org.tutske.logging.LogFunction;


public class CustomLogger implements Logger {

	private final LogFunction handle;
	private final String name;

	public CustomLogger (LogFunction handle, String name) {
		this.handle = handle;
		this.name = name;
	}

	@Override
	public String getName () {
		return name;
	}

	@Override public boolean isTraceEnabled () { return true; }
	@Override public boolean isTraceEnabled (Marker marker) { return true; }

	@Override public void trace (String msg) { handle.accept (LogEvent.convert (name, Level.TRACE, null, msg)); }
	@Override public void trace (String fmt, Object arg) { handle.accept (LogEvent.convert (name, Level.TRACE, null, fmt, arg)); }
	@Override public void trace (String fmt, Object arg1, Object arg2) {  handle.accept (LogEvent.convert (name, Level.TRACE, null, fmt, arg1, arg2)); }
	@Override public void trace (String fmt, Object ... arguments) { handle.accept (LogEvent.convert (name, Level.TRACE, null, fmt, arguments)); }
	@Override public void trace (String msg, Throwable t) { handle.accept (LogEvent.convert (name, Level.TRACE, null, msg, t)); }

	@Override public void trace (Marker marker, String msg) { handle.accept (LogEvent.convert (name, Level.TRACE, marker, msg)); }
	@Override public void trace (Marker marker, String fmt, Object arg) { handle.accept (LogEvent.convert (name, Level.TRACE, marker, fmt, arg)); }
	@Override public void trace (Marker marker, String fmt, Object arg1, Object arg2) {  handle.accept (LogEvent.convert (name, Level.TRACE, marker, fmt, arg1, arg2)); }
	@Override public void trace (Marker marker, String fmt, Object ... arguments) { handle.accept (LogEvent.convert (name, Level.TRACE, marker, fmt, arguments)); }
	@Override public void trace (Marker marker, String msg, Throwable t) { handle.accept (LogEvent.convert (name, Level.TRACE, marker, msg, t)); }

	@Override public boolean isDebugEnabled () { return true; }
	@Override public boolean isDebugEnabled (Marker marker) { return true; }

	@Override public void debug (String msg) { handle.accept (LogEvent.convert (name, Level.DEBUG, null, msg)); }
	@Override public void debug (String fmt, Object arg) { handle.accept (LogEvent.convert (name, Level.DEBUG, null, fmt, arg)); }
	@Override public void debug (String fmt, Object arg1, Object arg2) {  handle.accept (LogEvent.convert (name, Level.DEBUG, null, fmt, arg1, arg2)); }
	@Override public void debug (String fmt, Object ... arguments) { handle.accept (LogEvent.convert (name, Level.DEBUG, null, fmt, arguments)); }
	@Override public void debug (String msg, Throwable t) { handle.accept (LogEvent.convert (name, Level.DEBUG, null, msg, t)); }

	@Override public void debug (Marker marker, String msg) { handle.accept (LogEvent.convert (name, Level.DEBUG, marker, msg)); }
	@Override public void debug (Marker marker, String fmt, Object arg) { handle.accept (LogEvent.convert (name, Level.DEBUG, marker, fmt, arg)); }
	@Override public void debug (Marker marker, String fmt, Object arg1, Object arg2) {  handle.accept (LogEvent.convert (name, Level.DEBUG, marker, fmt, arg1, arg2)); }
	@Override public void debug (Marker marker, String fmt, Object ... arguments) { handle.accept (LogEvent.convert (name, Level.DEBUG, marker, fmt, arguments)); }
	@Override public void debug (Marker marker, String msg, Throwable t) { handle.accept (LogEvent.convert (name, Level.DEBUG, marker, msg, t)); }

	@Override public boolean isInfoEnabled () { return true; }
	@Override public boolean isInfoEnabled (Marker marker) { return true; }

	@Override public void info (String msg) { handle.accept (LogEvent.convert (name, Level.INFO, null, msg)); }
	@Override public void info (String fmt, Object arg) { handle.accept (LogEvent.convert (name, Level.INFO, null, fmt, arg)); }
	@Override public void info (String fmt, Object arg1, Object arg2) {  handle.accept (LogEvent.convert (name, Level.INFO, null, fmt, arg1, arg2)); }
	@Override public void info (String fmt, Object ... arguments) { handle.accept (LogEvent.convert (name, Level.INFO, null, fmt, arguments)); }
	@Override public void info (String msg, Throwable t) { handle.accept (LogEvent.convert (name, Level.INFO, null, msg, t)); }

	@Override public void info (Marker marker, String msg) { handle.accept (LogEvent.convert (name, Level.INFO, marker, msg)); }
	@Override public void info (Marker marker, String fmt, Object arg) { handle.accept (LogEvent.convert (name, Level.INFO, marker, fmt, arg)); }
	@Override public void info (Marker marker, String fmt, Object arg1, Object arg2) {  handle.accept (LogEvent.convert (name, Level.INFO, marker, fmt, arg1, arg2)); }
	@Override public void info (Marker marker, String fmt, Object ... arguments) { handle.accept (LogEvent.convert (name, Level.INFO, marker, fmt, arguments)); }
	@Override public void info (Marker marker, String msg, Throwable t) { handle.accept (LogEvent.convert (name, Level.INFO, marker, msg, t)); }

	@Override public boolean isWarnEnabled () { return false; }
	@Override public boolean isWarnEnabled (Marker marker) { return false; }

	@Override public void warn (String msg) { handle.accept (LogEvent.convert (name, Level.WARN, null, msg)); }
	@Override public void warn (String fmt, Object arg) { handle.accept (LogEvent.convert (name, Level.WARN, null, fmt, arg)); }
	@Override public void warn (String fmt, Object arg1, Object arg2) {  handle.accept (LogEvent.convert (name, Level.WARN, null, fmt, arg1, arg2)); }
	@Override public void warn (String fmt, Object ... arguments) { handle.accept (LogEvent.convert (name, Level.WARN, null, fmt, arguments)); }
	@Override public void warn (String msg, Throwable t) { handle.accept (LogEvent.convert (name, Level.WARN, null, msg, t)); }

	@Override public void warn (Marker marker, String msg) { handle.accept (LogEvent.convert (name, Level.WARN, marker, msg)); }
	@Override public void warn (Marker marker, String fmt, Object arg) { handle.accept (LogEvent.convert (name, Level.WARN, marker, fmt, arg)); }
	@Override public void warn (Marker marker, String fmt, Object arg1, Object arg2) {  handle.accept (LogEvent.convert (name, Level.WARN, marker, fmt, arg1, arg2)); }
	@Override public void warn (Marker marker, String fmt, Object ... arguments) { handle.accept (LogEvent.convert (name, Level.WARN, marker, fmt, arguments)); }
	@Override public void warn (Marker marker, String msg, Throwable t) { handle.accept (LogEvent.convert (name, Level.WARN, marker, msg, t)); }

	@Override public boolean isErrorEnabled () { return true; }
	@Override public boolean isErrorEnabled (Marker marker) { return true; }

	@Override public void error (String msg) { handle.accept (LogEvent.convert (name, Level.ERROR, null, msg)); }
	@Override public void error (String fmt, Object arg) { handle.accept (LogEvent.convert (name, Level.ERROR, null, fmt, arg)); }
	@Override public void error (String fmt, Object arg1, Object arg2) {  handle.accept (LogEvent.convert (name, Level.ERROR, null, fmt, arg1, arg2)); }
	@Override public void error (String fmt, Object ... arguments) { handle.accept (LogEvent.convert (name, Level.ERROR, null, fmt, arguments)); }
	@Override public void error (String msg, Throwable t) { handle.accept (LogEvent.convert (name, Level.ERROR, null, msg, t)); }

	@Override public void error (Marker marker, String msg) { handle.accept (LogEvent.convert (name, Level.ERROR, marker, msg)); }
	@Override public void error (Marker marker, String fmt, Object arg) { handle.accept (LogEvent.convert (name, Level.ERROR, marker, fmt, arg)); }
	@Override public void error (Marker marker, String fmt, Object arg1, Object arg2) {  handle.accept (LogEvent.convert (name, Level.ERROR, marker, fmt, arg1, arg2)); }
	@Override public void error (Marker marker, String fmt, Object ... arguments) { handle.accept (LogEvent.convert (name, Level.ERROR, marker, fmt, arguments)); }
	@Override public void error (Marker marker, String msg, Throwable t) { handle.accept (LogEvent.convert (name, Level.ERROR, marker, msg, t)); }

}
