package com.chasechocolate.streamnotify;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.chasechocolate.streamnotify.cmds.StreamNotifyCommand;
import com.chasechocolate.streamnotify.stream.TwitchStream;

public class StreamNotify extends JavaPlugin {

	public ArrayList<TwitchStream> streams;

	public void log(String msg) {
		this.getLogger().info(msg);
	}

	@Override
	public void onEnable() {
		this.saveDefaultConfig();

		log("Loading channels...");

		this.streams = new ArrayList<TwitchStream>();
		for (String channel : this.getConfig().getStringList("channels"))
			streams.add(new TwitchStream(channel));

		log("Starting broadcast timer...");
		startBroadcastTimer();

		this.getCommand("streamnotify").setExecutor(
				new StreamNotifyCommand(this));

		log("Enabled!");
	}

	@Override
	public void onDisable() {
		log("Disabled!");
	}

	public void startBroadcastTimer() {
		int delay = getConfig().getInt("broadcast.delay");
		new BukkitRunnable() {
			@Override
			public void run() {
				ArrayList<String> online = new ArrayList<String>();
				for (TwitchStream stream : streams) {
					stream.refresh();
					if (stream.isOnline())
						online.add(stream.getDisplayUrl());
				}
				if (!online.isEmpty()) {
					String msg = getConfig().getString(
							"broadcast.message-start");
					msg = ChatColor.translateAlternateColorCodes('&', msg);
					String color = getConfig().getString("broadcast.urlcolor");
					color = ChatColor.translateAlternateColorCodes('&', color);
					Bukkit.broadcastMessage(msg);
					for (String url : online)
						Bukkit.broadcastMessage(color + " - " + url);
				}
			}
		}.runTaskTimer(this, delay * 20, delay * 20);
	}
}