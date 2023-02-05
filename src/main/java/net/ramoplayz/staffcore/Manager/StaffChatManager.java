package net.ramoplayz.staffcore.Manager;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.ramoplayz.staffcore.Messages;

import java.util.ArrayList;
import java.util.List;

public class StaffChatManager {

	private List<ProxiedPlayer> isSCToggled = new ArrayList<>();
	private List<ProxiedPlayer> isACToggled = new ArrayList<>();

	private List<ProxiedPlayer> isVanishedToggled = new ArrayList<>();

	public void sendSCMessage(String message, ProxiedPlayer sender) {
		for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
			if (player.hasPermission(ConfigManager.getConfig().getString("permissions.staff"))) {
				player.sendMessage(Messages.SC_PREFIX.getMessage() + Messages.CHAT_MESSAGE.getMessage().replace("%server%", sender.getServer().getInfo().getName()).replace("%player%", sender.getDisplayName()) + message);
			}
		}
	}

	public void sendACMessage(String message, ProxiedPlayer sender) {
		for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
			if (player.hasPermission(ConfigManager.getConfig().getString("permission.admin"))) {
				player.sendMessage(Messages.AC_PREFIX.getMessage() + Messages.CHAT_MESSAGE.getMessage().replace("%server%", sender.getServer().getInfo().getName()).replace("%player%", sender.getDisplayName()) + message);
			}
		}
	}

	public void sendVanishMessage(String message, ProxiedPlayer vanishedPlayer) {
		for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
			if (!isVanishedToggled.contains(vanishedPlayer) && player != vanishedPlayer) {
				player.sendMessage(Messages.SC_PREFIX.getMessage() + message);
			}
		}
	}

	public void addSCToggled(ProxiedPlayer player) {
		isSCToggled.add(player);
	}

	public void removeSCToggled(ProxiedPlayer player) {
		isSCToggled.remove(player);
	}

	public boolean getSCToggled(ProxiedPlayer player) {
		return isSCToggled.contains(player);
	}

	public void addACToggled(ProxiedPlayer player) {
		isACToggled.add(player);
	}

	public void removeACToggled(ProxiedPlayer player) {
		isACToggled.remove(player);
	}

	public boolean getACToggled(ProxiedPlayer player) {
		return isACToggled.contains(player);
	}

	public void addVanishToggled(ProxiedPlayer player) {
		isVanishedToggled.add(player);
	}

	public void removeVanishToggled(ProxiedPlayer player) {
		isVanishedToggled.remove(player);
	}

	public boolean getVanishToggled(ProxiedPlayer player) {
		return isVanishedToggled.contains(player);
	}

}
