/**
 * 
 */
package com.cormag.twitchbot.chat.controller.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.cormag.twitchbot.chat.controller.ChatController;
import com.cormag.twitchbot.chat.model.IRCClient;
import com.cormag.twitchbot.chat.view.MainView;
import com.cormag.twitchbot.settings.Settings;
import com.cormag.twitchbot.settings.SettingsProvider;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class OnWindowCloseListener implements WindowListener {

	private ChatController controller;
	private IRCClient model;
	private SettingsProvider provider;
	private Settings settings;

	public OnWindowCloseListener(ChatController mController, IRCClient mModel, Settings mSettings,
			SettingsProvider mProvider) {
		this.controller = mController;
		this.settings = mSettings;
		this.provider = mProvider;
		this.model = mModel;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		if (e.getWindow() instanceof MainView) {
			this.model.logToFile(this.controller.getLogToFile());
			this.settings.saveSettings(this.provider);
			this.controller.setStop(true);

			e.getWindow().dispose();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.
	 * WindowEvent)
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.
	 * WindowEvent)
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		//
	}

}
