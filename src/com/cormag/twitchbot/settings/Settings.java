/**
 * 
 */
package com.cormag.twitchbot.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import java.util.Properties;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class Settings {

	private static final String FILE_COMMENT = "Chat Bot Authentification Settings";
	private static final String FILE_PATH = "config.ini";

	private final Properties properties;

	public Settings() {
		this.properties = new Properties();

	}

	@SuppressWarnings("resource")
	public void loadSettings(SettingsProvider mProvider) {
		try {
			try {
				this.properties.load(new FileInputStream(FILE_PATH));

			} catch (FileNotFoundException e) {
				this.saveSettings(mProvider);
				this.properties.load(new FileInputStream(FILE_PATH));

				String username = JOptionPane.showInputDialog("Enter your twitch username.").toLowerCase();
				String password = JOptionPane.showInputDialog("Enter your auth key.");
				String clientID = JOptionPane.showInputDialog("Enter your Client ID.");

				mProvider.setSetting("NICK", username);
				mProvider.setSetting("PASS", password);
				mProvider.setSetting("CLIENT_ID", clientID);

			}

			for (Entry<Object, Object> entry : this.properties.entrySet()) {
				mProvider.setSetting((String) entry.getKey(), (String) entry.getValue());

			}

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public void saveSettings(SettingsProvider mProvider) {
		@SuppressWarnings("resource")
		FileOutputStream target = null;

		for (Entry<String, String> entry : mProvider.getAllSettings().entrySet()) {
			this.properties.put(entry.getKey(), entry.getValue());

		}

		try {
			target = new FileOutputStream(new File(FILE_PATH));
			this.properties.store(target, FILE_COMMENT);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (target != null) {
					target.close();
				}

			} catch (IOException e) {
				e.printStackTrace();

			}

		}
	}

}
