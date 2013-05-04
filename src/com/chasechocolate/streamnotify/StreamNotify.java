package com.chasechocolate.streamnotify;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.chasechocolate.streamnotify.cmds.StreamNotifyCommand;
import com.chasechocolate.streamnotify.stream.TwitchStream;

public class StreamNotify extends JavaPlugin {
	public File configFile = new File(this.getDataFolder(), "config.yml");
	
	public TwitchStream stream;
	
	public String channel;
	public String messageOnline;
	public String messageOffline;
	
	public boolean broadcastWhenOffline;
	
	public int broadcastDelay;
	
	public void log(String msg){
		this.getLogger().info(msg);
	}
	
	@Override
	public void onEnable(){
		if(!(configFile.exists())){
			log("Found no config.yml! Creating one for you...");
			this.saveDefaultConfig();
			log("Successfully created config.yml!");
		}
		
		log("Loading configuration options...");
		loadConfig();
		
		this.stream = new TwitchStream(channel);
		
		log("Starting broadcast delay every " + broadcastDelay + " seconds...");
		startBroadcastTimer();
		
		this.getCommand("streamnotify").setExecutor(new StreamNotifyCommand(this));
		
		log("Enabled!");
	}
	
	@Override
	public void onDisable(){
		log("Disabled!");
	}
	
	public void loadConfig(){
		this.channel = this.getConfig().getString("channel");
		this.broadcastDelay = this.getConfig().getInt("broadcast.delay");
		this.broadcastWhenOffline = this.getConfig().getBoolean("broadcast.broadcast-when-offline");
		this.messageOnline = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("broadcast.message-online"));
		this.messageOffline = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("broadcast.message-offline"));
	}
	
	public void startBroadcastTimer(){
		new BukkitRunnable(){
			@Override
			public void run(){
				if(stream.isOnline()){
					Bukkit.broadcastMessage(messageOnline);
				} else {
					if(broadcastWhenOffline){
						Bukkit.broadcastMessage(messageOffline);
					}
				}
			}
		}.runTaskTimer(this, broadcastDelay * 20, broadcastDelay * 20);
	}
}