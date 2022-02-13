package com.github.nottyl.earwormsbot.commands;

import com.github.nottyl.earwormsbot.ICommand;
import com.github.nottyl.earwormsbot.Main;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.VoiceChannel;
import discord4j.voice.VoiceConnection;
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
