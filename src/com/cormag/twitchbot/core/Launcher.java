/**
 * 
 */
package com.cormag.twitchbot.core;

import com.cormag.twitchbot.api.devapp.Bot;
import com.cormag.twitchbot.chat.controller.ChatController;
import com.cormag.twitchbot.settings.Settings;
import com.cormag.twitchbot.settings.SettingsProvider;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class Launcher {

	public static void main(final String[] args) {

		Settings settings = new Settings();
		SettingsProvider settingsProvider = new SettingsProvider();

		settings.loadSettings(settingsProvider);

		new ChatController(new Bot(settingsProvider), settings, settingsProvider);

	}

	private Launcher() {

	}

}
