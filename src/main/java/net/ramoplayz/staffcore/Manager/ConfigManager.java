package net.ramoplayz.staffcore.manager;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.ramoplayz.staffcore.StaffCorePlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigManager {

	private static StaffCorePlugin staffCorePlugin;
	public static Configuration config;

	public ConfigManager(StaffCorePlugin staffCorePlugin) {
		this.staffCorePlugin = staffCorePlugin;
	}

	public void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(staffCorePlugin.getDataFolder(), "config.yml"));
		} catch (IOException var2) {
			System.out.println("Error saving Config file!");
			var2.printStackTrace();
		}
	}

	public void createFiles() {

		if (!staffCorePlugin.getDataFolder().exists()) {
			staffCorePlugin.getDataFolder().mkdir();
		}

		File file = new File(staffCorePlugin.getDataFolder(), "config.yml");
		InputStream in;

		if (!file.exists()) {
			try {
				in = staffCorePlugin.getResourceAsStream("config.yml");
				Files.copy(in, file.toPath());
			} catch (IOException var5) {
				var5.printStackTrace();
			}
		}
	}

	public static void registerConfig() {
		try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(staffCorePlugin.getDataFolder(), "config.yml"));
		} catch (IOException var1) {
			var1.printStackTrace();
		}
	}

	public static Configuration getConfig() {
		return config;
	}
}