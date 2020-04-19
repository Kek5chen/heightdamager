package me.itsjbey.hd.tasks;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TASK_HeightTest extends BukkitRunnable {

	double height;
	double damage;
	
	ArrayList<World> worlds = new ArrayList<>();
			
	public TASK_HeightTest(double h, double d, ArrayList<World> w) {

		height = h;
		damage = d;
		
		worlds = w;
		
	}
	
	@Override
	public void run() {

		for (Player p : Bukkit.getOnlinePlayers()) {
			
			if(worlds.size() == 0) {

				if(p.getLocation().getY() < height) {
					
					p.damage(damage);
					
				}
				
			} else {
				
				for (World world : worlds) {
					
					if(p.getLocation().getWorld().equals(world) && p.getLocation().getY() < height) {
						
						p.damage(damage);
						
					}
				
				}
				
			}
			
		}

	}

}
