package com.github.leana.bot.commands;

import com.github.leana.bot.ICommand;
import com.github.leana.bot.Main;
import com.github.leana.bot.MusicManager;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import discord4j.voice.AudioProvider;
import discord4j.voice.VoiceConnection;
import discord4j.voice.VoiceConnectionFactory;
import reactor.core.publisher.Mono;

import java.util.Observable;


/**
 * Command the bot to join the current voice channel
 */
public class Join implements ICommand {
	@Override
	public String name() {
		return "join";
	}

	@Override
	public Mono<Void> execute(MessageCreateEvent event) {
		final MusicManager mgr = Main.guildMusicManager.getMusicManager(event);
		final AudioProvider provider = mgr.getProvider();

		return Mono.justOrEmpty(event.getMember())
				.flatMap(Member::getVoiceState)
				.flatMap(VoiceState::getChannel)
				.flatMap(voiceChannel -> {
					return event.getMessage().getChannel()
							.flatMap(replyChannel -> replyChannel.createMessage("Ready for the music ?"))
							.flatMap(message -> voiceChannel.join(spec -> spec.setProvider(provider)));
				})
				.switchIfEmpty(event.getMessage().getChannel()
								.flatMap(replyChannel -> replyChannel.createMessage("You have to be in a voice channel for this to work..."))
								.flatMap(message -> Mono.empty()))
				.then();
	}
}