package com.github.leana.bot.commands;

import com.github.leana.bot.ICommand;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public class Stop implements ICommand {
	@Override
	public String name() {
		return "stop";
	}

	@Override
	public Mono<Void> execute(MessageCreateEvent event) {
//		TODO
		return Mono.empty();
	}
}
