package org.tutske.logging;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.junit.Test;


public class ContextTest {

	@Test
	public void it_should_unwrap_to_a_json_object () {
		ObjectNode wrapped = new Context () {{
			add ("key", new TextNode ("value"));
		}}.json ();

		assertThat (wrapped.get ("key").asText (), is ("value"));
	}

	@Test
	public void it_should_add_json_elements_to_a_context () {
		Context context = new Context () {{
			add ("key", new TextNode ("value"));
		}};
		assertThat (context.has ("key"), is (true));
	}

	@Test
	public void it_should_add_primitive_strings_to_the_context () {
		Context context = new Context () {{
			add ("key", "value");
		}};

		assertThat (context.json ().get ("key").asText (), is ("value"));
	}

	@Test
	public void it_should_add_primitive_numbers_to_the_context () {
		Context context = new Context () {{
			add ("int", 10);
			add ("float", 10.1);
		}};

		assertThat (context.json ().get ("int").asInt (), is (10));
		assertThat (context.json ().get ("float").asDouble (), is (10.1D));
	}


	@Test
	public void it_should_add_primitive_booleans_to_the_context () {
		Context context = new Context () {{
			add ("key", false);
		}};

		assertThat (context.json ().get ("key").asBoolean (), is (false));
	}

	@Test
	public void it_should_add_primitive_characters_to_the_context () {
		Context context = new Context () {{
			add ("key", 'c');
		}};

		assertThat (context.json ().get ("key").asText (), is ("c"));
	}

	@Test
	public void it_should_add_nested_contextes () {
		Context context = new Context () {{
			add ("sub", new Context () {{
				add ("key", "value");
			}});
		}};

		assertThat (context.json ().get ("sub").get ("key").asText (), is ("value"));
	}

	@Test
	public void it_should_remove_things_from_the_context () {
		Context context = new Context () {{
			add ("key", "value");
		}};

		context.remove ("key");

		assertThat (context.has ("key"), is (false));
		assertThat (context.json ().has ("key"), is (false));
	}

	@Test
	public void it_should_add_primitive_strings () {
		Context context = new Context () {{
			add ("key", "value");
		}};
	}

}
