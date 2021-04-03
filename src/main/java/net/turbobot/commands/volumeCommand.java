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
 Class: volumeCommand
 Date: 03.04.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class volumeCommand {
	public volumeCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {
		if (guild.getAudioManager().isConnected() && member.getVoiceState().getChannel() == null
				|| !(member.getVoiceState().getChannel() == guild.getAudioManager().getConnectedChannel())
		) {
			txt.sendMessage(EmbedCreator.getError(member).setTitle("You need to be in the right VC.").setDescription("You aren't in the " +
					"right VoiceChannel to add songs. #Troller").build()).queue();
			return;
		}

		if (args.length == 1) {
			txt.sendMessage(EmbedCreator.getError(member).setTitle("Wrong usage").setDescription("Please add the volume.").build()).queue();
		} else {
			try {
				MusicManager.getInstance().getGuildAudioPlayer(guild).player.setVolume(Integer.parseInt(args[1]));
				txt.sendMessage(EmbedCreator.getNormal(member).setTitle("Changed volume!").setDescription("Changed the volume to `" + args[1] + "`").build()).queue();
			} catch (Exception exception) {
				txt.sendMessage(EmbedCreator.getError(member).setTitle("Couldn't volume!").setDescription("I can't change the volume. " +
						"Maybe I don't have perms? Did you write a number? Is the number too high? Too low? Please fix your usage.").build()).queue();

			}


		}


	}


}
