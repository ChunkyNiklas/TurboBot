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

/*
 Class: inviteCommand
 Date: 03.04.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class inviteCommand {
	public inviteCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {
		txt.sendMessage(EmbedCreator.getNormal(member).setTitle("You want me?").setDescription("You can invite me to your server with " +
				"this link: \n\nhttps://discord.com/api/oauth2/authorize?client_id=825096640305430559&permissions=305491264&scope=bot").build()).queue();

	}



}
