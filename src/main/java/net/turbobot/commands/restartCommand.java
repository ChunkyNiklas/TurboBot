/**
 * This code was created by Niklas (Chunky Niklas#0001).
 * Any unauthorized use of this code is a crime and will be prosecuted accordingly.
 * Copyright (c) 2021
 */

package net.turbobot.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.turbobot.music.MusicManager;
import net.turbobot.utils.EmbedCreator;

/*
 Class: restartCommand
 Date: 03.04.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class restartCommand {


	public restartCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {
		if (guild.getAudioManager().isConnected() && member.getVoiceState().getChannel() == null
				|| !(member.getVoiceState().getChannel() == guild.getAudioManager().getConnectedChannel())
		) {
			txt.sendMessage(EmbedCreator.getError(member).setTitle("You need to be in the right VC.").setDescription("You aren't in the " +
					"right VoiceChannel to add songs. #Troller").build()).queue();
			return;
		}

		MusicManager.getInstance().getGuildAudioPlayer(guild).player.getPlayingTrack().setPosition(0);
		txt.sendMessage(EmbedCreator.getNormal(member).setTitle("Restartet song!").setDescription("I restartet the current song, have " +
				"fun!").build()).queue();


	}


}
