package com.github.leana.bot;

import com.github.leana.bot.commands.*;
import discord4j.core.event.domain.message.MessageCreateEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class CommandManager {
	public final static List<ICommand> commands = new ArrayList<>(
			Arrays.asList(
					new Hello(),
					new Join(),
					new Play(),
					new Pause(),
					new Leave(),
					new Next()
			)
	);


	/**
	 * If event is a valid command, execute the command
	 *
	 * @param event A valid command starting with prefix
	 */
	public static void handle(MessageCreateEvent event) {
		String query = event.getMessage().getContent().toLowerCase().substring(1);
		if (query.startsWith("play")) {
			getCommand("join").ifPresent(cmd -> cmd.execute(event).subscribe());
			getCommand("play").ifPresent(cmd -> cmd.execute(event).subscribe());
		} else if (query.startsWith("leave")) {
			getCommand("stop").ifPresent(cmd -> cmd.execute(event).subscribe());
			getCommand("leave").ifPresent(cmd -> cmd.execute(event).subscribe());
		}
		commands.stream().filter(cmd -> cmd.name().equals(query)).findFirst()
				.map(cmd -> cmd.execute(event).subscribe());
	}

	/**
	 * Access one command from another command
	 *
	 * @param cmdName name of the command to be returned
	 * @return a command if it's found
	 */
	public static Optional<ICommand> getCommand(String cmdName) {
		return commands.stream().filter(cmd -> cmd.name().equals(cmdName)).findFirst();
	}
}
