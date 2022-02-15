package com.github.leana.bot.commands;

import com.github.leana.bot.ICommand;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import reactor.core.publisher.Mono;

public class Leave implements ICommand {
	@Override
	public String name() {
		return "leave";
	}

	@Override
	public Mono<Void> execute(MessageCreateEvent event) {

//				FIXME: give both response if leave command used
		return Mono.justOrEmpty(event.getMember())
				.flatMap(Member::getVoiceState)
				.flatMap(VoiceState::getChannel)
				.flatMap(voiceChannel -> {
					return event.getMessage().getChannel()
							.flatMap(replyChannel -> replyChannel.createMessage("Bye :)"))
							.flatMap(message -> voiceChannel.sendDisconnectVoiceState());
				})
//				.switchIfEmpty(
//						event.getMessage().getChannel()
//								.flatMap(replyChannel -> replyChannel.createMessage("Where do you want me to go ??"))
//								.flatMap(message -> Mono.empty())
//				)
				.then();
	}

}
