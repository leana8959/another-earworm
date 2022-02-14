package com.github.leana.bot;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.voice.AudioProvider;


/**
 * Manages playerManager, player, and audioProvider of a specific guild.
 */
public class MusicManager {

	private final AudioPlayerManager playerManager;
	private final AudioPlayer player;
	private final AudioProvider provider;
	private final TrackScheduler trackScheduler;

	public MusicManager(MessageCreateEvent event) {
		playerManager = new DefaultAudioPlayerManager();
		playerManager.getConfiguration().setFrameBufferFactory(NonAllocatingAudioFrameBuffer::new);
		AudioSourceManagers.registerRemoteSources(playerManager);
		player = playerManager.createPlayer();
		provider = new LavaPlayerAudioProvider(player);
		trackScheduler = new TrackScheduler(event);
	}

	public AudioPlayerManager getPlayerManager() {
		return playerManager;
	}

	public AudioPlayer getPlayer() {
		return player;
	}

	public AudioProvider getProvider() {
		return provider;
	}

	public TrackScheduler getTrackScheduler() {
		return trackScheduler;
	}
}
