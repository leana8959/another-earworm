package com.github.leana.bot.commands;

import com.github.leana.bot.ICommand;
import com.github.leana.bot.Main;
import com.github.leana.bot.MusicManager;
import com.github.leana.bot.TrackScheduler;
import discord4j.core.event.domain.message.MessageCreateEvent;

import java.util.Arrays;
import java.util.List;


public class Play implements ICommand {


	@Override
	public String name() {
		return "play";
	}

	@Override
	public void execute(MessageCreateEvent event) {
		final MusicManager mgr = Main.guildMusicManager.getMusicManager(event);
		final TrackScheduler trackScheduler = mgr.getTrackScheduler();

//		TODO: see if user is in a voice channel
//		TODO: unpause the music

		final String content = event.getMessage().getContent();
		final List<String> command = Arrays.asList(content.split(" "));

		if (command.size() > 1) {
			trackScheduler.play(command.get(1), false);
//			 TODO: no interrupt
		} else {
			event.getMessage().getChannel().block().createMessage("nothing to play...").block();
		}

	}
}
