package com.github.leana.bot.commands;


import com.github.leana.bot.ICommand;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public class Hello implements ICommand {

	@Override
	public String name() {
		return "hello";
	}

	@Override
	public Mono<Void> execute(MessageCreateEvent event) {
		return event.getMessage().getChannel()
				.flatMap(replyChannel -> replyChannel.createMessage("Hi ! I'm Æ"))
				.then();
	}
}
