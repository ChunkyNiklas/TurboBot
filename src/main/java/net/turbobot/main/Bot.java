/**
 * This code was created by Niklas (Chunky Niklas#0001).
 * Any unauthorized use of this code is a crime and will be prosecuted accordingly.
 * Copyright (c) 2021
 */

package net.turbobot.main;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.turbobot.listener.onMessageReceived;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

/*
 Class: Bot
 Date: 28.03.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class Bot {
	public static JDA jda;
	public static ArrayList<String> moderators = new ArrayList<String>();
	public static ArrayList<String> administrators = new ArrayList<String>();
	public static Trello trello = new TrelloImpl(Secrets.TRELLOAPIKEY, Secrets.TRELLOTOKEN, new ApacheHttpClient());
	public static Board board = trello.getBoard("C5sd8Ngy");

	public static void main(String[] args) throws InterruptedException {
		JDABuilder jdaBuilder = JDABuilder.createDefault(Secrets.TOKEN);
		jdaBuilder.setStatus(OnlineStatus.ONLINE);
		jdaBuilder.addEventListeners(new onMessageReceived());
		try {
			jda = jdaBuilder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		getModerators();
	}

	public static void getModerators() {
		moderators.clear();
		administrators.clear();
		/*
		 */
		TList team = null;
		for (TList list : board.fetchLists()) {
			if (list.getName().equalsIgnoreCase("team")) {
				team = list;
			}
		}
		for (Card card : board.fetchCards()) {
			for (Label label : card.getLabels()) {
				if (label.getName().equals("Administrator") && card.getIdList().equals("6060fdf72b81fe5c646faee4")) {
					administrators.add(card.getName());
				}
				if (label.getName().equals("Moderator") && card.getIdList().equals("6060fdf72b81fe5c646faee4")) {
					moderators.add(card.getName());
				}
			}
		}
	}
}
