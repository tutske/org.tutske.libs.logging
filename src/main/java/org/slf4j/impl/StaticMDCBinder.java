package org.slf4j.impl;

import org.slf4j.spi.MDCAdapter;
import org.tutske.logging.mdc.ContextMDCAdaptor;


public class StaticMDCBinder {

	public static final StaticMDCBinder SINGLETON = new StaticMDCBinder ();

	private StaticMDCBinder () {
	}

	public static StaticMDCBinder getSingleton () {
		return SINGLETON;
	}

	public MDCAdapter getMDCA () {
		return new ContextMDCAdaptor ();
	}

	public String getMDCAdapterClassStr () {
		return ContextMDCAdaptor.class.getName ();
	}

}
