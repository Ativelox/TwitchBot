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
	
	private Channel channel;
	private ChannelsResource channels;
	private Twitch twitch;
	
	public Bot(SettingsProvider settingsProvider) {
		twitch = new Twitch();
		twitch.setClientId(settingsProvider.getClientID());
		channels = twitch.channels();
		channel = null;
	}
	
	public String getCurrentGame(){
		return channel.getGame();
		
	}
	
	public int getDelay(){
		return channel.getDelay();
	}
	
	public int getFollowerCount(){
		return channel.getFollowers();
		
	}
	
	public String getStatus(){
		return channel.getStatus();
	}
	
	public long getViewerCount(){
		return channel.getViews();
	}
	
	public void setCurrentChannel(String channelName){
		channels.get(channelName, new ChannelResponseHandler() {
			
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
				channel = arg0;
				
			}
		});
	}

}
