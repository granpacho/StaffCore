package net.ramoplayz.staffcore.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.ramoplayz.staffcore.Messages;
import net.ramoplayz.staffcore.StaffCorePlugin;
import net.ramoplayz.staffcore.manager.ConfigManager;

public class VanishToggleCommand extends Command {

	private StaffCorePlugin staffCorePlugin;

	public VanishToggleCommand(StaffCorePlugin staffCorePlugin) {
		super(ConfigManager.getConfig().getString("commands.vanish-toggle.command"), ConfigManager.getConfig().getString("commands.vanish-toggle.permission"));
		this.staffCorePlugin = staffCorePlugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) sender;
			boolean isVanishToggled = staffCorePlugin.getStaffManager().getVanishToggled().contains(player);

			if (!player.hasPermission(ConfigManager.getConfig().getString("permissions.staff"))) {
				player.sendMessage(Messages.NO_PERMISSION.getMessage());
				return;
			}

			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("off")) {
					if (isVanishToggled) {
						player.sendMessage(Messages.ALREADY_TOGGLED.getMessage());
						return;
					}

					staffCorePlugin.getStaffManager().getVanishToggled().add(player);
					player.sendMessage(Messages.VANISH_TOGGLED_OFF.getMessage());
					return;
				}

				if (args[0].equalsIgnoreCase("on")) {
					if (!isVanishToggled) {
						player.sendMessage(Messages.ALREADY_TOGGLED.getMessage());
						return;
					}

					staffCorePlugin.getStaffManager().getVanishToggled().remove(player);
					player.sendMessage(Messages.VANISH_TOGGLED_ON.getMessage());
					return;
				}
			}

			if (isVanishToggled) {
				staffCorePlugin.getStaffManager().getVanishToggled().remove(player);
				player.sendMessage(Messages.VANISH_TOGGLED_ON.getMessage());
			} else {
				staffCorePlugin.getStaffManager().getVanishToggled().add(player);
				player.sendMessage(Messages.VANISH_TOGGLED_OFF.getMessage());
			}
		}
	}
}
