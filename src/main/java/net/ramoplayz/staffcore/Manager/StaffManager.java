package net.ramoplayz.staffcore.manager;

import net.dv8tion.jda.api.EmbedBuilder;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.ramoplayz.staffcore.Messages;
import net.ramoplayz.staffcore.StaffCorePlugin;

import java.util.ArrayList;
import java.util.List;

public class StaffManager {

	private StaffCorePlugin staffCorePlugin;

	private List<ProxiedPlayer> isSCToggled = new ArrayList<>();
	private List<ProxiedPlayer> isACToggled = new ArrayList<>();

	private List<ProxiedPlayer> isVanishToggled = new ArrayList<>();

	public StaffManager(StaffCorePlugin staffCorePlugin) {
		this.staffCorePlugin = staffCorePlugin;
	}

	public void sendSCMessage(String message, ProxiedPlayer sender) {
		for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
			if (player.hasPermission(ConfigManager.getConfig().getString("permissions.staff"))) {
				player.sendMessage(Messages.SC_PREFIX.getMessage() + Messages.CHAT_FORMAT.getMessage().replace("%server%", sender.getServer().getInfo().getName()).replace("%player%", sender.getDisplayName()).replace("%message%", message));
			}
		}
	}

	public void sendPlainSCMessage(String message) {
		for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
			if (player.hasPermission(ConfigManager.getConfig().getString("permissions.staff"))) {
				player.sendMessage(Messages.SC_PREFIX.getMessage() + message);
			}
		}
	}

	public void sendACMessage(String message, ProxiedPlayer sender) {
		for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
			if (player.hasPermission(ConfigManager.getConfig().getString("permissions.admin"))) {
				player.sendMessage(Messages.AC_PREFIX.getMessage() + Messages.CHAT_FORMAT.getMessage().replace("%server%", sender.getServer().getInfo().getName()).replace("%player%", sender.getDisplayName()).replace("%message%", message));
			}
		}
	}

	public void sendVanishMessage(String message, ProxiedPlayer vanishedPlayer) {
		for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
			if (!isVanishToggled.contains(player) && player != vanishedPlayer) {
				player.sendMessage(Messages.SC_PREFIX.getMessage() + message);
			}
		}
	}

	public void sendDiscordSCMessage(EmbedBuilder builder) {
		staffCorePlugin.getJda().getTextChannelById(ConfigManager.getConfig().getString("discord.sc-channel-id")).sendMessageEmbeds(builder.build()).queue();
	}

	public void sendDiscordACMessage(EmbedBuilder builder) {
		staffCorePlugin.getJda().getTextChannelById(ConfigManager.getConfig().getString("discord.ac-channel-id")).sendMessageEmbeds(builder.build()).queue();
	}

	public List<ProxiedPlayer> getSCToggled() {
		return isSCToggled;
	}

	public List<ProxiedPlayer> getACToggled() {
		return isACToggled;
	}

	public List<ProxiedPlayer> getVanishToggled() {
		return isVanishToggled;
	}

}