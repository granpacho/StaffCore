package net.ramoplayz.staffcore.manager;

public class ModuleManager {

	private boolean discordModule = ConfigManager.getConfig().getBoolean("modules.discord");
	private boolean serverListModule = ConfigManager.getConfig().getBoolean("modules.server-list");
	private boolean premiumVanishModule = ConfigManager.getConfig().getBoolean("modules.premium-vanish");

	private boolean staffListModule = ConfigManager.getConfig().getBoolean("modules.staff-list-command");
	private boolean reportModule = ConfigManager.getConfig().getBoolean("modules.report-command");

	public boolean getDiscordModule() {
		return discordModule;
	}

	public boolean getServerListModule() {
		return serverListModule;
	}

	public boolean getPremiumVanishModule() {
		return premiumVanishModule;
	}

	public boolean getStaffListModule() {
		return staffListModule;
	}

	public boolean getReportModule() {
		return reportModule;
	}


}