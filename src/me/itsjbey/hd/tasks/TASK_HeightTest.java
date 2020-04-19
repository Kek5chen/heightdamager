package me.itsjbey.hd.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TASK_HeightTest extends BukkitRunnable {

	double height;
	double damage;
			
	public TASK_HeightTest(double h, double d) {

		height = h;
		damage = d;
		
	}
	
	@Override
	public void run() {

		for (Player p : Bukkit.getOnlinePlayers()) {
			
			if(p.getLocation().getY() < height) {
				
				p.damage(damage);
				
			}
			
		}

	}

}
