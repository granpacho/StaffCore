package net.ramoplayz.staffcore.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.ramoplayz.staffcore.Messages;
import net.ramoplayz.staffcore.StaffCorePlugin;
import net.ramoplayz.staffcore.manager.ConfigManager;

import java.awt.*;

public class ReportCommand extends Command {

	private StaffCorePlugin staffCorePlugin;

	public ReportCommand(StaffCorePlugin staffCorePlugin) {
		super(ConfigManager.getConfig().getString("commands.report.command"), ConfigManager.getConfig().getString("commands.report.permission"));

		this.staffCorePlugin = staffCorePlugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = (ProxiedPlayer) sender;
		if (!(sender instanceof ProxiedPlayer)) {
			return;
		}

		if (!staffCorePlugin.getModuleManager().getReportModule()) {
			return;
		}

		if (!player.hasPermission("permissions.report")) {
			player.sendMessage(Messages.NO_PERMISSION.getMessage());
			return;
		}

		ProxiedPlayer reportedPlayer = ProxyServer.getInstance().getPlayer(args[0]);

		if (args.length < 2 || reportedPlayer == null) {
			player.sendMessage(Messages.INVALID_COMMAND.getMessage());
			return;
		}

		if (reportedPlayer == player) {
			player.sendMessage(Messages.REPORT_SELF.getMessage());
			return;
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < args.length; i++) {
			builder.append(args[i] + " ");
		}

		staffCorePlugin.getStaffManager().sendPlainSCMessage(Messages.SC_REPORT.getMessage().replace("%player%", player.getDisplayName()).replace("%reported%", reportedPlayer.getDisplayName()).replace("%server%", player.getServer().getInfo().getName()).replace("%reason%", builder));
		player.sendMessage(Messages.REPORTED.getMessage().replace("%reported%", reportedPlayer.getDisplayName()).replace("%reason%", builder));

		if (staffCorePlugin.getModuleManager().getDiscordModule()) {

			EmbedBuilder embedBuilder = new EmbedBuilder().setAuthor(ChatColor.stripColor(Messages.SC_REPORT.getMessage().replace("%player%", player.getDisplayName()).replace("%reported%", reportedPlayer.getDisplayName()).replace("%server%", player.getServer().getInfo().getName()).replace("%reason%", builder)), null, "https://crafatar.com/avatars/" + player.getUniqueId().toString() + "?overlay=1");
			embedBuilder.setColor(Color.BLUE);

			staffCorePlugin.getJda().getTextChannelById(ConfigManager.getConfig().getString("discord.report-channel-id")).sendMessageEmbeds(embedBuilder.build()).queue();
		}
	}
}