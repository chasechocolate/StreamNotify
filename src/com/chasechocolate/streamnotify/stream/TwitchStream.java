package com.chasechocolate.streamnotify.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class TwitchStream {
	private String channel;
	
	private URL url;
	private BufferedReader reader;
	
	private boolean online = false;
	
	public TwitchStream(String channel){
		this.channel = channel;
		
		refresh();
	}
	
	public void refresh(){
		try {
			this.url = new URL("http://api.justin.tv/api/stream/list.json?jsonp=&channel=" + channel);
			this.reader = new BufferedReader(new InputStreamReader(url.openStream()));

			if(!(reader.readLine().equals("[]"))){
				online = true;
			} else {
				online = false;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public URL getUrl(){
		return this.url;
	}
	
	public boolean isOnline(){
		return this.online;
	}
}