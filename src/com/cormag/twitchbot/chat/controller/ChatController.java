/**
 * 
 */
package com.cormag.twitchbot.chat.controller;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cormag.twitchbot.api.devapp.Bot;
import com.cormag.twitchbot.chat.controller.listeners.CheckListener;
import com.cormag.twitchbot.chat.controller.listeners.FireKeyListener;
import com.cormag.twitchbot.chat.controller.listeners.MyActionListener;
import com.cormag.twitchbot.chat.controller.listeners.OnWindowCloseListener;
import com.cormag.twitchbot.chat.model.IRCClient;
import com.cormag.twitchbot.chat.view.MainView;
import com.cormag.twitchbot.commons.Utils;
import com.cormag.twitchbot.settings.Settings;
import com.cormag.twitchbot.settings.SettingsProvider;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class ChatController {

	private Bot bot;
	private boolean logToFile = true;
	private LinkedList<String> messagesToSend;
	private IRCClient model;

	private boolean reactAsBot = true;
	private boolean stop = false;
	private boolean trollMatti = false;
	private boolean trollPaul = false;

	private MainView view;

	public ChatController(Bot mBot, Settings settings, SettingsProvider settingsProvider) {

		Pattern p = Pattern.compile(Utils.MESSAGE_REGEX);

		this.bot = mBot;

		this.messagesToSend = new LinkedList<>();

		this.model = new IRCClient();
		this.view = new MainView();

		FireKeyListener l = new FireKeyListener(this.model);
		OnWindowCloseListener w = new OnWindowCloseListener(this, this.model, settings, settingsProvider);
		MyActionListener a = new MyActionListener(this.model, this.bot);
		CheckListener i = new CheckListener(this);

		this.view.setWindowListener(w);

		this.view.setKeyListener(l);

		this.view.setActionListener(a);

		this.view.setItemListener(i);

		// start connection

		this.model.connect();
		this.model.logOn(settingsProvider.getUsername(), settingsProvider.getPassword());

		while (!this.stop) {

			this.model.readServerMessages();

			LinkedList<String> lines = this.model.getProtocol();
			while (!lines.isEmpty()) {

				String line = this.model.pollFirst();

				Matcher m = p.matcher(line);

				if (m.find()) {

					String name = m.group(1);
					String message = m.group(2);

					this.view.appendTextToLog(Utils.getTimestamp() + " " + "SERVER: ");
					this.view.appendTextToLog(name + ": ");
					this.view.appendTextToLog(message + System.lineSeparator());

					if (this.reactAsBot) {
						if (message.equals("!status")) {
							this.messagesToSend.add("Current status: " + this.bot.getStatus());

						} else if (message.equals("!views")) {
							this.messagesToSend.add("Altogether " + this.bot.getViewerCount() + " channel views");

						} else if (message.equals("!game")) {
							this.messagesToSend.add("Currently playing " + this.bot.getCurrentGame());

						} else if (message.equals("!delay")) {
							this.messagesToSend.add("Current delay is " + this.bot.getDelay() + "s");

						} else if (message.equals("!followers")) {
							this.messagesToSend.add("Follower count: " + this.bot.getFollowerCount());

						}
					}

					if (this.trollPaul) {
						if (name.equals("swaul")) {
							this.messagesToSend.add(message);
						}
					}

					if (this.trollMatti) {
						if (name.equals("geniusmatti")) {
							this.messagesToSend.add(message);
						}

					}

				} else {
					this.view.appendTextToLog(line);
				}

			}

			while (!this.messagesToSend.isEmpty()) {
				this.model.sendMessageToChat(this.messagesToSend.pollFirst());
			}

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.model.disconnect();
	}

	public boolean getLogToFile() {
		return this.logToFile;
	}

	public void setLogToFile(boolean log) {
		this.logToFile = log;
	}

	public void setReactAsBot(boolean mReactAsBot) {
		this.reactAsBot = mReactAsBot;
	}

	public void setStop(boolean mStop) {
		this.stop = mStop;
	}

	public void setTrollMatti(boolean mTrollMatti) {
		this.trollMatti = mTrollMatti;
	}

	public void setTrollPaul(boolean mTrollPaul) {
		this.trollPaul = mTrollPaul;

	}
}
