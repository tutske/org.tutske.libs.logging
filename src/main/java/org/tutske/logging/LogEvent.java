package org.tutske.logging;

import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.helpers.MessageFormatter;

import java.util.Collections;
import java.util.Date;
import java.util.Map;


public class LogEvent {

	public final Date timestamp;
	public final String name;
	public final Level level;
	public final Marker marker;
	public final String msg;
	public final Throwable throwable;
	public final Map<String, Object> mdc;
	public final Context context;

	public LogEvent (String name, Level level, Marker marker, String msg, Throwable throwable, Map<String, Object> mdc, Context context) {
		this.name = name;
		this.timestamp = new Date ();
		this.level = level;
		this.marker = marker;
		this.msg = msg;
		this.throwable = throwable;
		this.mdc = mdc == null ? Collections.emptyMap (): mdc;
		this.context = context;
	}

	public static LogEvent convert (String name, Level level, Marker marker, String msg) {
		return new LogEvent (name, level, marker, msg, null,  getMdc (), null);
	}

	public static LogEvent convert (String name, Level level, Marker marker, String fmt, Object arg) {
		Context context = arg instanceof Context ? (Context) arg : null;
		Throwable throwable = arg instanceof Throwable ? (Throwable) arg : null;

		if ( context == null && throwable == null ) {
			fmt = MessageFormatter.format (fmt, arg).getMessage ();
		}

		return new LogEvent (name, level, marker, fmt, throwable, getMdc (), context);
	}

	public static LogEvent convert (String name, Level level, Marker marker, String fmt, Object arg1, Object arg2) {
		Context context = pickContext (arg1, arg2);
		Throwable throwable = pickThrowable (arg1, arg2);

		if ( context == null || throwable == null ) {
			fmt = MessageFormatter.format (fmt, arg1, arg2).getMessage ();
		}

		return new LogEvent (name, level, marker, fmt, throwable, getMdc (), context);
	}

	public static LogEvent convert (String name, Level level, Marker marker, String fmt, Object ... arguments) {
		int length = arguments.length;

		if ( length == 0 ) { return convert (name, level, marker, fmt); }
		if ( length == 1 ) { return convert (name, level, marker, fmt, arguments[0]); }
		if ( length == 2 ) { return convert (name, level, marker, fmt, arguments[0], arguments[1]); }

		Context context = pickContext (arguments[length - 2], arguments[length - 1]);
		Throwable throwable = pickThrowable (arguments[length - 2], arguments[length - 1]);
		fmt = MessageFormatter.arrayFormat (fmt, arguments).getMessage ();

		return new LogEvent (name, level, marker, fmt, throwable, getMdc (), context);
	}

	private static Context pickContext (Object first, Object second) {
		return (
			first instanceof Context ? (Context) first :
			second instanceof Context ? (Context) second :
			null
		);
	}

	private static Throwable pickThrowable (Object first, Object second) {
		return (
			first instanceof Throwable ? (Throwable) first :
			second instanceof Throwable ? (Throwable) second :
			null
		);
	}

	private static Map<String, Object> getMdc () {
		return null;
	}

}
