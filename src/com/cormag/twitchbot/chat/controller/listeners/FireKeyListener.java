/**
 * 
 */
package com.cormag.twitchbot.chat.controller.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.cormag.twitchbot.chat.model.IRCClient;
import com.cormag.twitchbot.chat.view.ChatArea;
import com.cormag.twitchbot.chat.view.RawMessageArea;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class FireKeyListener implements KeyListener{

	private IRCClient model;
	
	public FireKeyListener(IRCClient mModel) {
		model = mModel;
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getSource() instanceof ChatArea){ //The textbox used to send messages directly into the chat.
			if(KeyEvent.VK_ENTER == arg0.getKeyCode()){
				ChatArea chatArea = (ChatArea) arg0.getSource();
				model.sendMessageToChat(chatArea.getText());
				
				chatArea.setText(null);
			}
	
		}else if(arg0.getSource() instanceof RawMessageArea){
			if(KeyEvent.VK_ENTER == arg0.getKeyCode()){
				RawMessageArea rawMessageArea = (RawMessageArea) arg0.getSource();
				model.sendRawMessageToServer(rawMessageArea.getText());
				rawMessageArea.setText(null);
			}
			
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
