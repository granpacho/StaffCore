package net.ramoplayz.staffcore.listener;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.ramoplayz.staffcore.StaffCorePlugin;
import net.ramoplayz.staffcore.manager.ConfigManager;
import net.ramoplayz.staffcore.util.MessageUtil;

public class ServerPingListener implements Listener {

	private StaffCorePlugin staffCorePlugin;

	public ServerPingListener(StaffCorePlugin staffCorePlugin) {
		this.staffCorePlugin = staffCorePlugin;
	}

	@EventHandler
	public void onProxyPing(ProxyPingEvent e) {
		ServerPing ping = e.getResponse();

		if (!staffCorePlugin.getModuleManager().getServerListModule()) {
			return;
		}

		ping.setDescription(MessageUtil.translate(ConfigManager.getConfig().getString("server-list.motd")));
		ping.setPlayers(new ServerPing.Players(ConfigManager.getConfig().getInt("server-list.max-players"), ProxyServer.getInstance().getOnlineCount(), ping.getPlayers().getSample()));
	}

}