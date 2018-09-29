package org.tutske.logging.mdc;

import org.slf4j.spi.MDCAdapter;
import org.tutske.logging.Context;

import java.util.LinkedHashMap;
import java.util.Map;


public class ContextMDCAdaptor implements MDCAdapter {

	private ThreadLocal<LinkedHashMap<String, Object>> contexts = new ThreadLocal<> ();

	@Override
	public void put (String key, String val) {
		addToContext (key, val);
	}

	public void put (String key, Context context) {
		addToContext (key, context);
	}

	@Override
	public String get (String key) {
		return String.valueOf (contexts.get ().get (key));
	}

	public Context getContext (String key) {
		return (Context) contexts.get ().get (key);
	}

	@Override
	public void remove (String key) {
		LinkedHashMap<String, Object> old = contexts.get ();
		if ( old == null ) { return; }

		LinkedHashMap<String, Object> copy = new LinkedHashMap<> (old);
		copy.remove (key);

		contexts.set (copy);
	}

	@Override
	public void clear () {
		contexts.set (null);
	}

	@Override
	public Map<String, String> getCopyOfContextMap () {
		LinkedHashMap<String, String> result = new LinkedHashMap<> ();

		LinkedHashMap<String, Object> old = contexts.get ();
		if ( old == null ) { return result; }

		for ( Map.Entry<String, Object> entry : old.entrySet () ) {
			result.put (entry.getKey (), String.valueOf (entry.getValue ()));
		}

		return result;
	}

	@Override
	public void setContextMap (Map<String, String> context) {
		contexts.set (new LinkedHashMap<> (context));
	}

	private void addToContext (String key, Object object) {
		if ( key == null ) {
			throw new IllegalArgumentException ("key cannot be null");
		}
		if ( key.isEmpty () ) {
			throw new IllegalArgumentException ("key cannot be empty string");
		}

		LinkedHashMap<String, Object> old = contexts.get ();
		LinkedHashMap<String, Object> copy = old == null ? new LinkedHashMap<> () : new LinkedHashMap<> (old);
		copy.put (key, object);

		contexts.set (copy);
	}

}
