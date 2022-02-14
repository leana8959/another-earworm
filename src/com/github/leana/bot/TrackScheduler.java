package com.github.leana.bot;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import discord4j.core.event.domain.message.MessageCreateEvent;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Manages queued songs and playback
 */
public class TrackScheduler extends AudioEventAdapter {

	private final MessageCreateEvent event;
	private final Queue<AudioTrack> queue;

	public TrackScheduler(MessageCreateEvent event) {
		this.event = event;
		queue = new LinkedBlockingQueue<>();
	}

	/**
	 * @param trackUrl query or URL of a song
	 * @param noInterrupt interrupt the song currently playing
	 */
	public void play(String trackUrl, boolean noInterrupt) {
		final MusicManager mgr = Main.guildMusicManager.getMusicManager(this.event);
		final AudioPlayer player = mgr.getPlayer();
		final AudioPlayerManager playerManager = mgr.getPlayerManager();

		playerManager.loadItemOrdered(event, trackUrl, new AudioLoadResultHandler() {
			@Override
			public void trackLoaded(AudioTrack track) {
				queue(track, noInterrupt);
//				TODO: track loaded feedback
			}

			@Override
			public void playlistLoaded(AudioPlaylist playlist) {
				final List<AudioTrack> tracks = playlist.getTracks();
				queue(tracks.remove(0), noInterrupt);
				queue.addAll(tracks);
//				TODO: playlist loaded feedbock
			}

			@Override
			public void noMatches() {
//				TODO: no matches feedback
			}

			@Override
			public void loadFailed(FriendlyException exception) {
//				TODO: failed feedback
			}
		});
	}

	public void queue(AudioTrack track, boolean noInterrupt) {
		final MusicManager mgr = Main.guildMusicManager.getMusicManager(this.event);
		final AudioPlayer player = mgr.getPlayer();

		boolean isPlaying = !player.startTrack(track, noInterrupt);
		if (isPlaying) {
			this.queue.add(track);
		}

	}

	public void nextTrack() {
		final AudioTrack next = this.queue.poll();
		final MusicManager mgr = Main.guildMusicManager.getMusicManager(this.event);
		final AudioPlayer player = mgr.getPlayer();
		player.startTrack(next, false);
	}

	@Override
	public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
		if (endReason.mayStartNext) {
			nextTrack();
		}
	}
}
