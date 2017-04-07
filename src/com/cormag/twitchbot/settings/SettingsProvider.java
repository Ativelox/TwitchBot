/**
 * 
 */
package com.cormag.twitchbot.settings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class SettingsProvider {

	private static final String KEY_IDENTIFIER_CLIENT_ID = "CLIENT_ID";
	private static final String KEY_IDENTIFIER_PASSWORD = "PASS";
	private static final String KEY_IDENTIFIER_USERNAME = "NICK";

	private final HashMap<String, String> settingsStore;

	public SettingsProvider() {
		settingsStore = new HashMap<>();
	}

	public Map<String, String> getAllSettings() {
		return Collections.unmodifiableMap(this.settingsStore);

	}

	public String getClientID() {
		return this.getSetting(KEY_IDENTIFIER_CLIENT_ID);

	}

	public String getPassword() {
		return this.getSetting(KEY_IDENTIFIER_PASSWORD);
	}

	public String getSetting(final String key) {
		return settingsStore.get(key);
	}

	public String getUsername() {
		return this.getSetting(KEY_IDENTIFIER_USERNAME);
	}

	public void setSetting(final String key, final String value) {
		settingsStore.put(key, value);
	}

}
