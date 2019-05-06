package org.tutske.logging.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMDCAdapter;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;



public class Provider implements SLF4JServiceProvider {

	private ILoggerFactory loggers;
	private IMarkerFactory markers;
	private MDCAdapter mdc;

	@Override
	public void initialize () {
		this.loggers = new CustomLoggerFactory ();
		this.markers = new BasicMarkerFactory ();
		this.mdc = new BasicMDCAdapter ();
	}

	@Override
	public ILoggerFactory getLoggerFactory () {
		return this.loggers;
	}

	@Override
	public IMarkerFactory getMarkerFactory () {
		return this.markers;
	}

	@Override
	public MDCAdapter getMDCAdapter () {
		return this.mdc;
	}

	@Override
	public String getRequesteApiVersion () {
		return "1.8";
	}

}
