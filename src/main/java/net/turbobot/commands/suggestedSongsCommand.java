/**
 * This code was created by Niklas (Chunky Niklas#0001).
 * Any unauthorized use of this code is a crime and will be prosecuted accordingly.
 * Copyright (c) 2021
 */

package net.turbobot.commands;

import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.turbobot.main.Bot;
import net.turbobot.utils.EmbedCreator;

/*
 Class: restartCommand
 Date: 30.03.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class suggestedSongsCommand {
	public suggestedSongsCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {
		Board board = Bot.board;

		String songs = "";

		for (Card card : board.fetchCards()) {
			if (card.getIdList().equals("6062ea181e439e7222f09054")) {
				songs = songs + "`" + card.getName() + "`\n";
			}
		}

		txt.sendMessage(EmbedCreator.getNormal(member).setTitle("Suggested songs").setDescription(songs).build()).queue();


	}


}
