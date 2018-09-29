package org.tutske.logging;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Objects;


public class Context {

	private static ObjectMapper mapper = new ObjectMapper ()
		.disable (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
		.disable (SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
		.registerModule (new JavaTimeModule ())
		.registerModule (new Jdk8Module ());

	private final ObjectNode data = mapper.createObjectNode ();

	public void add (String property, Context context) {
		data.set (property, context.json ());
	}

	public void add (String property, JsonNode value) {
		data.set (property, value);
	}

	public JsonNode remove (String property) {
		return data.remove (property);
	}

	public void add (String property, String value) {
		data.put (property, value);
	}

	public void add (String property, Number value) {
		data.set (property, mapper.valueToTree (value));
	}

	public void add (String property, Boolean value) {
		data.put (property, value);
	}

	public void add (String property, Character value) {
		data.put (property, String.valueOf (value));
	}

	public void add (String property, Object value) {
		data.set (property, mapper.valueToTree (value));
	}

	public boolean has (String memberName) {
		return data.has (memberName);
	}

	public ObjectNode json () {
		return data;
	}

	@Override
	public boolean equals (Object o) {
		return this == o || (o instanceof Context && Objects.equals (data, ((Context) o).data));
	}

	@Override
	public int hashCode () {
		return Objects.hash (data);
	}

	@Override
	public String toString () {
		return data.toString ();
	}

}
