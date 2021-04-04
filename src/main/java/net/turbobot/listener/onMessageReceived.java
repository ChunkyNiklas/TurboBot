/**
 * This code was created by Niklas (Chunky Niklas#0001).
 * Any unauthorized use of this code is a crime and will be prosecuted accordingly.
 * Copyright (c) 2021
 */

package net.turbobot.listener;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.turbobot.commands.*;
import net.turbobot.main.Config;
import net.turbobot.utils.EmbedCreator;
import net.turbobot.utils.checkGuild;
import net.turbobot.utils.checkMember;

/*
 Class: onMessageReceived
 Date: 28.03.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class onMessageReceived extends ListenerAdapter {


	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		TextChannel txt = event.getMessage().getTextChannel();
		Member member = event.getMember();
		Guild guild = event.getGuild();

		if (event.getMessage().getContentDisplay().startsWith(Config.prefix)) {
			String[] args = event.getMessage().getContentDisplay().substring(Config.prefixLength).split(" ");


			if (checkGuild.checkBlacklisted(event.getGuild().getIdLong() + "") && checkMember.checkTeam(member) == false) {
				event.getMessage().getTextChannel().sendMessage("\uD83D\uDCE1 This guild got blacklisted by TurboBot. `You can't " +
						"execute any commands while the guild is blacklisted.`").queue();
				event.getGuild().getAudioManager().closeAudioConnection();
				return;
			}


			if (args[0].isEmpty() || args[0].equalsIgnoreCase("help")) {
				txt.sendMessage(EmbedCreator.sendHelp(member).build()).queue();
			} else if (args[0].equalsIgnoreCase("Play")) {
				new playCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("Skip")) {
				new skipCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("Queue")) {
				new queueCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("Suggestions")) {
				new suggestedSongsCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("currenttrack")) {
				new currentTrackCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("Pause")) {
				new pauseCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("Blacklist")) {
				new blacklistCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("Volume")) {
				new volumeCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("Restart")) {
				new restartCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("Invite")) {
				new inviteCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("Guilds")) {
				new guildsCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("Disconnect")) {
				new disconnectCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("Lycris")) {
				new lycrisCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("Loop")) {
				new loopCommand(event, member, guild, txt, args);
			} else if (args[0].equalsIgnoreCase("Update")) {
				new updateCommand(event, member, guild, txt, args);
			}


		}


	}


}
