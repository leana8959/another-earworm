package com.github.nottyl.earwormsbot.commands;

import com.github.nottyl.earwormsbot.ICommand;
import com.github.nottyl.earwormsbot.Main;
import com.github.nottyl.earwormsbot.TrackScheduler;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class Pause implements ICommand {
	@Override
	public String name() {
		return "pause";
	}

	@Override
	public void execute(MessageCreateEvent event) {
		final TrackScheduler scheduler = new TrackScheduler(Main.player);

		Mono.justOrEmpty(event.getMessage().getContent())
				.map(content -> Arrays.asList(content.split(" ")))
				.doOnNext(command -> Main.playerManager.loadItem(command.get(5), scheduler))
				.then()
				.block();
	}
}
