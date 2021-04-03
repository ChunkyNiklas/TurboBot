/**
 * This code was created by Niklas (Chunky Niklas#0001).
 * Any unauthorized use of this code is a crime and will be prosecuted accordingly.
 * Copyright (c) 2021
 */

package net.turbobot.utils;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.turbobot.main.Bot;

import java.util.EnumSet;

/*
 Class: checkMember
 Date: 29.03.2021
 Coded by Niklas / Chunky Niklas#0001
*/
public class checkMember {

	public static boolean checkModerator(Member member) {
		Bot.getModerators();
		if(Bot.moderators.contains(member.getIdLong()+"")){
			return true;
		}else{
			return false;
		}

	}

	public static boolean checkAdministrator(Member member) {
		Bot.getModerators();
		if(Bot.administrators.contains(member.getIdLong()+"")){
			return true;
		}else{
			return false;
		}

	}

	public static boolean checkTeam(Member member) {
		Bot.getModerators();
		if(Bot.administrators.contains(member.getIdLong()+"") || Bot.moderators.contains(member.getIdLong()+"")){
			return true;
		}else{
			return false;
		}

	}

	public static boolean checkGuildAdmin(Member member) {
		if (member.getPermissions().contains(Permission.ADMINISTRATOR)) {
			return true;
		} else {
			return false;
		}


	}
}
