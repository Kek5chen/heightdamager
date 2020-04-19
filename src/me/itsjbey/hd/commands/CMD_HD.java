package me.itsjbey.hd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.itsjbey.hd.Main;

public class CMD_HD implements CommandExecutor {

	Main main;
	
	public CMD_HD(Main m) {

		main = m;
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender.hasPermission("hd.reload")) {
			
			if(args.length == 1) {
				
				if(args[0].equals("reload")) {
					
					sender.sendMessage("§aReloading config...");
					
					main.reload();
					
					sender.sendMessage("§aReloaded config");
					
				}
				
			} else {
				
				sender.sendMessage("§cUsage: /hd reload");
				
			}
			
		} else {
			
			sender.sendMessage("§4No Permission.");
			
		}
		
		return true;
		
	}

}
