/**
 * This code was created by Niklas (Chunky Niklas#0001).
 * Any unauthorized use of this code is a crime and will be prosecuted accordingly.
 * Copyright (c) 2021
 */

package net.turbobot.utils;

import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.turbobot.main.Bot;
import net.turbobot.main.Config;

import java.awt.*;

/*
 Class: EmbedCreator
 Date: 29.03.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class EmbedCreator {

	public static EmbedBuilder sendHelp(Member member) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		String extra = "";

		if(checkGuild.checkPartner(member.getGuild().getIdLong()+"")){
			embedBuilder.setColor(new Color(255, 191, 0));
			embedBuilder.setFooter(Config.footerNormal +"|| This Guild is partnered with TurboBot");
			extra = "This server is partnered with TurboBot!";
		}else{
			embedBuilder.setColor(Config.colorNormal);
			embedBuilder.setFooter(Config.footerNormal);
		}

		embedBuilder.setThumbnail("https://cdn.discordapp.com/attachments/824981302095642685/825389689200181279/PNG.png");
		embedBuilder.setTitle("Commands");
		embedBuilder.setAuthor(member.getUser().getAsTag(), null, member.getUser().getEffectiveAvatarUrl());

		String playerCommands = "";
		String moderatorCommands = "";
		Board board = Bot.board;

		for (Card card : board.fetchCards()) {
			if (card.getLabels().size() == 0 && card.getIdList().equals("6060fdd40927897036c9cc0a")) {
				playerCommands = playerCommands + "`" + card.getName() + "`, ";
			}

			for (int i = 0; i < card.getLabels().size(); i++) {
				if (card.getIdList().equals("6060fdd40927897036c9cc0a")) {
					if (card.getLabels().get(i).getName().equalsIgnoreCase("Team")) {
						moderatorCommands = moderatorCommands + "`" + card.getName() + "`, ";
					}
				}
			}
		}



		if (checkMember.checkTeam(member)) {
			embedBuilder.setDescription("Here you find commands. \n" + playerCommands + "\n\n**Your a Moderator. Here are available" +
					" commands you can use. **" + moderatorCommands + "\n\n" + extra);

		} else {
			embedBuilder.setDescription("Here you find a few commands. \n" + playerCommands);

		}


		return embedBuilder;
	}

	public static EmbedBuilder getNormal(Member member) {
		EmbedBuilder embedBuilder = new EmbedBuilder();

		if(checkGuild.checkPartner(member.getGuild().getIdLong()+"")){
			embedBuilder.setColor(new Color(255, 191, 0));
			embedBuilder.setFooter(Config.footerNormal +"|| This Guild is partnered with TurboBot");
		}else{
			embedBuilder.setColor(Config.colorNormal);
			embedBuilder.setFooter(Config.footerNormal);
		}

		embedBuilder.setThumbnail("https://cdn.discordapp.com/attachments/824981302095642685/825389689200181279/PNG.png");
		embedBuilder.setTitle("title");
		embedBuilder.setAuthor(member.getUser().getAsTag(), null, member.getUser().getEffectiveAvatarUrl());

		return embedBuilder;
	}

	public static EmbedBuilder getError(Member member) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		embedBuilder.setThumbnail("https://cdn.discordapp.com/attachments/824981302095642685/825389689200181279/PNG.png");
		embedBuilder.setTitle("An error occurred!");
		embedBuilder.setColor(Config.colorError);
		embedBuilder.setAuthor(member.getUser().getAsTag(), null, member.getUser().getEffectiveAvatarUrl());
		if(checkGuild.checkPartner(member.getGuild().getIdLong()+"")){
			embedBuilder.setColor(new Color(255, 191, 0));
		}else{
			embedBuilder.setColor(Config.colorError);
		}
		return embedBuilder;
	}
}
