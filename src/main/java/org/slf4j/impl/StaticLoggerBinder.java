package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.tutske.logging.impl.CustomLoggerFactory;


public class StaticLoggerBinder implements LoggerFactoryBinder {

	private static final StaticLoggerBinder singleton = new StaticLoggerBinder ();

	public static StaticLoggerBinder getSingleton () { return singleton; }

	@Override
	public ILoggerFactory getLoggerFactory () {
		return new CustomLoggerFactory ();
	}

	@Override
	public String getLoggerFactoryClassStr () {
		return CustomLoggerFactory.class.getName ();
	}

}
