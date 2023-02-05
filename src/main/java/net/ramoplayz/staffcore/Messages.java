package net.ramoplayz.staffcore;

import net.ramoplayz.staffcore.Manager.ConfigManager;
import net.ramoplayz.staffcore.Util.MessageUtil;

public enum Messages {

	SC_PREFIX(MessageUtil.translate(ConfigManager.getConfig().getString("messages.sc-prefix"))),
	AC_PREFIX(MessageUtil.translate(ConfigManager.getConfig().getString("messages.ac-prefix"))),
	VANISH(MessageUtil.translate(ConfigManager.getConfig().getString("messages.vanish"))),
	REAPPEAR(MessageUtil.translate(ConfigManager.getConfig().getString("messages.reappear"))),
	CHAT_FORMAT(MessageUtil.translate(ConfigManager.getConfig().getString("messages.chat-format"))),
	JOINED(MessageUtil.translate(ConfigManager.getConfig().getString("messages.server-join"))),
	SWITCH(MessageUtil.translate(ConfigManager.getConfig().getString("messages.server-switch"))),
	QUIT(MessageUtil.translate(ConfigManager.getConfig().getString("messages.server-quit"))),
	JOINED_SILENT(MessageUtil.translate(ConfigManager.getConfig().getString("messages.server-join-silent"))),
	SWITCH_SILENT(MessageUtil.translate(ConfigManager.getConfig().getString("messages.server-switch-silent"))),
	QUIT_SILENT(MessageUtil.translate(ConfigManager.getConfig().getString("messages.server-quit-silent"))),
	SC_TOGGLE_ON(MessageUtil.translate(ConfigManager.getConfig().getString("messages.sc-toggle-on"))),
	SC_TOGGLE_OFF(MessageUtil.translate(ConfigManager.getConfig().getString("messages.sc-toggle-off"))),
	AC_TOGGLE_ON(MessageUtil.translate(ConfigManager.getConfig().getString("messages.ac-toggle-on"))),
	AC_TOGGLE_OFF(MessageUtil.translate(ConfigManager.getConfig().getString("messages.ac-toggle-off"))),
	VANISH_TOGGLED_ON(MessageUtil.translate(ConfigManager.getConfig().getString("messages.vanish-toggle-on"))),
	VANISH_TOGGLED_OFF(MessageUtil.translate(ConfigManager.getConfig().getString("messages.vanish-toggle-off"))),
	STAFF_LIST_HEADER(MessageUtil.translate(ConfigManager.getConfig().getString("messages.staff-list-header"))),
	STAFF_LIST_PLAYER(MessageUtil.translate(ConfigManager.getConfig().getString("messages.staff-list-player"))),
	SC_REPORT(MessageUtil.translate(ConfigManager.getConfig().getString("messages.sc-report"))),
	REPORTED(MessageUtil.translate(ConfigManager.getConfig().getString("messages.reported"))),
	REPORT_SELF(MessageUtil.translate(ConfigManager.getConfig().getString("messages.report-self"))),
	ALREADY_TOGGLED(MessageUtil.translate(ConfigManager.getConfig().getString("messages.already-toggled"))),
	INVALID_COMMAND(MessageUtil.translate(ConfigManager.getConfig().getString("messages.invalid-command"))),
	NO_PERMISSION(MessageUtil.translate(ConfigManager.getConfig().getString("messages.no-permission")));

	private String message;

	Messages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}