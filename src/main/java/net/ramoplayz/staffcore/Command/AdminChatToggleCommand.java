package net.ramoplayz.staffcore.Command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.ramoplayz.staffcore.Manager.ConfigManager;
import net.ramoplayz.staffcore.Messages;
import net.ramoplayz.staffcore.StaffCorePlugin;

public class AdminChatToggleCommand extends Command {

	private StaffCorePlugin staffCorePlugin;

	public AdminChatToggleCommand(StaffCorePlugin staffCorePlugin) {
		super(ConfigManager.getConfig().getString("commands.ac-toggle.command"), ConfigManager.getConfig().getString("commands.ac-toggle.permission"));

		this.staffCorePlugin = staffCorePlugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) sender;
			boolean isACToggled = staffCorePlugin.getStaffManager().getACToggled().contains(player);

			if (!player.hasPermission(ConfigManager.getConfig().getString("permissions.admin"))) {
				player.sendMessage(Messages.NO_PERMISSION.getMessage());
				return;
			}

			if (staffCorePlugin.getStaffManager().getSCToggled().contains(player)) {
				return;
			}

			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("on")) {
					if (isACToggled) {
						player.sendMessage(Messages.ALREADY_TOGGLED.getMessage());
						return;
					}

					staffCorePlugin.getStaffManager().getACToggled().add(player);
					player.sendMessage(Messages.AC_TOGGLE_ON.getMessage());
					return;
				}

				if (args[0].equalsIgnoreCase("off")) {
					if (!isACToggled) {
						player.sendMessage(Messages.ALREADY_TOGGLED.getMessage());
						return;
					}

					staffCorePlugin.getStaffManager().getACToggled().remove(player);
					player.sendMessage(Messages.AC_TOGGLE_OFF.getMessage());
					return;
				}

			} else {
				if (isACToggled) {
					staffCorePlugin.getStaffManager().getACToggled().remove(player);
					player.sendMessage(Messages.AC_TOGGLE_OFF.getMessage());
				} else if (!isACToggled) {
					staffCorePlugin.getStaffManager().getACToggled().add(player);
					player.sendMessage(Messages.AC_TOGGLE_ON.getMessage());
				}

			}
		}
	}
}