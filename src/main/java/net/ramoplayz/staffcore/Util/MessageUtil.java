package net.ramoplayz.staffcore.util;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtil {
	private static final Pattern pattern = Pattern.compile("&#[a-fA-F0-9]{6}");

	public static String translate(String input) {
		Matcher match = pattern.matcher(input);
		while (match.find()) {
			String color = input.substring(match.start() + 1, match.end());
			input = input.replace("&" + color, ChatColor.of(color) + "");
			match = pattern.matcher(input);
		}
		return input.replace("&", "\u00a7");
	}
}