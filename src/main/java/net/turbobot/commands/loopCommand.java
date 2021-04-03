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
import net.turbobot.utils.EmbedCreator;

/*
 Class: loopCommand
 Date: 03.04.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class loopCommand {


	public loopCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {
		if(MusicManager.getInstance().loop == false){
			if(MusicManager.getInstance().getGuildAudioPlayer(guild).player.getPlayingTrack() == null){
				txt.sendMessage(EmbedCreator.getError(member).setTitle("Hmmm.").setDescription("Theres currently no track playing!").build()).queue();
				return;
			}else{
				MusicManager.getInstance().loop = true;
				txt.sendMessage(EmbedCreator.getNormal(member).setTitle("Activated..").setDescription("The bot will loop your song now.").build()).queue();

			}
		}else if(MusicManager.getInstance().loop == true){
			MusicManager.getInstance().loop = false;
			txt.sendMessage(EmbedCreator.getNormal(member).setTitle("Deactivated.").setDescription("The bot wont loop your song now.").build()).queue();
		}


	}



	}
