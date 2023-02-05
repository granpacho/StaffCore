package net.ramoplayz.staffcore;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.ramoplayz.staffcore.command.*;
import net.ramoplayz.staffcore.listener.*;
import net.ramoplayz.staffcore.manager.ConfigManager;
import net.ramoplayz.staffcore.manager.ModuleManager;
import net.ramoplayz.staffcore.manager.StaffManager;
import net.ramoplayz.staffcore.util.EmbedUtil;

public final class StaffCorePlugin extends Plugin {

	private JDA jda;

	private StaffManager staffManager;
	private ModuleManager moduleManager;

	private EmbedUtil embedUtil;

	@Override
	public void onEnable() {
		setConfigManager(new ConfigManager(this));

		embedUtil = new EmbedUtil(this);

		staffManager = new StaffManager(this);
		moduleManager = new ModuleManager();

		initDiscord();

		ProxyServer.getInstance().getPluginManager().registerListener(this, new ChatListener(this));
		ProxyServer.getInstance().getPluginManager().registerListener(this, new ConnectionListener(this));
		ProxyServer.getInstance().getPluginManager().registerListener(this, new ServerPingListener(this));
		ProxyServer.getInstance().getPluginManager().registerListener(this, new VanishListener(this));

		ProxyServer.getInstance().getPluginManager().registerCommand(this, new AdminChatCommand(this));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new AdminChatToggleCommand(this));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new ReportCommand(this));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new StaffChatCommand(this));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new StaffChatToggleCommand(this));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new StaffListCommand(this));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new VanishToggleCommand(this));
	}

	private void initDiscord() {
		if (moduleManager.getDiscordModule() && !(ConfigManager.getConfig().getString("discord.token") == null) && !(ConfigManager.getConfig().getString("discord.sc-channel-id") == null) && !(ConfigManager.getConfig().getString("discord.ac-channel-id") == null)) {
			JDABuilder builder = JDABuilder.createLight(ConfigManager.getConfig().getString("discord.token"), GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);
			builder.setActivity(Activity.of(Activity.ActivityType.valueOf(ConfigManager.getConfig().getString("discord.activity.type")), ConfigManager.getConfig().getString("discord.activity.text")));
			builder.addEventListeners(new DiscordListener(this));
			jda = builder.build();
		}
	}
	
	@Override
	public void onDisable() {

		for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
			staffManager.getSCToggled().remove(player);
			staffManager.getACToggled().remove(player);
			staffManager.getVanishToggled().remove(player);
		}

		if (jda != null) jda.shutdownNow();
	}

	private void setConfigManager(ConfigManager configManager) {
		configManager.createFiles();
		ConfigManager.registerConfig();
		configManager.saveConfig();
	}

	public StaffManager getStaffManager() {
		return staffManager;
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	public JDA getJda() {
		return jda;
	}
}