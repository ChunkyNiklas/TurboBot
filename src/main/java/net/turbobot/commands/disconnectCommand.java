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
import net.turbobot.utils.EmbedCreator;
import net.turbobot.utils.checkMember;

/*
 Class: disconnectCommand
 Date: 03.04.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class disconnectCommand {

	public disconnectCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {
		if (guild.getAudioManager().getConnectedChannel() == null) {
			txt.sendMessage(EmbedCreator.getError(member).setTitle("Hmmm?").setDescription("I am not in a channel. How we will play out " +
					"the system this time?").build()).queue();
			return;
		} else {
			if (guild.getAudioManager().getConnectedChannel().getMembers().size() == 1) {
				guild.getAudioManager().closeAudioConnection();
				txt.sendMessage(EmbedCreator.getNormal(member).setTitle("Success!").setDescription("I successfully disconnected from the " +
						"lonely channel!").build()).queue();
				return;
			} else {
				if(guild.getAudioManager().getConnectedChannel() == member.getVoiceState().getChannel()){
					guild.getAudioManager().closeAudioConnection();
					txt.sendMessage(EmbedCreator.getNormal(member).setTitle("Success!").setDescription("I successfully disconnected from the " +
							"lonely channel!").build()).queue();
					return;
				}
				if (checkMember.checkGuildAdmin(member)) {
					guild.getAudioManager().closeAudioConnection();
					txt.sendMessage(EmbedCreator.getNormal(member).setTitle("Success!").setDescription("I got forced to leave by a Mod.").build()).queue();
					return;
				} else {
					txt.sendMessage(EmbedCreator.getError(member).setTitle("People are listening!!").setDescription("I wont leave. " +
							"People are currently listening to me...").build()).queue();
					return;
				}


			}


		}


	}


}
