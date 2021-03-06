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
import net.turbobot.main.Config;
import net.turbobot.utils.EmbedCreator;
import net.turbobot.utils.checkGuild;
import net.turbobot.utils.checkMember;

/*
 Class: blacklistCommand
 Date: 30.03.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class blacklistCommand {
	public blacklistCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {

		EmbedBuilder embedBuilder = new EmbedBuilder();
		embedBuilder.setTitle("Das ist mein Titel!");
		embedBuilder.setDescription("Meine Beschreibung!");
		embedBuilder.setFooter("Mein footer!");
		embedBuilder.setImage("LINK ZU EINEN BILD");
		embedBuilder.setThumbnail("LINK ZU EINEN BILD");
		embedBuilder.setAuthor("Name", "wo er hingeleitet wird wenn er auf Name drückt", "link zum image");
		// ich machs so:
		// 		embedBuilder.setAuthor(member.getUser().getAsTag(), null, member.getUser().getEffectiveAvatarUrl());
		event.getMessage().getTextChannel().sendMessage(embedBuilder.build()).queue();

		if (!checkMember.checkTeam(member)) {
			txt.sendMessage(EmbedCreator.getError(member).setTitle("No permissions.").setDescription(Config.noPermissions).setColor(Config.colorModeration).build()).queue();
			return;
		}


		if (checkGuild.checkBlacklisted(guild.getIdLong() + "")) {
			txt.sendMessage(EmbedCreator.getError(member).setDescription("Reason for Blacklist: " + checkGuild.getReason(guild.getIdLong() + "")).build()).queue();
		} else {
			txt.sendMessage(EmbedCreator.getError(member).setDescription("This Guild is not blacklisted").setTitle("Hmmmm?").build()).queue();
		}

	}
}
