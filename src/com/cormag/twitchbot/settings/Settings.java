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
		properties = new Properties();

	}

	public void loadSettings(SettingsProvider mProvider) {
		try {
			try {
				properties.load(new FileInputStream(FILE_PATH));

			} catch (FileNotFoundException e) {
				this.saveSettings(mProvider);
				properties.load(new FileInputStream(FILE_PATH));

			}

			for (Entry<Object, Object> entry : properties.entrySet()) {
				mProvider.setSetting((String) entry.getKey(), (String) entry.getValue());

			}

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public void saveSettings(SettingsProvider mProvider) {
		FileOutputStream target = null;

		for (Entry<String, String> entry : mProvider.getAllSettings().entrySet()) {
			properties.put(entry.getKey(), entry.getValue());

		}

		try {
			target = new FileOutputStream(new File(FILE_PATH));
			properties.store(target, FILE_COMMENT);

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
