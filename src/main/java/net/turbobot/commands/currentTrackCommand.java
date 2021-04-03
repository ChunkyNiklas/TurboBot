/**
 * This code was created by Niklas (Chunky Niklas#0001).
 * Any unauthorized use of this code is a crime and will be prosecuted accordingly.
 * Copyright (c) 2021
 */

package net.turbobot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.turbobot.music.MusicManager;
import net.turbobot.utils.EmbedCreator;

/*
 Class: currentTrackCommand
 Date: 30.03.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class currentTrackCommand {

	public currentTrackCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {
		EmbedBuilder embedBuilder = EmbedCreator.getNormal(member);
		embedBuilder.setDescription("Current track of playlist `" + MusicManager.getInstance().getGuildAudioPlayer(guild).player.getPlayingTrack().getInfo().title + "`");
		embedBuilder.setTitle("Currently playing");
		txt.sendMessage(embedBuilder.build()).queue();



	}
}
