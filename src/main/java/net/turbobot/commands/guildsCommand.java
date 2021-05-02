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
import net.turbobot.main.Bot;
import net.turbobot.main.Config;
import net.turbobot.utils.EmbedCreator;
import net.turbobot.utils.checkMember;

/*
 Class: serversCommand
 Date: 03.04.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class guildsCommand {
	public guildsCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {
		if (!checkMember.checkTeam(member)) {
			txt.sendMessage(EmbedCreator.getError(member).setTitle("No permissions.").setDescription(Config.noPermissions).setColor(Config.colorModeration).build()).queue();
			return;
		}
		String guildsString = "";
		for (Guild guilds : Bot.jda.getGuilds()) {
			String owner = "";
			if (guilds.getOwner() == null) {
				owner = "null";
			} else {
				owner = guilds.getOwner().getUser().getAsTag();
			}
			guildsString =
					guildsString + "Name: " + guilds.getName() + " - " + guilds.getIdLong() + " - " + owner + "\n";
		}
		EmbedBuilder embedBuilder = EmbedCreator.getNormal(member);
		embedBuilder.setDescription(guildsString);
		embedBuilder.setTitle("I am in following guilds. | " + Bot.jda.getGuilds().size());
		txt.sendMessage(embedBuilder.build()).queue();
	}
}
