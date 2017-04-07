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
	private LinkedList<String> messagesToSend;
	private IRCClient model;
	private boolean reactAsBot = true;

	private boolean stop = false;
	private boolean trollMatti = false;
	private boolean trollPaul = false;
	private boolean logToFile = true;

	private MainView view;

	public ChatController(Bot mBot, Settings settings, SettingsProvider settingsProvider) {

		Pattern p = Pattern.compile(Utils.MESSAGE_REGEX);

		bot = mBot;

		messagesToSend = new LinkedList<String>();

		model = new IRCClient();
		view = new MainView();

		FireKeyListener l = new FireKeyListener(model);
		OnWindowCloseListener w = new OnWindowCloseListener(this, model, settings, settingsProvider);
		MyActionListener a = new MyActionListener(model, bot);
		CheckListener i = new CheckListener(this);

		view.setWindowListener(w);

		view.setKeyListener(l);

		view.setActionListener(a);

		view.setItemListener(i);

		// start connection

		model.connect();
		model.logOn(settingsProvider.getUsername(), settingsProvider.getPassword());
		model.joinChannel("skumbagcormag");

		bot.setCurrentChannel("skumbagcormag");

		messagesToSend.add("PogChamp");
		messagesToSend.add("PogChamp PogChamp");
		messagesToSend.add("PogChamp PogChamp PogChamp");
		messagesToSend.add("PogChamp PogChamp");
		messagesToSend.add("PogChamp");

		while (!stop) {

			model.readServerMessages();

			LinkedList<String> lines = model.getProtocol();
			while (!lines.isEmpty()) {

				String line = model.pollFirst();

				Matcher m = p.matcher(line);

				if (m.find()) {

					String name = m.group(1);
					String message = m.group(2);

					view.appendTextToLog(Utils.getTimestamp() + " " + "SERVER: ");
					view.appendTextToLog(name + ": ");
					view.appendTextToLog(message + System.lineSeparator());

					if (reactAsBot) {
						if (message.equals("!status")) {
							messagesToSend.add("Current status: " + bot.getStatus());

						} else if (message.equals("!views")) {
							messagesToSend.add("Altogether " + bot.getViewerCount() + " channel views");

						} else if (message.equals("!game")) {
							messagesToSend.add("Currently playing " + bot.getCurrentGame());

						} else if (message.equals("!delay")) {
							messagesToSend.add("Current delay is " + bot.getDelay() + "s");

						} else if (message.equals("!followers")) {
							messagesToSend.add("Follower count: " + bot.getFollowerCount());

						}
					}

					if (trollPaul) {
						if (name.equals("swaul")) {
							messagesToSend.add(message);
						}
					}

					if (trollMatti) {
						if (name.equals("geniusmatti")) {
							messagesToSend.add(message);
						}

					}

				} else {
					view.appendTextToLog(line);
				}

			}

			while (!messagesToSend.isEmpty()) {
				model.sendMessageToChat(messagesToSend.pollFirst());
			}

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		model.disconnect();
	}

	public void setReactAsBot(boolean reactAsBot) {
		this.reactAsBot = reactAsBot;
	}

	public void setStop(boolean mStop) {
		this.stop = mStop;
	}

	public void setTrollMatti(boolean trollMatti) {
		this.trollMatti = trollMatti;
	}

	public void setTrollPaul(boolean trollPaul) {
		this.trollPaul = trollPaul;

	}

	public boolean getLogToFile() {
		return this.logToFile;
	}

	public void setLogToFile(boolean log) {
		this.logToFile = log;
	}
}
