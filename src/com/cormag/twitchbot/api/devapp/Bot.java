/**
 * 
 */
package com.cormag.twitchbot.api.devapp;

import com.cormag.twitchbot.settings.SettingsProvider;
import com.mb3364.twitch.api.Twitch;
import com.mb3364.twitch.api.handlers.ChannelResponseHandler;
import com.mb3364.twitch.api.models.Channel;
import com.mb3364.twitch.api.resources.ChannelsResource;

/**
 * @author Julian <juliantischner27@web.de>
 *
 */
public class Bot {

	Channel channel;
	private ChannelsResource channels;
	private Twitch twitch;

	public Bot(SettingsProvider settingsProvider) {
		this.twitch = new Twitch();
		this.twitch.setClientId(settingsProvider.getClientID());
		this.channels = this.twitch.channels();
		this.channel = null;
	}

	public String getCurrentGame() {
		return this.channel.getGame();

	}

	public int getDelay() {
		return this.channel.getDelay();
	}

	public int getFollowerCount() {
		return this.channel.getFollowers();

	}

	public String getStatus() {
		return this.channel.getStatus();
	}

	public long getViewerCount() {
		return this.channel.getViews();
	}

	public void setCurrentChannel(String channelName) {
		this.channels.get(channelName, new ChannelResponseHandler() {

			@Override
			public void onFailure(int arg0, String arg1, String arg2) {
				System.err.println(arg0 + arg1 + arg2);

			}

			@Override
			public void onFailure(Throwable arg0) {
				System.err.println(arg0);

			}

			@Override
			public void onSuccess(Channel arg0) {
				Bot.this.channel = arg0;

			}
		});
	}

}
