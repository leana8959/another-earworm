package com.github.nottyl.earwormsbot;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.MessageEvent;

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
