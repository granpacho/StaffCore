package net.ramoplayz.staffcore.Listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.ramoplayz.staffcore.Manager.ConfigManager;
import net.ramoplayz.staffcore.Messages;
import net.ramoplayz.staffcore.StaffCorePlugin;

import java.lang.reflect.Member;

public class DiscordListener extends ListenerAdapter {

	private StaffCorePlugin staffCorePlugin;

	public DiscordListener(StaffCorePlugin staffCorePlugin) {
		this.staffCorePlugin = staffCorePlugin;
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {

		if (!staffCorePlugin.getModuleManager().getDiscordModule()) {
			return;
		}

		if (e.getAuthor() == null | e.getAuthor().isBot()) {
			return;
		}

		Member member = (Member) e.getMember();

		if (e.getChannel().getId().equals(ConfigManager.getConfig().getString("discord.sc-channel-id"))) {
			staffCorePlugin.getStaffManager().sendPlainSCMessage(Messages.CHAT_FORMAT.getMessage().replace("%server%", "Discord").replace("%player%", member.getName()).replace("%message%", e.getMessage().getContentRaw()));

		} else if (e.getChannel().getId().equals(ConfigManager.getConfig().getString("discord.ac-channel-id"))) {
			for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
				if (player.hasPermission(ConfigManager.getConfig().getString("permissions.admin"))) {
					player.sendMessage(Messages.AC_PREFIX.getMessage() + Messages.CHAT_FORMAT.getMessage().replace("%server%", "Discord").replace("%player%", member.getName()).replace("%message%", e.getMessage().getContentRaw()));
				}
			}
		}
	}

}