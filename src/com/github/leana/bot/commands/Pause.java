package com.github.leana.bot.commands;

import com.github.leana.bot.ICommand;
import com.github.leana.bot.Main;
import com.github.leana.bot.MusicManager;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class Pause implements ICommand {
	@Override
	public String name() {
		return "pause";
	}

	@Override
	public Mono<Void> execute(MessageCreateEvent event) {
		final MusicManager mgr = Main.guildMusicManager.getMusicManager(event);
		final AudioPlayerManager playerManager = mgr.getPlayerManager();
//		TODO: pause
		return Mono.empty();

	}
}
