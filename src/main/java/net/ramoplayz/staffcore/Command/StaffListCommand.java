package net.ramoplayz.staffcore.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.ramoplayz.staffcore.Messages;
import net.ramoplayz.staffcore.StaffCorePlugin;
import net.ramoplayz.staffcore.manager.ConfigManager;

public class StaffListCommand extends Command {

	private StaffCorePlugin staffCorePlugin;

	public StaffListCommand(StaffCorePlugin staffCorePlugin) {
		super(ConfigManager.getConfig().getString("commands.staff-list.command"), ConfigManager.getConfig().getString("commands.staff-list.permission"));

		this.staffCorePlugin = staffCorePlugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) sender;

			if (!staffCorePlugin.getModuleManager().getStaffListModule()) {
				return;
			}

			if (!player.hasPermission(ConfigManager.getConfig().getString("commands.staff-list.permission"))) {
				player.sendMessage(Messages.NO_PERMISSION.getMessage());
				return;
			}

			player.sendMessage(Messages.STAFF_LIST_HEADER.getMessage());

			for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {

				if (p.hasPermission(ConfigManager.getConfig().getString("permissions.staff"))) {

					player.sendMessage(Messages.STAFF_LIST_PLAYER.getMessage().replace("%player%", p.getDisplayName()));
				}

			}
		}
	}


}
