/**
 * This code was created by Niklas (Chunky Niklas#0001).
 * Any unauthorized use of this code is a crime and will be prosecuted accordingly.
 * Copyright (c) 2021
 */

package net.turbobot.commands;

import core.GLA;
import genius.Lyrics;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.turbobot.music.MusicManager;
import net.turbobot.utils.EmbedCreator;

import java.util.List;

/*
 Class: lycrisCommand
 Date: 03.04.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class lycrisCommand {
	public lycrisCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {
		GLA gla = new GLA("mHhlkPSBD_jk-zn8_c3Ts71kQhwU4cWREPgkPcWynLqH0ubY_dim_FlITITFGPTV", "zBp8lbXkB_ILQTS2iP1VoOFfB7HHM3B58Ry1otogRwMAhKm4AeOiSbtrNXx_kzGO");
		String search = MusicManager.getInstance().getGuildAudioPlayer(guild).player.getPlayingTrack().getInfo().title;

		List<Lyrics> lyricsList = gla.search(search);
		EmbedBuilder embedBuilder = EmbedCreator.getNormal(member);
		embedBuilder.setTitle("Heres the lycris for the current playing song: " + MusicManager.getInstance().getGuildAudioPlayer(guild).player.getPlayingTrack().getInfo().title);
		String lycris = lyricsList.get(0).getText();
		int len = lycris.length();
		String a = lycris.substring(0, len / 2), b = lycris.substring(len / 2);
		embedBuilder.setDescription(a);
		txt.sendMessage(embedBuilder.build()).queue();
		if (MusicManager.getInstance().getGuildAudioPlayer(guild).player.getPlayingTrack() == null) {
			txt.sendMessage(EmbedCreator.getError(member).setTitle("Hmm.").setDescription("Theres currently no song playing.").build()).queue();
			return;
		}


	}


}
