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
 Class: skipCommand
 Date: 29.03.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class skipCommand {


	public skipCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {
		if (guild.getAudioManager().isConnected() && member.getVoiceState().getChannel() == null
				|| !(member.getVoiceState().getChannel() == guild.getAudioManager().getConnectedChannel())
		) {
			txt.sendMessage(EmbedCreator.getError(member).setTitle("You need to be in the right VC.").setDescription("You aren't in the " +
					"right VoiceChannel to add songs. #Troller").build()).queue();

			return;
		}


		if(args.length == 1){
			MusicManager.getInstance().skipTrack(txt,member);
		}else{
			try{
				int i = Integer.parseInt(args[1]);
				for(int a = 1; a<i;a++){
					MusicManager.getInstance().skipTrack(txt,member);
				}

			}catch (Exception e){
				txt.sendMessage(EmbedCreator.getError(member).setTitle("Hmmm!!").setDescription("Make sure you skip a number. Try again.").build()).queue();

			}


		}








	}
}