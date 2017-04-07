/**
 * 
 */
package com.cormag.twitchbot.chat.view;

import javax.swing.JTextArea;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class ChatArea extends JTextArea{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ChatArea() {
		super(1,  1);
		this.getDocument().putProperty("filterNewlines", Boolean.TRUE);
	}

}
