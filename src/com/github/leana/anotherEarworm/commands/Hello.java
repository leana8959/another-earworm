package com.github.leana.anotherEarworm.commands;


import com.github.leana.anotherEarworm.ICommand;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class Hello implements ICommand {

	@Override
	public String name() {
		return "hello";
	}

	@Override
	public void execute(MessageCreateEvent event) {
		event.getMessage()
				.getChannel().block()
				.createMessage("Hello to you too!").block();
	}
}
