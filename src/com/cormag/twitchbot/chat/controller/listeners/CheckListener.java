/**
 * 
 */
package com.cormag.twitchbot.chat.controller.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import com.cormag.twitchbot.chat.controller.ChatController;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class CheckListener implements ItemListener {

	ChatController controller;

	/**
	 * 
	 */
	public CheckListener(ChatController mController) {
		this.controller = mController;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getItemSelectable() instanceof JCheckBox) {
			JCheckBox box = (JCheckBox) arg0.getItemSelectable();

			if (arg0.getStateChange() == ItemEvent.SELECTED) {
				if (box.getText().contains("React")) {
					this.controller.setReactAsBot(true);

				} else if (box.getText().contains("GeniusMatti")) {
					this.controller.setTrollMatti(true);

				} else if (box.getText().contains("Swaul")) {
					this.controller.setTrollPaul(true);

				} else if (box.getText().contains("Log")) {
					this.controller.setLogToFile(true);

				}
			} else if (arg0.getStateChange() == ItemEvent.DESELECTED) {
				if (box.getText().contains("React")) {
					this.controller.setReactAsBot(false);

				} else if (box.getText().contains("GeniusMatti")) {
					this.controller.setTrollMatti(false);

				} else if (box.getText().contains("Swaul")) {
					this.controller.setTrollPaul(false);

				} else if (box.getText().contains("Log")) {
					this.controller.setLogToFile(false);

				}
			}

		}

	}

}
