package com.github.leana.anotherEarworm.commands;

import com.github.leana.anotherEarworm.ICommand;
import com.github.leana.anotherEarworm.Main;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import reactor.core.publisher.Mono;

public class Join implements ICommand {
	@Override
	public String name() {
		return "join";
	}

	@Override
	public void execute(MessageCreateEvent event) {
		System.out.println("I'm being executed");
		Mono.justOrEmpty(event.getMember())
				.flatMap(Member::getVoiceState)
				.flatMap(VoiceState::getChannel)
				.flatMap(channel -> channel.join(spec -> spec.setProvider(Main.provider)))
				.block();


	}
}
