package org.tutske.logging;

import org.tutske.utils.functions.RiskyConsumer;


@FunctionalInterface
public interface LogFunction extends RiskyConsumer<LogEvent> {
}

