package org.tutske.logging;

import org.tutske.lib.utils.Functions.RiskyConsumer;


@FunctionalInterface
public interface LogFunction extends RiskyConsumer<LogEvent> {
}

