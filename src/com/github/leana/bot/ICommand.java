package com.github.leana.bot;

import discord4j.core.event.domain.message.MessageCreateEvent;

public interface ICommand {

	/**
	 * @return Name of the command
	 */
	String name();

	/**
	 * Execute a command
	 * @param event The event that triggered a command
	 */
	void execute(MessageCreateEvent event);
}
