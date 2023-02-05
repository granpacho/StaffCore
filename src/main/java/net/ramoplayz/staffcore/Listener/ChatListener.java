package net.ramoplayz.staffcore.listener;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.ramoplayz.staffcore.StaffCorePlugin;
import net.ramoplayz.staffcore.util.EmbedUtil;

import java.awt.*;

public class ChatListener implements Listener {

	private StaffCorePlugin staffCorePlugin;

	public ChatListener(StaffCorePlugin staffCorePlugin) {
		this.staffCorePlugin = staffCorePlugin;
	}

	@EventHandler
	public void onChat(ChatEvent e) {
		ProxiedPlayer player = (ProxiedPlayer) e.getSender();

		if (staffCorePlugin.getStaffManager().getSCToggled().contains(player)) {
			e.setCancelled(true);

			if (e.getMessage().startsWith("/")) {
				e.setCancelled(false);
			} else {
				staffCorePlugin.getStaffManager().sendSCMessage(e.getMessage(), player);

				EmbedUtil.embedDescription(player, e.getMessage(), Color.CYAN);
			}
		} else if (staffCorePlugin.getStaffManager().getACToggled().contains(player)) {
			e.setCancelled(true);

			if (e.getMessage().startsWith("/")) {
				e.setCancelled(false);
			} else {
				staffCorePlugin.getStaffManager().sendACMessage(e.getMessage(), player);

				EmbedUtil.embedDescription(player, e.getMessage(), Color.CYAN);
			}
		}
	}
}