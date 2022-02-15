package com.github.leana.bot.commands;

import com.github.leana.bot.ICommand;
import com.github.leana.bot.Main;
import com.github.leana.bot.MusicManager;
import com.github.leana.bot.TrackScheduler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;


public class Play implements ICommand {


	@Override
	public String name() {
		return "play";
	}

	@Override
	public Mono<Void> execute(MessageCreateEvent event) {
//		FIXME: there's no sound
		return event.getMessage().getChannel()
				.flatMap(replyChannel -> {
					return replyChannel.createMessage("starting music")
							.flatMap(message -> {
								final MusicManager mgr = Main.guildMusicManager.getMusicManager(event);
								final AudioPlayerManager playerManager = mgr.getPlayerManager();
								final TrackScheduler trackScheduler = mgr.getTrackScheduler();
								final String query = event.getMessage().getContent()
										.replaceFirst("^~play", "")
										.replace(" ", "");
//								XXX
								System.out.println("what's up");
								System.out.println(query);
								trackScheduler.play(query, false);

								return Mono.empty();
							});
				})
				.then();
	}
}

