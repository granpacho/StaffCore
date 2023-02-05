package net.ramoplayz.staffcore.Command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.ramoplayz.staffcore.Manager.ConfigManager;
import net.ramoplayz.staffcore.Messages;
import net.ramoplayz.staffcore.StaffCorePlugin;

import java.awt.*;

public class AdminChatCommand extends Command {

	private StaffCorePlugin staffCorePlugin;

	public AdminChatCommand(StaffCorePlugin staffCorePlugin) {
		super(ConfigManager.getConfig().getString("commands.admin-chat.command"), ConfigManager.getConfig().getString("commands.admin-chat.permission"));

		this.staffCorePlugin = staffCorePlugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = (ProxiedPlayer) sender;
		if (!(sender instanceof ProxiedPlayer)) {
			return;
		}

		if (!player.hasPermission(ConfigManager.getConfig().getString("permissions.admin"))) {
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

		staffCorePlugin.getStaffManager().sendACMessage(builder.toString(), player);

		if (staffCorePlugin.getModuleManager().getDiscordModule()) {
			EmbedBuilder embedBuilder = new EmbedBuilder().setAuthor(player.getDisplayName() + " [" + player.getServer().getInfo().getName() + "]", null, "https://crafatar.com/avatars/" + player.getUniqueId().toString() + "?overlay=1");
			embedBuilder.setDescription(builder);
			embedBuilder.setColor(Color.CYAN);

			staffCorePlugin.getStaffManager().sendDiscordACMessage(embedBuilder);
		}
	}
}
