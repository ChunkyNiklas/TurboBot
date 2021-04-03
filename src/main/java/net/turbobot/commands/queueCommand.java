/**
 * This code was created by Niklas (Chunky Niklas#0001).
 * Any unauthorized use of this code is a crime and will be prosecuted accordingly.
 * Copyright (c) 2021
 */

package net.turbobot.commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.turbobot.music.MusicManager;
import net.turbobot.music.TrackScheduler;
import net.turbobot.utils.EmbedCreator;

import java.util.concurrent.LinkedBlockingQueue;

/*
 Class: queueCommand
 Date: 30.03.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class queueCommand {

	public queueCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {

		String songs = "";
		int pos = 1;

		LinkedBlockingQueue queue = MusicManager.getInstance().getGuildAudioPlayer(guild).scheduler.getQueue();

		for (Object obj : queue) {
			AudioTrack track = (AudioTrack)obj;
			songs = songs + pos+". `"+track.getInfo().title+"`\n";
			pos++;
			System.out.println(pos);
		}


		if(MusicManager.getInstance()
				.getGuildAudioPlayer(guild).player.getPlayingTrack() == null){
			txt.sendMessage(EmbedCreator.getNormal(member).setDescription("Currently playing: \n `Nothing` \nQueue: \n"+songs).setTitle(
					"Queue").build()).queue();
		}else{
			txt.sendMessage(EmbedCreator.getNormal(member).setDescription("Currently playing: \n `" + MusicManager.getInstance()
					.getGuildAudioPlayer(guild).player.getPlayingTrack().getInfo().title + "` \nQueue: \n"+songs).setTitle(
					"Queue").build()).queue();
		}




	}


}
