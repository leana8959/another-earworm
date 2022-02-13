package com.github.nottyl.earwormsbot;

import com.github.nottyl.earwormsbot.commands.*;
import discord4j.core.event.domain.message.MessageCreateEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommandManager {
	public final static List<ICommand> commands = new ArrayList<ICommand>(
			Arrays.asList(
					new Hello(),
					new Join(),
					new Play(),
					new Pause(),
					new Leave()
			)
	);


	/**
	 * If event is a valid command, execute the command
	 * @param event A valid command starting with prefix
	 */
	public static void handle(MessageCreateEvent event) {
		String query = event.getMessage().getContent().substring(1);
		for (ICommand cmd : commands) {
			if (query.equalsIgnoreCase(cmd.name())) {
				cmd.execute(event);
			}
		}
	}
}
