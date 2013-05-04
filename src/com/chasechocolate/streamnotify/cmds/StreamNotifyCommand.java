package com.chasechocolate.streamnotify.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.chasechocolate.streamnotify.StreamNotify;
import com.chasechocolate.streamnotify.stream.TwitchStream;

public class StreamNotifyCommand implements CommandExecutor {
	private StreamNotify plugin;
	
	public StreamNotifyCommand(StreamNotify plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("streamnotify")){
			TwitchStream stream = plugin.stream;
			
			if(stream.isOnline()){
				sender.sendMessage(plugin.messageOnline);
			} else {
				sender.sendMessage(plugin.messageOffline);
			}
			
			return true;
		}
		return false;
	}
}