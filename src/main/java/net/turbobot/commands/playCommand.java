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
 Class: playCommand
 Date: 29.03.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class playCommand {

	public playCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {

		if (args.length == 1) {
			txt.sendMessage(EmbedCreator.getError(member).setTitle("Error Occurred").setDescription("Format: `+Play NAME/LINK` If you " +
					"type in a Name it'll be searched on YouTube!").setFooter("Simply player-error. (This " +
					"error has not been sent to a Developer)").build()).queue();
			return;
		}

		//if(guild.getAudioManager().getConnectedChannel()){
		//	}

		if (guild.getAudioManager().isConnected() && member.getVoiceState().getChannel() == null
				|| guild.getAudioManager().isConnected()&&!(member.getVoiceState().getChannel() == guild.getAudioManager().getConnectedChannel())
		) {
			txt.sendMessage(EmbedCreator.getError(member).setTitle("You need to be in the right VC.").setDescription("You aren't in the " +
					"right VoiceChannel to add songs. #Troller").build()).queue();
			return;
		}


		if (MusicManager.connectToFirstVoiceChannel(guild.getAudioManager(), member)) {

			String trackUrl = "";
			String argsMessage = "";

			if (args[1].toLowerCase().startsWith("www") || args[1].toLowerCase().startsWith("https://") || args[1].toLowerCase().startsWith("http://")) {
				trackUrl = args[1];
			} else {
				for (int i = 1; i < args.length; i++) {
					argsMessage = argsMessage + " " + args[i];
				}
				trackUrl = "ytmsearch:" + argsMessage;
			}
			MusicManager.getInstance().loadAndPlay(txt, trackUrl, member);
		} else {
			EmbedBuilder embedBuilder = EmbedCreator.getError(member);
			embedBuilder.setDescription("You need to be in a VoiceChannel! `Error: 99173`");
			embedBuilder.setTitle("Error occurred!");
			embedBuilder.setFooter("Simply player-error. (This error has not been sent to a Developer)");
			txt.sendMessage(embedBuilder.build()).queue();
		}


	}


}
