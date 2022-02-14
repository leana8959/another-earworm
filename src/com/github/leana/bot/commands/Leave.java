package com.github.leana.bot.commands;

import com.github.leana.bot.ICommand;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.VoiceChannel;
import reactor.core.publisher.Mono;

public class Leave implements ICommand {
	@Override
	public String name() {
		return "leave";
	}

	@Override
	public void execute(MessageCreateEvent event) {
		Mono.justOrEmpty(event.getMember())
				.flatMap(Member::getVoiceState)
				.flatMap(VoiceState::getChannel)
				.flatMap(VoiceChannel::sendDisconnectVoiceState)
				.block();
	}
}
