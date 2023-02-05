package net.ramoplayz.staffcore.Util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.ramoplayz.staffcore.StaffCorePlugin;

import java.awt.*;

public class EmbedUtil {

	private static StaffCorePlugin staffCorePlugin;

	public EmbedUtil(StaffCorePlugin staffCorePlugin) {
		this.staffCorePlugin = staffCorePlugin;
	}

	public static void embedDescription(ProxiedPlayer player, String message, Color color) {

		if (!staffCorePlugin.getModuleManager().getDiscordModule()) {
			return;
		}

		EmbedBuilder builder = new EmbedBuilder().setAuthor(player.getDisplayName() + " [" + player.getServer().getInfo().getName() + "]", null, "https://crafatar.com/avatars/" + player.getUniqueId().toString() + "?overlay=1");
		builder.setDescription(ChatColor.stripColor(message));
		builder.setColor(color);

		staffCorePlugin.getStaffManager().sendDiscordSCMessage(builder);
	}

	public static void embed(ProxiedPlayer player, String message, Color color) {

		if (!staffCorePlugin.getModuleManager().getDiscordModule()) {
			return;
		}

		EmbedBuilder builder = new EmbedBuilder().setAuthor(ChatColor.stripColor(message), null, "https://crafatar.com/avatars/" + player.getUniqueId().toString() + "?overlay=1");
		builder.setColor(color);

		staffCorePlugin.getStaffManager().sendDiscordSCMessage(builder);
	}
}
