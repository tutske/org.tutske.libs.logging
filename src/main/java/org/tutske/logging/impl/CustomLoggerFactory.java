package org.tutske.logging.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.tutske.logging.LogConfiguration;


public class CustomLoggerFactory implements ILoggerFactory {

	@Override
	public Logger getLogger (String name) {
		return getLogger (LogConfiguration.configure (), name);
	}

	public Logger getLogger (LogConfiguration configuration, String name) {
		return new CustomLogger (((Configuration) configuration).getLogFn (), name);
	}

}
