package net.ramoplayz.staffcore.Listener;

import de.myzelyam.api.vanish.BungeePlayerHideEvent;
import de.myzelyam.api.vanish.BungeePlayerShowEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.ramoplayz.staffcore.Messages;
import net.ramoplayz.staffcore.StaffCorePlugin;
import net.ramoplayz.staffcore.Util.EmbedUtil;

import java.awt.*;

public class VanishListener implements Listener {

	private StaffCorePlugin staffCorePlugin;

	public VanishListener(StaffCorePlugin staffCorePlugin) {
		this.staffCorePlugin = staffCorePlugin;
	}

	@EventHandler
	public void onVanish(BungeePlayerHideEvent e) {
		ProxiedPlayer player = e.getPlayer();
		
		if (!staffCorePlugin.getModuleManager().getPremiumVanishModule()) {
			return;
		}

		staffCorePlugin.getStaffManager().sendVanishMessage(Messages.VANISH.getMessage().replace("%player%", player.getDisplayName()), e.getPlayer());

		EmbedUtil.embed(player, Messages.VANISH.getMessage().replace("%player%", player.getDisplayName()), Color.YELLOW);
	}

	@EventHandler
	public void onReappear(BungeePlayerShowEvent e) {
		ProxiedPlayer player = e.getPlayer();

		if (!staffCorePlugin.getModuleManager().getPremiumVanishModule()) {
			return;
		}

		staffCorePlugin.getStaffManager().sendVanishMessage(Messages.REAPPEAR.getMessage().replace("%player%", player.getDisplayName()), player);

		EmbedUtil.embed(player, Messages.REAPPEAR.getMessage().replace("%player%", player.getDisplayName()), Color.YELLOW);
	}

}