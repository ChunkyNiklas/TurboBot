/**
 * This code was created by Niklas (Chunky Niklas#0001).
 * Any unauthorized use of this code is a crime and will be prosecuted accordingly.
 * Copyright (c) 2021
 */

package net.turbobot.music;

/*
 Class: MusicManager
 Date: 29.03.2021
 Coded by Niklas / Chunky Niklas#0001
*/

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import net.turbobot.utils.EmbedCreator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MusicManager {


	public static MusicManager musicManager;
	public final AudioPlayerManager playerManager;
	public final Map<Long, GuildMusicManager> musicManagers;

	private MusicManager() {
		this.musicManagers = new HashMap<>();

		this.playerManager = new DefaultAudioPlayerManager();
		AudioSourceManagers.registerRemoteSources(playerManager);
		AudioSourceManagers.registerLocalSource(playerManager);
	}

	public static MusicManager getInstance() {
		if (musicManager == null) {
			musicManager = new MusicManager();
		}
		return musicManager;
	}

	public static boolean connectToFirstVoiceChannel(AudioManager audioManager, Member member) {

		if (audioManager.isConnected()) {
			return true;
		}
		if (!audioManager.isConnected()) {
			for (VoiceChannel voiceChannel : audioManager.getGuild().getVoiceChannels()) {
				if (voiceChannel.getName().equalsIgnoreCase("Music")) {
					audioManager.openAudioConnection(voiceChannel);
					return true;
				} else if (voiceChannel.getName().equalsIgnoreCase("Musik")) {
					audioManager.openAudioConnection(voiceChannel);
					return true;
				}
			}

			if (member.getVoiceState().getChannel() == null) {
				return false;
			} else {
				audioManager.openAudioConnection(member.getVoiceState().getChannel());
				return true;
			}


		}
		return false;
	}
/*
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] command = event.getMessage().getContentRaw().split(" ", 2);

		if ("~play".equals(command[0]) && command.length == 2) {
			loadAndPlay(event.getChannel(), command[1]);
		} else if ("~skip".equals(command[0])) {
			skipTrack(event.getChannel());
		}

		super.onGuildMessageReceived(event);
	}
	*/

	public synchronized GuildMusicManager getGuildAudioPlayer(Guild guild) {
		long guildId = Long.parseLong(guild.getId());
		GuildMusicManager musicManager = musicManagers.get(guildId);

		if (musicManager == null) {
			musicManager = new GuildMusicManager(playerManager);
			musicManagers.put(guildId, musicManager);
		}

		guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

		return musicManager;
	}

	public void loadAndPlay(final TextChannel channel, final String trackUrl, Member member) {
		GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
		playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
			@Override
			public void trackLoaded(AudioTrack track) {

				long millis = track.getInfo().length;
				String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
						TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
						TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));


				EmbedBuilder embedBuilder = EmbedCreator.getNormal(member);
				embedBuilder.setDescription("Added \n`" + track.getInfo().title + "` to queue. With total length of \n`" + hms +
						"` \n\n " + track.getInfo().uri);
				embedBuilder.setTitle("Added to queue.");
				channel.sendMessage(embedBuilder.build()).queue();
				play(channel.getGuild(), musicManager, track, member);
			}

			@Override
			public void playlistLoaded(AudioPlaylist playlist) {
				AudioTrack firstTrack = playlist.getSelectedTrack();

				if (firstTrack == null) {
					firstTrack = playlist.getTracks().get(0);
				}
				long millis = firstTrack.getInfo().length;
				String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
						TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
						TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
				EmbedBuilder embedBuilder = EmbedCreator.getNormal(member);
				embedBuilder.setDescription("Added first track of playlist `" + firstTrack.getInfo().title + "` to queue. With total length " +
						"of `" + hms +
						"` \n\n " + firstTrack.getInfo().uri);
				embedBuilder.setTitle("Added to queue.");
				channel.sendMessage(embedBuilder.build()).queue();
				play(channel.getGuild(), musicManager, firstTrack, member);
			}

			@Override
			public void noMatches() {
				channel.sendMessage("Nothing found by " + trackUrl).queue();
			}

			@Override
			public void loadFailed(FriendlyException exception) {
				channel.sendMessage("Could not play: " + exception.getMessage()).queue();
			}
		});
	}

	public void play(Guild guild, GuildMusicManager musicManager, AudioTrack track, Member member) {
		connectToFirstVoiceChannel(guild.getAudioManager(), member);

		musicManager.scheduler.queue(track);
	}

	public void skipTrack(TextChannel channel, Member member) {
		GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
		musicManager.scheduler.nextTrack();

		if (musicManager.player.getPlayingTrack() == null) {
			channel.sendMessage(EmbedCreator.getNormal(member).setTitle("Skipped track!").setDescription("I skipped the current track. Now " +
					"playing nothing.").build()).queue();
			return;
		}

		channel.sendMessage(EmbedCreator.getNormal(member).setTitle("Skipped track!").setDescription("I skipped the current track. Now " +
				"playing \n`" + musicManager.player.getPlayingTrack().getInfo().title + "`").build()).queue();
	}
}
