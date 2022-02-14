package com.github.leana.bot.commands;

import com.github.leana.bot.ICommand;
import com.github.leana.bot.Main;
import com.github.leana.bot.MusicManager;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import discord4j.voice.AudioProvider;
import reactor.core.publisher.Mono;


/**
 * Command the bot to join the current voice channel
 */
public class Join implements ICommand {
	@Override
	public String name() {
		return "join";
	}

	@Override
	public void execute(MessageCreateEvent event) {
		final MusicManager mgr = Main.guildMusicManager.getMusicManager(event);
		final AudioProvider provider = mgr.getProvider();


		Mono.justOrEmpty(event.getMember())
				.flatMap(Member::getVoiceState)
				.flatMap(VoiceState::getChannel)
				.flatMap(channel -> channel.join(spec -> spec.setProvider(provider)))
				.block();
	}
}
