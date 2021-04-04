/**
 * This code was created by Niklas (Chunky Niklas#0001).
 * Any unauthorized use of this code is a crime and will be prosecuted accordingly.
 * Copyright (c) 2021
 */

package net.turbobot.commands;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.turbobot.main.Bot;
import net.turbobot.main.Config;
import net.turbobot.utils.EmbedCreator;
import net.turbobot.utils.checkMember;

/*
 Class: updateCommand
 Date: 03.04.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class updateCommand {

	boolean updating = false;

	public updateCommand(GuildMessageReceivedEvent event, Member member, Guild guild, TextChannel txt, String[] args) {
		if (!checkMember.checkTeam(member)) {
			txt.sendMessage(EmbedCreator.getError(member).setTitle("No permissions.").setDescription(Config.noPermissions).setColor(Config.colorModeration).build()).queue();
			return;
		}
		if (updating == true) {
			txt.sendMessage(EmbedCreator.getError(member).setTitle("HUH!").setDescription("Im already updating!!!! CHECK MY STATUS DUDE!!!").build()).queue();
			return;
		}
		updating = true;
		Bot.jda.getPresence().setActivity(Activity.watching("Update in 5min"));
		try {
			Thread.sleep(1000*60*5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Bot.jda.getPresence().setActivity(Activity.competing("Bot-Update in 1minute"));
		try {
			Thread.sleep(1000*60*1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Bot.jda.shutdown();


	}
}
