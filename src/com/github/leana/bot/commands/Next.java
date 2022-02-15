package com.github.leana.bot.commands;

import com.github.leana.bot.ICommand;
import com.github.leana.bot.Main;
import com.github.leana.bot.MusicManager;
import com.github.leana.bot.TrackScheduler;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public class Next implements ICommand {
	@Override
	public String name() {
		return "next";
	}

	@Override
	public Mono<Void> execute(MessageCreateEvent event) {

		return event.getMessage().getChannel()
				.flatMap(replyChannel -> {
					final MusicManager mgr = Main.guildMusicManager.getMusicManager(event);
					final TrackScheduler trackScheduler = mgr.getTrackScheduler();
					trackScheduler.nextTrack();

					return replyChannel.createMessage("Skipped to the next song");
				})
				.then();
	}
}
