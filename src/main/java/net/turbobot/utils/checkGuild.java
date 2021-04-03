/**
 * This code was created by Niklas (Chunky Niklas#0001).
 * Any unauthorized use of this code is a crime and will be prosecuted accordingly.
 * Copyright (c) 2021
 */

package net.turbobot.utils;

import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import net.turbobot.main.Bot;

/*
 Class: checkPartner
 Date: 29.03.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class checkGuild {


	public static String getReason(String guildId) {

		Card target = null;
		Board board = Bot.board;
		for (Card card : board.fetchCards()) {
			if (card.getName().equals(guildId) && card.getIdList().equals("6060fe075a463e5fbad0f359")) {
				target = card;
			}
		}
		if (target.getDesc().isEmpty()) {
			return target.getDesc();

		} else {
			return "NONE";

		}


	}

	public static boolean checkPartner(String guildId) {
		Board board = Bot.board;
		for (Card card : board.fetchCards()) {
			if (card.getName().equals(guildId) && card.getIdList().equals("6060fe075a463e5fbad0f359")) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkBlacklisted(String guildId) {
		Board board = Bot.board;
		for (Card card : board.fetchCards()) {
			if (card.getName().equals(guildId) && card.getIdList().equals("6060fdfe2254ae6dec539c80")) {
				return true;
			}
		}
		return false;
	}


}
