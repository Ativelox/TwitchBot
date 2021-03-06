/**
 * 
 */
package com.cormag.twitchbot.chat.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.cormag.twitchbot.api.devapp.Bot;
import com.cormag.twitchbot.chat.model.IRCClient;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class MyActionListener implements ActionListener {

	private Bot bot;
	private IRCClient model;
	private String selectedChannel;

	public MyActionListener(final IRCClient mModel, final Bot mBot) {
		this.bot = mBot;
		this.model = mModel;
		this.selectedChannel = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();

		if (o instanceof JButton) {
			JButton button = (JButton) o;

			if (button.getText().startsWith("Join")) {
				if (this.selectedChannel == null) {
					return;
				}

				this.bot.setCurrentChannel(this.selectedChannel);
				this.model.joinChannel(this.selectedChannel);

			} else if (button.getText().startsWith("Leave")) {
				this.model.leaveChannel();
			}

		} else if (o instanceof JComboBox) {
			@SuppressWarnings("unchecked")
			JComboBox<String> box = (JComboBox<String>) o;

			String selectedItem = (String) box.getSelectedItem();
			this.selectedChannel = selectedItem;
		}

	}

}
