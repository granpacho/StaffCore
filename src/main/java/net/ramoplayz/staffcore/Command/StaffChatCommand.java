package net.ramoplayz.staffcore.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.ramoplayz.staffcore.Messages;
import net.ramoplayz.staffcore.StaffCorePlugin;
import net.ramoplayz.staffcore.manager.ConfigManager;
import net.ramoplayz.staffcore.util.EmbedUtil;

import java.awt.*;

public class StaffChatCommand extends Command {

	private StaffCorePlugin staffCorePlugin;

	public StaffChatCommand(StaffCorePlugin staffCorePlugin) {
		super(ConfigManager.getConfig().getString("commands.staff-chat.command"), ConfigManager.getConfig().getString("commands.staff-chat.permission"));

		this.staffCorePlugin = staffCorePlugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = (ProxiedPlayer) sender;

		if (!(sender instanceof ProxiedPlayer)) {
			return;
		}

		if (!player.hasPermission(ConfigManager.getConfig().getString("permissions.staff"))) {
			player.sendMessage(Messages.NO_PERMISSION.getMessage());
			return;
		}

		if (!(args.length >= 1)) {
			player.sendMessage(Messages.INVALID_COMMAND.getMessage());
			return;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			builder.append(args[i] + " ");
		}

		staffCorePlugin.getStaffManager().sendSCMessage(builder.toString(), player);

		EmbedUtil.embedDescription(player, builder.toString(), Color.CYAN);
	}
}
