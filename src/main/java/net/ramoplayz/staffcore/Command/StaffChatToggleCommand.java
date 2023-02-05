package net.ramoplayz.staffcore.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.ramoplayz.staffcore.Messages;
import net.ramoplayz.staffcore.StaffCorePlugin;
import net.ramoplayz.staffcore.manager.ConfigManager;

public class StaffChatToggleCommand extends Command {

	private StaffCorePlugin staffCorePlugin;

	public StaffChatToggleCommand(StaffCorePlugin staffCorePlugin) {
		super(ConfigManager.getConfig().getString("commands.sc-toggle.command"), ConfigManager.getConfig().getString("commands.sc-toggle.permission"));

		this.staffCorePlugin = staffCorePlugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) sender;
			boolean isSCToggled = staffCorePlugin.getStaffManager().getSCToggled().contains(player);

			if (!player.hasPermission(ConfigManager.getConfig().getString("permissions.admin"))) {
				player.sendMessage(Messages.NO_PERMISSION.getMessage());
				return;
			}

			if (staffCorePlugin.getStaffManager().getACToggled().contains(player)) {
				return;
			}

			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("on")) {
					if (isSCToggled) {
						player.sendMessage(Messages.ALREADY_TOGGLED.getMessage());
						return;
					}

					staffCorePlugin.getStaffManager().getSCToggled().add(player);
					player.sendMessage(Messages.SC_TOGGLE_ON.getMessage());
					return;
				}

				if (args[0].equalsIgnoreCase("off")) {
					if (!isSCToggled) {
						player.sendMessage(Messages.ALREADY_TOGGLED.getMessage());
						return;
					}

					staffCorePlugin.getStaffManager().getSCToggled().remove(player);
					player.sendMessage(Messages.SC_TOGGLE_OFF.getMessage());
					return;
				}

			} else {
				if (isSCToggled) {
					staffCorePlugin.getStaffManager().getSCToggled().remove(player);
					player.sendMessage(Messages.SC_TOGGLE_OFF.getMessage());
				} else if (!isSCToggled) {
					staffCorePlugin.getStaffManager().getSCToggled().add(player);
					player.sendMessage(Messages.SC_TOGGLE_ON.getMessage());
				}

			}
		}
	}
}
