package net.ramoplayz.staffcore.Listener;

import de.myzelyam.api.vanish.BungeeVanishAPI;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.ramoplayz.staffcore.Manager.ConfigManager;
import net.ramoplayz.staffcore.Messages;
import net.ramoplayz.staffcore.StaffCorePlugin;
import net.ramoplayz.staffcore.Util.EmbedUtil;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class ConnectionListener implements Listener {

	private StaffCorePlugin staffCorePlugin;

	public ConnectionListener(StaffCorePlugin staffCorePlugin) {
		this.staffCorePlugin = staffCorePlugin;
	}

	@EventHandler
	public void postLogin(PostLoginEvent e) {
		ProxiedPlayer player = e.getPlayer();
		ProxyServer.getInstance().getScheduler().schedule(staffCorePlugin, () -> {
			if (player.hasPermission(ConfigManager.getConfig().getString("permissions.staff")) && player != null && player.getServer() != null) {

				if (BungeeVanishAPI.isInvisible(player)) {
					staffCorePlugin.getStaffManager().sendPlainSCMessage(Messages.JOINED_SILENT.getMessage().replace("%player%", player.getDisplayName()).replace("%server%", player.getServer().getInfo().getName()));

					EmbedUtil.embed(player, Messages.JOINED_SILENT.getMessage().replace("%player%", player.getDisplayName()).replace("%server%", player.getServer().getInfo().getName()), Color.GREEN);
				} else {
					staffCorePlugin.getStaffManager().sendPlainSCMessage(Messages.JOINED.getMessage().replace("%player%", player.getDisplayName()).replace("%server%", player.getServer().getInfo().getName()));

					EmbedUtil.embed(player, Messages.JOINED.getMessage().replace("%player%", player.getDisplayName()).replace("%server%", player.getServer().getInfo().getName()), Color.GREEN);
				}

			}
		}, 1, TimeUnit.SECONDS);
	}

	@EventHandler
	public void onServerSwitch(ServerSwitchEvent e) {
		ProxiedPlayer player = e.getPlayer();

		if (player.hasPermission(ConfigManager.getConfig().getString("permissions.staff")) && player != null && e.getFrom() != null && player.getServer() != null) {
			if (BungeeVanishAPI.isInvisible(player)) {
				staffCorePlugin.getStaffManager().sendPlainSCMessage(Messages.SWITCH_SILENT.getMessage().replace("%player%", player.getDisplayName()).replace("%from%", e.getFrom().getName()).replace("%to%", player.getServer().getInfo().getName()));

				EmbedUtil.embed(player, Messages.SWITCH_SILENT.getMessage().replace("%player%", player.getDisplayName()).replace("%from%", e.getFrom().getName()).replace("%to%", player.getServer().getInfo().getName()), Color.ORANGE);
			} else {
				staffCorePlugin.getStaffManager().sendPlainSCMessage(Messages.SWITCH.getMessage().replace("%player%", player.getDisplayName()).replace("%from%", e.getFrom().getName()).replace("%to%", player.getServer().getInfo().getName()));

				EmbedUtil.embed(player, Messages.SWITCH.getMessage().replace("%player%", player.getDisplayName()).replace("%from%", e.getFrom().getName()).replace("%to%", player.getServer().getInfo().getName()), Color.ORANGE);
			}
		}
	}

	@EventHandler
	public void onDisconnect(PlayerDisconnectEvent e) {
		ProxiedPlayer player = e.getPlayer();

		if (player.hasPermission(ConfigManager.getConfig().getString("permissions.staff")) && player != null && player.getServer() != null) {
			if (BungeeVanishAPI.isInvisible(player)) {
				staffCorePlugin.getStaffManager().sendPlainSCMessage(Messages.QUIT_SILENT.getMessage().replace("%player%", player.getDisplayName()).replace("%server%", player.getServer().getInfo().getName()));

				EmbedUtil.embed(player, Messages.QUIT_SILENT.getMessage().replace("%player%", player.getDisplayName()).replace("%server%", player.getServer().getInfo().getName()), Color.RED);
			} else {
				staffCorePlugin.getStaffManager().sendPlainSCMessage(Messages.QUIT.getMessage().replace("%player%", player.getDisplayName()).replace("%server%", player.getServer().getInfo().getName()));

				EmbedUtil.embed(player, Messages.QUIT.getMessage().replace("%player%", player.getDisplayName()).replace("%server%", player.getServer().getInfo().getName()), Color.RED);
			}
		}

		staffCorePlugin.getStaffManager().getSCToggled().remove(player);
		staffCorePlugin.getStaffManager().getACToggled().remove(player);
		staffCorePlugin.getStaffManager().getVanishToggled().remove(player);

	}

}