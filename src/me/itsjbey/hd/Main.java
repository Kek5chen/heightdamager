package me.itsjbey.hd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import me.itsjbey.hd.commands.CMD_HD;
import me.itsjbey.hd.tasks.TASK_HeightTest;

public class Main extends JavaPlugin {

	BukkitTask heighttest;

	File f = new File("plugins/HeightDamager/config.yml");
	File folders = new File("plugins/HeightDamager");
	YamlConfiguration yml;
	
	boolean standard = false;
	
	double height = 4d;
	double damage = 1d;
	long timer = 1l;
	ArrayList<World> worlds = new ArrayList<>();
	
	@Override
	public void onEnable() {
		
		Bukkit.getPluginCommand("hd").setExecutor(new CMD_HD(this));
		
		reload();
		
	}
	
	@Override
	public void onDisable() {
		
		if(heighttest != null) {

			heighttest.cancel();
			
		}
		
	}

	public void reload() {
		
		if(heighttest != null) {

			heighttest.cancel();
			
		}

		folders.mkdirs();
		
		if(!f.exists()) {
			
			try {
				
				f.createNewFile();
				
				yml = YamlConfiguration.loadConfiguration(f);

				yml.set("height", 4d);
				yml.set("damage", 1d);
				yml.set("timer", 1l);
				yml.set("worlds." + Bukkit.getWorlds().get(0).getName(), "true");
				
				int i = 0;
				
				for (World world : Bukkit.getWorlds()) {
					
					if(i == 0) {

						i = 1;
						
						continue;
					}
					
					yml.set("worlds." + world.getName(), "false");
					
				}
				
				yml.save(f);
				
			} catch (IOException e) {

				Bukkit.getConsoleSender().sendMessage("§cCouldn't initialize File... Using standard config.");
				
				standard = true;
				
			}
			
		} else {
			
			yml = YamlConfiguration.loadConfiguration(f);
			
		}
		
		if(!standard) {

			height = yml.getDouble("height");
			damage = yml.getDouble("damage");
			timer = yml.getLong("timer");
			
			System.out.println(yml.getConfigurationSection("worlds").getKeys(false).toString());
			
			worlds.clear();
			
			for (String s : yml.getConfigurationSection("worlds").getKeys(true)) {
				
				System.out.println(s);
				
				if(yml.getBoolean("worlds." + s)) {
					
					worlds.add(Bukkit.getWorld(s));
					
				}
				
			}
			
		} else {
			
			height = 4d;
			damage = 1d;
			timer = 1l;
			
		}
		
		heighttest = new TASK_HeightTest(height, damage, worlds).runTaskTimer(this, 0, timer);
		
	}
	
}
